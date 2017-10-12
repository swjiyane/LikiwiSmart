package com.example.codetribe.likiwismart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private EditText UserEmail;
    private EditText UserPassword;
    private Button UserCreateAccount;
    private  EditText userName,nexOfKin1,nexOfKin2,cell1,cell2;

    //image
    private ImageButton mPhotoPickerButton;
    private ImageView mImageViewer;
    private static final int RC_PHOTO_PICKER = 2;
    private static final int CAMERA_REQUEST_CODE = 1 ;
    private StorageReference mStorageRef;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;



    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("User Registration");
        setContentView(R.layout.activity_register_user);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        userName = (EditText) findViewById(R.id.userFullName);
        nexOfKin1 = (EditText) findViewById(R.id.nextofkin1);
        nexOfKin2 = (EditText) findViewById(R.id.nextofkin2);
        cell1 = (EditText) findViewById(R.id.cell1);
        cell2 = (EditText) findViewById(R.id.cell2);

        mImageViewer = (ImageView)findViewById(R.id.imageView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.imageButton);


        UserEmail = (EditText) findViewById(R.id.UserEmail);
        UserPassword = (EditText) findViewById(R.id.UserPassword);
        UserCreateAccount = (Button) findViewById(R.id.UserCreateAccount);
        UserCreateAccount.setOnClickListener(this);

        //ImagePickerButton shows an image picker to upload an image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Complete action using"), RC_PHOTO_PICKER);
            }
        });



    }

    private void registerUser() {
        String email = UserEmail.getText().toString().trim();
        String password = UserPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if validations are ok
        //we will first show the request


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user successfully registered
                            progressDialog.dismiss();
                            finish();
                            //
                            String username = userName.getText().toString();
                            String nexofkin1 = nexOfKin1.getText().toString();
                            String nexofkin2 = nexOfKin2.getText().toString();
                            String cel1 = cell1.getText().toString();
                            String cel2 = cell2.getText().toString();
                            String useremail = UserEmail.getText().toString();
                            String userpassword = UserPassword.getText().toString();

                            String id = task.getResult().getUser().getUid();

                            // Save Id to sharedprefence;

                            startActivity(new Intent(getApplicationContext(),ProfileUser.class));
                            Toast.makeText(RegisterUser.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            mDatabaseReference = mFirebaseDatabase.getReference().child("User");

                            User user = new User(username,nexofkin1,nexofkin2,cel1,cel2,useremail,userpassword);

                            mDatabaseReference.child(id).setValue(user);
                        } else {
                            Toast.makeText(RegisterUser.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterUser.this, "Could not register. Please try again"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

       // mImageViewer.setVisibility();
    }

    @Override
    public void onClick(View view) {

        if(view == UserCreateAccount){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registering User...");
            progressDialog.show();
            registerUser();
            //progressDialog.dismiss();

        }

    }


}
