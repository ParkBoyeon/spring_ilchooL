package project.spring.ilchooL.model;

import lombok.Data;

/** finance_top(거래상위) 테이블 */
@Data
public class FinanceTop {
	private int top_id;
	private String[] t_name;
	private String[] t_amount;
	private String[] t_updown;
	private String[] t_prev;
	private String[] t_rate;
	
	private String t_name0;
	private String t_amount0;
	private String t_updown0;
	private String t_prev0;
	private String t_rate0;

}
