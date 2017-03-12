package com.example.rent.zulicywiesciapp.model;

import java.util.List;

/**
 * Created by md on 3/12/17.
 */
public class CategoryList {

    private List<Category> categories;

    public CategoryList(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
