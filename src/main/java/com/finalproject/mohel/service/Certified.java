package com.finalproject.mohel.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class Certified {
	
	@Inject
	JavaMailSender javaMailSender;
	
	public void sendMail(String subject, String text, String email) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
//		String htmlText = "<div style='text-align: center;'>";
//		htmlText += "<a href='http://localhost:8040'><img src='https://i.ibb.co/kB2CZhX/mohel-logo-11.png' alt='mohel-logo-11' border='0'></a>";
//		htmlText += "<div style='margin: 0 auto; margin-top: 20px; border-top: 1px solid gray; border-bottom: 1px solid gray; width: 470px; padding: 20px 0; font-size: 12px;'>";
//		htmlText += "<p style='font-size: 14px; font-weight: bold; margin-bottom: 10px;'>안녕하세요 회원님 비밀번호를 재설정 하시겠어요?</p>";
//		htmlText += "<p>비밀번호를 재설정 하길 원하시면 아래 버튼을 클릭해주세요!</p>";
//		htmlText += "<p>만약 회원님께서 비밀번호 재설정을 요청하지 않은 경우에는 이 메일을 무시해주세요.</p>";
//		htmlText += "<div style='margin-top: 30px; margin-bottom: 10px;'><a style='background-color: #01c9c6; color: white; border-radius: 5px; padding: 10px 40px; font-size: 16px; font-weight: bold;'>비밀번호 재설정</a></div>";
//		htmlText += "</div>";
//		htmlText += "<p style='margin-top: 60px;'>&#169; 2022 Mohel. All rigths reserved.</p>";
//		htmlText += "</div>";
		
		try {
			MimeMessageHelper mmh = new MimeMessageHelper(message, true, "UTF-8");
			mmh.setSubject(subject);
			mmh.setText(text, true);
			mmh.setTo(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		javaMailSender.send(message);
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
            con.setDoOutput(true); // POST요청시
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
	
	private String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) {
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
