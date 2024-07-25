package com.pub.secure.model;

import java.io.Serializable;

public class VideoTamplateModel implements Serializable {

    public String id, title,zip_link,code,video_url,image, zip_path_preview,template_json, language, category_id, status, premium, updated_at, created_at;
    public String tamplate_type;
    public String frame_type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFrame_type() {
        return frame_type;
    }

    public void setFrame_type(String frame_type) {
        this.frame_type = frame_type;
    }

    public String getTamplate_type() {
        return tamplate_type;
    }

    public void setTamplate_type(String tamplate_type) {
        this.tamplate_type = tamplate_type;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getZip_link() {
        return zip_link;
    }

    public void setZip_link(String zip_link) {
        this.zip_link = zip_link;
    }

    public String getZip_path_preview() {
        return zip_path_preview;
    }

    public void setZip_path_preview(String zip_path_preview) {
        this.zip_path_preview = zip_path_preview;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTemplate_json() {
        return template_json;
    }

    public void setTemplate_json(String template_json) {
        this.template_json = template_json;
    }
}
