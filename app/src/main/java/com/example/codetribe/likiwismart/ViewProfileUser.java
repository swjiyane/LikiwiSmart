package com.example.codetribe.likiwismart;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileUser extends AppCompatActivity {
    TextView name;
    TextView next1;
    TextView next2;
    TextView cell1;
    TextView cell2;
    private DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static String TAG = ViewProfileUser.class.getSimpleName();

    String user_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_user);
        getSupportActionBar().setTitle("Profile View");
        name = (TextView) findViewById(R.id.viewName);
        next1 = (TextView) findViewById(R.id.viewKin1);
        next2 = (TextView) findViewById(R.id.viewKin2);
        cell1 = (TextView) findViewById(R.id.viewCel1);
        cell2 = (TextView) findViewById(R.id.viewCel2);

        firebaseAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    user_id = user.getUid();
                    //ref = FirebaseDatabase.getInstance().getReference().child("User");
                    ref = FirebaseDatabase.getInstance().getReference().child("User").child(user_id);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.i("Ygritte", dataSnapshot.toString());

                            User user = dataSnapshot.getValue(User.class);
                            //Toast.makeText(ViewProfileUser.this, user.getNexOfKin1(), Toast.LENGTH_SHORT).show();

                            //if(user.getUserName().equalsIgnoreCase("sbu")){
                                Toast.makeText(getApplicationContext(),user.getUserName(),Toast.LENGTH_SHORT).show();
                            name.setText(user.getUserName());
                            next1.setText(user.getNexOfKin1());
                            next2.setText(user.getNexOfKin2());
                            cell1.setText(user.getCell1());
                            cell2.setText(user.getCell2());

                            //}
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
