package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.ilchooL.model.locItem;
import project.spring.ilchooL.service.LocService;

@Service
public class LocServiceImpl implements LocService{
	
	@Autowired SqlSession sqlSession;

	@Override
	public locItem searchLocItem(locItem input) throws Exception {
		locItem result = null;
		
		try {
			result = sqlSession.selectOne("locMapper.searchLoc", input);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return result;
	}

	@Override
	public List<locItem> searchLocItemAll() throws Exception {
		List<locItem> result = null;
		
		try {
			result = sqlSession.selectList("locMapper.searchLocAll");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
}
