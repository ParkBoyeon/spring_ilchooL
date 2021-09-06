package project.spring.ilchooL.model;

import lombok.Data;

@Data
public class Kospi {
	
	private String Date;
	private double kp_open;
	private double kp_high;
	private double kp_low;
	private double kp_close;
	private double kp_volume;
	private double kp_adj_close;

}
