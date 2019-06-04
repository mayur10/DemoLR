package com.example.demolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.demolr.Retrofit.INodeJs;
import com.example.demolr.Retrofit.RetroFitClient;
import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    INodeJs myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MaterialEditText edit_username,edit_password;
    MaterialButton btn_login;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);

        Retrofit retrofit = RetroFitClient.getInstance();
        myApi = retrofit.create(INodeJs.class);

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
        compositeDisposable.add(myApi.loginUser(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("uname")) {
                            Toast.makeText ( LoginActivity.this, "Login Success", Toast.LENGTH_SHORT ).show ();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText ( LoginActivity.this, "" + s, Toast.LENGTH_SHORT ).show ();
                        }
                    }
                })
        );

    }

}
