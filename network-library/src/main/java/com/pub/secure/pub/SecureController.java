package com.pub.secure.pub;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.pub.secure.classes.ApiKeyManager;
import com.pub.secure.model.LanguageModel;
import com.pub.secure.model.SettingModel;
import com.pub.secure.model.VideoTamplateModel;
import com.pub.secure.responses.FrameResponse;
import com.pub.secure.responses.HomeResponse;
import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.UserResponse;
import com.pub.secure.responses.WhatsappOtpResponse;
import com.pub.secure.viewmodel.FrameViewModel;
import com.pub.secure.viewmodel.HomeViewModel;
import com.pub.secure.viewmodel.LanguageViewModel;
import com.pub.secure.viewmodel.SettingsViewModel;
import com.pub.secure.viewmodel.UserViewModel;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SecureController {

    String baseUrl;
    Activity activity;
    Context context;

    public SecureController(Activity activity, String baseUrl,String apiKey) {
        this.activity = activity;
        this.baseUrl = baseUrl;
        ApiKeyManager.getInstance().setBaseUrl(baseUrl);
        ApiKeyManager.getInstance().setApiKey(apiKey);
    }

    public SecureController(Context context, String baseUrl, String apiKey) {
        this.context = context;
        this.baseUrl = baseUrl;
        ApiKeyManager.getInstance().setBaseUrl(baseUrl);
        ApiKeyManager.getInstance().setApiKey(apiKey);
    }

    public LiveData<List<SettingModel>> getSettings() {
        SettingsViewModel settingsViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(SettingsViewModel.class);
        return settingsViewModel.getAllSetting(activity);
    }


    public LiveData<UserResponse> updateProfile(String uid, String name, String referCode) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.updateProfile(uid, name, referCode,activity);
    }

    public LiveData<UserResponse> cheakReferCode(String referCode) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.cheakReferCode(referCode,activity);
    }

    public LiveData<List<LanguageModel>> getLangauges() {
        LanguageViewModel languageViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(LanguageViewModel.class);
        return languageViewModel.getData();
    }

    public LiveData<UserResponse> getUserProfile(String uid, String bid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getUserProfile(uid,bid,activity);
    }


    public LiveData<UserResponse> login(JSONObject parameters) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.login(parameters,activity);
    }

    public LiveData<WhatsappOtpResponse> createWhatsappOtp(String number) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.createWhatsappOtp(number,activity);
    }

    public LiveData<SimpleResponse> deleteUserAccount(String uid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.deleteUserAccount(uid,activity);
    }

    public LiveData<FrameResponse> getBackgrounds(String search) {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getBackgrounds(search,activity);
    }

    public LiveData<FrameResponse> getMusic(String search) {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getMusic(search,activity);
    }

    public LiveData<FrameResponse> getStickers(String search) {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getStickers(search,activity);
    }

    public LiveData<HomeResponse> getBusinessPoliticalCategories(String s, String type) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getBusinessPoliticalCategories(s,type,activity);
    }

    public LiveData<FrameResponse> getUserFrames(String uid, String bid) {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getUserFrames(uid,bid,activity);
    }

    public LiveData<UserResponse> getAutomationFrames(String uid, String business, String categoryID, String bussiness_id) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getAutomationFrames(uid,business,categoryID,bussiness_id,activity);
    }

    public LiveData<UserResponse> getBusinessDetail(String uid, String bussiness_id) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getBusinessDetail(uid,bussiness_id,activity);
    }

    public LiveData<UserResponse> getUserCategory(String category_id) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getUserCategory(category_id,activity);
    }

    public LiveData<UserResponse> addUserBussiness(JSONObject parameters) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.addUserBussiness(parameters,activity);
    }

    public LiveData<HomeResponse> getCategoriesByPage(String type, String search, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getCategoriesByPage(type,search,pageCount,activity);
    }

    public LiveData<HomeResponse> getInvitationCardsByCatId(String toString) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getInvitationCardsByCatId(toString,activity);
    }

    public LiveData<FrameResponse> getFrames(String uid, String bid, String category_id, boolean animated) {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getFrames(uid,bid,category_id,animated,activity);
    }

    public LiveData<SimpleResponse> createGroup(RequestBody userid, RequestBody name, RequestBody description, RequestBody privacy, MultipartBody.Part fileBody) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.createGroup(userid,name,description,privacy,fileBody,activity);
    }

    public LiveData<HomeResponse> getGroups(String uid, String s) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getGroups(uid,s,activity);
    }

    public LiveData<HomeResponse> getGroupsDetails(String uid, String groupID) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getGroupsDetails(uid,groupID,activity);
    }

    public LiveData<SimpleResponse> addUserGroup(String groupID, String id) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.addUserGroup(groupID,id,activity);
    }

    public LiveData<SimpleResponse> updateGroupUser(String user_id, String action) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.updateGroupUser(user_id,action,activity);
    }

    public LiveData<SimpleResponse> deleteExiteGroup(String groupID, String uid, String action) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.deleteExiteGroup(groupID,uid,action,activity);
    }

    public LiveData<HomeResponse> getGroupsPosts(String uid, String language, String groupID, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getGroupsPosts(uid,language,groupID,pageCount,activity);
    }

    public LiveData<SimpleResponse> createBusinessCard(String uid, String bid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.createBusinessCard(uid,bid,activity);
    }

    public LiveData<UserResponse> getInquiriesData(String uid, String businessID) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getInquiriesData(uid,businessID,activity);
    }


    public LiveData<HomeResponse> getPostByPage(String type, String language, String postType, String item_id, String subcategory, String s, String selectePostID, int pageCount, String uid) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return  homeViewModel.getPostByPage(type, language, postType, item_id, subcategory, s, selectePostID, pageCount, uid,activity);
    }

    public LiveData<List<VideoTamplateModel>> getVideoTamplates(String id) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getVideoTamplates(id,activity);
    }

    public LiveData<HomeResponse> getSubscriptions() {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getSubscriptions(activity);
    }

    public LiveData<HomeResponse> cheakPromo(String code) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.cheakPromo(code,activity);
    }

    public LiveData<UserResponse> updateUserSubscription(String uid, String type, String selected_id, String tid, String promocode, String selected_price) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.updateUserSubscription(uid,type,selected_id,tid,promocode,selected_price,activity);
    }

    public LiveData<HomeResponse> getSearchData(String s) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getSearchData(s,activity);
    }

    public LiveData<List<String>> getSearchSuggestion(String s) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getSearchSuggestion(s,activity);
    }

    public LiveData<UserResponse> getTransactionRequest(String uid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getTransactionRequest(uid,activity);
    }

    public LiveData<UserResponse> getProductData(String uid, String businessID) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getProductData(uid,businessID,activity);
    }

    public LiveData<HomeResponse> getSearchUsers(String uid, String groupID, String query) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getSearchUsers(uid,groupID,query,activity);
    }

    public LiveData<HomeResponse> getBrandingData(String uid, String language, String cotegoryID, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getBrandingData(uid,language,cotegoryID,pageCount,activity);
    }

    public LiveData<HomeResponse> getMyBusinessData(String uid, String language, String categoryID, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getMyBusinessData(uid,language,categoryID,pageCount,activity);
    }

    public LiveData<HomeResponse> getCustomData(String uid, String language, String cotegoryID, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getCustomData(uid,language,cotegoryID,pageCount,activity);
    }

    public LiveData<HomeResponse> getDailyPosts(String id, String language, String s, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getDailyPosts(id,language,s,pageCount,activity);
    }

    public LiveData<HomeResponse> getHomeData(String uid, String language, String cotegoryID, int pageCount) {
        HomeViewModel homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
        return homeViewModel.getData(uid,language,cotegoryID,pageCount,activity);
    }

    public LiveData<FrameResponse> getLogos() {
        FrameViewModel frameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(FrameViewModel.class);
        return frameViewModel.getLogos(activity);
    }

    public LiveData<UserResponse> addInquiry(String uid, String id, String number, String msg) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.addInquiry(uid,id,number,msg,activity);
    }

    public LiveData<UserResponse> addContact(String uid, String number, String msg) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.addContact(uid,number,msg,activity);
    }

    public LiveData<UserResponse> getUserBusiness(String uid, String type) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getUserBusiness(uid,type,activity);
    }

    public LiveData<SimpleResponse> updateDefaultBusiness(String uid, String id) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.updateDefaultBusiness(uid,id,activity);
    }

    public LiveData<SimpleResponse> deleteUserBusiness(String id) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.deleteUserBusiness(id,activity);
    }

    public LiveData<UserResponse> getInvitedUser(String uid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getInvitedUser(uid,activity);
    }

    public LiveData<UserResponse> getWithdrawRequest(String uid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.getWithdrawRequest(uid,activity);
    }

    public LiveData<UserResponse> withdrawRequest(String uid, int balance, String upi) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.withdrawRequest(uid,balance,upi,activity);
    }

    public LiveData<UserResponse> offlineSubscription(String uid, String offline, String sid, String imagePath, String promocode, String price) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.offlineSubscription(uid,offline,sid,imagePath,promocode,price,activity);
    }

    public LiveData<SimpleResponse> deleteUserFrame(String user_fid) {
        UserViewModel userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        return userViewModel.deleteUserFrame(user_fid,activity);
    }


}
