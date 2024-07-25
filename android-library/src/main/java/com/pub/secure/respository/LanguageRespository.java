package com.pub.secure.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pub.secure.classes.ApiKeyManager;
import com.pub.secure.model.LanguageModel;
import com.pub.secure.network.ApiClient;
import com.pub.secure.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguageRespository {


    private ApiService apiService;
    public LanguageRespository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<LanguageModel>> getData(){
        MutableLiveData<List<LanguageModel>> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getLanguages(apiKey).enqueue(new Callback<List<LanguageModel>>() {
            @Override
            public void onResponse(Call<List<LanguageModel>> call, Response<List<LanguageModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<LanguageModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
