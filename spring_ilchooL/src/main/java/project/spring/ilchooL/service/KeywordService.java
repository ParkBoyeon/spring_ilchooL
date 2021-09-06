package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.Keyword;

public interface KeywordService {
	
	
	/**
	 * keyword 테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addKeyword() throws Exception;
	
	/**
	 * keyword 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword1() throws Exception;
	
	/**
	 * keyword 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword2() throws Exception;
	
	/**
	 * keyword 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword3() throws Exception;
	
	/**
	 * keyword 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword4() throws Exception;
	
	/**
	 * keyword 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword5() throws Exception;
	
	
	/**
	 * keyword 데이터 가져오기
	 * @return
	 * @throws Exception
	 */
	public List<Keyword> selectKeyword() throws Exception;


}
