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
import com.pub.secure.network.ApiClient;
import com.pub.secure.network.ApiService;
import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.UserResponse;
import com.pub.secure.responses.WhatsappOtpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRespository {


    private ApiService apiService;
    SharePref sharePref;

    public UserRespository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public UserRespository(String baseUrl, String apiKey) {
        ApiKeyManager.getInstance().setBaseUrl(baseUrl);
        ApiKeyManager.getInstance().setApiKey(apiKey);
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<UserResponse> getProfileData(String uid, String bid, Activity activity) {
        sharePref = new SharePref(activity);
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.geUserProfile(apiKey, uid, bid).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Log.d("onResponse___", uid + " -> " + response.body());
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("onResponse___", uid + " E-> " + t.getMessage());
                    data.setValue(null);
                }
            });
        } else {
            data.setValue(null);
            showError(activity);
        }
        return data;
    }

    public LiveData<UserResponse> getUserPosts(String uid) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getUserPosts(apiKey, uid).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d("onResponse___", uid + " -> " + response.body());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("onResponse___", uid + " E-> " + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<UserResponse> updateProfilePic(String uid, String path) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();

        String apiKey = ApiKeyManager.getInstance().getApiKey();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));
        MultipartBody.Part imageBody =
                MultipartBody.Part.createFormData("image", new File(path).getName(), requestFile);
        RequestBody userid =
                RequestBody.create(MediaType.parse("multipart/form-data"), uid);

        apiService.updateProfilePic(apiKey, userid, imageBody).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<UserResponse> offlineSuscription(String uid, String typ, String planid, String screenshot, String promocod, String amnt, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(screenshot));
            MultipartBody.Part imageBody = MultipartBody.Part.createFormData("image", new File(screenshot).getName(), requestFile);

            RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), uid);
            RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), typ);
            RequestBody sid = RequestBody.create(MediaType.parse("multipart/form-data"), planid);
            RequestBody promocode = RequestBody.create(MediaType.parse("multipart/form-data"), promocod);
            RequestBody amount = RequestBody.create(MediaType.parse("multipart/form-data"), amnt);

            apiService.offlineSuscription(apiKey, userid, type, sid, promocode, amount, imageBody).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("onFailure___", t.getMessage());
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> uploadUserPost(String uid, String path) {

        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));

        MultipartBody.Part fileBody = null;
        if (path.endsWith(".mp4")) {
            fileBody = MultipartBody.Part.createFormData("video", new File(path).getName(), requestFile);
        } else {
            fileBody = MultipartBody.Part.createFormData("image", new File(path).getName(), requestFile);
        }

        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), uid);

        apiService.uploadUserPost(apiKey, userid, fileBody).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<UserResponse> login(JSONObject object, Activity activity, Activity activity1) throws JSONException {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();

        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.login(
                    apiKey,
                    "" + object.getString("social"),
                    "" + object.getString("social_id"),
                    "" + object.getString("auth_token"),
                    "" + object.getString("email"),
                    "" + object.getString("number"),
                    "" + object.getString("profile_pic"),
                    "" + object.getString("name"),
                    "" + object.getString("device_token")).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                    Log.d("login__", "onResponse: " + apiKey + " " + response.code());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                    Log.d("login__", "onFailure: " + t.getMessage());
                }
            });
        } else {
            data.setValue(null);
            showError(activity);
        }
        return data;
    }

    public LiveData<UserResponse> updateProfile(String uid, String name, String refercode, Activity activity) {
        sharePref = new SharePref(activity);
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.updateProfile(apiKey, "" + uid, "" + name, "" + refercode).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            data.setValue(null);
            showError(activity);
        }
        return data;
    }

    public LiveData<UserResponse> addContact(String uid, String number, String message, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.addContact(
                    apiKey,
                    "" + uid,
                    "" + number,
                    "" + message).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getBusinessDetail(String uid, String bid, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getBusinessDetail(
                    apiKey,
                    "" + uid,
                    "" + bid).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> updateSuscription(String uid, String type, String sid, String tid, String promocode, String amount, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.updateSubscription(
                    apiKey,
                    "" + uid,
                    "" + type,
                    "" + sid,
                    "" + tid,
                    "" + promocode,
                    "" + amount).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getAutomationFrames(String uid, String type, String category_id, String bussiness_id, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getAutomationFrames(
                    apiKey,
                    "" + uid,
                    "" + type,
                    category_id,
                    bussiness_id).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getInvitedUser(String uid, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getInvitedUser(
                    apiKey,
                    "" + uid).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getWithdrawRequest(String uid, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getWithdrawRequest(
                    apiKey,
                    "" + uid).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getTransactionRequest(String uid, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getTransactionRequest(
                    apiKey,
                    "" + uid).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> withdrawRequest(String uid, int balance, String s, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.withdrawRequest(
                    apiKey,
                    "" + uid,
                    balance,
                    s).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> cheakReferCode(String code, Activity activity, Activity activity1) {
        sharePref = new SharePref(activity);
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.cheakReferCode(
                    apiKey,
                    "" + code).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<WhatsappOtpResponse> createWhatsappOtp(String number, Activity activity, Activity activity1) {
        sharePref = new SharePref(activity);
        MutableLiveData<WhatsappOtpResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.createWhatsappOtp(
                    apiKey, number).enqueue(new Callback<WhatsappOtpResponse>() {
                @Override
                public void onResponse(Call<WhatsappOtpResponse> call, Response<WhatsappOtpResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<WhatsappOtpResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<SimpleResponse> deleteUserAccount(String uid, Activity activity, Activity activity1) {
        sharePref = new SharePref(activity);
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.deleteUserAccount(
                    apiKey, uid).enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public void updateDeviceToken(String uid, String token) {
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.updateDeviceToken(apiKey, uid, token)
                .enqueue(new Callback<SimpleResponse>() {
                    @Override
                    public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<SimpleResponse> call, Throwable t) {

                    }
                });
    }

    public LiveData<UserResponse> getUserCategory(String category_id, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getUserCategory(
                    apiKey,
                    "" + category_id).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getUserBusiness(String uid, String type, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getUserBusiness(
                    apiKey,
                    "" + uid,
                    "" + type).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> addUserBussiness(JSONObject jsonObject, Activity activity) throws JSONException {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();

            MultipartBody.Part fileBody = null;
            if (jsonObject.getString("image").length() > 10) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(jsonObject.getString("image")));
                fileBody = MultipartBody.Part.createFormData("image", new File(jsonObject.getString("image")).getName(), requestFile);
            }
            RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("id"));
            RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("user_id"));
            RequestBody company = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("company"));
            RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("name"));
            RequestBody number = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("number"));
            RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("email"));
            RequestBody website = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("website"));
            RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("address"));
            RequestBody whatsapp = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("whatsapp"));
            RequestBody facebook = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("facebook"));
            RequestBody twitter = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("twitter"));
            RequestBody youtube = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("youtube"));
            RequestBody instagram = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("instagram"));
            RequestBody about = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("about"));
            RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("type"));
            RequestBody designation = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("designation"));
            RequestBody category_id = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("category_id"));

            //Automation
            RequestBody automation = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("automation"));
            RequestBody time = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("time"));
            RequestBody auto_frame_type = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("auto_frame_type"));
            RequestBody auto_frame_id = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("auto_frame_id"));
            RequestBody auto_festival = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("auto_festival"));
            RequestBody auto_business = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("auto_business"));
            RequestBody auto_language = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.getString("auto_language"));

            apiService.addBusiness(
                    apiKey,
                    fileBody,
                    id,
                    userid,
                    company,
                    name,
                    number,
                    email,
                    website,
                    address,
                    whatsapp,
                    facebook,
                    twitter,
                    youtube,
                    instagram,
                    about,
                    type,
                    designation,
                    category_id,
                    automation,
                    time,
                    auto_frame_type,
                    auto_frame_id,
                    auto_festival,
                    auto_business,
                    auto_language).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Log.d("onResponse___", " -> " + response.body());
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("onResponse___", "Error -> " + t.getMessage());
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> addInquiry(String uid, String service_id, String number, String message, Activity activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.addInquiry(
                    apiKey,
                    "" + uid,
                    "" + service_id,
                    "" + number,
                    "" + message).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<SimpleResponse> createBusinessCard(String uid, String bid, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.createBusinessCard(apiKey, uid, bid).enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<SimpleResponse> updateDefaultBusiness(String uid, String id, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.updateDefaultBusiness(apiKey, uid, id).enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }


    public LiveData<SimpleResponse> deleteUserBusiness(String id, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.deleteUserBusiness(apiKey, id).enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<SimpleResponse> deleteUserFrame(String user_fid, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.deleteUserFrame(apiKey, user_fid).enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
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
