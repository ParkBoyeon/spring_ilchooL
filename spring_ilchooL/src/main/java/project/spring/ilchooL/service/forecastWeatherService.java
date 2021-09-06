package project.spring.ilchooL.service;

import project.spring.ilchooL.model.forecastWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 날씨 OPEN API 데이터 가져오기 
 * @author: 박수인
 */

public interface forecastWeatherService {
	public static final String BASE_URL ="http://apis.data.go.kr";

	//인증키 - StBzGzV%2BbfGoWn%2F3PAB08w9ZZmJSz0lOX3%2BE8MlTapZsJqETWwd71dB8syBPznAhytzUVYMLSjXVxm5i5CADMA%3D%3D
	//x좌표:61, y좌표:126 - 강남구 (로그인 전 기본위치)

			// base_date: 예보시각 - (전날 23시)
			@GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=StBzGzV%2BbfGoWn%2F3PAB08w9ZZmJSz0lOX3%2BE8MlTapZsJqETWwd71dB8syBPznAhytzUVYMLSjXVxm5i5CADMA%3D%3D&pageNo=1&numOfRows=266&dataType=JSON&base_time=2300")
			Call<forecastWeather> getforecastWeather(@Query("base_date") String prev_date, @Query("nx") int nx, @Query("ny") int ny);
			
}


