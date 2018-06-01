<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Facebook Page Manager</title>

        <!-- CSS Spring tags -->
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
        <spring:url value="/resources/css/kendo.common-bootstrap.min.css" var="kendoCommonCss" />
        <spring:url value="/resources/css/kendo.bootstrap-v4.min.css" var="kendoCss" />
        <spring:url value="/resources/css/pagemanager.css" var="pageManagerCss" />
        
        <!-- JS Spring tags -->
        <spring:url value="resources/js/jquery-1.12.3.min.js" var="jQueryJs" />
        <spring:url value="resources/js/bootstrap.bundle.min.js" var="bootsrapJs" />
        <spring:url value="resources/js/kendo.ui.core.min.js" var="kendoJs" />
        <spring:url value="resources/js/moment.js" var="momentJs" />
        <spring:url value="resources/js/moment-timezone-with-data-2012-2022.min.js" var="momentTimezoneJs" />
        <spring:url value="resources/js/pagemanagerenums.js" var="pageManagerEnumsJs" />
        <spring:url value="resources/js/pagemanagersettings.js" var="pageManagerSettingsJs" />
        <spring:url value="resources/js/pagemanager.js" var="pageManagerJs" />
        
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
        <link rel="stylesheet" type="text/css" href="${kendoCommonCss}" />
        <link rel="stylesheet" type="text/css" href="${kendoCss}" />
        <link rel="stylesheet" type="text/css" href="${pageManagerCss}" />
        
        <!-- JS -->
        <script type="text/javascript" src="${jQueryJs}"></script>
        <script type="text/javascript" src="${bootstrapJs}"></script>
        <script type="text/javascript" src="${kendoJs}"></script>
        <script type="text/javascript" src="${momentJs}"></script>
        <script type="text/javascript" src="${momentTimezoneJs}"></script>
        <script type="text/javascript" src="${pageManagerEnumsJs}"></script>
        <script type="text/javascript" src="${pageManagerSettingsJs}"></script>
        <script type="text/javascript" src="${pageManagerJs}"></script>

    </head>
    <body>
        <!-- page content -->
        <div class="container">
            <div class="mb-4 mt-3 pb-2 page-header">
                <button id="writePost" type="button" class="btn btn-primary mt-2 write-post">New Post</button>
                <h1>Facebook Page Posts</h1>
            </div>
            <div class="row">
                <div class="col-12">
                    <div id="postsGrid"><!-- filled in by JS --></div>
                </div>
            </div>
        </div>

        <!-- new post popup window -->
        <div id="newPostWindow" style="display:none;">
            <div class="container-fluid">
                <br />
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="newPostMessage">Please enter your post content:</label>
                            <textarea id="newPostMessage" class="form-control"></textarea>
                            <br />
                            <input id="newPostIsPublished" type="checkbox" checked />
                            <label for="newPostIsPublished">Publish this post</label>
                        </div>
                        <div class="new-post-submit-button-wrapper">
                            <button id="newPostWindowSubmit" type="button" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </div>
    </body>
</html>


