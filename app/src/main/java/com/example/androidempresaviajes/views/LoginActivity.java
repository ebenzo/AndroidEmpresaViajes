package com.example.androidempresaviajes.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidempresaviajes.R;

public class LoginActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private Button btnLogin;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView loginError;

    private String token;
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        btnLogin = findViewById(R.id.btnLogin);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginError = findViewById(R.id.loginError);

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //loginError.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginEmail.getText().toString().equals("") && !loginPassword.getText().toString().equals("")) {
                //if (loginEmail.getText().toString().equals("e") && loginPassword.getText().toString().equals("1")) {
                    /*Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                    //le llevo todos los parametros que va a usar la vista
                    i.putExtra("email", "benzoemma@gmail.com");
                    i.putExtra("nombre", loginEmail.getText().toString());
                    startActivity(i);*/
                    viewModel.ingresar(loginEmail.getText().toString(), loginPassword.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Usuario y/o Password incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
