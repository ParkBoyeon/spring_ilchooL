package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.Witem;
import project.spring.ilchooL.model.forecastWeather.Response.Body.Items.Item;

public interface WeatherService {
	
	/**
	 * 날씨 OpenAPI의 수집 결과를 DB에 저장.
	 * @param w_item 날씨수집결과
	 * @throws Exception
	 */
	public void collectWeather(List<Item> w_item) throws Exception;
	
	/**
	 * 단일행 조회
	 * @param w_item
	 * @return
	 * @throws Exception
	 */
	public Item getItem(Item w_item) throws Exception;
	
	/**
	 * 다중행 조회
	 * @return
	 * @throws Exception
	 */
	public List<Witem> getItemList(Witem witem) throws Exception;
	
	/**
	 * PTY 시각화 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<Witem> getChartItemList(Witem witem) throws Exception;
	
	/**
	 * TMX,TMN 시각화 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<Witem> getTmxItemList(Witem witem) throws Exception;
	public List<Witem> getTmnItemList(Witem witem) throws Exception;
}
