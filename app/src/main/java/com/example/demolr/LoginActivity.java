package com.example.demolr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText edit_username,edit_password;
    MaterialButton btn_login;
    private final String TAG = "Login Activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);

        btn_login = (MaterialButton)findViewById(R.id.login_button);

        edit_username = (MaterialEditText)findViewById(R.id.edit_username);

        edit_password = (MaterialEditText)findViewById(R.id.edit_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(edit_username.getText().toString(),edit_password.getText().toString());
            }
        });
    }


    private void loginUser(String username, String password) {
        Log.d ( TAG,"loginUser Called." );
    }

}
