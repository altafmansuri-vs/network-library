package com.pub.secure.responses;

import com.pub.secure.model.BackgroundCategoryModel;
import com.pub.secure.model.BackgroundModel;
import com.pub.secure.model.FrameCategoryModel;
import com.pub.secure.model.FrameModel;
import com.pub.secure.model.MusicCategoryModel;
import com.pub.secure.model.MusicModel;
import com.pub.secure.model.StickerModel;
import com.pub.secure.model.StickerModelCategory;
import com.pub.secure.model.UserFrameModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FrameResponse {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("count")
    public String count;

    @SerializedName("demo-frames")
    public List<FrameModel> frames;

    @SerializedName("businessframes")
    public List<FrameModel> businessframes;

    @SerializedName("festivalframes")
    public List<FrameModel> festivalframes;

    @SerializedName("branindframes")
    public List<FrameModel> branindframes;

    @SerializedName("userframes")
    public List<UserFrameModel> userframes;

    @SerializedName("framecategories")
    public List<FrameCategoryModel> framecategories;

    @SerializedName("stickercategory")
    public List<StickerModelCategory> stickercategory;

    @SerializedName("stickers")
    public List<StickerModel> stickers;

    @SerializedName("musiccategories")
    public List<MusicCategoryModel> musiccategories;

    @SerializedName("musics")
    public List<MusicModel> musics;

    @SerializedName("logoscategory")
    public List<StickerModelCategory> logoscategory;

    @SerializedName("backgroundcategory")
    public List<BackgroundCategoryModel> backgroundcategory;

    @SerializedName("backgrounds")
    public List<BackgroundModel> backgrounds;

    public List<FrameModel> getFestivalframes() {
        return festivalframes;
    }

    public void setFestivalframes(List<FrameModel> festivalframes) {
        this.festivalframes = festivalframes;
    }

    public List<FrameModel> getBranindframes() {
        return branindframes;
    }

    public void setBranindframes(List<FrameModel> branindframes) {
        this.branindframes = branindframes;
    }

    public List<FrameModel> getBusinessframes() {
        return businessframes;
    }

    public void setBusinessframes(List<FrameModel> businessframes) {
        this.businessframes = businessframes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FrameModel> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameModel> frames) {
        this.frames = frames;
    }

    public List<UserFrameModel> getUserframes() {
        return userframes;
    }

    public void setUserframes(List<UserFrameModel> userframes) {
        this.userframes = userframes;
    }

    public List<FrameCategoryModel> getFramecategories() {
        return framecategories;
    }

    public void setFramecategories(List<FrameCategoryModel> framecategories) {
        this.framecategories = framecategories;
    }

    public List<StickerModelCategory> getStickercategory() {
        return stickercategory;
    }

    public void setStickercategory(List<StickerModelCategory> stickercategory) {
        this.stickercategory = stickercategory;
    }

    public List<StickerModelCategory> getLogoscategory() {
        return logoscategory;
    }

    public void setLogoscategory(List<StickerModelCategory> logoscategory) {
        this.logoscategory = logoscategory;
    }

    public List<MusicCategoryModel> getMusiccategories() {
        return musiccategories;
    }

    public void setMusiccategories(List<MusicCategoryModel> musiccategories) {
        this.musiccategories = musiccategories;
    }

    public List<BackgroundModel> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(List<BackgroundModel> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public List<StickerModel> getStickers() {
        return stickers;
    }

    public void setStickers(List<StickerModel> stickers) {
        this.stickers = stickers;
    }

    public List<MusicModel> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicModel> musics) {
        this.musics = musics;
    }

    public List<BackgroundCategoryModel> getBackgroundcategory() {
        return backgroundcategory;
    }

    public void setBackgroundcategory(List<BackgroundCategoryModel> backgroundcategory) {
        this.backgroundcategory = backgroundcategory;
    }
}
