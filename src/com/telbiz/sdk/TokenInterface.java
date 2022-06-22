package com.telbiz.sdk;

import com.telbiz.model.UrlDefault;
import com.telbiz.model.RefreshTokenEndPointRequest;
import com.telbiz.model.TokenEndPointRequest;
import com.telbiz.model.TokenResponse;
import java.io.IOException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class TokenInterface implements ITokenInterface {

	//Get Access Token
		@Override
		public TokenResponse GetAccessToken(TokenEndPointRequest request) {
			TokenResponse response = new TokenResponse();
			String jsonData = "";

			try {
				
				String bodyStr = "{\r\n    "
						+ "\"ClientID\": \""+ request.ClientID +"\",\r\n    "
						+ "\"Secret\": \""+ request.Secret +"\",\r\n    "
						+ "\"GrantType\": \""+ request.GrantType +"\",\r\n    "
						+ "\"Password\": \""+ request.Password +"\",\r\n    "
						+ "\"RedirectUri\": \""+ request.RedirectUri +"\",\r\n    "
						+ "\"Scope\": \""+ request.Scope +"\",\r\n    "
						+ "\"Username\":\""+ request.Username +"\"\r\n}";

				 Connection.Response execute = Jsoup.connect(UrlDefault.UrlEndpoint 
							+ UrlDefault.TokenUrl)
			                .header("Content-Type", "application/json")
			                .header("Accept", "application/json")
			                .followRedirects(true)
			                .ignoreHttpErrors(true)
			                .ignoreContentType(true)
			                .sslSocketFactory(socketFactory())
			                .method(Connection.Method.POST)
			                .requestBody(bodyStr)
			                .maxBodySize(1_000_000 * 30) // 30 mb ~
			                .timeout(0) // infinite timeout
			                .execute();
			        jsonData = execute.body();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(jsonData != "") {
				JSONObject Jobject = new JSONObject(jsonData);
				if(Jobject != null) {
					response.setAccessToken(Jobject.getString("accessToken"));
					response.setExpire(Jobject.getInt("expire"));
					if(!Jobject.isNull("refreshToken")) {
						response.setRefreshToken(Jobject.getString("refreshToken"));
					}else {
						response.setRefreshToken("");
					}
					response.setCode(Jobject.getString("code"));
					response.setMessage(Jobject.getString("message"));
					response.setSuccess(Jobject.getBoolean("success"));
					response.setDetail(Jobject.getString("detail"));
				}
			}
			return response;
		}
		//Get Refresh Token
		@Override
		public TokenResponse GetRefreshToken(RefreshTokenEndPointRequest request) {
			TokenResponse response = new TokenResponse();
			String jsonData = "";
			String bodyStr = "{\r\n    "
					+ "\"ClientID\": \""+ request.ClientID +"\",\r\n    "
					+ "\"Secret\": \""+ request.Secret +"\",\r\n    "
					+ "\"refreshToken\":\""+ request.RefreshToken +"\"\r\n}";
			
			 try {
				Connection.Response execute = Jsoup.connect(UrlDefault.UrlEndpoint 
							+ UrlDefault.RefreshTokenUrl)
				            .header("Content-Type", "application/json")
				            .header("Accept", "application/json")
				            .followRedirects(true)
				            .ignoreHttpErrors(true)
				            .ignoreContentType(true)
				            .sslSocketFactory(socketFactory())
				            .method(Connection.Method.POST)
				            .requestBody(bodyStr)
				            .maxBodySize(1_000_000 * 30) // 30 mb ~
				            .timeout(0) // infinite timeout
				            .execute();
				  //int statusCode = execute.statusCode();
			        jsonData = execute.body();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(jsonData != "") {
				JSONObject Jobject = new JSONObject(jsonData);
				if(Jobject != null) {
					response.setAccessToken(Jobject.getString("accessToken"));
					response.setExpire(Jobject.getInt("expire"));
					if(!Jobject.isNull("refreshToken")) {
						response.setRefreshToken(Jobject.getString("refreshToken"));
					}else {
						response.setRefreshToken("");
					}
					response.setCode(Jobject.getString("code"));
					response.setMessage(Jobject.getString("message"));
					response.setSuccess(Jobject.getBoolean("success"));
					response.setDetail(Jobject.getString("detail"));
				}
			}
			return response;
		}
		
		private static SSLSocketFactory socketFactory() {
		    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
		      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		        return null;
		      }

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
		    }};

		    SSLContext sslContext = null;
			try {
				sslContext = SSLContext.getInstance("TLS");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      try {
				sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      return sslContext.getSocketFactory();
		  }

	}