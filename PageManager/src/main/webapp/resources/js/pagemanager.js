/**
 * The PageManager object provides all functionality for the page manager table and "New Post" window.
 */
var PageManager = {
    _accessToken: null,

    /**
     * Renders the page elements
     */
    render: function() {
        $("#writePost").on("click", $.proxy(this._newPost, this));

        this._accessToken = this._getAccessToken();
        this._renderNewPostWindow();
        this._renderPostsGrid();
    },

    /**
     * Sets up the "create new post" window.
     */
    _renderNewPostWindow: function() {
        $("#newPostWindow").kendoWindow({
            title: "New Post",
            width: 500,
            options: ["close"]
        });

        $("#newPostWindowSubmit").on("click", $.proxy(function() {
            var postContent = $("#newPostMessage").val(),
                isPublished = $("#newPostIsPublished").attr("checked") === true,

                callback = $.proxy(function() {
                    $("#newPostWindow").data("kendoWindow").close();
                    this._renderPostsGrid();
                }, this);

            this._createPost(this._accessToken, postContent, isPublished, callback);
        }, this));
    },

    /**
     * Opens the new post window when the "new post" button is clicked
     */
    _newPost: function() {
        $("#newPostWindow").data("kendoWindow").center();
        $("#newPostWindow").data("kendoWindow").open();
    },

    /**
     * Sets up the grid of existing posts
     */
    _renderPostsGrid: function() {
        var callback = function(data) {
            $("#postsGrid").empty();

            $("#postsGrid").html("" +
                "<table class='table table-striped table-bordered table-hover'>" +
                    "<thead class='posts-table-header'>" +
                        "<tr>" +
                            "<th>Post Content</th>" +
                            "<th>Date Posted</th>" +
                            "<th>Published</th>" +
                        "</tr>" +
                    "</thead>" +
                    "<tbody>" +
                        data.reduce(function(x, y) {
                            return x + "" +
                                "<tr>" +
                                    "<td>" + $("<div />").text(y.message).html() + "</td>" +
                                    "<td>" + $("<div />").text(y.datePosted).html() + "</td>" +
                                    "<td>" + (y.isPublished ? "Yes" : "No") + "</td>" +
                                "</tr>";
                        }, "") +
                    "</tbody>" +
                "</table>"
            );
        };

        this._readPost(this._accessToken, callback);
    },

    /**
     * Wrapper for getting an access token that redirects based on the chosen language
     */
    _getAccessToken: function() {
        var url;

        switch (PageManagerSettings.CodingLanguage) {
            case CodingLanguage.JavaScript:
                return PageManagerUtils.getAccessToken();
            case CodingLanguage.PHP:
                url = "getAccessToken.php";
                break;
            default:
                url = "/PageManager/GetAccessToken/";
                break;
        }

        // If we aren't using JS, make an AJAX call.
        return $.ajax({
            url: url,
            data: {},
            method: "GET",
            async: false
        }).responseText;
    },

    /**
     * Wrapper for creating a post that redirects based on the chosen language
     */
    _createPost: function(accessToken, postContent, isPublished, callback) {
        var url;

        switch (PageManagerSettings.CodingLanguage) {
            case CodingLanguage.JavaScript:
                PageManagerUtils.createPost(accessToken, postContent, isPublished, callback);
                return;
            default:
                url = "/PageManager/CreatePost/";
                break;
            case CodingLanguage.PHP:
                url = "createPost.php";
                break;
        }

        // If we aren't using JS, make an AJAX call.
        $.ajax({
            url: url,
            data: {
                accessToken: accessToken,
                postContent: postContent,
                isPublished: isPublished
            },
            method: "POST",
            success: callback,
        });
    },

    /**
     * Wrapper for reading posts that redirects based on the chosen language
     */
    _readPost: function(accessToken, callback) {
        switch (PageManagerSettings.CodingLanguage) {
            case CodingLanguage.JavaScript:
                PageManagerUtils.readPost(accessToken, callback);
                return;
            case CodingLanguage.PHP:
                $.ajax({
                    url: "/readPost.php",
                    data: {
                        accessToken: accessToken
                    },
                    method: "GET",
                    success: function(responseText) {
                        callback($.parseJSON(responseText));
                    },
                });
                break;
            default:
                $.ajax({
                    url: "/PageManager/ReadPost/",
                    data: {
                        accessToken: accessToken
                    },
                    method: "GET",
                    success: callback,
                });
                break;
        }
    }
};

/**
 * The below can be thought of like a "main" method
 */
$(document).ready(function() {
    // In Python/Django, we need to provide a CSRF token with each ajax call, which we obtain from the CSRF cookie.
    if (PageManagerSettings.CodingLanguage === CodingLanguage.Python) {
        var getCookie,
            isCsrfSafeMethod,
            csrfToken;

        /**
         * Helper function for getting a cookie of the given name
         */
        getCookie = function(name) {
            var cookieValue,
                cookies,
                cookie,
                i;

            cookieValue = null;
            if (document.cookie && document.cookie != "") {
                cookies = document.cookie.split(';');
                for (i = 0; i < cookies.length; i++) {
                    cookie = jQuery.trim(cookies[i]);

                    // Does this cookie string begin with the name we want?
                    if (cookie.substring(0, name.length + 1) == (name + '=')) {
                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                        break;
                    }
                }
            }
            return cookieValue;
        };

        /**
         * Helper function for determining if a method is already CSRF-enabled
         */
        isCsrfSafeMethod = function(method) {
            // these HTTP methods do not require CSRF protection
            return (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
        };


        // Set the X-CSRFToken header as necessary (particularly on AJAX requests)
        csrfToken = getCookie('csrftoken');
        $.ajaxSetup({
            beforeSend: function(xhr, settings) {
                if (!isCsrfSafeMethod(settings.type) && !this.crossDomain) {
                    // Send the token to same-origin, relative URLs only.
                    // Send the token only if the method warrants CSRF protection
                    // Using the CSRFToken value acquired earlier
                    xhr.setRequestHeader("X-CSRFToken", csrfToken);
                }
            }
        });
    }

    PageManager.render();
});
