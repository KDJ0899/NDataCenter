package com.kdj.ndatacenter.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.kdj.ndatacenter.EncodingUTF8;
import com.kdj.ndatacenter.dto.KeyWord;
import com.kdj.ndatacenter.dto.SearchTrend;

@Controller
public class MainController {
	
	EncodingUTF8 encoder = new EncodingUTF8();
	
	@RequestMapping("/")
	public ModelAndView APIExamDatalabTrend() {
	
		String clientId = "aYQR3UekVkUdLCaYXjcF";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "ruHZN2NlCd";//애플리케이션 클라이언트 시크릿값";
	    String answer="";
        String[] arr;
        
	    try {
	        String apiURL = "https://openapi.naver.com/v1/datalab/search";
	        List<String> keywords = new ArrayList<String>();
	        keywords.add("삼성");
	        SearchTrend body = SearchTrend.builder()
	        					.startDate("2017-01-01")
	        					.endDate("2017-04-30")
	        					.timeUnit("month")
	        					.keywordGroups(new KeyWord[] {
	        							KeyWord.builder()
	        							.groupName("hi")
	        							.keywords(keywords)
	        							.build()
	        					})
	        					.device("")
	        					.ages(new String[] {})
	        					.gender("")
	        					.build();
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("X-Naver-Client-Id", clientId);
	        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	        con.setRequestProperty("Content-Type", "application/json");
//	        con.setRequestProperty("Accept-Charset", "UTF-8"); 
	
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        System.out.println(new GsonBuilder().create().toJson(body).getBytes("UTF-8"));
	        wr.writeBytes(new String(new GsonBuilder().create().toJson(body).getBytes("EUC-KR")));

	        wr.flush();
	        wr.close();
	
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
	        }
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        System.out.println(response);
	        
	        answer = URLDecoder.decode(response.toString(),"UTF-8");
	
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    arr = answer.split(",");
        answer="";
        for(String str : arr)
        	answer+=str+"<br>";
	    return new ModelAndView("welcome","answer",answer);
	}
	

}
