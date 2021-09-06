package project.spring.ilchooL.model;

import lombok.Data;

@Data
public class CovidItem {
	private int covid_id;
	private String date;
	private int active;
	private int confirmed;
	private int confirmed_acc;
	private int released; 
	private int released_acc;
	private int death;
	private int death_acc;
}
