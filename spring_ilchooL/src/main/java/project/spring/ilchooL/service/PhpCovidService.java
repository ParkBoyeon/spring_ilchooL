package project.spring.ilchooL.service;

import project.spring.ilchooL.model.Covid19Item;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PhpCovidService {
	public static final String BASE_URL = "http://itpaper.co.kr";
	
	@GET("/demo/covid19/all.php")
	Call<Covid19Item> getCovidCount();
}
