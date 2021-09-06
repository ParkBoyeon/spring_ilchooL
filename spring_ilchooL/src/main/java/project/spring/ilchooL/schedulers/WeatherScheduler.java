package project.spring.ilchooL.schedulers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.model.forecastWeather;
import project.spring.ilchooL.model.locItem;
import project.spring.ilchooL.model.forecastWeather.Response.Body.Items.Item;
import project.spring.ilchooL.service.LocService;
import project.spring.ilchooL.service.WeatherService;
import project.spring.ilchooL.service.forecastWeatherService;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 날씨 API에서 필요한 데이터를 받아와 DB에 저장하는 weatherService로 전달 
 * @author: 박수인
 */

@Slf4j
@Controller
public class WeatherScheduler {
	@Autowired RetrofitHelper retrofitHelper;
	@Autowired WeatherService weatherService;
	@Autowired LocService locService;
	
	public void collectWeather() {
		
		int nx = 0;
		int ny = 0;
		
		List<locItem> s_locItem = null;
		
		try {
			s_locItem = locService.searchLocItemAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	// end try
		
		for(int j = 0; j < s_locItem.size(); j++) {
			
			nx = s_locItem.get(j).getLoc_x();
			ny = s_locItem.get(j).getLoc_y();
			
			/** 1) API 연동 객체 생성 */
			// Retrofit 객체 생성
			Retrofit retrofit_weather = retrofitHelper.getRetrofit(forecastWeatherService.BASE_URL);
			
			// Service 객체를 생성한다. 구현체는 Retrofit이 자동 생성
			forecastWeatherService ForecastWeatherService = retrofit_weather.create(forecastWeatherService.class);
			
			/** 2) 검색 파라미터 처리 */
			// 하루전 날짜를 사용 prev_date : yyyymmdd 형식으로 생성한다. 
			Calendar past_c = Calendar.getInstance();
			past_c.add(Calendar.DAY_OF_MONTH, -1);
			String prev_date = String.format("%04d%02d%02d", past_c.get(Calendar.YEAR), past_c.get(Calendar.MONTH)+1, past_c.get(Calendar.DAY_OF_MONTH));
			
			/** 3) 날씨 데이터 받아오기 */
			Call<forecastWeather> call_weather = ForecastWeatherService.getforecastWeather(prev_date, nx, ny);
		
			forecastWeather ForecastWeather = null;
			
			try {
				ForecastWeather = call_weather.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 변수 list_weather 생성
			List<Item> list_weather = null;

			// 검색결과가 있다면 list_weather만 추출
			if (ForecastWeather != null) {
				list_weather = ForecastWeather.getResponse().getBody().getItems().getItem();
			} else {
				log.debug(">>>>>API 데이터 조회결과 없음");
				return;
			}
			
			// 'SKY,TMP,TMN,TMX'데이터를 담을 list_category 생성
			List<Item> list_category = new ArrayList<Item>();
	        
			for (int i = 0; i < list_weather.size(); i++) {
		           if(list_weather.get(i).getCategory().equals("SKY") || list_weather.get(i).getCategory().equals("TMP")) {
		        	   list_category.add(list_weather.get(i));
		           }else if (list_weather.get(i).getCategory().equals("TMN") || list_weather.get(i).getCategory().equals("TMX")) {
		        	   list_category.add(list_weather.get(i));
		           }else if (list_weather.get(i).getCategory().equals("PTY")) {
		        	   list_category.add(list_weather.get(i));
		           }
		        }	// end for
	        
	        /** 4) 수집 결과를 weatherService에 보내기 (DB에 저장) -> w_item */
	        try {
	         	 weatherService.collectWeather(list_category);
	          } catch (Exception e) {
	        	  log.error(e.getLocalizedMessage());
	        	  e.printStackTrace();
	        }	// end try
		}
	}
}
