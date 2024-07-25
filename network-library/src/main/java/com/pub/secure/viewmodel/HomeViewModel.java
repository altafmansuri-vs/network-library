package com.pub.secure.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pub.secure.model.VideoTamplateModel;
import com.pub.secure.responses.HomeResponse;
import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.UserResponse;
import com.pub.secure.respository.HomeRespository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomeViewModel extends ViewModel {

    private HomeRespository respository;

    public HomeViewModel() {
        respository = new HomeRespository();
    }

    public LiveData<HomeResponse> getData(String uid, String language, String category, int pageCount, Activity activity) {
        return respository.getData(uid,language,category,pageCount,activity);
    }

    public LiveData<HomeResponse> cheakPromo(String promo, Activity activity) {
        return respository.cheakPromo(promo,activity);
    }

    public LiveData<HomeResponse> getMyBusinessData(String uid, String language, String businessID, int pagecount, Activity activity) {
        return respository.getMyBusinessData(language,uid,businessID,pagecount,activity);
    }

    public LiveData<HomeResponse> getPremiumPostByCategory(String type) {
        return respository.getPremiumPostByCategory(type);
    }

    public LiveData<HomeResponse> getSubscriptions(Activity activity) {
        return respository.getSubscriptions(activity);
    }

    public LiveData<HomeResponse> getPostByPage(String type, String language, String post_type, String item_id, String subcategory, String search, String postid, int page, String uid, Activity activity) {
        return respository.getPostByPage(type,language,post_type,item_id,subcategory,search,postid,page,uid,activity);
    }

    public LiveData<HomeResponse> getCategoriesByPage(String type, String search, int page, Activity activity) {
        return respository.getCategoriesByPage(type,search,page,activity);
    }

    public LiveData<HomeResponse> getVideoTamplateCategoriesByPage(String type,String search,int page) {
        return respository.getVideoTamplateCategoriesByPage(type,search,page);
    }

    public LiveData<HomeResponse> getDailyPosts(String search, String language, String param, int pageCount, Activity activity) {
        return respository.getDailyPosts(search,language,param,pageCount,activity);
    }

    public LiveData<HomeResponse> getBusinessPoliticalCategories(String search, String type, Activity activity) {
        return respository.getBusinessPoliticalCategory(search,type,activity);
    }

    public LiveData<HomeResponse> getBusinessCards() {
        return respository.getBusinessCards();
    }

    public LiveData<HomeResponse> getServices() {
        return respository.getServices();
    }

    public LiveData<HomeResponse> getInvitationCategories(String query) {
        return respository.getInvitationCategories(query);
    }

    public LiveData<HomeResponse> getInvitationCardsByCatId(String id, Activity activity) {
        return respository.getInvitationCardsByCatId(id,activity);
    }

    public LiveData<List<VideoTamplateModel>> getVideoTamplates(String id, Activity activity) {
        return respository.getVideoTamplates(id,activity);
    }

    public LiveData<HomeResponse> getBrandingData(String uid, String language, String category, int pagecount, Activity activity) {
        return respository.getBrandingData(language,uid,category,pagecount,activity);
    }
    public LiveData<HomeResponse> getCustomData(String uid, String language, String category, int pagecount, Activity activity) {
        return respository.getCustomData(language,uid,category,pagecount,activity);
    }
    public LiveData<HomeResponse> getSearchData(String s, Activity activity) {
        return respository.getSearchData(s,activity);
    }

    public LiveData<HomeResponse> getGroups(String user_id, String search, Activity activity) {
        return respository.getGroups(user_id,search,activity);
    }

    public LiveData<HomeResponse> getGroupsDetails(String user_id, String gid, Activity activity) {
        return respository.getGroupsDetails(user_id, gid,activity);
    }

    public LiveData<HomeResponse> getGroupsPosts(String uid, String language, String gid, int pagecount, Activity activity) {
        return respository.getGroupsPosts(language,uid,gid,pagecount,activity);
    }

    public LiveData<HomeResponse> getSearchUsers(String uid, String groupID, String query, Activity activity) {
        return respository.getSearchUsers(uid,groupID,query,activity);
    }


    public LiveData<UserResponse> getProductData(String uid, String businessID, Activity activity) {
        return respository.getProductData(uid,businessID,activity);
    }

    public LiveData<UserResponse> getInquiriesData(String uid, String businessID, Activity activity) {
        return respository.getInquiriesData(uid,businessID,activity);
    }

    public LiveData<SimpleResponse> updateGroupUser(String user_id, String action, Activity activity) {
        return respository.updateGroupUser(user_id,action,activity);
    }

    public LiveData<SimpleResponse> deleteExiteGroup(String groupID, String uid, String action, Activity activity) {
        return respository.deleteExiteGroup(groupID,uid,action,activity);
    }

    public LiveData<SimpleResponse> addUserGroup(String groupID, String id, Activity activity) {
        return respository.addUserGroup(groupID,id,activity);
    }

    public LiveData<List<String>> getSearchSuggestion(String s, Activity activity) {
        return respository.getSearchSuggestion(s,activity);
    }

    public LiveData<SimpleResponse> createGroup(RequestBody userid, RequestBody name, RequestBody description, RequestBody privacy, MultipartBody.Part fileBody, Activity activity) {
        return respository.createGroup(userid,name,description,privacy,fileBody,activity);
    }
}
