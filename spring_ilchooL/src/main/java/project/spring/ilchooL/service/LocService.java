package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.locItem;

public interface LocService {
	public locItem searchLocItem(locItem input) throws Exception;
	
	public List<locItem> searchLocItemAll() throws Exception;
}
