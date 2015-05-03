package bl.matchbl;

import java.util.ArrayList;

import po.matchPO;
import dataservice.matchdataservice.MatchDataService;
import dataservice.matchdataservice.MatchDataServiceImpl;
import vo.matchVO;
import blservice.matchblservice.MatchBLService;

public class MatchBL implements MatchBLService{
	MatchDataService matchdataservice=new MatchDataServiceImpl();

	@Override
	public ArrayList<String> findMatchBasicInfo(String season) {
		ArrayList<String> matchBasicInfo=matchdataservice.findAllMatches(season);
		// TODO Auto-generated method stub
		return matchBasicInfo;
	}

	@Override
	public matchVO findSingleMatchInfo(String matchBasicInfo) {
		matchVO matchvo=new matchVO();
		matchPO matchpo=matchdataservice.findSingleMatch(matchBasicInfo);
		
		matchvo.home=matchpo.getHome();
		matchvo.visiting=matchpo.getVisiting();
		matchvo.homeScore=matchpo.getHomeScore();
		matchvo.visitingScore=matchpo.getVisitingScore();
		matchvo.homeDetailScore=matchpo.getHomeDetailScore();
		matchvo.visitingDetailScore=matchpo.getVisitingDetailScore();
		matchvo.hostPlayerInfo=matchpo.getHostPlayerInfo();
		matchvo.visitPlayerInfo=matchpo.getVisitPlayerInfo();
		// TODO Auto-generated method stub
		return matchvo;
	}

}
