package com.facebook.seinterview.pagemanager.utils;

public class PageManagerPagePost {
    private String accessToken;
    private String message;
    private String datePosted;
    //private boolean isPublished;
	private boolean published;

    public String getAccessToken() {
        return accessToken;
    }
    public PageManagerPagePost setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    public String getMessage() {
		System.out.println("Getting message:" + message);
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
    /*public boolean isPublished() {
		System.out.println("I'm called! : " + isPublished);
        return isPublished;
    }
    public PageManagerPagePost setPublished(boolean isPublished) {
        this.isPublished = isPublished;
        return this;
    }
	*/
	
	public boolean isPublished() {
		System.out.println("I'm called! : " + published);
        return published;
    }
    public PageManagerPagePost setPublished(boolean published) {
        this.published = published;
        return this;
    }
}
