package project.spring.ilchooL.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsSearch {
   
   @SerializedName("items")      		private List<items> items;
   
   @Data
   public class items {
      @SerializedName("title")    		private String title;
      @SerializedName("link")        	private String link;
      @SerializedName("description")   	private String description;
      @SerializedName("pubDate")      	private String pubDate;
      
      
   }

   

}