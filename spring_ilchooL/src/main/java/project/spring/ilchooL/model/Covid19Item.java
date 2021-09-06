package project.spring.ilchooL.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Covid19Item {
	@SerializedName("collection_datetime") private String datetime;
	@SerializedName("data") private Data_covid data_covid;
	
	@Data
	public class Data_covid {
		@SerializedName("서울") private Seoul seoul;
		
		@Data
		public class Seoul {
			@SerializedName("date") private String[] php_date;
			@SerializedName("active") private String[] php_active;
			@SerializedName("confirmed_acc") private String[] php_confirmed_acc;
			@SerializedName("death_acc") private String[] php_death_acc;
			@SerializedName("released_acc") private String[] php_released_acc;
			@SerializedName("confirmed") private String[] php_confirmed;
			@SerializedName("death") private String[] php_death;
			@SerializedName("released") private String[] php_released;
		}
	}
}
