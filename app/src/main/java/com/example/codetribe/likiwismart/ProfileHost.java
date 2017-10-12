package com.example.codetribe.likiwismart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ProfileHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_host);
    }


    private void ProfileUpdate(int i) {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int assign;
        switch (item.getItemId()) {

            case R.id.viewProfile:
                ProfileUpdate(0);
                Intent intent = new Intent(ProfileHost.this, ViewProfileHost.class);
                startActivity(intent);
                return true;

            case R.id.updateProfile:
                ProfileUpdate(1);
                Intent intent1 = new Intent(ProfileHost.this, RegisterHost.class);
                startActivity(intent1);
                return true;

            case R.id.signOut:
                ProfileUpdate(2);
                Intent intent2 = new Intent(ProfileHost.this, MainActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}