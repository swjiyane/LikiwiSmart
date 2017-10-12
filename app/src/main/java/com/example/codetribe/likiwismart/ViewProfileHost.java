package com.example.codetribe.likiwismart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewProfileHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_host);

        getSupportActionBar().setTitle("Profile View");
    }
}
