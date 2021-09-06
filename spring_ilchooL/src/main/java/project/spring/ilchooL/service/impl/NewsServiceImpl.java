package project.spring.ilchooL.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.spring.ilchooL.model.Keyword;
import project.spring.ilchooL.model.News;
import project.spring.ilchooL.service.NewsService;


public class NewsServiceImpl implements NewsService {
	
	@Autowired
	SqlSession sqlSession;

	
	/**
	 * news 테이블에 데이터 추가
	 */
	@Override
	public int addNews(News input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert("newsMapper.insertNews");
			
			if(result == 0 ) {
				throw new NullPointerException("result=0");
			}
		} catch (NullPointerException e) {
			throw new Exception("저장된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 저장에 실패했습니다.");
		}
		
		return result;
	}

	
	/**
	 * keyword 테이블에 데이터 추가
	 */
	@Override
	public int addKeyword(Keyword input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert("keywordMapper.insertKeyword");
			
			if(result == 0 ) {
				throw new NullPointerException("result=0");
			}
		} catch (NullPointerException e) {
			throw new Exception("저장된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 저장에 실패했습니다.");
		}
		
		return result;
	}


	
	/**
	 * News 테이블에 데이터 갱신
	 */
	@Override
	public int updateKeyword(Keyword input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("NewsMapper.updateNews");
			
			if(result == 0 ) {
				throw new NullPointerException("result=0");
			}
		} catch (NullPointerException e) {
			throw new Exception("저장된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 저장에 실패했습니다.");
		}
		
		return result;
	}

	
	

}
