package com.example.androidempresaviajes.ui.reservas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReservasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}