package com.example.demolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

public class OtpVerification extends AppCompatActivity {
    public final String TAG = "OTP VERIFICATION";

    MaterialEditText otp_code;
    MaterialButton verify_button;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView ( R.layout.otp_activity );
        super.onCreate ( savedInstanceState );

        Intent intent = getIntent();
        final String first_name = intent.getStringExtra("first_name");
        final String last_name = intent.getStringExtra("last_name");
        final String username = intent.getStringExtra("username");
        final String mobile = intent.getStringExtra("mobile");
        final String password = intent.getStringExtra("password");
        final String otp = intent.getStringExtra("otp");

        verify_button = (MaterialButton)findViewById ( R.id.verify_button );

        verify_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                otp_code = (MaterialEditText)findViewById(R.id.otp);

                if(otp.equals(otp_code.getText().toString())){
                    //Toast.makeText ( getApplicationContext (), "Registration Successful.", Toast.LENGTH_LONG ).show ();
                    Intent intent = new Intent(getApplicationContext(), SocialData.class);
                    intent.putExtra ( "first_name", first_name);
                    intent.putExtra ( "last_name", last_name);
                    intent.putExtra ( "username", username);
                    intent.putExtra ( "mobile", mobile);
                    intent.putExtra ( "password", password);
                    startActivity(intent);
                    //registerUser(first_name, last_name, username, mobile, password);
                }else{
                    Toast.makeText ( getApplicationContext (), "Wrong OTP.", Toast.LENGTH_LONG ).show ();
                }
            }
        } );


    }


}
