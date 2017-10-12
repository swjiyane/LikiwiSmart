package com.example.codetribe.likiwismart;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialogue_spinner, null);
                mBuilder.setTitle("Account Registration");

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item
                        , getResources().getStringArray(R.array.accountType));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);


                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("Choose account type")) {
                            //Toast.makeText(MainActivity.this,
                            // mSpinner.getSelectedItem().toString(),
                            //Toast.LENGTH_SHORT).show();

                            dialogInterface.dismiss();
                        }
                        final String text= mSpinner.getSelectedItem().toString();

                        switch(text) {
                            case "BusinessAccount (Event Organiser, Tavern Owner, Club Owner...)":
                                Intent intent = new Intent(MainActivity.this, RegisterHost.class);
                                startActivity(intent);
                                break;
                            case "PersonalAccount (User)":
                                Intent i = new Intent(MainActivity.this, RegisterUser.class);
                                startActivity(i);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "To continue select an Item ",
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }


                });


                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

    }

    public void login(View v){
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);
    }


   // public void createaccount (View v){
      //  Intent intent = new Intent(MainActivity.this,ProfileUser.class);
      //  startActivity(intent);
    //}

    public void twitter (View v){
        Intent twitterintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/LikwiSmart"));
        startActivity(twitterintent);
    }
    public void facebook (View v){
        Intent facebookintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SmartPartier"));
        startActivity(facebookintent);
    }
    public void website (View v){
        Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://likwi-smart.business.site"));
        startActivity(webintent);
    }


}
