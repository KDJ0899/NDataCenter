package com.kdj.ndatacenter.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kdj.ndatacenter.EncodingUTF8;
import com.kdj.ndatacenter.dto.KeyWord;
import com.kdj.ndatacenter.dto.ResponseResult;
import com.kdj.ndatacenter.dto.SearchTrend;

@Controller
public class MainController {
	
	EncodingUTF8 encoder = new EncodingUTF8();
	
	@RequestMapping("/")
	public ModelAndView APIExamDatalabTrend(HttpServletRequest request) {
	
		String clientId = "aYQR3UekVkUdLCaYXjcF";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "ruHZN2NlCd";//애플리케이션 클라이언트 시크릿값";
        String[] arr;
        Gson json = new Gson();
        ResponseResult result = null;
        
        
	    try {
	        String apiURL = "https://openapi.naver.com/v1/datalab/search";
	        List<String> keywords = new ArrayList<String>();
	        keywords.add("코로나");
	        keywords.add("우한");
	        SearchTrend body = SearchTrend.builder()
	        					.startDate("2020-01-17")
	        					.endDate("2020-02-08")
	        					.timeUnit("date")
	        					.keywordGroups(new KeyWord[] {
	        							KeyWord.builder()
	        							.groupName("코로나 바이러스")
	        							.keywords(keywords)
	        							.build()
	        					})
	        					.device("")
	        					.ages(new String[] {})
	        					.gender("")
	        					.build();
	        
            String body1 = "{\"startDate\":\"2017-01-01\",\"endDate\":\"2017-04-30\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"한글\",\"keywords\":[\"한글\",\"korean\"]},{\"groupName\":\"영어\",\"keywords\":[\"영어\",\"english\"]}],\"device\":\"pc\",\"ages\":[\"1\",\"2\"],\"gender\":\"f\"}";

	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("X-Naver-Client-Id", clientId);
	        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	        con.setRequestProperty("Content-Type", "application/json");
	        
//	        con.setRequestProperty("Accept-Charset", "UTF-8"); 
	
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.write(json.toJson(body).getBytes());
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
	        
	        result = json.fromJson(response.toString(),ResponseResult.class);
	        
	
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    request.setAttribute("result", result);
	    
	    
	    System.out.println(result.getResults()[0].getData()[0].getPeriod());
	   
	    return new ModelAndView("welcome","result",result);
	}
	

}
