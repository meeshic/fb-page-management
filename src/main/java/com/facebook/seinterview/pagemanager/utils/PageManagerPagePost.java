package com.facebook.seinterview.pagemanager.utils;

public class PageManagerPagePost {
    private String accessToken;
    private String message;
    private String datePosted;
    private boolean isPublished;

    public String getAccessToken() {
        return accessToken;
    }
    public PageManagerPagePost setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public PageManagerPagePost setMessage(String message) {
        this.message = message;
        return this;
    }
    public String getDatePosted() {
        return datePosted;
    }
    public PageManagerPagePost setDatePosted(String datePosted) {
        this.datePosted = datePosted;
        return this;
    }
    public boolean isPublished() {
        return isPublished;
    }
    public PageManagerPagePost setPublished(boolean isPublished) {
        this.isPublished = isPublished;
        return this;
    }
}
