package com.example.demolr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.demolr.Retrofit.INodeJs;
import com.example.demolr.Retrofit.RetroFitClient;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    INodeJs myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    MaterialEditText edit_username,edit_password;
    MaterialButton btn_register,btn_login;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetroFitClient.getInstance();
        myApi = retrofit.create(INodeJs.class);

        btn_login = (MaterialButton)findViewById(R.id.login_button);

        btn_register = (MaterialButton)findViewById(R.id.register_button);

        edit_username = (MaterialEditText)findViewById(R.id.edit_username);

        edit_password = (MaterialEditText)findViewById(R.id.edit_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(edit_username.getText().toString(),edit_password.getText().toString());
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(edit_username.getText().toString(),edit_password.getText().toString());
            }
        });

    }

    private void registerUser(final String username, final String password) {

        final View additional_data_layout = LayoutInflater.from((this)).inflate(R.layout.additional_data_layout, null);

        new MaterialStyledDialog.Builder(this)
                .setTitle("Register")
                .setDescription("One More Step")
                .setCustomView(additional_data_layout)
                .setNegativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveText("Submit")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MaterialEditText edit_first_name,edit_last_name,edit_mobile;

                        edit_first_name = (MaterialEditText)additional_data_layout.findViewById(R.id.edit_first_name);

                        edit_last_name = (MaterialEditText)additional_data_layout.findViewById(R.id.edit_last_name);

                        edit_mobile = (MaterialEditText)additional_data_layout.findViewById(R.id.edit_mobile);

                        compositeDisposable.add(myApi.registerUser(edit_first_name.getText().toString(), edit_last_name.getText().toString(), username, edit_mobile.getText().toString(), password)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        Toast.makeText(MainActivity.this,""+s,Toast.LENGTH_SHORT).show();
                                    }
                                })
                        );
                    }
                }).show();

    }

    private void loginUser(String username, String password) {

        compositeDisposable.add(myApi.loginUser(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("password"))
                            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,""+s,Toast.LENGTH_SHORT).show();
                    }
                })
        );

    }
}
