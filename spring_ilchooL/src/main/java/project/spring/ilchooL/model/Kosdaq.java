package project.spring.ilchooL.model;

import lombok.Data;

@Data
public class Kosdaq {
	
	private String Date;
	private double kd_open;
	private double kd_high;
	private double kd_low;
	private double kd_close;
	private double kd_volume;
	private double kd_adj_close;

}
