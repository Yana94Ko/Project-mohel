package com.finalproject.mohel.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KakaoAPI {
	final String REST_API_KEY = "8299169b3aa46a93e89d0f3fe4ed0583";
	final String REDIRECT_URI = "http://localhost:8040/member/kakaologin"; 
	
	public String getAccessToken(String authorizeCode) {
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		String mothod = "POST";
		
		JSONObject tokenJSON = null;
		
		try {
			URL url = new URL(tokenUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(mothod);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			
            StringBuffer sb = new StringBuffer();
            sb.append("grant_type=authorization_code");
			sb.append("&client_id="+REST_API_KEY);
			sb.append("&redirect_uri="+REDIRECT_URI);
			sb.append("&code="+authorizeCode);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(sb.toString());
            bw.flush();
			
			int responseCode = con.getResponseCode();
			BufferedReader br = null;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
//			System.out.println(response.toString());
			tokenJSON = new JSONObject(response.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tokenJSON.getString("access_token");
	}
	
	public HashMap<String, String> getUserInfo(String accessToken) {
		String infoUrl = "https://kapi.kakao.com/v2/user/me";
		String method = "GET";
		
		HashMap<String, String> userInfo = new HashMap<String, String>();
		
		try {
			URL url = new URL(infoUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = con.getResponseCode();
			BufferedReader br = null;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			StringBuffer response = new StringBuffer();
			while(br.ready()) {
				response.append(br.readLine());
			}
			JSONObject userJSON = new JSONObject(response.toString());
			JSONObject profileJSON = (JSONObject)userJSON.get("properties");
			
			userInfo.put("id", userJSON.get("id").toString());
			userInfo.put("nickname", profileJSON.getString("nickname"));
			userInfo.put("profile", profileJSON.getString("profile_image"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}
}
