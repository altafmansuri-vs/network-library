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
import com.pub.secure.model.VideoTamplateModel;
import com.pub.secure.network.ApiClient;
import com.pub.secure.network.ApiService;
import com.pub.secure.responses.HomeResponse;
import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRespository {


    private ApiService apiService;
    SharePref sharePref;

    public HomeRespository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<HomeResponse> getData(String uid, String language, String category, int pageCount, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getHomeData(apiKey, language, uid, category, pageCount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> cheakPromo(String promo, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.cheakPromo(apiKey, promo).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getMyBusinessData(String language, String uid, String businessID, int pagecount, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getMyBusinessData(apiKey, language, uid, businessID, pagecount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getBrandingData(String language, String uid, String category, int pagecount, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getBrandingData(apiKey, language, uid, category, pagecount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getCustomData(String language, String uid, String category, int pagecount, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getCustomData(apiKey, language, uid, category, pagecount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getBusinessCards() {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getBusinessCards(apiKey).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<HomeResponse> getServices() {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getServices(apiKey).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<HomeResponse> getInvitationCategories(String query) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getInvitationCategories(apiKey, query).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<HomeResponse> getInvitationCardsByCatId(String id, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getInvitationCardsByCatId(apiKey, id).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getPremiumPostByCategory(String type) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getPremiumPostByCategory(apiKey, type).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<HomeResponse> getSubscriptions(Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {

            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getSubscriptions(apiKey).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getCategoriesByPage(String type, String search, int page, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getCategoriesByPage(apiKey, page, type, search).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getVideoTamplateCategoriesByPage(String type, String search, int page) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        apiService.getVideoTamplateCategoriesByPage(apiKey, page, type, search).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<HomeResponse> getPostByPage(String type, String language, String post_type, String item_id, String subcategory, String search, String postid, int page, String uid, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getPostByPage(apiKey, page, type, language, post_type, item_id, subcategory, postid, search, uid).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getBusinessPoliticalCategory(String search, String
            type, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getBusinessPoliticalCategory(apiKey, search, type).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getDailyPosts(String search, String language, String type, int pageCount, Activity activity) {
        sharePref = new SharePref(activity);
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getDailyPosts(apiKey, search, language, type, pageCount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        data.setValue(response.body());
                    } else {
                        data.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    Log.d("getDailyPosts", "" + t.getMessage());
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<List<VideoTamplateModel>> getVideoTamplates(String id, Activity activity) {
        MutableLiveData<List<VideoTamplateModel>> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getVideoTamplates(apiKey, id).enqueue(new Callback<List<VideoTamplateModel>>() {
                @Override
                public void onResponse(Call<List<VideoTamplateModel>> call, Response<List<VideoTamplateModel>> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<VideoTamplateModel>> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getSearchData(String s, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getSearchData(apiKey, s).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getGroups(String uid, String search, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getGroups(apiKey, uid, search).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getGroupsDetails(String uid, String gid, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getGroupsDetails(apiKey, uid, gid).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getGroupsPosts(String language, String uid, String gid,
                                                 int pagecount, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getGroupsPosts(apiKey, language, uid, gid, pagecount).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<HomeResponse> getSearchUsers(String uid, String groupID, String
            query, Activity activity) {
        MutableLiveData<HomeResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getSearchUsers(apiKey, uid, groupID, query).enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<UserResponse> getProductData(String uid, String businessID, Activity
            activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getProductData(apiKey, uid, businessID).enqueue(new Callback<UserResponse>() {
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

    public LiveData<UserResponse> getInquiriesData(String uid, String businessID, Activity
            activity) {
        MutableLiveData<UserResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getInquiriesData(apiKey, uid, businessID).enqueue(new Callback<UserResponse>() {
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

    public LiveData<SimpleResponse> updateGroupUser(String user_id, String action, Activity
            activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        String apiKey = ApiKeyManager.getInstance().getApiKey();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            apiService.updateGroupUser(apiKey, user_id, action).enqueue(new Callback<SimpleResponse>() {
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

    public LiveData<SimpleResponse> deleteExiteGroup(String groupID, String uid, String
            action, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.deleteExiteGroup(apiKey, groupID, uid, action).enqueue(new Callback<SimpleResponse>() {
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

    public LiveData<SimpleResponse> addUserGroup(String groupID, String id, Activity activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.addUserGroup(apiKey, groupID, id).enqueue(new Callback<SimpleResponse>() {
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

    public LiveData<List<String>> getSearchSuggestion(String s, Activity activity) {
        MutableLiveData<List<String>> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.getSearchSuggestion(apiKey, s).enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } else {
            showError(activity);
            data.setValue(null);
        }
        return data;
    }

    public LiveData<SimpleResponse> createGroup(RequestBody userid, RequestBody
            name, RequestBody description, RequestBody privacy, MultipartBody.Part fileBody, Activity
                                                        activity) {
        MutableLiveData<SimpleResponse> data = new MutableLiveData<>();
        sharePref = new SharePref(activity);
        if (sharePref.getBoolean("verified")) {
            String apiKey = ApiKeyManager.getInstance().getApiKey();
            apiService.createGroup(apiKey, userid, name, description, privacy, fileBody).enqueue(new Callback<SimpleResponse>() {
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
