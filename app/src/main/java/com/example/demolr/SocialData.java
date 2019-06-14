package com.example.demolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SocialData extends AppCompatActivity {

    public final String TAG = "Social Data Activity";
    MaterialEditText sc_username,fb_username,ig_username,li_username;
    MaterialButton submit_button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.social_data_activity);
        super.onCreate(savedInstanceState);

        submit_button = (MaterialButton)findViewById ( R.id.submit_button );
        submit_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                sc_username = (MaterialEditText)findViewById ( R.id.snapchat );
                fb_username = (MaterialEditText)findViewById ( R.id.facebook );
                ig_username = (MaterialEditText)findViewById ( R.id.instagram );
                li_username = (MaterialEditText)findViewById ( R.id.linkedin );

                Intent intent = getIntent();

                final String first_name = intent.getStringExtra("first_name");
                final String last_name = intent.getStringExtra("last_name");
                final String username = intent.getStringExtra("username");
                final String mobile = intent.getStringExtra("mobile");
                final String password = intent.getStringExtra("password");
                final String sc =  (sc_username.getText().toString());
                final String fb =  (fb_username.getText().toString());
                final String ig =  (ig_username.getText().toString());
                final String li =  (li_username.getText().toString());

                registerUser(first_name, last_name, username, mobile, password, sc, fb, ig, li);
            }
        } );

    }

    private void registerUser(final String first_name, final String last_name, final String username, final String mobile,final String password, final String snapchat_username, final String facebook_username, final String instagram_username, final String linkedin_username) {
        Log.d (TAG, "registerUser called");
    }
}
