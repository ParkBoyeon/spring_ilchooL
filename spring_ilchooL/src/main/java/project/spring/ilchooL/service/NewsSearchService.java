package project.spring.ilchooL.service;


import java.util.List;

import project.spring.ilchooL.model.NewsSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsSearchService {

	public static final String BASE_URL = "https://openapi.naver.com";
	
	@Headers({"X-Naver-Client-Id:Z4eNJKNSDX5iQQWxgDNk", "X-Naver-Client-Secret:TINYEnTg6T"})
	@GET("/v1/search/news.json")
	Call<NewsSearch> getNewsSearch(@Query("query") String query, @Query("display") int display);

   
}