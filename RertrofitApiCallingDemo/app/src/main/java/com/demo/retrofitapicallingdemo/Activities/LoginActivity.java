package com.demo.retrofitapicallingdemo.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.retrofitapicallingdemo.Interfaces.ApiInterface;
import com.demo.retrofitapicallingdemo.Model.LoginModel;
import com.demo.retrofitapicallingdemo.R;
import com.demo.retrofitapicallingdemo.Utils.ApiCall;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_userName, edt_password;
    Button btn_login;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVeiws();
        initActioBar();
    }

    /**
    * Finding views
    * */
    public void initVeiws(){
        edt_userName = findViewById(R.id.edt_useremailaddress);
        edt_password = findViewById(R.id.edt_userpassword);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    public void initActioBar(){
        actionBar = getSupportActionBar();
        try{
            actionBar.hide();
        }catch (NullPointerException np){
            Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn_login:
                LoginRequest();
                break;
        }
    }

    public void LoginRequest(){
        ApiInterface apiInterface = ApiCall.getClient().create(ApiInterface.class);

        Call<ResponseBody> apiCall = apiInterface.loginwithcredential(new LoginModel(edt_userName.getText().toString().trim(),
                edt_password.getText().toString().trim()));
        apiCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        if (response.body() != null) {
                            String strResponse = response.body().string();
                            Log.e("LoginActivity","Getting Response "+strResponse);
                            openActivity();
                        }
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity","Getting Failure Response "+t.getMessage());
            }
        });
    }

    private void openActivity(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

}
