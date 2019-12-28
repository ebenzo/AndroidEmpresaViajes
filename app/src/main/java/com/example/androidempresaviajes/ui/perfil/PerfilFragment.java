package com.example.androidempresaviajes.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidempresaviajes.R;
import com.example.androidempresaviajes.model.Empresa;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText nombre, direccion, telefono, email, cuit, password;
    private Button editar, guardar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombre = root.findViewById(R.id.perfil_nombre);
        direccion = root.findViewById(R.id.perfil_direccion);
        telefono = root.findViewById(R.id.perfil_telefono);
        email = root.findViewById(R.id.perfil_email);
        cuit = root.findViewById(R.id.perfil_cuit);
        password = root.findViewById(R.id.perfil_password);

        editar = root.findViewById(R.id.perfil_editar);
        guardar = root.findViewById(R.id.perfil_guardar);

        perfilViewModel.getEmpresa().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(@Nullable Empresa emp) {
                setDatosControles(emp);
            }
        });

        perfilViewModel.getPerfil();

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarPerfil();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarPerfil();
            }
        });

        return root;
    }

    public void guardarPerfil() {
        habilitarControles(false);

        editar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.GONE);

        perfilViewModel.setPerfil(getDatosControles());
    }

    public void editarPerfil() {
        habilitarControles(true);

        editar.setVisibility(View.GONE);
        guardar.setVisibility(View.VISIBLE);
    }

    public void habilitarControles(boolean habilitar) {
        nombre.setEnabled(habilitar);
        direccion.setEnabled(habilitar);
        telefono.setEnabled(habilitar);
        //email.setEnabled(habilitar);
        cuit.setEnabled(habilitar);
        password.setEnabled(habilitar);
    }

    public void setDatosControles(Empresa emp){

        nombre.setText(String.valueOf(emp.getNombre()));
        direccion.setText(String.valueOf(emp.getDireccion()));
        telefono.setText(String.valueOf(emp.getTelefono()));
        email.setText(String.valueOf(emp.getEmail()));
        cuit.setText(String.valueOf(emp.getCuit()));
        password.setText(String.valueOf(emp.getPassword()));
        //Log.d("salida",pass.getText()+"");

        habilitarControles(false);

        editar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.GONE);

    }

    public Empresa getDatosControles(){

        Empresa emp = new Empresa();
        emp.setNombre(nombre.getText().toString());
        emp.setDireccion(direccion.getText().toString());
        emp.setTelefono(telefono.getText().toString());
        emp.setEmail(email.getText().toString());
        emp.setCuit(cuit.getText().toString());
        emp.setPassword(password.getText().toString());

        return  emp;
    }
}