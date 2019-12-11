package com.example.androidempresaviajes.ui.pasajeros;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PasajerosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PasajerosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}