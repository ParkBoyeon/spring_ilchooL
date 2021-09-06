package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.Kosdaq;
import project.spring.ilchooL.model.Kospi;


public interface KosdaqService {
	
	
	/**
	 * 코스닥 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<Kosdaq> getKosdaqList() throws Exception;
	
	/**
	 * 코스피 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<Kospi> getKospiList() throws Exception;

}
