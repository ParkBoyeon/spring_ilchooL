package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.ilchooL.model.Keyword;
import project.spring.ilchooL.service.KeywordService;


@Service
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	SqlSession sqlSession;
	
	
	/**
	 * keyword 테이블에 데이터 추가
	 */
	@Override
	public int addKeyword() throws Exception {
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
	 * keyword 테이블에 데이터 갱신
	 */
	@Override
	public int updateKeyword1() throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("keywordMapper.updateKeyword1");
			
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


	@Override
	public int updateKeyword2() throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("keywordMapper.updateKeyword2");
			
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


	@Override
	public int updateKeyword3() throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("keywordMapper.updateKeyword3");
			
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


	@Override
	public int updateKeyword4() throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("keywordMapper.updateKeyword4");
			
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


	@Override
	public int updateKeyword5() throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("keywordMapper.updateKeyword5");
			
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



	@Override
	public List<Keyword> selectKeyword() throws Exception {
		List<Keyword> result = null;
		
		try {
			result = sqlSession.selectList("keywordMapper.selectKeyword");
			
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
