package com.example.androidempresaviajes.ui.changePassword;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidempresaviajes.model.ApiJsonResult;
import com.example.androidempresaviajes.request.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PasswordViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<ApiJsonResult> res;

    public PasswordViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<ApiJsonResult> getResult() {
        if (res == null) {
            res = new MutableLiveData<>();
        }

        return res;
    }

    public Boolean validarPassword(String newPass, String newPass2, String oldPass) {
        if (newPass.equals("") || newPass2.equals("") || oldPass.equals("") || !newPass.equals(newPass2))
            return  false;

        return true;
    }

    public void changePassword(String newPass, String oldPass) {

        Call<ApiJsonResult> dato = ApiClient.getMyApiClient().changePassword(newPass, oldPass);

        dato.enqueue(new Callback<ApiJsonResult>() {
            @Override
            public void onResponse(Call<ApiJsonResult> call, Response<ApiJsonResult> response) {
                if (response.isSuccessful()) {
                    ApiJsonResult result = response.body();

                    res.postValue(result);
                } else {
                    Log.d("Error en response ", "mal salio algo");//response.errorBody().string()
                    try {
                        ApiJsonResult result = new ApiJsonResult(1, response.message() + " " + response.errorBody().string());
                        res.postValue(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ApiJsonResult> call, Throwable t) {
                Log.d("algo salio mal: ", t.getMessage());
                ApiJsonResult failure = new ApiJsonResult(1, "Ocurrió un error al cambiar la password");
                res.postValue(failure);
            }
        });
    }
}
