package com.example.demolr;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    MaterialEditText username,password,first_name,last_name,mobile;
    MaterialButton btn_register;
    public final String TAG = "REGISTER ACTIVITY";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.register_activity);
        super.onCreate(savedInstanceState);

        btn_register = (MaterialButton)findViewById(R.id.register_button);

        first_name = (MaterialEditText)findViewById(R.id.first_name);

        last_name = (MaterialEditText)findViewById(R.id.last_name);

        mobile = (MaterialEditText)findViewById(R.id.mobile);

        username = (MaterialEditText)findViewById(R.id.username);

        password = (MaterialEditText)findViewById(R.id.password);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendSms(first_name.getText ().toString(),last_name.getText ().toString(),username.getText ().toString(),mobile.getText ().toString(),password.getText ().toString());
            }
        });
    }

    public String sendSms(final String first_name,final String last_name,final String username,final String mobile,final String password) {
        try {
            // Construct data
            String apiKey = "apikey=" + "bwk+q/+AqNU-BQWnGRUk8K0DIFoCiqfWxNAfquWX6I\t";

            Random random = new Random();
            int randomNumber = random.nextInt(999999);

            String message = "&message=" + "Your OTP is" + randomNumber;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "91" + mobile;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL ("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader (conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            Log.d(TAG,stringBuffer.toString());
            Toast.makeText ( getApplicationContext (),"OTP SENT SUCCESSFULLY", Toast.LENGTH_LONG).show();
            Intent intent = new Intent ( getApplicationContext (), OtpVerification.class );

            intent.putExtra ( "first_name", first_name);
            intent.putExtra ( "last_name", last_name);
            intent.putExtra ( "username", username);
            intent.putExtra ( "mobile", mobile);
            intent.putExtra ( "password", password);
            intent.putExtra ( "otp", Integer.toString(randomNumber));

            startActivity ( intent );
            //return stringBuffer.toString();
        } catch (Exception e) {
            Toast.makeText ( getApplicationContext (),"Error in sending OTP", Toast.LENGTH_LONG).show();
            //System.out.println("Error SMS "+e);
            //return "Error "+e;
        }
        return null;
    }

}
