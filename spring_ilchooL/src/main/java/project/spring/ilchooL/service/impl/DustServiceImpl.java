package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.DustItem;
import project.spring.ilchooL.model.forecastDust.Response.Body.Items;
import project.spring.ilchooL.service.DustService;

@Slf4j
@Service
public class DustServiceImpl implements DustService {

	// MyBatis 연동을 위한 객체 주입
	@Autowired
	SqlSession sqlSession;

	/**
	 * 데이터 저장
	 * @param d_item
	 * @throws Exception
	 */

	@Override
	public void collectdust(List<Items> d_item) throws Exception {
		try {
			for (Items items : d_item) {
				if (sqlSession.update("dustMapper.updateDustItem", items) == 0) {
					sqlSession.insert("dustMapper.insertDustItem", items);

				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}
	}

	/**
	 * 단일행 조회
	 * @param d_item
	 * @return
	 * @throws Exception
	 */
	@Override
	public Items getItem(Items d_item) throws Exception {
		Items result = null;
		
		try {
			result = sqlSession.selectOne("dustMapper.selectDustItem", d_item);
			
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	/**
	 * 다중행 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DustItem> getItemList(DustItem ditem) throws Exception {
		List<DustItem> result = null;
		
		try {
			result = sqlSession.selectList("dustMapper.selectDustList", ditem);
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}
	
	/**
	 * 시각화 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DustItem> getPmList(DustItem ditem) throws Exception {
		List<DustItem> result = null;
		
		try {
			result = sqlSession.selectList("dustMapper.selectPmList", ditem);
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}
}