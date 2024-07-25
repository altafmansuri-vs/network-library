package com.pub.secure.model;

import java.util.List;

public class SectionModel {

    private String id, name, orders, type, value,content_type,background, style, status, updated_at, created_at;
    private List<SliderModel> sliderList;
    private List<FestivalCalenderModel> festivalCalenderList;
    private List<PostsModel> posts;
    private List<CategoryModel> categories;
    private List<CategoryModel> subcategories;
    private SubscriptionModel subscription;
    private List<VideoTamplateModel> videoTamplateList;
    private List<TamplateModel> visitingCardList;
    private List<BusinessCardModel> digitalCardList;
    private List<TemplateCategoryModel> templateCategoryList;
    public List<ServicesModel> servicesList;
    public List<InvitationModel> postTemplate;
    public List<FrameModel> frames;
    public List<UserFrameModel> userFrames;
    private CategoryModel category;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<UserFrameModel> getUserFrames() {
        return userFrames;
    }

    public void setUserFrames(List<UserFrameModel> userFrameModelList) {
        this.userFrames = userFrameModelList;
    }

    public List<FrameModel> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameModel> frames) {
        this.frames = frames;
    }

    public List<InvitationModel> getPostTemplate() {
        return postTemplate;
    }

    public void setPostTemplate(List<InvitationModel> postTemplate) {
        this.postTemplate = postTemplate;
    }

    public List<ServicesModel> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<ServicesModel> servicesList) {
        this.servicesList = servicesList;
    }

    public List<TemplateCategoryModel> getTemplateCategoryList() {
        return templateCategoryList;
    }

    public void setTemplateCategoryList(List<TemplateCategoryModel> templateCategoryList) {
        this.templateCategoryList = templateCategoryList;
    }

    public List<CategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryModel> subcategories) {
        this.subcategories = subcategories;
    }

    public List<BusinessCardModel> getDigitalCardList() {
        return digitalCardList;
    }

    public void setDigitalCardList(List<BusinessCardModel> digitalCardList) {
        this.digitalCardList = digitalCardList;
    }

    public List<TamplateModel> getVisitingCardList() {
        return visitingCardList;
    }

    public void setVisitingCardList(List<TamplateModel> visitingCardList) {
        this.visitingCardList = visitingCardList;
    }

    public List<VideoTamplateModel> getVideoTamplateList() {
        return videoTamplateList;
    }

    public void setVideoTamplateList(List<VideoTamplateModel> videoTamplateList) {
        this.videoTamplateList = videoTamplateList;
    }

    public List<SliderModel> getSliderList() {
        return sliderList;
    }

    public void setSliderList(List<SliderModel> sliderList) {
        this.sliderList = sliderList;
    }

    public List<FestivalCalenderModel> getFestivalCalenderList() {
        return festivalCalenderList;
    }

    public void setFestivalCalenderList(List<FestivalCalenderModel> festivalCalenderList) {
        this.festivalCalenderList = festivalCalenderList;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public SubscriptionModel getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionModel subscription) {
        this.subscription = subscription;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public List<PostsModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsModel> posts) {
        this.posts = posts;
    }
}
