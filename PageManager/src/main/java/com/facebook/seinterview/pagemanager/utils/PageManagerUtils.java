package com.facebook.seinterview.pagemanager.utils;

import java.util.ArrayList;
import java.util.List;

public class PageManagerUtils {
    /**
     * Returns a page access token for use in hitting the Facebook APIs.
     */
    public static String getAccessToken()
    {
        // TODO Return a real access token
        return "";
    }

    /**
     * Makes a call to the Facebook APIs to create a page post, and calls the provided callback function
     * when the API call is complete.
     *
     * Note that the callback function expects no arguments.
     *
     * Parameters:
     *   accessToken: The Facebook access token for use in querying the API
     *   postContent: A string representing the page post's content.
     *   isPublished: A boolean stating whether this is supposed to be a published post.
     *
     * This function should not return anything.
     */
    public static void createPost(
        String accessToken,
        String postContent,
        boolean isPublished)
    {
        // TODO Actually call the Facebook API
    }

    /**
     * Makes a call to the Facebook APIs to read all posts and unpublished posts from a page, and calls the provided
     * callback function when the API call is complete.
     *
     * Note that the callback function expects an array of objects in the following format:
     * { message: "a message", datePosted: "2017-08-26 11:36:00", isPublished: false }
     * (The date format doesn't need to match exactly but should include the date and time.)
     *
     * Parameters:
     *   accessToken: The Facebook access token for use in querying the API
     *
     * This function should not return anything.
     */
    public static List<PageManagerPagePost> readPost(String accessToken)
    {
        // TODO Actually call the Facebook API
        List<PageManagerPagePost> result = new ArrayList<>();
        
        PageManagerPagePost post = new PageManagerPagePost()
            .setMessage("This is not a real post, just an example.")
            .setDatePosted("1970-01-01 12:00:00")
            .setPublished(true);

        result.add(post);
        return result;
    }
}
