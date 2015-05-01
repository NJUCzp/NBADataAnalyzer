package dataservice.matchdataservice;

import java.util.ArrayList;

import po.matchPO;

public interface MatchDataService {
	public ArrayList<String> findAllMatches(String season);
	public matchPO findSingleMatch(String matchBasicInfo);

}
