package com.pub.secure.responses;

import com.pub.secure.model.AutomationFrameModel;
import com.pub.secure.model.BussinessModel;
import com.pub.secure.model.CategoryModel;
import com.pub.secure.model.GalleryModel;
import com.pub.secure.model.InquiriesModel;
import com.pub.secure.model.ProductModel;
import com.pub.secure.model.TransactionModel;
import com.pub.secure.model.UserFrameModel;
import com.pub.secure.model.UserModel;
import com.pub.secure.model.UserPostModel;
import com.pub.secure.model.WebsiteTemplateModel;
import com.pub.secure.model.WithdrawModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("balance")
    public int balance;

    @SerializedName("total_withdraw")
    public int total_withdraw;

    @SerializedName("user")
    public UserModel userModel;

    @SerializedName("business")
    public BussinessModel business;

    @SerializedName("businesses")
    public List<BussinessModel> businesses;

    @SerializedName("userframes")
    public List<UserFrameModel> userframes;

    @SerializedName("userposts")
    public List<UserPostModel> userposts;

    @SerializedName("usercategory")
    public CategoryModel usercategory;

    @SerializedName("userslist")
    public List<UserModel> userslist;

    @SerializedName("withdrawlist")
    public List<WithdrawModel> withdrawlist;

    @SerializedName("transactionlist")
    public List<TransactionModel> transactionlist;

    @SerializedName("automationframes")
    public List<AutomationFrameModel> automationframes;

    @SerializedName("products")
    public List<ProductModel> products;

    @SerializedName("inquiries")
    public int inquiries;

    @SerializedName("product_inquiries")
    public int product_inquiries;

    @SerializedName("website_template")
    public WebsiteTemplateModel website_template;

    @SerializedName("gallery_images")
    public List<GalleryModel> gallery_images;

    @SerializedName("website_templates")
    public List<WebsiteTemplateModel> website_templates;

    @SerializedName("inquiries-list")
    public List<InquiriesModel> inquiriesList;

    public List<InquiriesModel> getInquiriesList() {
        return inquiriesList;
    }

    public void setInquiriesList(List<InquiriesModel> inquiriesList) {
        this.inquiriesList = inquiriesList;
    }

    public List<WebsiteTemplateModel> getWebsite_templates() {
        return website_templates;
    }

    public void setWebsite_templates(List<WebsiteTemplateModel> website_templates) {
        this.website_templates = website_templates;
    }

    public List<GalleryModel> getGallery_images() {
        return gallery_images;
    }

    public void setGallery_images(List<GalleryModel> gallery_images) {
        this.gallery_images = gallery_images;
    }

    public WebsiteTemplateModel getWebsite_template() {
        return website_template;
    }

    public void setWebsite_template(WebsiteTemplateModel website_template) {
        this.website_template = website_template;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public int getInquiries() {
        return inquiries;
    }

    public void setInquiries(int inquiries) {
        this.inquiries = inquiries;
    }

    public int getProduct_inquiries() {
        return product_inquiries;
    }

    public void setProduct_inquiries(int product_inquiries) {
        this.product_inquiries = product_inquiries;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTotal_withdraw() {
        return total_withdraw;
    }

    public void setTotal_withdraw(int total_withdraw) {
        this.total_withdraw = total_withdraw;
    }

    public List<BussinessModel> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BussinessModel> businesses) {
        this.businesses = businesses;
    }

    public BussinessModel getBusiness() {
        return business;
    }

    public void setBusiness(BussinessModel business) {
        this.business = business;
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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<UserFrameModel> getUserframes() {
        return userframes;
    }

    public void setUserframes(List<UserFrameModel> userframes) {
        this.userframes = userframes;
    }

    public List<UserPostModel> getUserposts() {
        return userposts;
    }

    public void setUserposts(List<UserPostModel> userposts) {
        this.userposts = userposts;
    }

    public CategoryModel getUsercategory() {
        return usercategory;
    }

    public void setUsercategory(CategoryModel usercategory) {
        this.usercategory = usercategory;
    }

    public List<UserModel> getUserslist() {
        return userslist;
    }

    public void setUserslist(List<UserModel> userslist) {
        this.userslist = userslist;
    }

    public List<WithdrawModel> getWithdrawlist() {
        return withdrawlist;
    }

    public void setWithdrawlist(List<WithdrawModel> withdrawlist) {
        this.withdrawlist = withdrawlist;
    }

    public List<TransactionModel> getTransactionlist() {
        return transactionlist;
    }

    public void setTransactionlist(List<TransactionModel> transactionlist) {
        this.transactionlist = transactionlist;
    }

    public List<AutomationFrameModel> getAutomationframes() {
        return automationframes;
    }

    public void setAutomationframes(List<AutomationFrameModel> automationframes) {
        this.automationframes = automationframes;
    }
}
