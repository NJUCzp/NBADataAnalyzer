package bl.playerbl;

import java.util.ArrayList;

import po.playerPO;
import vo.playerVO;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.PlayerDataServiceImpl;

public class PlayerBLFind {
	PlayerDataService playerdataservice;
	public PlayerBLFind(){
		playerdataservice=new PlayerDataServiceImpl();
	}
	public ArrayList<playerVO> findAll(){
		ArrayList<playerVO> playervolist=new ArrayList<playerVO>();
		ArrayList<playerPO> playerpolist=playerdataservice.finaAll();
		
		for(int i=0;i<playerpolist.size();i++){
			playerVO list=new playerVO();
			list.assistPercent=playerpolist.get(i).getAssistPercent();
			list.defendReboundPercent=playerpolist.get(i).getDefendReboundPercent();
			list.efficiency=playerpolist.get(i).getEfficiency();
			list.freeThrowPercent=playerpolist.get(i).getFreeThrowPercent();
			list.gmsc=playerpolist.get(i).getGmsc();
			list.name=playerpolist.get(i).getName();
			list.position=playerpolist.get(i).getPosition();
			list.offendReboundPercent=playerpolist.get(i).getOffendReboundPercent();
			list.realShotPercent=playerpolist.get(i).getRealShotPercent();
			list.reboundPercent=playerpolist.get(i).getReboundPercent();
			list.rejectionPercent=playerpolist.get(i).getRejectionPercent();
			list.shotEfficiency=playerpolist.get(i).getShotEfficiency();
			list.shotPercent=playerpolist.get(i).getShotPercent();
			list.startingMatches=playerpolist.get(i).getStartingMatches();
			list.stealPercent=playerpolist.get(i).getStealPercent();
			list.team=playerpolist.get(i).getTeam();
			list.threePointPercent=playerpolist.get(i).getThreePointPercent();
			list.totalScores=playerpolist.get(i).getTotalScores();
			list.totalshots=playerpolist.get(i).getTotalShots();
			list.totalFreeThrows=playerpolist.get(i).getTotalFreeThrows();
			list.totalThreePointShots=playerpolist.get(i).getTotalThreePointShots();
			list.pairs=playerpolist.get(i).getPairs();
			list.timeOnCourt=playerpolist.get(i).getTimeOnCourt();
			list.totalAssists=playerpolist.get(i).getTotalAssists();
			list.totalDefend=playerpolist.get(i).getTotalDefend();
			list.totalFouls=playerpolist.get(i).getTotalFouls();
			list.totalMatches=playerpolist.get(i).getTotalMatches();
			list.totalOffend=playerpolist.get(i).getTotalOffend();
			list.totalRebounds=playerpolist.get(i).getTotalRebounds();
			list.totalRejection=playerpolist.get(i).getTotalRejection();
			list.totalSteals=playerpolist.get(i).getTotalSteals();
			list.totalTurnovers=playerpolist.get(i).getTotalTurnovers();
			list.turnoverPercent=playerpolist.get(i).getTurnoverPercent();
			list.usePercent=playerpolist.get(i).getUsePercent();
			
			playervolist.add(list);
		}
		return playervolist;

	}

}
