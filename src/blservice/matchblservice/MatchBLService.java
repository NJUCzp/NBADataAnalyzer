package blservice.matchblservice;

import java.util.ArrayList;

import vo.matchVO;

public interface MatchBLService {
	public ArrayList<String> findMatchBasicInfo(String season);
	public matchVO findSingleMatchInfo(String matchBasicInfo);

}
