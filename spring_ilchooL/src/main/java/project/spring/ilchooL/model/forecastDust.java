package project.spring.ilchooL.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 미세먼지 API데이터를 DB에 넣기위한 Beans 
 * @author: 박수인
 */

@Data
public class forecastDust {
	@SerializedName("response") private Response response;
	
	@Data
	public class Response {
		@SerializedName("body") private Body body;
		
		@Data
		public class Body {
			@SerializedName("items") private List<Items> items;
			
			@Data
			@NoArgsConstructor  //Default Constructor 추가
			@AllArgsConstructor
			public class Items {
				
				private int dust_id;
				
				@SerializedName("pm10Value")	private String pm10Value;
				@SerializedName("pm25Value")	private String pm25Value;
				@SerializedName("dataTime")		private String dataTime;
				
				private String stationName;
			}

		}
		
	}

}
