package com.example.androidempresaviajes.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ApiClient {
    private static final String PATH="http://localhost/127.0.0.1:51518/api/";//Failed to connect to localhost/127.0.0.1
    private static MyApiInterface myApiInterface;
    private static String accessToken = null;

    public static MyApiInterface getMyApiClient()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Log.d("Mensaje",retrofit.baseUrl().toString());
        myApiInterface=retrofit.create(MyApiInterface.class);
        return myApiInterface;
    }

    public interface MyApiInterface{

        @POST("/empresas/login")
        Call<String> login(@Query("Email")String Email, @Query("Password")String Password);
    }
}
