package com.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

public class ParsingData {
	public String getWifiInfo(int startPage, int endPage) throws IOException {
		
		String requestURL = "http://openapi.seoul.go.kr:8088";
		
		String apiKey = System.getenv().get("API_KEY");
		String requestType = "json";
		String apiType = "TbPublicWifiInfo";
		String WifiRequest = String.format("%s/%s/%s/%s/%d/%d", requestURL, apiKey, requestType, apiType, startPage, endPage);
		StringBuilder urlBuilder = new StringBuilder(WifiRequest);
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
			
		BufferedReader rd;
		Gson gson = new Gson();
		
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		String result = sb.toString();
		rd.close();
		conn.disconnect();
		
		return result;	
	}
	
}
