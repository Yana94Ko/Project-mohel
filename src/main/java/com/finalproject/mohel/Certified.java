package com.finalproject.mohel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Certified {
	
	public String checkMail(String email, JavaMailSender javaMailSender) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		String key = String.format("%06d", (int)(Math.random()*1000000));
		try {
			MimeMessageHelper mmh = new MimeMessageHelper(message, true, "UTF-8");
			
			mmh.setSubject("모두의 헬스 메일 인증번호를 알려드립니다.");
			String htmlText = "<h3>아래의 인증번호 6자리를 인증번호 입력창에 입력해주세요.</h3>";
			htmlText += "<hr>";
			htmlText += "<h2>"+key+"</h2>";
			htmlText += "<hr>";
			mmh.setText(htmlText, true);
			mmh.setTo(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		javaMailSender.send(message);

		return key;
	}
	
	public String sendSMS(String tel) {
		String hostNameUrl = "https://sens.apigw.ntruss.com";			// 호스트 URL
		String requestUrl= "/sms/v2/services/";							// 요청 URL
		String requestUrlType = "/messages";							// 요청 URL
		String accessKey = "8QnBwPVjENpQfigWlqSC";						// 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키			// Access Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID
		String secretKey = "ctbgMGAq3N7Wnwxf2SnAbqjDipyGqtqOrLm6ng7j";	// 2차 인증을 위해 서비스마다 할당되는 service secret key	// Service Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID	
		String serviceId = "ncp:sms:kr:284866756103:mohel_sms";			// 프로젝트에 할당된 SMS 서비스 ID							// service ID : https://console.ncloud.com/sens/project > Simple & ... > Project > 서비스 ID
		String method = "POST";											// 요청 method
		String timestamp = Long.toString(System.currentTimeMillis());	// current timestamp (epoch)
		
		requestUrl += serviceId + requestUrlType;
		String apiUrl = hostNameUrl + requestUrl;
		String key = String.format("%06d", (int)(Math.random()*1000000));
		
		// JSON 을 활용한 body data 생성
		JSONObject bodyJson = new JSONObject();
		JSONObject toJson = new JSONObject();
	    JSONArray  toArr = new JSONArray();

	    toJson.put("to", tel);												// Mandatory(필수), messages.to	수신번호, -를 제외한 숫자만 입력 가능
	    toArr.put(toJson);
	    
	    bodyJson.put("type","SMS");											// Madantory, 메시지 Type (SMS | LMS | MMS), (소문자 가능)
	    bodyJson.put("from","01045491574");									// Mandatory, 발신번호, 사전 등록된 발신번호만 사용 가능
	    bodyJson.put("content","[모두의 헬스] 인증번호[" + key + "]를 입력하세요.");	// Mandatory(필수), 기본 메시지 내용, SMS: 최대 80byte, LMS, MMS: 최대 2000byte
	    bodyJson.put("messages", toArr);									// Mandatory(필수), 아래 항목들 참조 (messages.XXX), 최대 1,000개
	    
	    String body = bodyJson.toString();
	    
//	    System.out.println(body);
	    
        try {
            URL url = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            
            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
//            System.out.println("responseCode" +" " + responseCode);
            if(responseCode == 202) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
//            System.out.println(response.toString());

        } catch (Exception e) {}
        
		return key;
	}
	
	String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) {
		String space = " ";					// one space
		String newLine = "\n";					// new line
//		String method = "GET";					// method
//		String url = "/photos/puppy.jpg?query1=&query2";	// url (include query string)
//		String timestamp = "{timestamp}";			// current timestamp (epoch)
//		String accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
//		String secretKey = "{secretKey}";

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey;
		Mac mac = null;
		byte[] rawHmac = null;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		Encoder encoder = Base64.getEncoder();
		String encodeBase64String = encoder.encodeToString(rawHmac);

	  return encodeBase64String;
	}
}
