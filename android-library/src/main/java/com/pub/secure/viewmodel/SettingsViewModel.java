package com.pub.secure.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pub.secure.model.SettingModel;
import com.pub.secure.respository.SettingRespository;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    private SettingRespository respository;

    public SettingsViewModel(){
        respository = new SettingRespository();
    }

    public LiveData<List<SettingModel>> getAllSetting(Activity context){
        return respository.getData(context);
    }

}
