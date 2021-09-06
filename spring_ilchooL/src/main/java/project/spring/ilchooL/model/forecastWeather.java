package project.spring.ilchooL.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * 날씨 API데이터를 DB에 넣기위한 Beans 
 * @author: 박수인
 */

@Data
public class forecastWeather {
@SerializedName("response") private Response response;
	
	@Data
	public class Response {
		@SerializedName("body") private Body body;
		
		@Data
		public class Body {
			@SerializedName("items") private Items items;
			
			@Data
			public class Items {
				@SerializedName("item")	private List<Item> item;
				
				@Data
				public class Item {
					private int weather_id;
					@SerializedName("category") private String category;
					@SerializedName("fcstValue") private String fcst_value;
					@SerializedName("fcstDate") private String fcst_date;
					@SerializedName("fcstTime") private String fcst_time;
					@SerializedName("nx") private String nx;
					@SerializedName("ny") private String ny;
				}
			}
		}
	}
}