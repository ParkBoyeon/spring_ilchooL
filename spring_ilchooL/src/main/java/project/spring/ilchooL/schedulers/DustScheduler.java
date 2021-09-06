package project.spring.ilchooL.schedulers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.model.forecastDust;
import project.spring.ilchooL.model.locItem;
import project.spring.ilchooL.model.forecastDust.Response.Body.Items;
import project.spring.ilchooL.service.DustService;
import project.spring.ilchooL.service.LocService;
import project.spring.ilchooL.service.forecastDustService;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 미세먼지 API에서 필요한 데이터를 받아와 DB에 저장하는 dustService로 전달
 * @author: 박수인
 */

@Slf4j
@Controller
public class DustScheduler {
	@Autowired
	RetrofitHelper retrofitHelper;
	@Autowired
	DustService dustService;
	@Autowired LocService locService;
	

	public void collectDust() {
		
		String stationName = null;
		
		List<locItem> s_locItem = null;
		
		try {
			s_locItem = locService.searchLocItemAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	// end try
		
		for (int j = 0; j < s_locItem.size(); j++) {
			
			stationName = s_locItem.get(j).getLoc();
			
			/** 1) API 연동 객체 생성 */
			// Retrofit 객체 생성
			Retrofit retrofit_dust = retrofitHelper.getRetrofit(forecastDustService.BASE_URL);

			// Service 객체를 생성한다. 구현체는 Retrofit이 자동 생성
			forecastDustService ForecastDustService = retrofit_dust.create(forecastDustService.class);

			/** 3) 미세먼지 데이터 받아오기 */
			// 검색 결과 받아오기
			Call<forecastDust> call_dust = ForecastDustService.getforecastDust(stationName);
			forecastDust ForecastDust = null;

			try {
				ForecastDust = call_dust.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 변수명이 길어서 참조변수 생성
			List<Items> list_dust = null;

			// 검색결과가 있다면 list만 추출
			if (ForecastDust != null) {
				list_dust = ForecastDust.getResponse().getBody().getItems();
			} else {
				log.debug(">>>>>API 데이터 조회결과 없음");
				return;
			}
			
			for(int i = 0; i < list_dust.size(); i++) {
				list_dust.get(i).setStationName(stationName);
			}
			
			/** 4) 수집 결과를 dustService에 보내기 (DB에 저장) -> d_item */
	        try {
	         	 dustService.collectdust(list_dust);
	          } catch (Exception e) {
	        	  log.error(e.getLocalizedMessage());
	        	  e.printStackTrace();
	        }	// end try
		}
	}
}
