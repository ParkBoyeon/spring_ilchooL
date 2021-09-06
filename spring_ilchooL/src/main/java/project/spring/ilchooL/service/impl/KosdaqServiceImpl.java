package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.Kosdaq;
import project.spring.ilchooL.model.Kospi;
import project.spring.ilchooL.service.KosdaqService;

@Service
@Slf4j
public class KosdaqServiceImpl implements KosdaqService {
	
	@Autowired
	SqlSession sqlSession;

	/**
	 * 코스닥 데이터 조회
	 */
	@Override
	public List<Kosdaq> getKosdaqList() throws Exception {
		List<Kosdaq> result = null;
		
		try {
			result = sqlSession.selectList("KosdaqMapper.selectKosdaq");
			
			if (result == null) {
				throw new NullPointerException("result=null");
				}
			} catch (NullPointerException e) {
				throw new Exception("조회된 데이터가 없습니다.");
			} catch (Exception e) {
				throw new Exception("데이터 조회에 실패했습니다.");
			}
		
		return result;
	}

	
	/**
	 * 코스피 데이터 조회
	 */
	@Override
	public List<Kospi> getKospiList() throws Exception {
		List<Kospi> result = null;
		
		try {
			result = sqlSession.selectList("KospiMapper.selectKospi");
			
			if (result == null) {
				throw new NullPointerException("result=null");
				}
			} catch (NullPointerException e) {
				throw new Exception("조회된 데이터가 없습니다.");
			} catch (Exception e) {
				throw new Exception("데이터 조회에 실패했습니다.");
			}
		
		return result;
	}

}
