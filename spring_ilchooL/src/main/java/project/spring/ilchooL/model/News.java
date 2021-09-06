package project.spring.ilchooL.model;

import lombok.Data;

@Data
public class News {
	private int news_id;
	private String news_url;
	private String news_date;
	private String news_title;
	private int news_category_id;
	private String news_keyword;
	
}
