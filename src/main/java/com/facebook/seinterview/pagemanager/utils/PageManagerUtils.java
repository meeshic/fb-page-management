package com.facebook.seinterview.pagemanager.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import io.github.cdimascio.dotenv.Dotenv;


public class PageManagerUtils {
    public static final String REDIRECT_URI = "https://fb-page-management.herokuapp.com/";
	public String pageId = "158282391689644";
	
    /**
     * Returns a page access token for use in hitting the Facebook APIs.
     */
    public static String getAccessToken()
    {
		String appId="";
		String appSecret="";
		if(new File("./.env").isFile()){
			Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();
			System.out.println(dotenv.get("APP_ID"));
			System.out.println(dotenv.get("APP_SECRET"));
			appId = dotenv.get("APP_ID");
			appSecret = dotenv.get("APP_SECRET");
		}
		else{
			appId = System.getenv("APP_ID");
			appSecret = System.getenv("APP_SECRET");
		}
		
		System.out.println("ID:"+appId);
		System.out.println("S:"+appSecret);
		//check login status
		/*
		//login request
		try{
			String baseURL = "https://www.facebook.com/v3.0/dialog/oauth?" + 
								"client_id=" + APP_ID +
								"&redirect_uri=" + REDIRECT_URI +
								"&scope={manage_pages,publish_pages}";
			URL url = new URL(URLEncoder.encode(baseURL, "UTF-8"));
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while((inputLine = br.readLine())!=null){
				response.append(inputLine);
			}br.close();

			connection.close();
			
			
			baseURL = "https://www.facebook.com/v3.0/dialog/oauth/access_token?" + 
						"grant_type=fb_exchange_token" +
						"&client_id=" + APP_ID +
						"&client_secret=" + APP_SECRET +
						"&fb_exchange_token=" + response.toString();
			url = new URL(URLEncoder.encode(baseURL, "UTF-8"));
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String extendedToken = new StringBuilder();

			while((inputLine = br.readLine())!=null){
				extendedToken.append(inputLine);
			}br.close();

			connection.close();
			
			baseURL = "https://www.facebook.com/v3.0/" + 
						"/page_id=" + "158282391689644" +
						"?fields=" + extendedToken.toString();
			url = new URL(URLEncoder.encode(baseURL, "UTF-8"));
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String extendedToken = new StringBuilder();
			
			
			/{page-id}?fields=access_token
			
			//oauth/access_token?grant_type=fb_exchange_token&client_id=388426784900461&client_secret=92b07932703c03c484c5b52811a55f90&fb_exchange_token=EAAFhRaiJBW0BAMTSCZA8M2rJUMtalHSAh14ZBkIVln4WuDfkAj0USsZCzsqICE5y7ZADAAj1fe9uf0vFXbtZBZANqIHEqGfZCCqH2qBwLO29iECmZChV0mLIhB9274aNE92ugCbX3MsIAee8jUzEXqVUIbfL33QROVwiRqzXRskgSaFZArK0kjEWq3oZCd6LC5GL8ZD 

			System.out.println("LOGIN RESPONSE:" + response);
		}catch (Exception e){
			e.printStackTrace();
		}
		*/

		return "EAAFhRaiJBW0BAADjRNSMJMqtC05KhDu0LxrKneFRr3M512pLb1QlKVIdqWkESq6AZCqYEJ6JWw0Oe5XnnP2SHcsDAjm36WFMAMqHigI5gxZBHN5PfJZAHAJ5nma78jPCXCoiU21ErQ4rlrCF7CZAzaDnw1tHAx5Vk8woxuT37wZDZD";
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
        try{
            URL url = new URL("https://graph.facebook.com/v3.0/158282391689644/feed"); 
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String urlParameters = "access_token="+accessToken+"&message="+postContent+"&published="+isPublished;
            byte[] postData = urlParameters.getBytes("UTF-8");
            int postDataLength = postData.length;

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData);
            wr.flush();
            wr.close();

            //OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            //out.write(urlParameters);
            //out.flush();
            //out.close();

            //connection.connect();

            int code = connection.getResponseCode();
            System.out.println("CODE FOR POST:" + code);
            System.out.println("The message is.."+postContent);
            System.out.println("published:" + isPublished);
        } catch(Exception e){
            e.printStackTrace();
        }
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
		
		List<PageManagerPagePost> result = new ArrayList<PageManagerPagePost>();
		

        try{
			//https://graph.facebook.com/158282391689644/promotable_posts?access_token={PAGE_TOKEN}
            URL url = new URL("https://graph.facebook.com/v3.0/158282391689644/feed?access_token="+accessToken); 
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while((inputLine = br.readLine())!=null){
                response.append(inputLine);
            }br.close();

            JSONParser jsonParse = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParse.parse(response.toString());
            JSONArray jsonArr = (JSONArray) jsonObj.get("data");

            for(int index=0; index<jsonArr.size();index++){
                JSONObject currPost = (JSONObject) jsonArr.get(index);
				if(currPost.get("message") == null)
					continue;
				String timeCreated = (String) currPost.get("created_time");
				String message = (String) currPost.get("message");
				String id = (String) currPost.get("id");
				PageManagerPagePost post = new PageManagerPagePost();
				post.setMessage(message);
				post.setDatePosted(timeCreated);
				post.setPublished(true);
				System.out.println("POST INFORMATION:" + timeCreated + ", " + message);
				
				result.add(post);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        
        /*PageManagerPagePost post = new PageManagerPagePost();
        post.setMessage("This is not a real post, just an example.");
		post.setDatePosted("1970-01-01 12:00:00");
		post.setPublished(true);
        
        result.add(post);*/
		
        return result;
    }
}