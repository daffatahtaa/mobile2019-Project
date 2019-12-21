package com.daffatahta.mobile2019UAS.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Swipe dari kiri untuk membuka menu");
    }

    public LiveData<String> getText() {
        return mText;
    }
}