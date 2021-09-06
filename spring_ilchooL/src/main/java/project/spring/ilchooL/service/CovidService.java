
package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.CovidItem;

public interface CovidService {

	public void collectCovid(CovidItem covid_item) throws Exception;
	
	public CovidItem getCovidItem(CovidItem covid_item) throws Exception;
	
	public List<CovidItem> getCovidList() throws Exception;
}
