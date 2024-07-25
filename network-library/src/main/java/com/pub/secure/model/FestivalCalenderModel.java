package com.pub.secure.model;

import java.util.List;

public class FestivalCalenderModel {

    String event_date;
    List<CategoryModel> categories;

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }
}
