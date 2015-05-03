package dataservice.matchdataservice;

import java.io.File;
import java.util.ArrayList;

import databaseservice.DataBaseServiceImpl;
import po.matchPO;
import po.playerInSingleMatchPO;

public class MatchDataServiceImpl implements MatchDataService{

	@Override
	public ArrayList<String> findAllMatches(String season) {
		ArrayList<String> basicinfo=new ArrayList<String>();
		File file=new File("E:/JavaWorkbench/NBAData/matches");
		
		String[] filelist=file.list();
		
		for(String singlefile:filelist){
			if(singlefile.split("_")[0].contains(season))
				basicinfo.add(singlefile);
		}
		// TODO Auto-generated method stub
		return basicinfo;
	}

	@Override
	public matchPO findSingleMatch(String matchBasicInfo) {
		ArrayList<String> singleMatchInfo=new ArrayList<String>();
		matchPO matchInfo=new matchPO();
		
		DataBaseServiceImpl databaseservice=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/matches/"+matchBasicInfo,singleMatchInfo);
		databaseservice.readFromfile();
		
		matchInfo.setHome(singleMatchInfo.get(0).split(";")[1].substring(0, 3));
		matchInfo.setVisiting(singleMatchInfo.get(0).split(";")[1].substring(4, 7));
		matchInfo.setHomeScore(Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[0]));
		matchInfo.setVisitingScore(Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[1]));
		//添加各节比分
		String[]detailscores=singleMatchInfo.get(1).split(";");
		matchInfo.setHomeDetailScore(new int[]{Integer.parseInt(detailscores[0].split("-")[0]),Integer.parseInt(detailscores[1].split("-")[0]),Integer.parseInt(detailscores[2].split("-")[0]),Integer.parseInt(detailscores[3].split("-")[0])});
		matchInfo.setVisitingDetailScore(new int[]{Integer.parseInt(detailscores[0].split("-")[1]),Integer.parseInt(detailscores[1].split("-")[1]),Integer.parseInt(detailscores[2].split("-")[1]),Integer.parseInt(detailscores[3].split("-")[1])});

		//添加球员信息
		int length=3;
		boolean isHome=true;
		for(int i=3;i<singleMatchInfo.size();i++){
			if(singleMatchInfo.get(i).length()==3){
				isHome=false;
				continue;
			}else{
				playerInSingleMatchPO singleplayer=new playerInSingleMatchPO();
				String[] playerinfo=singleMatchInfo.get(i).split(";");
				
				singleplayer.setName(playerinfo[0]);
				singleplayer.setRebounds(Integer.parseInt(playerinfo[11]));
				singleplayer.setAssists(Integer.parseInt(playerinfo[12]));
				singleplayer.setSteals(Integer.parseInt(playerinfo[13]));
				singleplayer.setRejections(Integer.parseInt(playerinfo[14]));
				singleplayer.setScore(Integer.parseInt(playerinfo[17]));
				singleplayer.setDetailTimeOnCourt(playerinfo[2]);
				
				singleplayer.setTotalShots(Integer.parseInt(playerinfo[4]));
				singleplayer.setShotsOnTargets(Integer.parseInt(playerinfo[3]));
				singleplayer.setTotalThreePointShots(Integer.parseInt(playerinfo[6]));
				singleplayer.setThreePointShotsOnTargets(Integer.parseInt(playerinfo[5]));
				singleplayer.setTotalFreeThrows(Integer.parseInt(playerinfo[8]));
				singleplayer.setFreeThrowOnTargets(Integer.parseInt(playerinfo[7]));

				if(singleplayer.getTotalShots()==0)
					singleplayer.setShotPercent(0.0);
				else
					singleplayer.setShotPercent((double)singleplayer.getShotsOnTargets()/(double)singleplayer.getTotalShots());
			
				if(singleplayer.getTotalThreePointShots()==0)
					singleplayer.setThreePointPercent(0.0);
				else
					singleplayer.setThreePointPercent((double)singleplayer.getThreePointShotsOnTargets()/(double)singleplayer.getTotalThreePointShots());

				if(singleplayer.getTotalFreeThrows()==0)
					singleplayer.setFreeThrowPercent(0.0);
				else
					singleplayer.setFreeThrowPercent((double)singleplayer.getFreeThrowOnTargets()/(double)singleplayer.getTotalFreeThrows());
				
				if(isHome){
					ArrayList<playerInSingleMatchPO> temp=matchInfo.getHostPlayerInfo();
					temp.add(singleplayer);
					matchInfo.setHostPlayerInfo(temp);
				}
				else{
					ArrayList<playerInSingleMatchPO> temp=matchInfo.getVisitPlayerInfo();
					temp.add(singleplayer);
					matchInfo.setVisitPlayerInfo(temp);
				}
			}
		}
		
		// TODO Auto-generated method stub
		return matchInfo;
	}

}
