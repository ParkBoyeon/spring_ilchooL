package project.spring.ilchooL.service;

import project.spring.ilchooL.model.Keyword;
import project.spring.ilchooL.model.News;

public interface NewsService {

	/**
	 * News(뉴스) 테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addNews(News input) throws Exception;
	
	
	/**
	 * Keyword(뉴스키워드) 테이블에 데이터 추가
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int addKeyword(Keyword input) throws Exception;
	
	
	/**
	 * News(뉴스) 테이블에 데이터 갱신
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public int updateKeyword(Keyword input) throws Exception;
	
	
}
