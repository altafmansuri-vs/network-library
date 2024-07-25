package com.pub.secure.respository;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pub.secure.classes.ApiKeyManager;
import com.pub.secure.classes.SharePref;
import com.pub.secure.network.ApiClient;
import com.pub.secure.network.ApiService;
import com.pub.secure.responses.FrameResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrameRespository {


    private ApiService apiService;
    SharePref sharePref;

    public FrameRespository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<FrameResponse> getFrames(String uid, String bid, String cid, boolean animated, Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getFrames(apiKey, uid, bid, cid, animated).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<FrameResponse> getStickers(String search, Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getStickersByCategory(apiKey, search).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<FrameResponse> getLogos(Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getLogosByCategory(apiKey).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
//                data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<FrameResponse> getMusicByCategory(String search, Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getMusicByCategory(apiKey, search).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<FrameResponse> getBackgrounds(String search, Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getBackgrounds(apiKey, search).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<FrameResponse> getUserFrames(String user_id, String bid, Activity activity) {
        MutableLiveData<FrameResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getUserFrames(apiKey, user_id, bid).enqueue(new Callback<FrameResponse>() {
                @Override
                public void onResponse(Call<FrameResponse> call, Response<FrameResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<FrameResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
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
