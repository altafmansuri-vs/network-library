package com.pub.secure.model;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

    private String id, name, about, image, language, event_date, type, status, updated_at, created_at;
    int category_id;

    private List<PostsModel> premiumposts;
    private List<PostsModel> posts;
    private List<InvitationModel> postTemplate;
    private List<FrameModel> frames;
    private List<VideoTamplateModel> templateCategoryList;


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public List<PostsModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsModel> posts) {
        this.posts = posts;
    }

    public List<InvitationModel> getPostTemplate() {
        return postTemplate;
    }

    public void setPostTemplate(List<InvitationModel> postTemplate) {
        this.postTemplate = postTemplate;
    }

    public List<FrameModel> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameModel> frames) {
        this.frames = frames;
    }

    public List<VideoTamplateModel> getTemplateCategoryList() {
        return templateCategoryList;
    }

    public void setTemplateCategoryList(List<VideoTamplateModel> templateCategoryList) {
        this.templateCategoryList = templateCategoryList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<PostsModel> getPremiumposts() {
        return premiumposts;
    }

    public void setPremiumposts(List<PostsModel> premiumposts) {
        this.premiumposts = premiumposts;
    }
}
