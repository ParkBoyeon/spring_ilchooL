package project.spring.ilchooL.model;

import lombok.Data;

/**
 * 미세먼지 API데이터를 조회하는 Beans 
 * @author: 박수인
 */

@Data
public class DustItem {
	private String dust_id;
	private String pm10Value;
	private String pm25Value;
	private String dataTime;
	private String stationName;
}