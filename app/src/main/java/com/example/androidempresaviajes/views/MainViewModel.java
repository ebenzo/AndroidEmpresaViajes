package com.example.androidempresaviajes.views;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidempresaviajes.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<String> token;
    private MutableLiveData<String> error;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }

        return  error;
    }

    public LiveData<String> getToken() {
        if (token == null) {
            token = new MutableLiveData<>();
        }

        return  token;
    }

    public void ingresar(String email, String pass) {
        Call<String> dato = ApiClient.getMyApiClient().login(email, pass);

        dato.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    token.postValue(response.body());
                    SharedPreferences sp = contexto.getSharedPreferences("token", 0);//0 porque es privado?
                    SharedPreferences.Editor editor = sp.edit();
                    String t = "Bearer " + response.body();
                    editor.putString("token", t);
                    editor.commit();
                    Log.d("salida ultimo token ", t);

                    error.postValue("login correcto");
                }
                else {
                    Log.d("Error en response ", "mal salio algo");
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("algo salio mal: ", t.getMessage());
            }
        });
    }
}













