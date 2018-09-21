package com.demo.retrofitapicallingdemo.Interfaces;

import com.demo.retrofitapicallingdemo.Model.LoginModel;
import com.demo.retrofitapicallingdemo.Model.ModelClass;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("marvel")
    Call<List<ModelClass>> getModelClass();

    @POST("login")
    Call<ResponseBody> loginwithcredential(@Body LoginModel model);
}
