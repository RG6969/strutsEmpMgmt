/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.JsonDataToDBService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hp
 */
public class Transcript extends ActionSupport implements ApplicationAware, SessionAware, Serializable{
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }
    private String url;
    private String sizeOfData;
    private String userId;
    private String id;
    private String title;
    private String completed;
    
    public String doFetchUrl() throws Exception {
        
        String result = "FAILURE";
        boolean dataEntry = false;
        
        if (this.url != null) {

            HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI(this.url)).build();

            //creating client object to send request
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            //to get body of response
            // System.out.println(postResponse.body());
            String textArea = postResponse.body();
            sessionMap.put("TextArea", textArea);

            Gson gson = new Gson();
            if ("1".equals(this.sizeOfData)) {
                Transcript transcript = gson.fromJson(postResponse.body(), Transcript.class);
                dataEntry = JsonDataToDBService.InsertJsonData(transcript);
            }
            
            if (!"1".equals(this.sizeOfData) && !"0".equals(this.sizeOfData)) {
                Transcript[] transcript = gson.fromJson(postResponse.body(), Transcript[].class);
                JsonDataToDBService.InsertJsonData(transcript);
            
            }
            // String jsonString = gson.toJson(transcript);
            // System.out.println("printing jsonString ="+jsonString);

//            dataEntry = JsonDataToDBService.InsertJsonData(transcript);
//            System.out.println("dataEntrySuccessful = " + dataEntry);
        }

        if (dataEntry) {
            result = "SUCCESS";
        }
        else {
            Logger log = Logger.getLogger(User.class.getName());
            log.error("returning Failure from Login method");
            System.out.println("returning Failure from doLogin method");
        }

        
        return result;
        
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the sizeOfData
     */
    public String getSizeOfData() {
        return sizeOfData;
    }

    /**
     * @param sizeOfData the sizeOfData to set
     */
    public void setSizeOfData(String sizeOfData) {
        this.sizeOfData = sizeOfData;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the completed
     */
    public String getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
