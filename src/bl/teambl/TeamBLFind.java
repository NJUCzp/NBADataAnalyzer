package bl.teambl;

import java.util.ArrayList;

import dataservice.teamdataservice.TeamDataService;
import dataservice.teamdataservice.TeamDataServiceImpl;
import po.teamPO;
import vo.teamVO;

public class TeamBLFind {
	TeamDataService teamdataservice;
	public TeamBLFind(){
		teamdataservice=new TeamDataServiceImpl();
	}
	public ArrayList<teamVO> finaAll(String season){
		ArrayList<teamVO> teamvolist=new ArrayList<teamVO>();
		ArrayList<teamPO> teampolist=teamdataservice.findAll(season);
		
		for(int i=0;i<teampolist.size();i++){
			teamVO list=new teamVO();
			list.assistEfficiency=teampolist.get(i).getAssistEfficiency();
			list.defendEfficiency=teampolist.get(i).getDefendEfficiency();
			list.defendReboundEfficiency=teampolist.get(i).getDefendReboundEfficiency();
			list.defensiveRebound=teampolist.get(i).getDefensiveRebound();
			list.freeThrowOnTargets=teampolist.get(i).getFreeThrowOnTargets();
			list.freeThrowPercent=teampolist.get(i).getFreeThrowPercent();
			list.fullname=teampolist.get(i).getFullName();
			list.league=teampolist.get(i).getLeague();
			list.city=teampolist.get(i).getCity();
			list.offendEfficiency=teampolist.get(i).getOffendEfficiency();
			list.offendReboundEfficiency=teampolist.get(i).getOffendReboundEfficiency();
			list.offensiveRebound=teampolist.get(i).getOffensiveRebound();
			list.offensiveRound=teampolist.get(i).getOffensiveRound();
			list.shortname=teampolist.get(i).getShortName();
			list.shotPercent=teampolist.get(i).getShotPercent();
			list.shotsOnTargets=teampolist.get(i).getShotsOnTargets();
			list.stealEfficiency=teampolist.get(i).getStealEfficiency();
			list.threePointPercent=teampolist.get(i).getThreePointPercent();
			list.threePointShotsOnTargets=teampolist.get(i).getThreePointShotsOnTargets();
			list.totalAssists=teampolist.get(i).getTotalAssists();
			list.totalFouls=teampolist.get(i).getTotalFouls();
			list.totalFreeThrows=teampolist.get(i).getTotalFreeThrows();
			list.totalMatches=teampolist.get(i).getTotalMatches();
			list.totalRebounds=teampolist.get(i).getTotalRebounds();
			list.totalRejection=teampolist.get(i).getTotalRejection();
			list.totalScores=teampolist.get(i).getTotalScores();
			list.totalShots=teampolist.get(i).getTotalShots();
			list.totalSteals=teampolist.get(i).getTotalSteals();
			list.totalThreePointShots=teampolist.get(i).getTotalThreePointShots();
			list.totalTurnovers=teampolist.get(i).getTotalTurnovers();
			list.winPercent=teampolist.get(i).getWinPercent();
			list.recentFive=teampolist.get(i).getRecentFive();
			
			teamvolist.add(list);
		}
		return teamvolist;		
	}
	
	public void deleteTemp(String season){
		teamdataservice.deleteTemp(season);
	}

}
