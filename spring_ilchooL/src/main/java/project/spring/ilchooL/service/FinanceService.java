package project.spring.ilchooL.service;


public interface FinanceService {
	
	/**
	 * finance(해외증시)테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addFinance() throws Exception;
	
	
	/**
	 * finance_popular(인기종목)테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addFinancePopular() throws Exception;
	
	
	/**
	 * finance_top(거래상위)테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addFinanceTop() throws Exception;
	
	
	
	/**
	 * finance(해외증시)테이블 데이터 갱신
	 * @return
	 * @throws Exception
	 */
	public int updateFinance() throws Exception;
	
	
	/**
	 * finance_popular(인기종목)테이블 데이터 갱신
	 * @return
	 * @throws Exception
	 */
	public int updateFinancePopular() throws Exception;
	
	
	/**
	 * finance_top(거래상위)테이블 데이터 갱신
	 * @return
	 * @throws Exception
	 */
	public int updateFinanceTop() throws Exception;

}
