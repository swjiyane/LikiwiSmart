package com.example.codetribe.likiwismart;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;


import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.name;
import static android.R.attr.data;


public class ProfileUser extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private final int REQUEST_CODE_PLACEPICKER=1;
    GoogleMap mGoogleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        getSupportActionBar().setTitle("Profile User");

        firebaseAuth = FirebaseAuth.getInstance();
        startPlacePickerActivity();

        Button button = (Button)findViewById(R.id.viewmap);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startPlacePickerActivity();

            }

        });

    }

    private void startPlacePickerActivity() {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

            try {
                Intent intent = intentBuilder.build(this);
                startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onActivityResult (int requestCode , int resultCode, Intent data){
            if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK){
                displaySelectedPlaceFromPlacePicker(data);
            }
        }

        protected void displaySelectedPlaceFromPlacePicker(Intent data){
            Place placeSelected = PlacePicker.getPlace(this,data);

            String name = placeSelected.getName().toString();
            String address = placeSelected.getAddress().toString();

            TextView selectedLocation = (TextView)findViewById(R.id.text);
            selectedLocation.setText(name+","+address);

        }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private void ProfileUpdate(int i) {}

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
                Intent intent = new Intent(ProfileUser.this,ViewProfileUser.class);
                startActivity(intent);
                return true;

            case R.id.updateProfile:
                ProfileUpdate(1);
                Intent intent1 = new Intent(ProfileUser.this, RegisterUser.class);
                startActivity(intent1);
                return true;

            case R.id.signOut:
                ProfileUpdate(2);
                firebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(ProfileUser.this, MainActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


