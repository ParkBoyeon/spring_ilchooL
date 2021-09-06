package project.spring.ilchooL.service;

import project.spring.ilchooL.model.forecastDust;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 미세먼지 OPEN API 데이터 가져오기 
 * @author: 박수인
 */

public interface forecastDustService {
public static final String BASE_URL = "http://apis.data.go.kr";
	
	@GET("/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=StBzGzV%2BbfGoWn%2F3PAB08w9ZZmJSz0lOX3%2BE8MlTapZsJqETWwd71dB8syBPznAhytzUVYMLSjXVxm5i5CADMA%3D%3D&returnType=json&dataTerm=DAILY&ver=1.3")
	Call<forecastDust> getforecastDust(@Query("stationName") String stationName);

}
