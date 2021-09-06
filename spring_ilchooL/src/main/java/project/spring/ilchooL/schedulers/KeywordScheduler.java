package project.spring.ilchooL.schedulers;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.model.Keyword;
import project.spring.ilchooL.model.News;
import project.spring.ilchooL.model.NewsSearch;
import project.spring.ilchooL.model.NewsSearch.items;
import project.spring.ilchooL.service.KeywordService;
import project.spring.ilchooL.service.NewsSearchService;
import retrofit2.Call;
import retrofit2.Retrofit;

@Slf4j
@Controller
public class KeywordScheduler {
	
	@Autowired
	KeywordService keywordService;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	RetrofitHelper retrofitHelper;
	
	

	/** 뉴스 키워드 크롤링 */
	String url_keyword = "https://www.nate.com";
	
	// keyword_id 1번 ~ 5번 까지
	public void keyword() throws IOException {
		
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
		
		Keyword input = new Keyword();
		News news = new News();
		
		/** API 연동 객체 생성 */
	      // Retrofit 객체 생성
	      Retrofit retrofit = retrofitHelper.getRetrofit(NewsSearchService.BASE_URL);

	      // Service 객체 생성. 구현체는 Retrofit이 자동 생성
	      NewsSearchService newsSearchService = retrofit.create(NewsSearchService.class);
	      
	      String search1 = null;
	      String search2 = null;
	      String search3 = null;
	      String search4 = null;
	      String search5 = null;
	      
	      // 네이버 뉴스 검색 API를 통해 검색 결과 받아오기
	      Call<NewsSearch> call1 = null;
	      Call<NewsSearch> call2 = null;
	      Call<NewsSearch> call3 = null;
	      Call<NewsSearch> call4 = null;
	      Call<NewsSearch> call5 = null;
	      
	      NewsSearch newsSearch1 = null;
	      NewsSearch newsSearch2 = null;
	      NewsSearch newsSearch3 = null;
	      NewsSearch newsSearch4 = null;
	      NewsSearch newsSearch5 = null;
	      
	      // 변수명이 길어서 참조변수를 만듬
	      List<items> list1 = null;
	      List<items> list2 = null;
	      List<items> list3 = null;
	      List<items> list4 = null;
	      List<items> list5 = null;
	      
	      /** 네이버 뉴스 검색 API 요청 */
	     
	      
	        search1 = rank[0];
	    	call1 = newsSearchService.getNewsSearch(search1, 50);
	    	
	    	search2 = rank[1];
	    	call2 = newsSearchService.getNewsSearch(search2, 50);
	    	
	    	search3 = rank[2];
	    	call3 = newsSearchService.getNewsSearch(search3, 50);
	    	
	    	search4 = rank[3];
	    	call4 = newsSearchService.getNewsSearch(search4, 50);
	    	
	    	search5 = rank[4];
	    	call5 = newsSearchService.getNewsSearch(search5, 50);
	    	
	      
		      try {
			         newsSearch1 = call1.execute().body();
			         newsSearch2 = call2.execute().body();
			         newsSearch3 = call3.execute().body();
			         newsSearch4 = call4.execute().body();
			         newsSearch5 = call5.execute().body();
			      } catch (Exception e) {
			         e.printStackTrace();
			      }
			      // 검색 결과가 있다면 list만 추출
			      if (newsSearch1 != null) {
			         list1 = newsSearch1.getItems();
			      }
			      if (newsSearch2 != null) {
				     list2 = newsSearch2.getItems();
				      }
			      if (newsSearch3 != null) {
				     list3 = newsSearch3.getItems();
				      }
			      if (newsSearch4 != null) {
				     list4 = newsSearch4.getItems();
				      }
			      if (newsSearch5 != null) {
				     list5 = newsSearch5.getItems();
				      }

			      
			      
				/** 뉴스 원문 크롤링 시작 */
				   
				String connUrl1 = null;
				Document doc1 = null;
				Elements ele1 = null;
				Elements element1 = null;
				int c_id1 = 0;
				String str1 = null;
				   
				String connUrl2 = null;
				Document doc2 = null;
				Elements ele2 = null;
				Elements element2 = null;
				int c_id2 = 0;
				String str2 = null;
				   
				String connUrl3 = null;
				Document doc3 = null;
				Elements ele3 = null;
				Elements element3 = null;
				int c_id3 = 0;
				String str3 = null;
				   
				String connUrl4 = null;
				Document doc4 = null;
				Elements ele4 = null;
				Elements element4 = null;
				int c_id4 = 0;
				String str4 = null;
				   
				String connUrl5 = null;
				Document doc5 = null;
				Elements ele5 = null;
				Elements element5 = null;
				int c_id5 = 0;
				String str5 = null;
				 
				String connUrl6 = null;
				Document doc6 = null;
				Elements ele6 = null;
				Elements element6 = null;
				int c_id6 = 0;
				String str6 = null;
				  
				String connUrl7 = null;
				Document doc7 = null;
				Elements ele7 = null;
				Elements element7 = null;
				int c_id7 = 0;
				String str7 = null;
				   
				String connUrl8 = null;
				Document doc8 = null;
				Elements ele8 = null;
				Elements element8 = null;
				int c_id8 = 0;
				String str8 = null;
				   
				String connUrl9 = null;
				Document doc9 = null;
				Elements ele9 = null;
				Elements element9 = null;
				int c_id9 = 0;
				String str9 = null;
				   
				items[] item1 = new items[50];
				items[] item2 = new items[50];
				items[] item3 = new items[50];
				items[] item4 = new items[50];
				items[] item5 = new items[50];
				   
				   
				   /** ----- 1번 키워드 -----*/
				   int j = 0;
				   for(int k=0; k<50; k++) {
					   if(list1.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
						   item1[j] = list1.get(k);
						   j++;
				      } else if(list1.get(k).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
				    	  item1[j] = list1.get(k);
				    	  j++;
				      } else if(list1.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
				    	  item1[j] = list1.get(k);
				    	  j++;
				      	  }
				      }

					  // 뉴스 제목 ->  ele
					  // 뉴스 원문 ->  element
					  // category_id -> c_id
					  connUrl1 = item1[0].getLink();
					  doc1 = Jsoup.connect(connUrl1).get();
					  ele1 = null;
					  element1 = null;
					  c_id1 = 0;
					  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele1 = doc1.select("h2.end_tit");
						  element1 = doc1.select("div.end_body_wrp");
						  c_id1 = 106;
					  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
						 ele1 = doc1.select("h4.title");
						 element1 = doc1.select("div#newsEndContents");
						 c_id1 = 107;
					  } else {
						  ele1 = doc1.select("h3#articleTitle");
						  element1 = doc1.select("div#articleBodyContents");
						  c_id1 = Integer.parseInt(connUrl1.substring(61, 64));
					  }
					  str1 = ele1.text();
					  
					
					  connUrl2 = item1[1].getLink();
					  doc2 = Jsoup.connect(connUrl2).get();
					  ele2 = null;
					  element2 = null;
					  c_id2 = 0;
					  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele2 = doc2.select("h2.end_tit");
						  element2 = doc2.select("div.end_body_wrp");
						  c_id2 = 106;
					  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele2 = doc2.select("h4.title");
							 element2 = doc2.select("div#newsEndContents");
							 c_id2 = 107;
					  } else {
						  ele2 = doc2.select("h3#articleTitle");
						  element2 = doc2.select("div#articleBodyContents");
						  c_id2 = Integer.parseInt(connUrl2.substring(61, 64));
					  }
					  str2 = ele2.text();
					  
					
					  connUrl3 = item1[2].getLink();
					  doc3 = Jsoup.connect(connUrl3).get();
					  ele3 = null;
					  element3 = null;
					  c_id3 = 0;
					  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele3 = doc3.select("h2.end_tit");
						  element3 = doc3.select("div.end_body_wrp");
						  c_id3 = 106;
					  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele3 = doc3.select("h4.title");
							 element3 = doc3.select("div#newsEndContents");
							 c_id3 = 107;
					  } else {
						  ele3 = doc3.select("h3#articleTitle");
						  element3 = doc3.select("div#articleBodyContents");
						  c_id3 = Integer.parseInt(connUrl3.substring(61, 64));
					  }
					  str3 = ele3.text();
					  
					
					  connUrl4 = item1[3].getLink();
					  doc4 = Jsoup.connect(connUrl4).get();
					  ele4 = null;
					  element4 = null;
					  c_id4 = 0;
					  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele4 = doc4.select("h2.end_tit");
						  element4 = doc4.select("div.end_body_wrp");
						  c_id4 = 106;
					  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele4 = doc4.select("h4.title");
							 element4 = doc4.select("div#newsEndContents");
							 c_id4 = 107;
					  } else {
						  ele4 = doc4.select("h3#articleTitle");
						  element4 = doc4.select("div#articleBodyContents");
						  c_id4 = Integer.parseInt(connUrl4.substring(61, 64));
					  }
					  str4 = ele4.text();
					  
					
					  connUrl5 = item1[4].getLink();
					  doc5 = Jsoup.connect(connUrl5).get();
					  ele5 = null;
					  element5 = null;
					  c_id5 = 0;
					  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele5 = doc5.select("h2.end_tit");
						  element5 = doc5.select("div.end_body_wrp");
						  c_id5 = 106;
					  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele5 = doc5.select("h4.title");
							 element5 = doc5.select("div#newsEndContents");
							 c_id5 = 107;
					  } else {
						  ele5 = doc5.select("h3#articleTitle");
						  element5 = doc5.select("div#articleBodyContents");
						  c_id5 = Integer.parseInt(connUrl5.substring(61, 64));
					  }
					  str5 = ele5.text();
					  
					  connUrl6 = item1[5].getLink();
					  doc6 = Jsoup.connect(connUrl6).get();
					  ele6 = null;
					  element6 = null;
					  c_id6 = 0;
					  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele6 = doc6.select("h2.end_tit");
						  element6 = doc6.select("div.end_body_wrp");
						  c_id6 = 106;
					  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
						  ele6 = doc6.select("h4.title");
						  element6 = doc6.select("div#newsEndContents");
						  c_id6 = 107;
					  } else {
						  ele6 = doc6.select("h3#articleTitle");
						  element6 = doc6.select("div#articleBodyContents");
						  c_id6 = Integer.parseInt(connUrl6.substring(61, 64));
					  }
						  str6 = ele6.text();
						  
						
						  connUrl7 = item1[6].getLink();
						  doc7 = Jsoup.connect(connUrl7).get();
						  ele7 = null;
						  element7 = null;
						  c_id7 = 0;
						  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele7 = doc7.select("h2.end_tit");
							  element7 = doc7.select("div.end_body_wrp");
							  c_id7 = 106;
						  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele7 = doc7.select("h4.title");
								 element7 = doc7.select("div#newsEndContents");
								 c_id7 = 107;
						  } else {
							  ele7 = doc7.select("h3#articleTitle");
							  element7 = doc7.select("div#articleBodyContents");
							  c_id7 = Integer.parseInt(connUrl7.substring(61, 64));
						  }
						  str7 = ele7.text();
						  
						
						  connUrl8 = item1[7].getLink();
						  doc8 = Jsoup.connect(connUrl8).get();
						  ele8 = null;
						  element8 = null;
						  c_id8 = 0;
						  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele8 = doc8.select("h2.end_tit");
							  element8 = doc8.select("div.end_body_wrp");
							  c_id8 = 106;
						  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele8 = doc8.select("h4.title");
								 element8 = doc8.select("div#newsEndContents");
								 c_id8 = 107;
						  } else {
							  ele8 = doc8.select("h3#articleTitle");
							  element8 = doc8.select("div#articleBodyContents");
							  c_id8 = Integer.parseInt(connUrl8.substring(61, 64));
						  }
						  str8 = ele8.text();
						  
						
						  connUrl9 = item1[8].getLink();
						  doc9 = Jsoup.connect(connUrl9).get();
						  ele9 = null;
						  element9 = null;
						  c_id9 = 0;
						  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele9 = doc9.select("h2.end_tit");
							  element9 = doc9.select("div.end_body_wrp");
							  c_id9 = 106;
						  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele9 = doc9.select("h4.title");
								 element9 = doc9.select("div#newsEndContents");
								 c_id9 = 107;
						  } else {
							  ele9 = doc9.select("h3#articleTitle");
							  element9 = doc9.select("div#articleBodyContents");
							  c_id9 = Integer.parseInt(connUrl9.substring(61, 64));
						  }
						  str9 = ele9.text();
						  

						  /** 1번 키워드 */
					      
						  input.setNews_keyword(rank[0]);
//						  sqlSession.insert("keywordMapper.insertKeyword", input);
						  sqlSession.update("keywordMapper.updateKeyword1", input);
						  
						  news.setNews_keyword(rank[0]);
						  news.setNews_url(connUrl1);
						  news.setNews_date(newsSearch1.getItems().get(0).getPubDate());
						  news.setNews_title(str1);
						  news.setNews_category_id(c_id1);
						  news.setNews_id(1);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews1", news);
						  
						  news.setNews_url(connUrl2);
						  news.setNews_date(newsSearch1.getItems().get(1).getPubDate());
						  news.setNews_title(str2);
						  news.setNews_category_id(c_id2);
						  news.setNews_id(2);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews2", news);
						  
						  news.setNews_url(connUrl3);
						  news.setNews_date(newsSearch1.getItems().get(2).getPubDate());
						  news.setNews_title(str3);
						  news.setNews_category_id(c_id3);
						  news.setNews_id(3);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews3", news);
						  
						  news.setNews_url(connUrl4);
						  news.setNews_date(newsSearch1.getItems().get(3).getPubDate());
						  news.setNews_title(str4);
						  news.setNews_category_id(c_id4);
						  news.setNews_id(4);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews4", news);
						  
						  news.setNews_url(connUrl5);
						  news.setNews_date(newsSearch1.getItems().get(4).getPubDate());
						  news.setNews_title(str5);
						  news.setNews_category_id(c_id5);
						  news.setNews_id(5);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews5", news);
						  
						  news.setNews_url(connUrl6);
						  news.setNews_date(newsSearch1.getItems().get(5).getPubDate());
						  news.setNews_title(str6);
						  news.setNews_category_id(c_id6);
						  news.setNews_id(6);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews6", news);
						  
						  news.setNews_url(connUrl7);
						  news.setNews_date(newsSearch1.getItems().get(6).getPubDate());
						  news.setNews_title(str7);
						  news.setNews_category_id(c_id7);
						  news.setNews_id(7);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews7", news);
						  
						  news.setNews_url(connUrl8);
						  news.setNews_date(newsSearch1.getItems().get(7).getPubDate());
						  news.setNews_title(str8);
						  news.setNews_category_id(c_id8);
						  news.setNews_id(8);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews8", news);
						  
						  news.setNews_url(connUrl9);
						  news.setNews_date(newsSearch1.getItems().get(8).getPubDate());
						  news.setNews_title(str9);
						  news.setNews_category_id(c_id9);
						  news.setNews_id(9);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews9", news);
						  
						  
				
				   /** ----- 2번 키워드 -----*/
				   j = 0;
				   for(int k=0; k<50; k++) {
					   if(list2.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
						   item2[j] = list2.get(k);
						   j++;
				      } else if(list2.get(k).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
				    	  item2[j] = list2.get(k);
				    	  j++;
				      } else if(list2.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
				    	  item2[j] = list2.get(k);
				    	  j++;
				      	  }
				      }
				   
					  // 뉴스 제목 ->  ele
					  // 뉴스 원문 ->  element
					  // category_id -> c_id
					  connUrl1 = item2[0].getLink();
					  doc1 = Jsoup.connect(connUrl1).get();
					  ele1 = null;
					  element1 = null;
					  c_id1 = 0;
					  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele1 = doc1.select("h2.end_tit");
						  element1 = doc1.select("div.end_body_wrp");
						  c_id1 = 106;
					  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
						 ele1 = doc1.select("h4.title");
						 element1 = doc1.select("div#newsEndContents");
						 c_id1 = 107;
					  } else {
						  ele1 = doc1.select("h3#articleTitle");
						  element1 = doc1.select("div#articleBodyContents");
						  c_id1 = Integer.parseInt(connUrl1.substring(61, 64));
					  }
					  str1 = ele1.text();
					  
					
					  connUrl2 = item2[1].getLink();
					  doc2 = Jsoup.connect(connUrl2).get();
					  ele2 = null;
					  element2 = null;
					  c_id2 = 0;
					  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele2 = doc2.select("h2.end_tit");
						  element2 = doc2.select("div.end_body_wrp");
						  c_id2 = 106;
					  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele2 = doc2.select("h4.title");
							 element2 = doc2.select("div#newsEndContents");
							 c_id2 = 107;
					  } else {
						  ele2 = doc2.select("h3#articleTitle");
						  element2 = doc2.select("div#articleBodyContents");
						  c_id2 = Integer.parseInt(connUrl2.substring(61, 64));
					  }
					  str2 = ele2.text();
					  
					
					  connUrl3 = item2[2].getLink();
					  doc3 = Jsoup.connect(connUrl3).get();
					  ele3 = null;
					  element3 = null;
					  c_id3 = 0;
					  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele3 = doc3.select("h2.end_tit");
						  element3 = doc3.select("div.end_body_wrp");
						  c_id3 = 106;
					  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele3 = doc3.select("h4.title");
							 element3 = doc3.select("div#newsEndContents");
							 c_id3 = 107;
					  } else {
						  ele3 = doc3.select("h3#articleTitle");
						  element3 = doc3.select("div#articleBodyContents");
						  c_id3 = Integer.parseInt(connUrl3.substring(61, 64));
					  }
					  str3 = ele3.text();
					  
					
					  connUrl4 = item2[3].getLink();
					  doc4 = Jsoup.connect(connUrl4).get();
					  ele4 = null;
					  element4 = null;
					  c_id4 = 0;
					  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele4 = doc4.select("h2.end_tit");
						  element4 = doc4.select("div.end_body_wrp");
						  c_id4 = 106;
					  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele4 = doc4.select("h4.title");
							 element4 = doc4.select("div#newsEndContents");
							 c_id4 = 107;
					  } else {
						  ele4 = doc4.select("h3#articleTitle");
						  element4 = doc4.select("div#articleBodyContents");
						  c_id4 = Integer.parseInt(connUrl4.substring(61, 64));
					  }
					  str4 = ele4.text();
					  
					
					  connUrl5 = item2[4].getLink();
					  doc5 = Jsoup.connect(connUrl5).get();
					  ele5 = null;
					  element5 = null;
					  c_id5 = 0;
					  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele5 = doc5.select("h2.end_tit");
						  element5 = doc5.select("div.end_body_wrp");
						  c_id5 = 106;
					  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele5 = doc5.select("h4.title");
							 element5 = doc5.select("div#newsEndContents");
							 c_id5 = 107;
					  } else {
						  ele5 = doc5.select("h3#articleTitle");
						  element5 = doc5.select("div#articleBodyContents");
						  c_id5 = Integer.parseInt(connUrl5.substring(61, 64));
					  }
					  str5 = ele5.text();
					  
					  connUrl6 = item2[5].getLink();
					  doc6 = Jsoup.connect(connUrl6).get();
					  ele6 = null;
					  element6 = null;
					  c_id6 = 0;
					  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele6 = doc6.select("h2.end_tit");
						  element6 = doc6.select("div.end_body_wrp");
						  c_id6 = 106;
					  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
						  ele6 = doc6.select("h4.title");
						  element6 = doc6.select("div#newsEndContents");
						  c_id6 = 107;
					  } else {
						  ele6 = doc6.select("h3#articleTitle");
						  element6 = doc6.select("div#articleBodyContents");
						  c_id6 = Integer.parseInt(connUrl6.substring(61, 64));
					  }
						  str6 = ele6.text();
						  
						
						  connUrl7 = item2[6].getLink();
						  doc7 = Jsoup.connect(connUrl7).get();
						  ele7 = null;
						  element7 = null;
						  c_id7 = 0;
						  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele7 = doc7.select("h2.end_tit");
							  element7 = doc7.select("div.end_body_wrp");
							  c_id7 = 106;
						  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele7 = doc7.select("h4.title");
								 element7 = doc7.select("div#newsEndContents");
								 c_id7 = 107;
						  } else {
							  ele7 = doc7.select("h3#articleTitle");
							  element7 = doc7.select("div#articleBodyContents");
							  c_id7 = Integer.parseInt(connUrl7.substring(61, 64));
						  }
						  str7 = ele7.text();
						  
						
						  connUrl8 = item2[7].getLink();
						  doc8 = Jsoup.connect(connUrl8).get();
						  ele8 = null;
						  element8 = null;
						  c_id8 = 0;
						  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele8 = doc8.select("h2.end_tit");
							  element8 = doc8.select("div.end_body_wrp");
							  c_id8 = 106;
						  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele8 = doc8.select("h4.title");
								 element8 = doc8.select("div#newsEndContents");
								 c_id8 = 107;
						  } else {
							  ele8 = doc8.select("h3#articleTitle");
							  element8 = doc8.select("div#articleBodyContents");
							  c_id8 = Integer.parseInt(connUrl8.substring(61, 64));
						  }
						  str8 = ele8.text();
						  
						
						  connUrl9 = item2[8].getLink();
						  doc9 = Jsoup.connect(connUrl9).get();
						  ele9 = null;
						  element9 = null;
						  c_id9 = 0;
						  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele9 = doc9.select("h2.end_tit");
							  element9 = doc9.select("div.end_body_wrp");
							  c_id9 = 106;
						  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele9 = doc9.select("h4.title");
								 element9 = doc9.select("div#newsEndContents");
								 c_id9 = 107;
						  } else {
							  ele9 = doc9.select("h3#articleTitle");
							  element9 = doc9.select("div#articleBodyContents");
							  c_id9 = Integer.parseInt(connUrl9.substring(61, 64));
						  }
						  str9 = ele9.text();

						  /** 2번 키워드 */
						  input.setNews_keyword(rank[1]);
//						  sqlSession.insert("keywordMapper.insertKeyword", input);
						  sqlSession.update("keywordMapper.updateKeyword2", input);
						  
						  news.setNews_keyword(rank[1]);
						  news.setNews_url(connUrl1);
						  news.setNews_date(newsSearch2.getItems().get(0).getPubDate());
						  news.setNews_title(str1);
						  news.setNews_category_id(c_id1);
						  news.setNews_id(10);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews1", news);
						  
						  news.setNews_url(connUrl2);
						  news.setNews_date(newsSearch2.getItems().get(1).getPubDate());
						  news.setNews_title(str2);
						  news.setNews_category_id(c_id2);
						  news.setNews_id(11);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews2", news);
						  
						  news.setNews_url(connUrl3);
						  news.setNews_date(newsSearch2.getItems().get(2).getPubDate());
						  news.setNews_title(str3);
						  news.setNews_category_id(c_id3);
						  news.setNews_id(12);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews3", news);
						  
						  news.setNews_url(connUrl4);
						  news.setNews_date(newsSearch2.getItems().get(3).getPubDate());
						  news.setNews_title(str4);
						  news.setNews_category_id(c_id4);
						  news.setNews_id(13);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews4", news);
						  
						  news.setNews_url(connUrl5);
						  news.setNews_date(newsSearch2.getItems().get(4).getPubDate());
						  news.setNews_title(str5);
						  news.setNews_category_id(c_id5);
						  news.setNews_id(14);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews5", news);
						  
						  news.setNews_url(connUrl6);
						  news.setNews_date(newsSearch2.getItems().get(5).getPubDate());
						  news.setNews_title(str6);
						  news.setNews_category_id(c_id6);
						  news.setNews_id(15);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews6", news);
						  
						  news.setNews_url(connUrl7);
						  news.setNews_date(newsSearch2.getItems().get(6).getPubDate());
						  news.setNews_title(str7);
						  news.setNews_category_id(c_id7);
						  news.setNews_id(16);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews7", news);
						  
						  news.setNews_url(connUrl8);
						  news.setNews_date(newsSearch2.getItems().get(7).getPubDate());
						  news.setNews_title(str8);
						  news.setNews_category_id(c_id8);
						  news.setNews_id(17);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews8", news);
						  
						  news.setNews_url(connUrl9);
						  news.setNews_date(newsSearch2.getItems().get(8).getPubDate());
						  news.setNews_title(str9);
						  news.setNews_category_id(c_id9);
						  news.setNews_id(18);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews9", news);	    
				   
					  
					/** ----- 3번 키워드 -----*/
				   j = 0;
				   for(int k=0; k<50; k++) {
					   if(list3.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
						   item3[j] = list3.get(k);
						   j++;
				      } else if(list3.get(k).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
				    	  item3[j] = list3.get(k);
				    	  j++;
				      } else if(list3.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
				    	  item3[j] = list3.get(k);
				    	  j++;
				      	  }
				      }
				   

					  // 뉴스 제목 ->  ele
					  // 뉴스 원문 ->  element
					  // category_id -> c_id
					  connUrl1 = item3[0].getLink();
					  doc1 = Jsoup.connect(connUrl1).get();
					  ele1 = null;
					  element1 = null;
					  c_id1 = 0;
					  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele1 = doc1.select("h2.end_tit");
						  element1 = doc1.select("div.end_body_wrp");
						  c_id1 = 106;
					  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
						 ele1 = doc1.select("h4.title");
						 element1 = doc1.select("div#newsEndContents");
						 c_id1 = 107;
					  } else {
						  ele1 = doc1.select("h3#articleTitle");
						  element1 = doc1.select("div#articleBodyContents");
						  c_id1 = Integer.parseInt(connUrl1.substring(61, 64));
					  }
					  str1 = ele1.text();
					  
					
					  connUrl2 = item3[1].getLink();
					  doc2 = Jsoup.connect(connUrl2).get();
					  ele2 = null;
					  element2 = null;
					  c_id2 = 0;
					  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele2 = doc2.select("h2.end_tit");
						  element2 = doc2.select("div.end_body_wrp");
						  c_id2 = 106;
					  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele2 = doc2.select("h4.title");
							 element2 = doc2.select("div#newsEndContents");
							 c_id2 = 107;
					  } else {
						  ele2 = doc2.select("h3#articleTitle");
						  element2 = doc2.select("div#articleBodyContents");
						  c_id2 = Integer.parseInt(connUrl2.substring(61, 64));
					  }
					  str2 = ele2.text();
					  
					
					  connUrl3 = item3[2].getLink();
					  doc3 = Jsoup.connect(connUrl3).get();
					  ele3 = null;
					  element3 = null;
					  c_id3 = 0;
					  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele3 = doc3.select("h2.end_tit");
						  element3 = doc3.select("div.end_body_wrp");
						  c_id3 = 106;
					  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele3 = doc3.select("h4.title");
							 element3 = doc3.select("div#newsEndContents");
							 c_id3 = 107;
					  } else {
						  ele3 = doc3.select("h3#articleTitle");
						  element3 = doc3.select("div#articleBodyContents");
						  c_id3 = Integer.parseInt(connUrl3.substring(61, 64));
					  }
					  str3 = ele3.text();
					  
					
					  connUrl4 = item3[3].getLink();
					  doc4 = Jsoup.connect(connUrl4).get();
					  ele4 = null;
					  element4 = null;
					  c_id4 = 0;
					  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele4 = doc4.select("h2.end_tit");
						  element4 = doc4.select("div.end_body_wrp");
						  c_id4 = 106;
					  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele4 = doc4.select("h4.title");
							 element4 = doc4.select("div#newsEndContents");
							 c_id4 = 107;
					  } else {
						  ele4 = doc4.select("h3#articleTitle");
						  element4 = doc4.select("div#articleBodyContents");
						  c_id4 = Integer.parseInt(connUrl4.substring(61, 64));
					  }
					  str4 = ele4.text();
					  
					
					  connUrl5 = item3[4].getLink();
					  doc5 = Jsoup.connect(connUrl5).get();
					  ele5 = null;
					  element5 = null;
					  c_id5 = 0;
					  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele5 = doc5.select("h2.end_tit");
						  element5 = doc5.select("div.end_body_wrp");
						  c_id5 = 106;
					  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele5 = doc5.select("h4.title");
							 element5 = doc5.select("div#newsEndContents");
							 c_id5 = 107;
					  } else {
						  ele5 = doc5.select("h3#articleTitle");
						  element5 = doc5.select("div#articleBodyContents");
						  c_id5 = Integer.parseInt(connUrl5.substring(61, 64));
					  }
					  str5 = ele5.text();
					  
					  connUrl6 = item3[5].getLink();
					  doc6 = Jsoup.connect(connUrl6).get();
					  ele6 = null;
					  element6 = null;
					  c_id6 = 0;
					  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele6 = doc6.select("h2.end_tit");
						  element6 = doc6.select("div.end_body_wrp");
						  c_id6 = 106;
					  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
						  ele6 = doc6.select("h4.title");
						  element6 = doc6.select("div#newsEndContents");
						  c_id6 = 107;
					  } else {
						  ele6 = doc6.select("h3#articleTitle");
						  element6 = doc6.select("div#articleBodyContents");
						  c_id6 = Integer.parseInt(connUrl6.substring(61, 64));
					  }
						  str6 = ele6.text();
						  
						
						  connUrl7 = item3[6].getLink();
						  doc7 = Jsoup.connect(connUrl7).get();
						  ele7 = null;
						  element7 = null;
						  c_id7 = 0;
						  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele7 = doc7.select("h2.end_tit");
							  element7 = doc7.select("div.end_body_wrp");
							  c_id7 = 106;
						  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele7 = doc7.select("h4.title");
								 element7 = doc7.select("div#newsEndContents");
								 c_id7 = 107;
						  } else {
							  ele7 = doc7.select("h3#articleTitle");
							  element7 = doc7.select("div#articleBodyContents");
							  c_id7 = Integer.parseInt(connUrl7.substring(61, 64));
						  }
						  str7 = ele7.text();
						  
						
						  connUrl8 = item3[7].getLink();
						  doc8 = Jsoup.connect(connUrl8).get();
						  ele8 = null;
						  element8 = null;
						  c_id8 = 0;
						  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele8 = doc8.select("h2.end_tit");
							  element8 = doc8.select("div.end_body_wrp");
							  c_id8 = 106;
						  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele8 = doc8.select("h4.title");
								 element8 = doc8.select("div#newsEndContents");
								 c_id8 = 107;
						  } else {
							  ele8 = doc8.select("h3#articleTitle");
							  element8 = doc8.select("div#articleBodyContents");
							  c_id8 = Integer.parseInt(connUrl8.substring(61, 64));
						  }
						  str8 = ele8.text();
						  
						
						  connUrl9 = item3[8].getLink();
						  doc9 = Jsoup.connect(connUrl9).get();
						  ele9 = null;
						  element9 = null;
						  c_id9 = 0;
						  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele9 = doc9.select("h2.end_tit");
							  element9 = doc9.select("div.end_body_wrp");
							  c_id9 = 106;
						  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele9 = doc9.select("h4.title");
								 element9 = doc9.select("div#newsEndContents");
								 c_id9 = 107;
						  } else {
							  ele9 = doc9.select("h3#articleTitle");
							  element9 = doc9.select("div#articleBodyContents");
							  c_id9 = Integer.parseInt(connUrl9.substring(61, 64));
						  }
						  str9 = ele9.text();
						  
						  /** 3번 키워드 */
						  input.setNews_keyword(rank[2]);
//						  sqlSession.insert("keywordMapper.insertKeyword", input);
						  sqlSession.update("keywordMapper.updateKeyword3", input);
						  
						  news.setNews_keyword(rank[2]);
						  news.setNews_url(connUrl1);
						  news.setNews_date(newsSearch3.getItems().get(0).getPubDate());
						  news.setNews_title(str1);
						  news.setNews_category_id(c_id1);
						  news.setNews_id(19);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews1", news);
						  
						  news.setNews_url(connUrl2);
						  news.setNews_date(newsSearch3.getItems().get(1).getPubDate());
						  news.setNews_title(str2);
						  news.setNews_category_id(c_id2);
						  news.setNews_id(20);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews2", news);
						  
						  news.setNews_url(connUrl3);
						  news.setNews_date(newsSearch3.getItems().get(2).getPubDate());
						  news.setNews_title(str3);
						  news.setNews_category_id(c_id3);
						  news.setNews_id(21);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews3", news);
						  
						  news.setNews_url(connUrl4);
						  news.setNews_date(newsSearch3.getItems().get(3).getPubDate());
						  news.setNews_title(str4);
						  news.setNews_category_id(c_id4);
						  news.setNews_id(22);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews4", news);
						  
						  news.setNews_url(connUrl5);
						  news.setNews_date(newsSearch3.getItems().get(4).getPubDate());
						  news.setNews_title(str5);
						  news.setNews_category_id(c_id5);
						  news.setNews_id(23);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews5", news);
						  
						  news.setNews_url(connUrl6);
						  news.setNews_date(newsSearch3.getItems().get(5).getPubDate());
						  news.setNews_title(str6);
						  news.setNews_category_id(c_id6);
						  news.setNews_id(24);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews6", news);
						  
						  news.setNews_url(connUrl7);
						  news.setNews_date(newsSearch3.getItems().get(6).getPubDate());
						  news.setNews_title(str7);
						  news.setNews_category_id(c_id7);
						  news.setNews_id(25);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews7", news);
						  
						  news.setNews_url(connUrl8);
						  news.setNews_date(newsSearch3.getItems().get(7).getPubDate());
						  news.setNews_title(str8);
						  news.setNews_category_id(c_id8);
						  news.setNews_id(26);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews8", news);
						  
						  news.setNews_url(connUrl9);
						  news.setNews_date(newsSearch3.getItems().get(8).getPubDate());
						  news.setNews_title(str9);
						  news.setNews_category_id(c_id9);
						  news.setNews_id(27);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews9", news);
						  
				   
				   /** ----- 4번 키워드 -----*/
				   j = 0;
				   for(int k=0; k<50; k++) {
					   if(list4.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
						   item4[j] = list4.get(k);
						   j++;
				      } else if(list4.get(k).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
				    	  item4[j] = list4.get(k);
				    	  j++;
				      } else if(list4.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
				    	  item4[j] = list4.get(k);
				    	  j++;
				      	  }
				      }
				   
					  // 뉴스 제목 ->  ele
					  // 뉴스 원문 ->  element
					  // category_id -> c_id
					  connUrl1 = item4[0].getLink();
					  doc1 = Jsoup.connect(connUrl1).get();
					  ele1 = null;
					  element1 = null;
					  c_id1 = 0;
					  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele1 = doc1.select("h2.end_tit");
						  element1 = doc1.select("div.end_body_wrp");
						  c_id1 = 106;
					  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
						 ele1 = doc1.select("h4.title");
						 element1 = doc1.select("div#newsEndContents");
						 c_id1 = 107;
					  } else {
						  ele1 = doc1.select("h3#articleTitle");
						  element1 = doc1.select("div#articleBodyContents");
						  c_id1 = Integer.parseInt(connUrl1.substring(61, 64));
					  }
					  str1 = ele1.text();
					  
					
					  connUrl2 = item4[1].getLink();
					  doc2 = Jsoup.connect(connUrl2).get();
					  ele2 = null;
					  element2 = null;
					  c_id2 = 0;
					  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele2 = doc2.select("h2.end_tit");
						  element2 = doc2.select("div.end_body_wrp");
						  c_id2 = 106;
					  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele2 = doc2.select("h4.title");
							 element2 = doc2.select("div#newsEndContents");
							 c_id2 = 107;
					  } else {
						  ele2 = doc2.select("h3#articleTitle");
						  element2 = doc2.select("div#articleBodyContents");
						  c_id2 = Integer.parseInt(connUrl2.substring(61, 64));
					  }
					  str2 = ele2.text();
					  
					
					  connUrl3 = item4[2].getLink();
					  doc3 = Jsoup.connect(connUrl3).get();
					  ele3 = null;
					  element3 = null;
					  c_id3 = 0;
					  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele3 = doc3.select("h2.end_tit");
						  element3 = doc3.select("div.end_body_wrp");
						  c_id3 = 106;
					  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele3 = doc3.select("h4.title");
							 element3 = doc3.select("div#newsEndContents");
							 c_id3 = 107;
					  } else {
						  ele3 = doc3.select("h3#articleTitle");
						  element3 = doc3.select("div#articleBodyContents");
						  c_id3 = Integer.parseInt(connUrl3.substring(61, 64));
					  }
					  str3 = ele3.text();
					  
					
					  connUrl4 = item4[3].getLink();
					  doc4 = Jsoup.connect(connUrl4).get();
					  ele4 = null;
					  element4 = null;
					  c_id4 = 0;
					  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele4 = doc4.select("h2.end_tit");
						  element4 = doc4.select("div.end_body_wrp");
						  c_id4 = 106;
					  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele4 = doc4.select("h4.title");
							 element4 = doc4.select("div#newsEndContents");
							 c_id4 = 107;
					  } else {
						  ele4 = doc4.select("h3#articleTitle");
						  element4 = doc4.select("div#articleBodyContents");
						  c_id4 = Integer.parseInt(connUrl4.substring(61, 64));
					  }
					  str4 = ele4.text();
					  
					
					  connUrl5 = item4[4].getLink();
					  doc5 = Jsoup.connect(connUrl5).get();
					  ele5 = null;
					  element5 = null;
					  c_id5 = 0;
					  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele5 = doc5.select("h2.end_tit");
						  element5 = doc5.select("div.end_body_wrp");
						  c_id5 = 106;
					  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele5 = doc5.select("h4.title");
							 element5 = doc5.select("div#newsEndContents");
							 c_id5 = 107;
					  } else {
						  ele5 = doc5.select("h3#articleTitle");
						  element5 = doc5.select("div#articleBodyContents");
						  c_id5 = Integer.parseInt(connUrl5.substring(61, 64));
					  }
					  str5 = ele5.text();
					  
					  connUrl6 = item4[5].getLink();
					  doc6 = Jsoup.connect(connUrl6).get();
					  ele6 = null;
					  element6 = null;
					  c_id6 = 0;
					  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele6 = doc6.select("h2.end_tit");
						  element6 = doc6.select("div.end_body_wrp");
						  c_id6 = 106;
					  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
						  ele6 = doc6.select("h4.title");
						  element6 = doc6.select("div#newsEndContents");
						  c_id6 = 107;
					  } else {
						  ele6 = doc6.select("h3#articleTitle");
						  element6 = doc6.select("div#articleBodyContents");
						  c_id6 = Integer.parseInt(connUrl6.substring(61, 64));
					  }
						  str6 = ele6.text();
						  
						
						  connUrl7 = item4[6].getLink();
						  doc7 = Jsoup.connect(connUrl7).get();
						  ele7 = null;
						  element7 = null;
						  c_id7 = 0;
						  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele7 = doc7.select("h2.end_tit");
							  element7 = doc7.select("div.end_body_wrp");
							  c_id7 = 106;
						  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele7 = doc7.select("h4.title");
								 element7 = doc7.select("div#newsEndContents");
								 c_id7 = 107;
						  } else {
							  ele7 = doc7.select("h3#articleTitle");
							  element7 = doc7.select("div#articleBodyContents");
							  c_id7 = Integer.parseInt(connUrl7.substring(61, 64));
						  }
						  str7 = ele7.text();
						  
						
						  connUrl8 = item4[7].getLink();
						  doc8 = Jsoup.connect(connUrl8).get();
						  ele8 = null;
						  element8 = null;
						  c_id8 = 0;
						  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele8 = doc8.select("h2.end_tit");
							  element8 = doc8.select("div.end_body_wrp");
							  c_id8 = 106;
						  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele8 = doc8.select("h4.title");
								 element8 = doc8.select("div#newsEndContents");
								 c_id8 = 107;
						  } else {
							  ele8 = doc8.select("h3#articleTitle");
							  element8 = doc8.select("div#articleBodyContents");
							  c_id8 = Integer.parseInt(connUrl8.substring(61, 64));
						  }
						  str8 = ele8.text();
						  
						
						  connUrl9 = item4[8].getLink();
						  doc9 = Jsoup.connect(connUrl9).get();
						  ele9 = null;
						  element9 = null;
						  c_id9 = 0;
						  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele9 = doc9.select("h2.end_tit");
							  element9 = doc9.select("div.end_body_wrp");
							  c_id9 = 106;
						  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele9 = doc9.select("h4.title");
								 element9 = doc9.select("div#newsEndContents");
								 c_id9 = 107;
						  } else {
							  ele9 = doc9.select("h3#articleTitle");
							  element9 = doc9.select("div#articleBodyContents");
							  c_id9 = Integer.parseInt(connUrl9.substring(61, 64));
						  }
						  str9 = ele9.text();
						  
						  /** 4번 키워드 */
						  input.setNews_keyword(rank[3]);
//						  sqlSession.insert("keywordMapper.insertKeyword", input);
						  sqlSession.update("keywordMapper.updateKeyword4", input);
						  
						  news.setNews_keyword(rank[3]);
						  news.setNews_url(connUrl1);
						  news.setNews_date(newsSearch4.getItems().get(0).getPubDate());
						  news.setNews_title(str1);
						  news.setNews_category_id(c_id1);
						  news.setNews_id(28);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews1", news);
						  
						  news.setNews_url(connUrl2);
						  news.setNews_date(newsSearch4.getItems().get(1).getPubDate());
						  news.setNews_title(str2);
						  news.setNews_category_id(c_id2);
						  news.setNews_id(29);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews2", news);
						  
						  news.setNews_url(connUrl3);
						  news.setNews_date(newsSearch4.getItems().get(2).getPubDate());
						  news.setNews_title(str3);
						  news.setNews_category_id(c_id3);
						  news.setNews_id(30);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews3", news);
						  
						  news.setNews_url(connUrl4);
						  news.setNews_date(newsSearch4.getItems().get(3).getPubDate());
						  news.setNews_title(str4);
						  news.setNews_category_id(c_id4);
						  news.setNews_id(31);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews4", news);
						  
						  news.setNews_url(connUrl5);
						  news.setNews_date(newsSearch4.getItems().get(4).getPubDate());
						  news.setNews_title(str5);
						  news.setNews_category_id(c_id5);
						  news.setNews_id(32);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews5", news);
						  
						  news.setNews_url(connUrl6);
						  news.setNews_date(newsSearch4.getItems().get(5).getPubDate());
						  news.setNews_title(str6);
						  news.setNews_category_id(c_id6);
						  news.setNews_id(33);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews6", news);
						  
						  news.setNews_url(connUrl7);
						  news.setNews_date(newsSearch4.getItems().get(6).getPubDate());
						  news.setNews_title(str7);
						  news.setNews_category_id(c_id7);
						  news.setNews_id(34);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews7", news);
						  
						  news.setNews_url(connUrl8);
						  news.setNews_date(newsSearch4.getItems().get(7).getPubDate());
						  news.setNews_title(str8);
						  news.setNews_category_id(c_id8);
						  news.setNews_id(35);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews8", news);
						  
						  news.setNews_url(connUrl9);
						  news.setNews_date(newsSearch4.getItems().get(8).getPubDate());
						  news.setNews_title(str9);
						  news.setNews_category_id(c_id9);
						  news.setNews_id(36);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews9", news);
				   

				   
				   /** ----- 5번 키워드 -----*/
				   j = 0;
				   for(int k=0; k<50; k++) {
					   if(list5.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec")) {
						   item5[j] = list5.get(k);
						   j++;
				      } else if(list5.get(k).getLink().contains("https://sports.news.naver.com/news.nhn?oid")) {
				    	  item5[j] = list5.get(k);
				    	  j++;
				      } else if(list5.get(k).getLink().contains("https://news.naver.com/main/read.naver?mode=LSD&mid=shm")) {
				    	  item5[j] = list5.get(k);
				    	  j++;
				      	  }
				      }
			   
					  // 뉴스 제목 ->  ele
					  // 뉴스 원문 ->  element
					  // category_id -> c_id
					  connUrl1 = item5[0].getLink();
					  doc1 = Jsoup.connect(connUrl1).get();
					  ele1 = null;
					  element1 = null;
					  c_id1 = 0;
					  if(connUrl1.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele1 = doc1.select("h2.end_tit");
						  element1 = doc1.select("div.end_body_wrp");
						  c_id1 = 106;
					  } else if (connUrl1.contains("https://sports.news.naver.com/news.nhn?oid")) {
						 ele1 = doc1.select("h4.title");
						 element1 = doc1.select("div#newsEndContents");
						 c_id1 = 107;
					  } else {
						  ele1 = doc1.select("h3#articleTitle");
						  element1 = doc1.select("div#articleBodyContents");
						  c_id1 = Integer.parseInt(connUrl1.substring(61, 64));
					  }
					  str1 = ele1.text();
					  
					
					  connUrl2 = item5[1].getLink();
					  doc2 = Jsoup.connect(connUrl2).get();
					  ele2 = null;
					  element2 = null;
					  c_id2 = 0;
					  if(connUrl2.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele2 = doc2.select("h2.end_tit");
						  element2 = doc2.select("div.end_body_wrp");
						  c_id2 = 106;
					  } else if (connUrl2.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele2 = doc2.select("h4.title");
							 element2 = doc2.select("div#newsEndContents");
							 c_id2 = 107;
					  } else {
						  ele2 = doc2.select("h3#articleTitle");
						  element2 = doc2.select("div#articleBodyContents");
						  c_id2 = Integer.parseInt(connUrl2.substring(61, 64));
					  }
					  str2 = ele2.text();
					  
					
					  connUrl3 = item5[2].getLink();
					  doc3 = Jsoup.connect(connUrl3).get();
					  ele3 = null;
					  element3 = null;
					  c_id3 = 0;
					  if(connUrl3.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele3 = doc3.select("h2.end_tit");
						  element3 = doc3.select("div.end_body_wrp");
						  c_id3 = 106;
					  } else if (connUrl3.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele3 = doc3.select("h4.title");
							 element3 = doc3.select("div#newsEndContents");
							 c_id3 = 107;
					  } else {
						  ele3 = doc3.select("h3#articleTitle");
						  element3 = doc3.select("div#articleBodyContents");
						  c_id3 = Integer.parseInt(connUrl3.substring(61, 64));
					  }
					  str3 = ele3.text();
					  
					
					  connUrl4 = item5[3].getLink();
					  doc4 = Jsoup.connect(connUrl4).get();
					  ele4 = null;
					  element4 = null;
					  c_id4 = 0;
					  if(connUrl4.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele4 = doc4.select("h2.end_tit");
						  element4 = doc4.select("div.end_body_wrp");
						  c_id4 = 106;
					  } else if (connUrl4.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele4 = doc4.select("h4.title");
							 element4 = doc4.select("div#newsEndContents");
							 c_id4 = 107;
					  } else {
						  ele4 = doc4.select("h3#articleTitle");
						  element4 = doc4.select("div#articleBodyContents");
						  c_id4 = Integer.parseInt(connUrl4.substring(61, 64));
					  }
					  str4 = ele4.text();
					  
					
					  connUrl5 = item5[4].getLink();
					  doc5 = Jsoup.connect(connUrl5).get();
					  ele5 = null;
					  element5 = null;
					  c_id5 = 0;
					  if(connUrl5.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele5 = doc5.select("h2.end_tit");
						  element5 = doc5.select("div.end_body_wrp");
						  c_id5 = 106;
					  } else if (connUrl5.contains("https://sports.news.naver.com/news.nhn?oid")) {
							 ele5 = doc5.select("h4.title");
							 element5 = doc5.select("div#newsEndContents");
							 c_id5 = 107;
					  } else {
						  ele5 = doc5.select("h3#articleTitle");
						  element5 = doc5.select("div#articleBodyContents");
						  c_id5 = Integer.parseInt(connUrl5.substring(61, 64));
					  }
					  str5 = ele5.text();
					  
					  connUrl6 = item5[5].getLink();
					  doc6 = Jsoup.connect(connUrl6).get();
					  ele6 = null;
					  element6 = null;
					  c_id6 = 0;
					  if(connUrl6.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
						  ele6 = doc6.select("h2.end_tit");
						  element6 = doc6.select("div.end_body_wrp");
						  c_id6 = 106;
					  } else if (connUrl6.contains("https://sports.news.naver.com/news.nhn?oid")) {
						  ele6 = doc6.select("h4.title");
						  element6 = doc6.select("div#newsEndContents");
						  c_id6 = 107;
					  } else {
						  ele6 = doc6.select("h3#articleTitle");
						  element6 = doc6.select("div#articleBodyContents");
						  c_id6 = Integer.parseInt(connUrl6.substring(61, 64));
					  }
						  str6 = ele6.text();
						  
						
						  connUrl7 = item5[6].getLink();
						  doc7 = Jsoup.connect(connUrl7).get();
						  ele7 = null;
						  element7 = null;
						  c_id7 = 0;
						  if(connUrl7.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele7 = doc7.select("h2.end_tit");
							  element7 = doc7.select("div.end_body_wrp");
							  c_id7 = 106;
						  } else if (connUrl7.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele7 = doc7.select("h4.title");
								 element7 = doc7.select("div#newsEndContents");
								 c_id7 = 107;
						  } else {
							  ele7 = doc7.select("h3#articleTitle");
							  element7 = doc7.select("div#articleBodyContents");
							  c_id7 = Integer.parseInt(connUrl7.substring(61, 64));
						  }
						  str7 = ele7.text();
						  
						
						  connUrl8 = item5[7].getLink();
						  doc8 = Jsoup.connect(connUrl8).get();
						  ele8 = null;
						  element8 = null;
						  c_id8 = 0;
						  if(connUrl8.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele8 = doc8.select("h2.end_tit");
							  element8 = doc8.select("div.end_body_wrp");
							  c_id8 = 106;
						  } else if (connUrl8.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele8 = doc8.select("h4.title");
								 element8 = doc8.select("div#newsEndContents");
								 c_id8 = 107;
						  } else {
							  ele8 = doc8.select("h3#articleTitle");
							  element8 = doc8.select("div#articleBodyContents");
							  c_id8 = Integer.parseInt(connUrl8.substring(61, 64));
						  }
						  str8 = ele8.text();
						  
						
						  connUrl9 = item5[8].getLink();
						  doc9 = Jsoup.connect(connUrl9).get();
						  ele9 = null;
						  element9 = null;
						  c_id9 = 0;
						  if(connUrl9.contains("https://news.naver.com/main/read.naver?mode=LSD&mid=sec&sid1=106")) {
							  ele9 = doc9.select("h2.end_tit");
							  element9 = doc9.select("div.end_body_wrp");
							  c_id9 = 106;
						  } else if (connUrl9.contains("https://sports.news.naver.com/news.nhn?oid")) {
								 ele9 = doc9.select("h4.title");
								 element9 = doc9.select("div#newsEndContents");
								 c_id9 = 107;
						  } else {
							  ele9 = doc9.select("h3#articleTitle");
							  element9 = doc9.select("div#articleBodyContents");
							  c_id9 = Integer.parseInt(connUrl9.substring(61, 64));
						  }
						  str9 = ele9.text();
					
						  /** 5번 키워드 */
						  input.setNews_keyword(rank[4]);
//						  sqlSession.insert("keywordMapper.insertKeyword", input);
						  sqlSession.update("keywordMapper.updateKeyword5", input);
						  
						  news.setNews_keyword(rank[4]);
						  news.setNews_url(connUrl1);
						  news.setNews_date(newsSearch5.getItems().get(0).getPubDate());
						  news.setNews_title(str1);
						  news.setNews_category_id(c_id1);
						  news.setNews_id(37);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews1", news);
						  
						  news.setNews_url(connUrl2);
						  news.setNews_date(newsSearch5.getItems().get(1).getPubDate());
						  news.setNews_title(str2);
						  news.setNews_category_id(c_id2);
						  news.setNews_id(38);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews2", news);
						  
						  news.setNews_url(connUrl3);
						  news.setNews_date(newsSearch5.getItems().get(2).getPubDate());
						  news.setNews_title(str3);
						  news.setNews_category_id(c_id3);
						  news.setNews_id(39);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews3", news);
						  
						  news.setNews_url(connUrl4);
						  news.setNews_date(newsSearch5.getItems().get(3).getPubDate());
						  news.setNews_title(str4);
						  news.setNews_category_id(c_id4);
						  news.setNews_id(40);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews4", news);
						  
						  news.setNews_url(connUrl5);
						  news.setNews_date(newsSearch5.getItems().get(4).getPubDate());
						  news.setNews_title(str5);
						  news.setNews_category_id(c_id5);
						  news.setNews_id(41);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews5", news);
						  
						  news.setNews_url(connUrl6);
						  news.setNews_date(newsSearch5.getItems().get(5).getPubDate());
						  news.setNews_title(str6);
						  news.setNews_category_id(c_id6);
						  news.setNews_id(42);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews6", news);
						  
						  news.setNews_url(connUrl7);
						  news.setNews_date(newsSearch5.getItems().get(6).getPubDate());
						  news.setNews_title(str7);
						  news.setNews_category_id(c_id7);
						  news.setNews_id(43);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews7", news);
						  
						  news.setNews_url(connUrl8);
						  news.setNews_date(newsSearch5.getItems().get(7).getPubDate());
						  news.setNews_title(str8);
						  news.setNews_category_id(c_id8);
						  news.setNews_id(44);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews8", news);
						  
						  news.setNews_url(connUrl9);
						  news.setNews_date(newsSearch5.getItems().get(8).getPubDate());
						  news.setNews_title(str9);
						  news.setNews_category_id(c_id9);
						  news.setNews_id(45);
//						  sqlSession.insert("newsMapper.insertNews", news);
						  sqlSession.update("newsMapper.updateNews9", news);
				
	
				  

	}
	
	
	


}
