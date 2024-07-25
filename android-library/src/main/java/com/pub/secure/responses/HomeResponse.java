package com.pub.secure.responses;

import com.pub.secure.model.BusinessCardModel;
import com.pub.secure.model.CategoryModel;
import com.pub.secure.model.FestivalCalenderModel;
import com.pub.secure.model.GroupPostsModel;
import com.pub.secure.model.GroupsModel;
import com.pub.secure.model.InvitationModel;
import com.pub.secure.model.OfferDialogModel;
import com.pub.secure.model.PostsModel;
import com.pub.secure.model.PromocodeModel;
import com.pub.secure.model.SectionModel;
import com.pub.secure.model.ServicesModel;
import com.pub.secure.model.SliderModel;
import com.pub.secure.model.SubscriptionModel;
import com.pub.secure.model.TamplateModel;
import com.pub.secure.model.TemplateCategoryModel;
import com.pub.secure.model.UserFrameModel;
import com.pub.secure.model.UserModel;
import com.pub.secure.model.VideoTamplateCategory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("slider")
    public List<SliderModel> sliderdata;

    @SerializedName("dynamicsection")
    public List<SectionModel> dynamicsection;

    @SerializedName("section")
    public List<SectionModel> section;

    @SerializedName("upcoming_event")
    public List<CategoryModel> upcoming_event;

    @SerializedName("subcategories")
    public List<CategoryModel> subcategories;

    @SerializedName("festival_category")
    public List<CategoryModel> festival_category;

    @SerializedName("video_tamplate_categories")
    public List<VideoTamplateCategory> video_tamplate_categories;

    @SerializedName("business_category")
    public List<CategoryModel> business_category;

    @SerializedName("political_category")
    public List<CategoryModel> political_category;

    @SerializedName("custom_category")
    public List<CategoryModel> custom_category;

    @SerializedName("daily_post")
    public List<PostsModel> daily_post;

    @SerializedName("business_political_category")
    public List<CategoryModel> business_political_category;

    @SerializedName("recent")
    public List<PostsModel> recent;

    @SerializedName("greeting_section")
    public List<SectionModel> greeting_section;

    @SerializedName("subscriptions")
    public List<SubscriptionModel> subscriptions;

    @SerializedName("categories")
    public List<CategoryModel> categories;

    @SerializedName("offerdialog")
    public OfferDialogModel offerdialog;

    @SerializedName("posts")
    public List<PostsModel> posts;

    @SerializedName("promocode")
    public PromocodeModel promocode;

    @SerializedName("foryou")
    public List<PostsModel> foryou;

    @SerializedName("businesscardtamplate")
    public List<TamplateModel> businesscardtamplate;

    @SerializedName("businesscarddigital")
    public List<BusinessCardModel> businesscarddigital;

    @SerializedName("services")
    public List<ServicesModel> services;

    @SerializedName("invitationcategories")
    public List<TemplateCategoryModel> invitationcategories;

    @SerializedName("customTamplateCategory")
    public List<TemplateCategoryModel> customTamplateCategory;

    @SerializedName("customTamplates")
    public List<InvitationModel> customTamplates;

    @SerializedName("festivalCalendar")
    public List<FestivalCalenderModel> festivalCalendar;

    @SerializedName("groups")
    public List<GroupsModel> groups;

    @SerializedName("groupdetail")
    public GroupsModel groupdetail;

    @SerializedName("groupposts")
    public List<GroupPostsModel> groupposts;

    @SerializedName("users")
    public List<UserModel> users;

    @SerializedName("userFrames")
    public List<UserFrameModel> userFrames;

    public List<UserFrameModel> getUserFrameList() {
        return userFrames;
    }

    public void setUserFrameList(List<UserFrameModel> userFrameList) {
        this.userFrames = userFrameList;
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

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public GroupsModel getGroupdetail() {
        return groupdetail;
    }

    public void setGroupdetail(GroupsModel groupdetail) {
        this.groupdetail = groupdetail;
    }

    public List<GroupsModel> getGroups() {
        return groups;
    }

    public List<GroupPostsModel> getGroupposts() {
        return groupposts;
    }

    public void setGroupposts(List<GroupPostsModel> groupposts) {
        this.groupposts = groupposts;
    }

    public void setGroups(List<GroupsModel> groups) {
        this.groups = groups;
    }

    public List<SliderModel> getSliderdata() {
        return sliderdata;
    }

    public void setSliderdata(List<SliderModel> sliderdata) {
        this.sliderdata = sliderdata;
    }

    public List<SectionModel> getDynamicsection() {
        return dynamicsection;
    }

    public void setDynamicsection(List<SectionModel> dynamicsection) {
        this.dynamicsection = dynamicsection;
    }

    public List<FestivalCalenderModel> getFestivalCalendar() {
        return festivalCalendar;
    }

    public void setFestivalCalendar(List<FestivalCalenderModel> festivalCalendar) {
        this.festivalCalendar = festivalCalendar;
    }

    public List<SectionModel> getSection() {
        return section;
    }

    public void setSection(List<SectionModel> section) {
        this.section = section;
    }

    public List<CategoryModel> getUpcoming_event() {
        return upcoming_event;
    }

    public List<CategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryModel> subcategories) {
        this.subcategories = subcategories;
    }

    public void setUpcoming_event(List<CategoryModel> upcoming_event) {
        this.upcoming_event = upcoming_event;
    }

    public List<CategoryModel> getFestival_category() {
        return festival_category;
    }

    public void setFestival_category(List<CategoryModel> festival_category) {
        this.festival_category = festival_category;
    }

    public List<CategoryModel> getBusiness_category() {
        return business_category;
    }

    public void setBusiness_category(List<CategoryModel> business_category) {
        this.business_category = business_category;
    }

    public List<CategoryModel> getPolitical_category() {
        return political_category;
    }

    public void setPolitical_category(List<CategoryModel> political_category) {
        this.political_category = political_category;
    }

    public List<CategoryModel> getCustom_category() {
        return custom_category;
    }

    public void setCustom_category(List<CategoryModel> custom_category) {
        this.custom_category = custom_category;
    }

    public List<PostsModel> getDaily_post() {
        return daily_post;
    }

    public void setDaily_post(List<PostsModel> daily_post) {
        this.daily_post = daily_post;
    }

    public List<CategoryModel> getBusiness_political_category() {
        return business_political_category;
    }

    public void setBusiness_political_category(List<CategoryModel> business_political_category) {
        this.business_political_category = business_political_category;
    }

    public List<PostsModel> getRecent() {
        return recent;
    }

    public void setRecent(List<PostsModel> recent) {
        this.recent = recent;
    }

    public List<SectionModel> getGreeting_section() {
        return greeting_section;
    }

    public void setGreeting_section(List<SectionModel> greeting_section) {
        this.greeting_section = greeting_section;
    }

    public List<SubscriptionModel> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionModel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<PostsModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsModel> posts) {
        this.posts = posts;
    }

    public OfferDialogModel getOfferdialog() {
        return offerdialog;
    }

    public void setOfferdialog(OfferDialogModel offerdialog) {
        this.offerdialog = offerdialog;
    }

    public PromocodeModel getPromocode() {
        return promocode;
    }

    public void setPromocode(PromocodeModel promocode) {
        this.promocode = promocode;
    }

    public List<PostsModel> getForyou() {
        return foryou;
    }

    public void setForyou(List<PostsModel> foryou) {
        this.foryou = foryou;
    }

    public List<TamplateModel> getBusinesscardtamplate() {
        return businesscardtamplate;
    }

    public void setBusinesscardtamplate(List<TamplateModel> businesscardtamplate) {
        this.businesscardtamplate = businesscardtamplate;
    }

    public List<BusinessCardModel> getBusinesscarddigital() {
        return businesscarddigital;
    }

    public void setBusinesscarddigital(List<BusinessCardModel> businesscarddigital) {
        this.businesscarddigital = businesscarddigital;
    }

    public List<VideoTamplateCategory> getVideo_tamplate_category() {
        return video_tamplate_categories;
    }

    public void setVideo_tamplate_category(List<VideoTamplateCategory> video_tamplate_category) {
        this.video_tamplate_categories = video_tamplate_category;
    }

    public List<ServicesModel> getServices() {
        return services;
    }

    public void setServices(List<ServicesModel> services) {
        this.services = services;
    }

    public List<TemplateCategoryModel> getInvitationcategories() {
        return invitationcategories;
    }

    public void setInvitationcategories(List<TemplateCategoryModel> invitationcategories) {
        this.invitationcategories = invitationcategories;
    }

    public List<TemplateCategoryModel> getCustomTamplateCategory() {
        return customTamplateCategory;
    }

    public void setCustomTamplateCategory(List<TemplateCategoryModel> customTamplateCategory) {
        this.customTamplateCategory = customTamplateCategory;
    }

    public List<InvitationModel> getCustomTamplates() {
        return customTamplates;
    }

    public void setCustomTamplates(List<InvitationModel> customTamplates) {
        this.customTamplates = customTamplates;
    }

    public List<VideoTamplateCategory> getVideo_tamplate_categories() {
        return video_tamplate_categories;
    }

    public void setVideo_tamplate_categories(List<VideoTamplateCategory> video_tamplate_categories) {
        this.video_tamplate_categories = video_tamplate_categories;
    }
}
