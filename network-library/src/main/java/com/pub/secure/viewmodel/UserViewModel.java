package com.pub.secure.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.UserResponse;
import com.pub.secure.responses.WhatsappOtpResponse;
import com.pub.secure.respository.UserRespository;

import org.json.JSONException;
import org.json.JSONObject;

public class UserViewModel extends ViewModel {

    private UserRespository respository;

    public UserViewModel() {
        respository = new UserRespository();
    }

    public LiveData<UserResponse> getUserProfile(String uid, String bid, Activity activity) {
        return respository.getProfileData(uid,bid,activity);
    }

    public LiveData<UserResponse> updateProfilePic(String uid, String path) {
        return respository.updateProfilePic(uid, path);
    }

    public LiveData<UserResponse> getUserPosts(String uid) {
        return respository.getUserPosts(uid);
    }

    public LiveData<UserResponse> uploadUserPost(String uid, String path) {
        return respository.uploadUserPost(uid, path);
    }

    public LiveData<UserResponse> updateProfile(String uid, String name, String refercode, Activity activity) {
        return respository.updateProfile(uid, name, refercode,activity);
    }

    public LiveData<UserResponse> addContact(String uid, String number, String message, Activity activity) {
        return respository.addContact(uid, number, message,activity);
    }

    public LiveData<UserResponse> addInquiry(String uid, String service_id, String number, String message, Activity activity) {
        return respository.addInquiry(uid, service_id, number, message,activity);
    }

    public LiveData<UserResponse> getUserBusiness(String uid, String type, Activity activity) {
        return respository.getUserBusiness(uid, type,activity);
    }

    public LiveData<UserResponse> getBusinessDetail(String uid, String id, Activity activity) {
        return respository.getBusinessDetail(uid, id,activity);
    }

    public LiveData<UserResponse> addUserBussiness(JSONObject jsonObject, Activity activity) {
        try {
            return respository.addUserBussiness(jsonObject,activity);
        } catch (JSONException e) {
            Log.d("addUserBussiness___",e.getMessage());
            return null;
        }
    }

    public LiveData<UserResponse> updateUserSubscription(String uid, String type, String sid, String tid, String promocode, String amount, Activity activity) {
        return respository.updateSuscription(uid, type, sid, tid, promocode,amount,activity);
    }

    public LiveData<UserResponse> offlineSubscription(String uid, String type, String sid, String screenshot, String promocode, String amount, Activity activity) {
        return respository.offlineSuscription(uid, type, sid, screenshot, promocode, amount,activity);
    }

    public LiveData<UserResponse> getAutomationFrames(String uid, String type, String category_id, String bussiness_id, Activity activity) {
        return respository.getAutomationFrames(uid,type,category_id,bussiness_id,activity);
    }

    public LiveData<UserResponse> getInvitedUser(String uid, Activity activity) {
        return respository.getInvitedUser(uid,activity);
    }

    public LiveData<UserResponse> getWithdrawRequest(String uid, Activity activity) {
        return respository.getWithdrawRequest(uid,activity);
    }
    public LiveData<UserResponse> getTransactionRequest(String uid, Activity activity) {
        return respository.getTransactionRequest(uid,activity);
    }
    public LiveData<UserResponse> withdrawRequest(String uid, int balance, String s, Activity activity) {
        return respository.withdrawRequest(uid,balance,s,activity);
    }

    public LiveData<UserResponse> cheakReferCode(String code, Activity activity) {
        return respository.cheakReferCode(code,activity,activity);
    }

    public LiveData<UserResponse> getUserCategory(String category_id, Activity activity) {
        return respository.getUserCategory(category_id,activity);
    }

    public LiveData<UserResponse> login(JSONObject object, Activity activity) {
        try {
            return respository.login(object,activity,activity);
        } catch (JSONException e) {
            return null;
        }
    }

    public LiveData<WhatsappOtpResponse> createWhatsappOtp(String number, Activity activity) {
        return respository.createWhatsappOtp(number,activity,activity);
    }

    public LiveData<SimpleResponse> deleteUserAccount(String uid, Activity activity) {
        return respository.deleteUserAccount(uid,activity,activity);
    }

    public LiveData<SimpleResponse> createBusinessCard(String uid, String bid, Activity activity) {
        return respository.createBusinessCard(uid,bid,activity);
    }

    public LiveData<SimpleResponse> updateDefaultBusiness(String uid, String id, Activity activity) {
        return respository.updateDefaultBusiness(uid,id,activity);
    }

    public LiveData<SimpleResponse> deleteUserBusiness(String id, Activity activity) {
        return respository.deleteUserBusiness(id,activity);
    }

    public LiveData<SimpleResponse> deleteUserFrame(String user_fid, Activity activity) {
        return respository.deleteUserFrame(user_fid,activity);
    }
}
