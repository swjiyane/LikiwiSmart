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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText passwordsignin;
    private Button SignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.username);
        passwordsignin = (EditText) findViewById(R.id.passwordsignin);
        SignIn = (Button) findViewById(R.id.SignIn);
        SignIn.setOnClickListener(this);
    }

    private void userLogin(){
        String email = username.getText().toString().trim();
        String password = passwordsignin.getText().toString().trim();

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

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start profile activity
                            progressDialog.dismiss();
                            finish();








                            // TO DO
                            //  enter if statement to intent to host role








                            startActivity(new Intent(getApplicationContext(),ProfileUser.class));
                            Toast.makeText(Login.this, "Signed In", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "Could not sign in. Please try again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == SignIn){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging in...");
            progressDialog.show();
            //will login in activity here
            userLogin();
        }
    }
}
