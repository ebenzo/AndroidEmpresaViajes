package com.example.androidempresaviajes.request;

import android.util.Log;

import com.example.androidempresaviajes.model.ApiJsonResult;
import com.example.androidempresaviajes.model.Empresa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {
    private static final String PATH="http://192.168.1.130:45455/api/";//Failed to connect to localhost/127.0.0.1
    private static MyApiInterface myApiInterface;
    private static String accessToken = null;

    public static MyApiInterface getMyApiClient()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                //.callTimeout(2, TimeUnit.MINUTES) - default 0 (no timeout)
                .connectTimeout(60, TimeUnit.SECONDS) // default 10 sec
                .readTimeout(60, TimeUnit.SECONDS) // default 10 sec
                .writeTimeout(60, TimeUnit.SECONDS); // default 10 sec

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Log.d("Mensaje",retrofit.baseUrl().toString());
        myApiInterface=retrofit.create(MyApiInterface.class);
        return myApiInterface;
    }

    public interface MyApiInterface{

        @POST("empresas/login")
        Call<String> login(@Query("Email")String Email, @Query("Password")String Password);

        @GET("empresas/")
        Call<Empresa> getPerfil(@Header("Authorization")String Token);

        @PUT("empresas/{id}")
        Call<Empresa> setPerfil(@Header("Authorization")String Token,
                                @Path("id") int idEmpresa,
                                @Query("nombre")String nombre,
                                @Query("direccion")String direccion,
                                @Query("telefono")String telefono,
                                @Query("email")String email,
                                @Query("cuit")String cuit,
                                @Query("password")String password);

        @PUT("ChangePassword/{id}")
        Call<ApiJsonResult> changePassword(@Query("newPass")String newPass, @Query("oldPass")String oldPass);
    }
}
