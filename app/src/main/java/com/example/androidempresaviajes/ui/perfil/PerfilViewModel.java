package com.example.androidempresaviajes.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidempresaviajes.model.Empresa;
import com.example.androidempresaviajes.request.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<Empresa> empresa;
    private MutableLiveData<String> res;
    private Empresa emp;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<String> getRes() {
        if (res == null) {
            res = new MutableLiveData<>();
        }

        return  res;
    }

    public LiveData<Empresa> getEmpresa() {
        if (empresa == null) {
            empresa = new MutableLiveData<>();
        }

        return  empresa;
    }

    public void leer(){
        SharedPreferences sp = contexto.getSharedPreferences("Empresa",0);
        emp = new Empresa();
        emp.setIdEmpresa(sp.getInt("idEmpresa",0));
        emp.setNombre(sp.getString("nombre", ""));
        emp.setDireccion(sp.getString("direccion", ""));
        emp.setTelefono(sp.getString("telefono", ""));
        emp.setEmail(sp.getString("email", ""));
        emp.setCuit(sp.getString("cuit", ""));
        emp.setPassword(sp.getString("password", ""));

        empresa.setValue(emp);
    }

    public void getPerfil() {
        SharedPreferences sp = contexto.getSharedPreferences("Token",0);
        String accessToken = sp.getString("Token","");

        Call<Empresa> dato = ApiClient.getMyApiClient().getPerfil(accessToken);

        dato.enqueue(new Callback<Empresa>() {

            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                if (response.isSuccessful()) {
                    emp = response.body();

                    SharedPreferences sp = contexto.getSharedPreferences("Empresa", 0);// 0 porque es privado?
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putInt("idEmpresa", emp.getIdEmpresa());
                    editor.putString("nombre", emp.getNombre());
                    editor.putString("direccion", emp.getDireccion());
                    editor.putString("telefono", emp.getTelefono());
                    editor.putString("email", emp.getEmail());
                    editor.putString("cuit", emp.getCuit());
                    editor.putString("password", emp.getPassword());

                    editor.commit();
                    //leer();
                    //res.postValue("login correcto");
                    empresa.setValue(emp);
                }
                else {
                    Log.d("Error en response ", "mal salio algo");//response.errorBody().string()
                    try {
                        res.postValue(response.message() + " " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                Log.d("algo salio mal: ", t.getMessage());
                res.postValue("Ocurrió un error al loguear");
            }
        });
    }

    public void setPerfil(Empresa e) {
        SharedPreferences sp = contexto.getSharedPreferences("Token",0);
        String accessToken = sp.getString("Token","");

        SharedPreferences sp2 = contexto.getSharedPreferences("Empresa",0);
        Integer id = sp2.getInt("idEmpresa",0);

        Call<Empresa> dato = ApiClient.getMyApiClient().setPerfil(accessToken, id, e.getNombre(), e.getDireccion(),
                e.getTelefono(), e.getEmail(), e.getCuit(), e.getPassword());

        dato.enqueue(new Callback<Empresa>() {

            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                if (response.isSuccessful()) {
                    emp = response.body();

                    SharedPreferences sp = contexto.getSharedPreferences("Empresa", 0);// 0 porque es privado?
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putInt("idEmpresa", emp.getIdEmpresa());
                    editor.putString("nombre", emp.getNombre());
                    editor.putString("direccion", emp.getDireccion());
                    editor.putString("telefono", emp.getTelefono());
                    editor.putString("email", emp.getEmail());
                    editor.putString("cuit", emp.getCuit());
                    editor.putString("password", emp.getPassword());

                    editor.commit();

                    empresa.setValue(emp);
                }
                else {
                    Log.d("Error en response ", "mal salio algo");//response.errorBody().string()
                    try {
                        res.postValue(response.message() + " " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                Log.d("algo salio mal: ", t.getMessage());
                res.postValue("Ocurrió un error al loguear");
            }
        });
    }
}