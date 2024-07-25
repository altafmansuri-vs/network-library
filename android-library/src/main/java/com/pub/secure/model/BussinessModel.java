package com.pub.secure.model;

public class BussinessModel {

    private String id, user_id,company, name, about,designation, number, email, image,category_id,type, website, address, whatsapp, facebook,twitter, youtube, instagram, updated_at, created_at;

    private String time, auto_frame_type,auto_language;
    private  int  automation, auto_festival, auto_business, auto_frame_id;
    private  String cover,domain, web_template_id, webpage_status;


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeb_template_id() {
        return web_template_id;
    }

    public void setWeb_template_id(String web_template_id) {
        this.web_template_id = web_template_id;
    }

    public String getWebpage_status() {
        return webpage_status;
    }

    public void setWebpage_status(String webpage_status) {
        this.webpage_status = webpage_status;
    }

    private CategoryModel category;

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getWebsite() {
        return website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuto_frame_type() {
        return auto_frame_type;
    }

    public void setAuto_frame_type(String auto_frame_type) {
        this.auto_frame_type = auto_frame_type;
    }

    public int getAutomation() {
        return automation;
    }

    public void setAutomation(int automation) {
        this.automation = automation;
    }

    public int getAuto_festival() {
        return auto_festival;
    }

    public void setAuto_festival(int auto_festival) {
        this.auto_festival = auto_festival;
    }

    public int getAuto_business() {
        return auto_business;
    }

    public void setAuto_business(int auto_business) {
        this.auto_business = auto_business;
    }

    public int getAuto_frame_id() {
        return auto_frame_id;
    }

    public void setAuto_frame_id(int auto_frame_id) {
        this.auto_frame_id = auto_frame_id;
    }

    public String getAuto_language() {
        return auto_language;
    }

    public void setAuto_language(String auto_language) {
        this.auto_language = auto_language;
    }
}
