package project.spring.ilchooL.model;

import lombok.Data;

/**
 * 저장된 DB에서 다중행조회를 위한 Beans 
 * @author: 박수인
 */

@Data
public class Witem {
	private int weather_id;
	private String category;
	private String fcst_value;
	private String fcst_date;
	private String fcst_time;
	private String nx;
	private String ny;
}
