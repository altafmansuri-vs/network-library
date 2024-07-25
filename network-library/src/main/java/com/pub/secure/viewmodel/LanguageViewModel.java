package com.pub.secure.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pub.secure.model.LanguageModel;
import com.pub.secure.respository.LanguageRespository;

import java.util.List;

public class LanguageViewModel extends ViewModel {

    private LanguageRespository respository;

    public LanguageViewModel(){
        respository = new LanguageRespository();
    }

    public LiveData<List<LanguageModel>> getData(){
        return respository.getData();
    }

}
