package com.example.androidempresaviajes.ui.pasajeros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidempresaviajes.R;

public class PasajerosFragment extends Fragment {

    private PasajerosViewModel pasajerosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pasajerosViewModel =
                ViewModelProviders.of(this).get(PasajerosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pasajeros, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        pasajerosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}