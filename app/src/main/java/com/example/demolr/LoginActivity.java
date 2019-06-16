package com.example.demolr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText edit_username,edit_password;
    MaterialButton btn_login;
    RequestQueue requestQueue;
    Context context = this;
    SharedPreferences.Editor editor;
    private final String TAG = "Login Activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(LoginActivity.this);
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
        Log.d (TAG, "loginUser called");
        HashMap<String, String> params = new HashMap<> ();
        params.put("uname", username);
        params.put("password", password);

        JsonObjectRequest request_json = new JsonObjectRequest (Constants.loginUrl, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {

                        try {
                            Log.i("qwerty", "onResponse: " + response);
                            //Process os success response
                            String error = response.get("Error").toString();
                            if (error.equals("true")) {

                            } else if (error.equals("false")) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = (JSONObject) response.get("User");

                                    final int userId = (Integer) jsonObject.get("id");
                                    final String name = jsonObject.get("name").toString();
                                    final String userName = jsonObject.get("uname").toString();
                                    final String userLocation = jsonObject.get("location").toString();
                                    final String userMobileNo = jsonObject.get("mobile").toString();
                                    final String wa_link = jsonObject.get("wa_link").toString();
                                    final String fb_link = jsonObject.get("fb_link").toString();
                                    final String profile_pic = jsonObject.get("profile_pic").toString();
                                    final int age = (Integer) jsonObject.get("age");
                                    editor = getSharedPreferences(Constants.userDetails, MODE_PRIVATE).edit();
                                    editor.putInt("User_Id", userId);
                                    editor.putString("Name", name);
                                    editor.putString("Username", userName);
                                    editor.putString("Location", userLocation);
                                    editor.putString("Mobile", userMobileNo);
                                    editor.putString("Whatsapp_Link", wa_link);
                                    editor.putString("Facebook_Link", fb_link);
                                    editor.putString("Avatar", profile_pic);
                                    editor.putInt("Age", age);
                                    editor.putBoolean("Logged_in", true);
                                    editor.apply();
                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        request_json.setRetryPolicy(new DefaultRetryPolicy (
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request_json);
    }

}
