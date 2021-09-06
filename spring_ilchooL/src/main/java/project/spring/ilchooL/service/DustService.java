package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.DustItem;
import project.spring.ilchooL.model.forecastDust.Response.Body.Items;

public interface DustService {
	
	/**
	 * 날씨 OpenAPI의 수집 결과를 DB에 저장.
	 * @param d_item 날씨수집결과
	 * @throws Exception
	 */
	public void collectdust(List<Items> d_item) throws Exception;
	
	/**
	 * 단일행 조회
	 * @param d_item
	 * @return
	 * @throws Exception
	 */
	public Items getItem(Items d_item) throws Exception;
	
	/**
	 * 다중행 조회
	 * @return
	 * @throws Exception
	 */
	public List<DustItem> getItemList(DustItem ditem) throws Exception;
	
	/**
	 * 시각화 데이터 조회
	 * @return
	 * @throws Exception
	 */
	public List<DustItem> getPmList(DustItem ditem) throws Exception;

}

