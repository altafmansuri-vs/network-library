package com.pub.secure.respository;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pub.secure.classes.ApiKeyManager;
import com.pub.secure.classes.SharePref;
import com.pub.secure.model.SettingModel;
import com.pub.secure.network.ApiClient;
import com.pub.secure.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingRespository {


    private ApiService apiService;
    SharePref sharePref;

    public SettingRespository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<SettingModel>> getData(Activity context){
        sharePref = new SharePref(context);
        MutableLiveData<List<SettingModel>> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")){
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getSettings(apiKey).enqueue(new Callback<List<SettingModel>>() {
                @Override
                public void onResponse(Call<List<SettingModel>> call, Response<List<SettingModel>> response) {
                    data.setValue(response.body());
                    Log.d("setting__", "onResponse: "+apiKey+" " + response.code());
                }

                @Override
                public void onFailure(Call<List<SettingModel>> call, Throwable t) {
                    data.setValue(null);
                }
            });
        }else {
            showError(context);
            List<SettingModel> list = new ArrayList<>();
            data.setValue(list);
        }
        return data;
    }


    private void showError(Activity context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(sharePref.getString("message"));
        String bgf = "QnV5IE5vdw==";
        String dghh = "aHR0cHM6Ly9jb2RlY2FueW9uLm5ldC91c2VyL3Zpc3RpY3NvbHV0aW9uL3BvcnRmb2xpbw==";
        if (sharePref.getString("message").contains("License")) {
            String decbgf = new String(Base64.decode(bgf, Base64.DEFAULT));
            String decdghh = new String(Base64.decode(dghh, Base64.DEFAULT));
            alertDialog.setPositiveButton(decbgf, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(decdghh)));
                }
            });
        } else {
            alertDialog.setPositiveButton("Restart App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(context, context.getClass());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    context.finish();
                }
            });
        }
        alertDialog.show();
    }
}
