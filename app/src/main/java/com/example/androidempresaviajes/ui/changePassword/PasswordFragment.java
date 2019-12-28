package com.example.androidempresaviajes.ui.changePassword;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidempresaviajes.R;
import com.example.androidempresaviajes.model.ApiJsonResult;


public class PasswordFragment extends Fragment {

    private PasswordViewModel passwordViewModel;
    private EditText oldPass, newPass, newPass2;
    private Button editar, guardar;

    public PasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        passwordViewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        oldPass = root.findViewById(R.id.password_passwordActual);
        newPass = root.findViewById(R.id.password_passwordNueva);
        newPass2 = root.findViewById(R.id.password_passwordNueva2);

        guardar = root.findViewById(R.id.password_btnGuardar);

        passwordViewModel.getResult().observe(this, new Observer<ApiJsonResult>() {
            @Override
            public void onChanged(@Nullable ApiJsonResult result) {
                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                if (result.getErrors() == 0)
                    limpiarControles();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).setTitle("").setMessage("Desea cambiar su password?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (passwordViewModel.validarPassword(newPass.getText().toString(),
                                newPass2.getText().toString(),
                                oldPass.getText().toString()))
                            passwordViewModel.changePassword(newPass.toString(), oldPass.toString());
                        else
                            Toast.makeText(getContext(), "Complete todos los campos correctamente", Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });

        return root;
    }

    public void limpiarControles() {
        newPass.setText("");
        newPass2.setText("");
        oldPass.setText("");
    }

    public void habilitarControles(boolean habilitar) {
        newPass.setEnabled(habilitar);
        newPass2.setEnabled(habilitar);
        oldPass.setEnabled(habilitar);
    }

}
