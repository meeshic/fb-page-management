package com.facebook.seinterview.pagemanager.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.facebook.seinterview.pagemanager.utils.PageManagerPagePost;
import com.facebook.seinterview.pagemanager.utils.PageManagerUtils;

@Controller
public class PageManagerController {	

    @ResponseBody
    @RequestMapping(value = "/PageManager/GetAccessToken", method = RequestMethod.GET)
    public String getAccessToken(HttpServletRequest request) {
        return PageManagerUtils.getAccessToken();
    }

    @ResponseBody
    @RequestMapping(value = "/PageManager/CreatePost", method = RequestMethod.POST)
    public void createPost(HttpServletRequest request) {
        String accessToken = request.getParameter("accessToken");
        String postContent = request.getParameter("postContent");
        boolean isPublished = request.getParameter("isPublished") == "true";

        PageManagerUtils.createPost(accessToken, postContent, isPublished);
    }

    @RequestMapping(value = "/PageManager/ReadPost", method = RequestMethod.GET)
    public @ResponseBody List<PageManagerPagePost> readPost(HttpServletRequest request) {
        String accessToken = request.getParameter("accessToken");

        return PageManagerUtils.readPost(accessToken);
    }
}