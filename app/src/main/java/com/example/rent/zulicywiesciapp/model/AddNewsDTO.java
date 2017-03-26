package com.example.rent.zulicywiesciapp.model;

import java.util.List;

/**
 * Created by md on 3/26/17.
 */

public class AddNewsDTO {
    private String title;
    private String content;
    private Integer priority;
    private List<Integer> categories;

    public AddNewsDTO(String title, String content, Integer priority, List<Integer> categories) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
