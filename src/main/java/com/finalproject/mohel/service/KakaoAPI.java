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
	private final String REST_API_KEY = "8299169b3aa46a93e89d0f3fe4ed0583";
//	private final String REDIRECT_URI = "http://localhost:8040/member/kakaologin";
	private final String REDIRECT_URI = "http://43.200.8.117/member/kakaologin";

//	토큰 받기
//	 -d "grant_type=authorization_code"
//	 -d "client_id=${REST_API_KEY}"
//	 --data-urlencode "redirect_uri=${REDIRECT_URI}"
//	 -d "code=${AUTHORIZE_CODE}"
	public JSONObject getToken(String authorizeCode) {
        StringBuffer sb = new StringBuffer();
        sb.append("grant_type=authorization_code");
		sb.append("&client_id="+REST_API_KEY);
		sb.append("&redirect_uri="+REDIRECT_URI);
		sb.append("&code="+authorizeCode);
		
		return getTokenJson(sb);
	}

//	토큰 갱신
//	 -d "grant_type=refresh_token"
//	 -d "client_id=${REST_API_KEY}"
//	 -d "refresh_token=${USER_REFRESH_TOKEN}"
	public JSONObject getTokenRefresh(String refreshToken) {
		StringBuffer sb = new StringBuffer();
        sb.append("grant_type=refresh_token");
		sb.append("&client_id="+REST_API_KEY);
		sb.append("&refresh_token="+refreshToken);
		
		return getTokenJson(sb);
	}
	
	public HashMap<String, String> getUserInfo(String accessToken) {
		String infoUrl = "https://kapi.kakao.com/v2/user/me";
		String method = "GET";
		
		HashMap<String, String> userInfo = new HashMap<String, String>();
		
		try {
			URL url = new URL(infoUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			StringBuffer response = getResponse(con);
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
	
	public String disconnKakao(String accessToken) {
		String disconnUrl = "https://kapi.kakao.com/v1/user/unlink";
		String method = "POST";
		
		String disconnId = null;
		try {
			URL url = new URL(disconnUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(false);
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			StringBuffer response = getResponse(con);
			JSONObject disconnJSON = new JSONObject(response.toString());
			
			disconnId = disconnJSON.get("id").toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return disconnId;
	}
	
	private JSONObject getTokenJson(StringBuffer sb) {
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		String method = "POST";
		
		JSONObject tokenJson = null;
		try {
			URL url = new URL(tokenUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
	        bw.write(sb.toString());
	        bw.flush();
			
	        StringBuffer response = getResponse(con);
			
//			//System.out.println(response.toString());
			tokenJson = new JSONObject(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tokenJson;
	}
	
	private StringBuffer getResponse(HttpURLConnection con) throws IOException {
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
		
		return response;
	}
}
