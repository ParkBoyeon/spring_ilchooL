package project.spring.ilchooL.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import retrofit2.Call;
import retrofit2.Retrofit;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Keyword;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.model.NewsSearch;
import project.spring.ilchooL.model.NewsSearch.items;
import project.spring.ilchooL.service.KeywordService;
import project.spring.ilchooL.service.NewsSearchService;

@Controller
public class NewsSearchController {
   @Autowired
   WebHelper webHelper;

   @Autowired
   RetrofitHelper retrofitHelper;
   
   @Autowired
   KeywordService keywordService;
   
   @Value("#{servletContext.contextPath}")
   String contextPath;   

   @RequestMapping(value = "/contents/contents_news.do", method = RequestMethod.GET)
   public ModelAndView NaverNewsSearch(Model model, HttpServletRequest request) throws IOException {
	   
	    HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
		
		if(loginSession == null) { 
			return webHelper.redirect("/ilchooL/account/login.do", "로그인 후 이용해주세요.");
		}
	   
	   
	   /** 뉴스 키워드 크롤링 */
	   String url_keyword = "https://www.nate.com";
	   Document doc = Jsoup.connect(url_keyword).get();

	   Elements key1 = doc.select("div#news_area div.bizCnt ol.isKeywordList li:nth-child(1) span.txt_rank");
	   Elements key2 = doc.select("div#news_area div.bizCnt ol.isKeywordList li:nth-child(2) span.txt_rank");
	   Elements key3 = doc.select("div#news_area div.bizCnt ol.isKeywordList li:nth-child(3) span.txt_rank");
	   Elements key4 = doc.select("div#news_area div.bizCnt ol.isKeywordList li:nth-child(4) span.txt_rank");
	   Elements key5 = doc.select("div#news_area div.bizCnt ol.isKeywordList li:nth-child(5) span.txt_rank");

	   String[] rank = new String[5];

	   rank[0] = key1.text();
	   rank[1] = key2.text();
	   rank[2] = key3.text();
	   rank[3] = key4.text();
	   rank[4] = key5.text();
	   
	   model.addAttribute("rank0", rank[0]);
	   model.addAttribute("rank1", rank[1]);
	   model.addAttribute("rank2", rank[2]);
	   model.addAttribute("rank3", rank[3]);
	   model.addAttribute("rank4", rank[4]);
	   
	   

      return new ModelAndView("contents/contents_news");

   }
   
/** --------- 키워드 검색 후 ---------- */
   
   @RequestMapping(value = "/contents/contents_search.do", method = RequestMethod.POST)
	   public ModelAndView NaverNewsSearch2(Model model, HttpServletResponse response, HttpServletRequest request,
		   @RequestParam("search") String search) throws IOException {
	   
	    HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
		
		if(loginSession == null) { 
			return webHelper.redirect(null, "로그인 후 이용해주세요.");
		}
	   
	   /** API 연동 객체 생성 */
	      // Retrofit 객체 생성
	      Retrofit retrofit = retrofitHelper.getRetrofit(NewsSearchService.BASE_URL);

	      // Service 객체 생성. 구현체는 Retrofit이 자동 생성
	      NewsSearchService newsSearchService = retrofit.create(NewsSearchService.class);
	      
	      /** 네이버 뉴스 검색 API 요청 */
	      
	      // 네이버 뉴스 검색 API를 통해 검색 결과 받아오기
	      Call<NewsSearch> call = newsSearchService.getNewsSearch(search, 50);
	      
	      NewsSearch newsSearch = null;
	      
	      try {
	         newsSearch = call.execute().body();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      // 변수명이 길어서 참조변수를 만듬
	      List<items> list = null;

	      // 검색 결과가 있다면 list만 추출
	      if (newsSearch != null) {
	         list = newsSearch.getItems();
	      }
	      
	      String url = "";
	      System.out.println("-----------------" + list.size() + "------------------");
	     
	      
	      if(list.size() != 50) {
	    	  System.out.println("-----------------" + list.size() + "------------------");
	    	  response.setContentType("text/html; charset=UTF-8");
	    	  PrintWriter out = response.getWriter();
	    	  out.println("<script>alert('검색된 뉴스가 없습니다.'); history.go(-1);</script>");
	    	  out.flush(); 
	      }
	      
	    
	      
	     

	      /** View 처리 */
	      model.addAttribute("keyword", search);
	      model.addAttribute("list", list);
	   
	   
	 
	   /** keyword DB에서 가져오기 */
	   List<Keyword> key_rank = null;
	   try {
		key_rank = keywordService.selectKeyword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   
	   
	   model.addAttribute("rank0", key_rank.get(0).getNews_keyword());
	   model.addAttribute("rank1", key_rank.get(1).getNews_keyword());
	   model.addAttribute("rank2", key_rank.get(2).getNews_keyword());
	   model.addAttribute("rank3", key_rank.get(3).getNews_keyword());
	   model.addAttribute("rank4", key_rank.get(4).getNews_keyword());
	   



	   /** 뉴스 원문 크롤링 시작 */
	   items[] item = new items[50];
	   int j = 0;
	   for(int i=0; i<50; i++) {
		   if(list.get(i).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
			   item[j] = list.get(i);
			   j++;
	      } else if(list.get(i).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
	    	  item[j] = list.get(i);
	    	  j++;
	      } else if(list.get(i).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
	    	  item[j] = list.get(i);
	    	  j++;
	  	  } else {
	  		  continue;
	  	  }
	  }
	   

	  // 뉴스 제목 ->  ele
	  // 뉴스 원문 ->  element
	  // category_id -> c_id
	  String connUrl1 = item[0].getLink();
	  Document doc1 = Jsoup.connect(connUrl1).get();
	  Elements ele1 = null;
	  Elements element1 = null;
	  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele1 = doc1.select("h2.end_tit");
		  element1 = doc1.select("div.end_body_wrp");
	  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
		 ele1 = doc1.select("h4.title");
		 element1 = doc1.select("div#newsEndContents");
	  } else {
		  ele1 = doc1.select("h3#articleTitle");
		  element1 = doc1.select("div#articleBodyContents");
	  }
	  String str1 = ele1.text();
	  
	
	  String connUrl2 = item[1].getLink();
	  Document doc2 = Jsoup.connect(connUrl2).get();
	  Elements ele2 = null;
	  Elements element2 = null;
	  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele2 = doc2.select("h2.end_tit");
		  element2 = doc2.select("div.end_body_wrp");
	  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele2 = doc2.select("h4.title");
			 element2 = doc2.select("div#newsEndContents");
	  } else {
		  ele2 = doc2.select("h3#articleTitle");
		  element2 = doc2.select("div#articleBodyContents");
	  }
	  String str2 = ele2.text();
	  
	
	  String connUrl3 = item[2].getLink();
	  Document doc3 = Jsoup.connect(connUrl3).get();
	  Elements ele3 = null;
	  Elements element3 = null;
	  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele3 = doc3.select("h2.end_tit");
		  element3 = doc3.select("div.end_body_wrp");
	  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele3 = doc3.select("h4.title");
			 element3 = doc3.select("div#newsEndContents");
	  } else {
		  ele3 = doc3.select("h3#articleTitle");
		  element3 = doc3.select("div#articleBodyContents");
	  }
	  String str3 = ele3.text();
	  
	
	  String connUrl4 = item[3].getLink();
	  Document doc4 = Jsoup.connect(connUrl4).get();
	  Elements ele4 = null;
	  Elements element4 = null;
	  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele4 = doc4.select("h2.end_tit");
		  element4 = doc4.select("div.end_body_wrp");
	  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele4 = doc4.select("h4.title");
			 element4 = doc4.select("div#newsEndContents");
	  } else {
		  ele4 = doc4.select("h3#articleTitle");
		  element4 = doc4.select("div#articleBodyContents");
	  }
	  String str4 = ele4.text();
	  
	
	  String connUrl5 = item[4].getLink();
	  Document doc5 = Jsoup.connect(connUrl5).get();
	  Elements ele5 = null;
	  Elements element5 = null;
	  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele5 = doc5.select("h2.end_tit");
		  element5 = doc5.select("div.end_body_wrp");
	  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele5 = doc5.select("h4.title");
			 element5 = doc5.select("div#newsEndContents");
	  } else {
		  ele5 = doc5.select("h3#articleTitle");
		  element5 = doc5.select("div#articleBodyContents");
	  }
	  String str5 = ele5.text();
	  
	
	  String connUrl6 = item[5].getLink();
	  Document doc6 = Jsoup.connect(connUrl6).get();
	  Elements ele6 = null;
	  Elements element6 = null;
	  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele6 = doc6.select("h2.end_tit");
		  element6 = doc6.select("div.end_body_wrp");
	  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele6 = doc6.select("h4.title");
			 element6 = doc6.select("div#newsEndContents");
	  } else {
		  ele6 = doc6.select("h3#articleTitle");
		  element6 = doc6.select("div#articleBodyContents");
	  }
	  String str6 = ele6.text();
	  
	
	  String connUrl7 = item[6].getLink();
	  Document doc7 = Jsoup.connect(connUrl7).get();
	  Elements ele7 = null;
	  Elements element7 = null;
	  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele7 = doc7.select("h2.end_tit");
		  element7 = doc7.select("div.end_body_wrp");
	  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele7 = doc7.select("h4.title");
			 element7 = doc7.select("div#newsEndContents");
	  } else {
		  ele7 = doc7.select("h3#articleTitle");
		  element7 = doc7.select("div#articleBodyContents");
	  }
	  String str7 = ele7.text();
	  
	
	  String connUrl8 = item[7].getLink();
	  Document doc8 = Jsoup.connect(connUrl8).get();
	  Elements ele8 = null;
	  Elements element8 = null;
	  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele8 = doc8.select("h2.end_tit");
		  element8 = doc8.select("div.end_body_wrp");
	  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele8 = doc8.select("h4.title");
			 element8 = doc8.select("div#newsEndContents");
	  } else {
		  ele8 = doc8.select("h3#articleTitle");
		  element8 = doc8.select("div#articleBodyContents");
	  }
	  String str8 = ele8.text();
	  
	
	  String connUrl9 = item[8].getLink();
	  Document doc9 = Jsoup.connect(connUrl9).get();
	  Elements ele9 = null;
	  Elements element9 = null;
	  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
		  ele9 = doc9.select("h2.end_tit");
		  element9 = doc9.select("div.end_body_wrp");
	  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
			 ele9 = doc9.select("h4.title");
			 element9 = doc9.select("div#newsEndContents");
	  } else {
		  ele9 = doc9.select("h3#articleTitle");
		  element9 = doc9.select("div#articleBodyContents");
	  }
	  String str9 = ele9.text();
	 
	  
	  // 뉴스 날짜 view 처리
	  model.addAttribute("news_date1", newsSearch.getItems().get(0).getPubDate());
	  model.addAttribute("news_date2", newsSearch.getItems().get(1).getPubDate());
	  model.addAttribute("news_date3", newsSearch.getItems().get(2).getPubDate());
	  model.addAttribute("news_date4", newsSearch.getItems().get(3).getPubDate());
	  model.addAttribute("news_date5", newsSearch.getItems().get(4).getPubDate());
	  model.addAttribute("news_date6", newsSearch.getItems().get(5).getPubDate());
	  model.addAttribute("news_date7", newsSearch.getItems().get(6).getPubDate());
	  model.addAttribute("news_date8", newsSearch.getItems().get(7).getPubDate());
	  model.addAttribute("news_date9", newsSearch.getItems().get(8).getPubDate());
	  
	  // 뉴스 제목 view 처리
	  model.addAttribute("str1",str1);
	  model.addAttribute("str2",str2);
	  model.addAttribute("str3",str3);
	  model.addAttribute("str4",str4);
	  model.addAttribute("str5",str5);
	  model.addAttribute("str6",str6);
	  model.addAttribute("str7",str7);
	  model.addAttribute("str8",str8);
	  model.addAttribute("str9",str9);
	  
	  // 뉴스 원문 view 처리
	  model.addAttribute("element1", element1);
	  model.addAttribute("element2", element2);
	  model.addAttribute("element3", element3);
	  model.addAttribute("element4", element4);
	  model.addAttribute("element5", element5);
	  model.addAttribute("element6", element6);
	  model.addAttribute("element7", element7);
	  model.addAttribute("element8", element8);
	  model.addAttribute("element9", element9);
	  
	  
	  
	  

	  return new ModelAndView("contents/contents_news_search");
   }

}