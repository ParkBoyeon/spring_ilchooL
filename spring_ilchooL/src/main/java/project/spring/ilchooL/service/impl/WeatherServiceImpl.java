package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.Witem;
import project.spring.ilchooL.model.forecastWeather.Response.Body.Items.Item;
import project.spring.ilchooL.service.WeatherService;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
	
	// MyBatis 연동을 위한 객체 주입
	@Autowired SqlSession sqlSession;
	
	/**
	 * 데이터 저장
	 * @param w_item
	 * @throws Exception
	 */
	@Override
	public void collectWeather(List<Item> w_item) throws Exception {
		try {
			// 수집 결과 수 만큼 반복하며 데이터를 저장한다.
			for (Item item : w_item ) {
				if (sqlSession.update("weatherMapper.updateWeatherItem", item) == 0) {
					sqlSession.insert("weatherMapper.insertWeatherItem", item);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}
	}	// end collectWeather

	
	/**
	 * 단일행 조회
	 * @param w_item
	 * @return
	 * @throws Exception
	 */
	@Override
	public Item getItem(Item w_item) throws Exception {
		Item result = null;
		
		try {
			result = sqlSession.selectOne("weatherMapper.selectWeatherItem", w_item);
			
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
	}	// end getItem

	
	/**
	 * 다중행 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Witem> getItemList(Witem witem) throws Exception {
		List<Witem> result = null;
		
		try {
			result = sqlSession.selectList("weatherMapper.selectWeatherList", witem);
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
	}	// end getItemList

	/**
	 * 시각화 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Witem> getChartItemList(Witem witem) throws Exception {
		List<Witem> result = null;
		
		try {
			result = sqlSession.selectList("weatherMapper.selectChartList", witem);
			if(result ==null) {
				throw new NullPointerException("result=null");
			}
		}catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	} // end getPtyItemList


	@Override
	public List<Witem> getTmxItemList(Witem witem) throws Exception {
		List<Witem> result = null;
		
		try {
			result = sqlSession.selectList("weatherMapper.selectTmxList", witem);
			if(result ==null) {
				throw new NullPointerException("result=null");
			}
		}catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}


	@Override
	public List<Witem> getTmnItemList(Witem witem) throws Exception {
		List<Witem> result = null;
		
		try {
			result = sqlSession.selectList("weatherMapper.selectTmnList", witem);
			if(result ==null) {
				throw new NullPointerException("result=null");
			}
		}catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}

}
