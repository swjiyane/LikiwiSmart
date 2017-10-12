package com.example.codetribe.likiwismart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterHost extends AppCompatActivity implements View.OnClickListener {

    private EditText HostEmail;
    private EditText HostPassword;
    private Button HostCreateAccount;
    private EditText hostName,nameOfEvent;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Host Registration");
        setContentView(R.layout.activity_register_host);

        firebaseAuth = FirebaseAuth.getInstance();

        hostName = (EditText) findViewById(R.id.Namehost);
        nameOfEvent = (EditText) findViewById(R.id.event);

        HostEmail = (EditText) findViewById(R.id.HostEmail);
        HostPassword = (EditText) findViewById(R.id.HostPassword);
        HostCreateAccount = (Button) findViewById(R.id.HostCreateAccount);
        HostCreateAccount.setOnClickListener(this);
    }

    private void registerHost() {
        String email = HostEmail.getText().toString().trim();
        String password = HostPassword.getText().toString().trim();

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

                            String hostname = hostName.getText().toString();
                            String nameofevent = nameOfEvent.getText().toString();
                            String password = HostPassword.getText().toString();
                            String hostemail = HostEmail.getText().toString();

                            String id = task.getResult().getUser().getUid();
                            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Host");

                            Host host = new Host(hostname,nameofevent,password,hostemail);
                            mDatabaseReference.child(id).setValue(host);mDatabaseReference.child(id).setValue(host);


                            startActivity(new Intent(getApplicationContext(), ProfileHost.class));
                            Toast.makeText(RegisterHost.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterHost.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

        @Override
        public void onClick (View view){

            if(view == HostCreateAccount){
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Registering Host...");
                progressDialog.show();
                registerHost();
                //progressDialog.dismiss();

            }

        }
    }
