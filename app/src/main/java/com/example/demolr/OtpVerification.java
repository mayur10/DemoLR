package com.example.demolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rengwuxian.materialedittext.MaterialEditText;
//import com.example.demolr.Retrofit.INodeJs;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;

public class OtpVerification extends AppCompatActivity {
    public final String TAG = "OTP VERIFICATION";
//    INodeJs myApi;
//    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MaterialEditText otp_code;
    MaterialButton verify_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView ( R.layout.otp_activity );
        super.onCreate ( savedInstanceState );

        Intent intent = getIntent();
//        final String first_name = intent.getStringExtra("first_name");
//        final String last_name = intent.getStringExtra("last_name");
//        final String username = intent.getStringExtra("username");
//        final String mobile = intent.getStringExtra("mobile");
//        final String password = intent.getStringExtra("password");
        final String otp = intent.getStringExtra("otp");

        verify_button = (MaterialButton)findViewById ( R.id.verify_button );

        verify_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                otp_code = (MaterialEditText)findViewById(R.id.otp);

                Log.d(TAG,otp);
                Log.d(TAG,otp.getClass().getSimpleName());

                Log.d(TAG,otp_code.getText().toString());
                Log.d(TAG,otp_code.getText().toString().getClass().getSimpleName());

                if(otp.equals(otp_code.getText().toString())){
                    Toast.makeText ( getApplicationContext (), "Registration Successful.", Toast.LENGTH_LONG ).show ();
                    //registerUser(first_name, last_name, username, mobile, password);
                }else{
                    Toast.makeText ( getApplicationContext (), "Wrong OTP.", Toast.LENGTH_LONG ).show ();
                }
            }
        } );


    }

//     private void registerUser(final String first_name, final String last_name, final String username, final String mobile,final String password) {
//        compositeDisposable.add(myApi.registerUser(first_name, last_name, username, mobile, password)
//                .subscribeOn( Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Toast.makeText(OtpVerification.this,""+s,Toast.LENGTH_SHORT).show();
//                    }
//                })
//        );
//    }
}
