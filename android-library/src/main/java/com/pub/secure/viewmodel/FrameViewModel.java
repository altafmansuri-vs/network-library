package com.pub.secure.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pub.secure.responses.FrameResponse;
import com.pub.secure.respository.FrameRespository;

public class FrameViewModel extends ViewModel {

    private FrameRespository respository;

    public FrameViewModel(){
        respository = new FrameRespository();
    }

    public LiveData<FrameResponse> getFrames(String uid, String bid, String cid, boolean animated, Activity activity){
        return respository.getFrames(uid,bid,cid,animated,activity);
    }

    public LiveData<FrameResponse> getUserFrames(String user_id, String bid, Activity activity){
        return respository.getUserFrames(user_id,bid,activity);
    }

    public LiveData<FrameResponse> getStickers(String search, Activity activity){
        return respository.getStickers(search,activity);
    }

    public LiveData<FrameResponse> getLogos(Activity activity){
        return respository.getLogos(activity);
    }

    public LiveData<FrameResponse> getMusic(String search, Activity activity){
        return respository.getMusicByCategory(search,activity);
    }

    public LiveData<FrameResponse> getBackgrounds(String search, Activity activity){
        return respository.getBackgrounds(search,activity);
    }
}
