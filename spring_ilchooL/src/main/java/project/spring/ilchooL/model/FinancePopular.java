package project.spring.ilchooL.model;

import lombok.Data;

/** finance_popular(인기종목) 테이블 */
@Data
public class FinancePopular {
	private int popular_id;
	private String[] p_name;
	private String[] p_amount;
	private String[] p_prev;
	private String[] p_up;
	private String[] p_down;
	
	private String p_name0;
	private String p_amount0;
	private String p_prev0;
	private String p_updown0;

}
