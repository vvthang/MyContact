/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.common;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvthang.mycontact.DAO.TokenRequest;
import com.vvthang.mycontact.DAO.TokenResponse;


/**
 * The Class Utils.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Utils {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    
    /** The Constant mapper. */
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Parses the object to json.
     *
     * @param object the object
     * @return the json string
     */
    public static String parseObjectToJson(Object object) {
        logger.debug("IN - parseObjectToJson");
        if (object == null) {
            logger.debug("OUT - parseObjectToJson");
            return null;
        }
        String json = null;
        try {
            logger.debug("OUT - parseObjectToJson");
            mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.info("Exception: ", e);
        }
        return json;
    }
    
    
    
    // =====================TEST RUNDECK =============================
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        // Authentication Username and Password
        authenticationUserNamePassword();
        
          // Create token
           createToken();
        
        // Get list project
          getListProjects();
        
    }
    
    private static String cookie = "";
    private static final String ENDPOINT = "http://192.168.74.145:4440";
    private static String token = "";
    
    // Get list projects
    private static void getListProjects(){
        System.out.println("==> getListProjects");
        
        HttpHeaders hd = setTockenToHeader();
        
        String url = "http://192.168.74.145:4440/api/13/projects";
        ResponseEntity<String> response = (ResponseEntity<String>) doSendRequestWithData(url, HttpMethod.GET, hd, null, null, null, String.class);
        System.out.println("getListProjects - Response = " + response.toString());
    }
    
    
    private static void authenticationUserNamePassword(){
        System.out.println("==> authenticationUserNamePassword");
        String url = "http://192.168.74.145:4440/j_security_check?j_username=admin&j_password=admin";
        @SuppressWarnings("unchecked")
        ResponseEntity<String> response = (ResponseEntity<String>) doSendRequestWithData(url, HttpMethod.POST, null, null, null, null, String.class);
        cookie = response.getHeaders().get("Set-Cookie").get(0);
        System.out.println("authenticationUserNamePassword - Cookie = " + cookie);
        System.out.println("authenticationUserNamePassword - Response = " + response.toString());
    }
    
    @SuppressWarnings("unchecked")
    private static String createToken(){
        System.out.println("==> createToken");
        String url = "http://192.168.74.145:4440/api/13/tokens";
        HttpHeaders hd = setCookieToHeader();
        
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUser("vvthang1");
        tokenRequest.setRoles(new String[]{"sre", "dev"});
        tokenRequest.setDuration("120d");
        
        ResponseEntity<TokenResponse> response = (ResponseEntity<TokenResponse>) doSendRequestWithData(url, HttpMethod.POST, hd, null, null, tokenRequest, TokenResponse.class);
        token = response.getBody().getId();
        
        System.out.println("createToken - Response = " + response.toString());
        return token;
        
        
    }
    
    // Set cookie to HttpHeader for CreateTocken
    private static HttpHeaders setCookieToHeader(){
        System.out.println("==> setCookieToHeader");
        HttpHeaders hd = new HttpHeaders();
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", cookie);
        hd.setAll(header);
        hd.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println("setCookieToHeader - Cookie = " + cookie);
        return hd;
    }
    
    // Set token to Header
    private static HttpHeaders setTockenToHeader(){
        System.out.println("==> setTockenToHeader");
        HttpHeaders hd = new HttpHeaders();
        hd.setContentType(MediaType.APPLICATION_JSON_UTF8);
        hd.set("X-Rundeck-Auth-Token", token);
        System.out.println("setTockenToHeader - Token = " + token);
        return hd;
    }
    
    private static ResponseEntity<?> doSendRequestWithData(String uri, HttpMethod method, HttpHeaders hd, Map<String, String> pathParams,
            Map<String, String[]> queryParams, Object bodyData, Class<?> T) {

        RestTemplate restTemplate = new RestTemplate();
        if (pathParams != null && !pathParams.isEmpty()) {
            for (Map.Entry<String, ?> entry : pathParams.entrySet()) {
                uri += "{" + entry.getKey() + "}/";
            }
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        if (queryParams != null && !queryParams.isEmpty()) {
            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                builder = builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        ResponseEntity<?> respResult = null;

        try {
            HttpEntity<?> entity = null;
            String jsonData = null;
            if (bodyData != null) {
                if (bodyData instanceof String) {
                    jsonData = bodyData.toString();
                } else {
                    jsonData = parseObjectToJson(bodyData);
                }
                entity = new HttpEntity<>(jsonData, hd);
                System.out.println("doSendRequestWithData - Body = " + jsonData);
            } else {
                entity = new HttpEntity<>(hd);
            }

            URI sendUri;
            if (pathParams != null && !pathParams.isEmpty()) {
                sendUri = builder.buildAndExpand(pathParams).encode().toUri();
            } else {
                sendUri = builder.build().encode().toUri();
            }

            if (T == null) {
                if (bodyData != null) {
                    T = bodyData.getClass();
                } else {
                    T = Void.class;
                }
            }

            logger.debug("doSendRequestWithData with URI: {}", sendUri);
            respResult = restTemplate.exchange(sendUri, method, entity, T);
        }  catch (Exception e) {
            respResult = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println("SEND REQUEST ERROR." + e);
        }
        return respResult;
    }
    
    
}
