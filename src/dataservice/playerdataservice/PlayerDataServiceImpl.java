package dataservice.playerdataservice;

import java.io.File;
import java.util.ArrayList;

import constantinfo.Constant;
import databaseservice.DataBaseServiceImpl;
import po.playerInSingleMatchPO;
import po.playerPO;
import po.teamInSingleMatchPO;
import po.teamPO;

public class PlayerDataServiceImpl implements PlayerDataService{
	ArrayList<playerPO> playerAllInfo=new ArrayList<playerPO>();
	
	public void initialData(String season){
		//读入球员基本信息
		File basicfile=new File("E:/JavaWorkbench/NBAData/players/info");
		String[] basicplayerlist=basicfile.list();
		
		for(String basicpath:basicplayerlist){
			ArrayList<String> playerBasicInfo=new ArrayList<String>();
			DataBaseServiceImpl basicplayerdataservice=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/players/info"+"/"+basicpath,playerBasicInfo);
			basicplayerdataservice.readFromfile();
			playerPO tempPO=new playerPO(playerBasicInfo.get(0).split("│")[1]);
			tempPO.setPosition(playerBasicInfo.get(2).split("│")[1]);
			playerAllInfo.add(tempPO);
		}
		
		//读入球员比赛信息
		File matchfile=new File("E:/JavaWorkbench/NBAData/matches");
		String[] matchlist=matchfile.list();
		ArrayList<String> seasonmatchlist=new ArrayList<String>();
		
		for(String singlematch:matchlist){
			if(singlematch.contains(season))
				seasonmatchlist.add(singlematch);
		}
		
		for(String singlematch:seasonmatchlist){
						
			ArrayList<String> playerMatchInfo=new ArrayList<String>();
			DataBaseServiceImpl playermatchdataservice=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/matches"+"/"+singlematch,playerMatchInfo);
			playermatchdataservice.readFromfile();
			//确定主客队
			String hometeam=playerMatchInfo.get(0).split(";")[1].split("-")[0];
			String visitingteam=playerMatchInfo.get(0).split(";")[1].split("-")[1];
			//搜索球员队伍
			int homeposition=2;
			int visitingposition=3;
			for(int i=3;i<playerMatchInfo.size();i++){
				if(playerMatchInfo.get(i).length()==3){
					visitingposition=i;
					break;
				}
			}
			
			int hometotaltime=0;
			int hometotalshots=0;
			int hometotalfreethrows=0;
			int hometotalturnovers=0;
			int hometotalrebounds=0;
			int hometotaloffend=0;
			int hometotaltwoshots=0;
			int hometotalontargets=0;
			
			int visitingtotaltime=0;
			int visitingtotalshots=0;
			int visitingtotalfreethrows=0;
			int visitingtotalturnovers=0;
			int visitingtotalrebounds=0;
			int visitingtotaloffend=0;
			int visitingtotaltwoshots=0;
			int visitingtotalontargets=0;

			
			for(int i=3;i<visitingposition;i++){
				String playerdetail[]=playerMatchInfo.get(i).split(";");
				//处理空数据
				for(int j=2;j<playerdetail.length;j++){
					if(playerdetail[j].equals("")||playerdetail[j].equals("null")||playerdetail[j].equals("None")){
						if(j==2)
							playerdetail[j]="0:00";
						else
							playerdetail[j]="0";
					}
				}
								
				hometotaltime=hometotaltime+Integer.parseInt(playerdetail[2].split(":")[0]);
				hometotalshots=hometotalshots+Integer.parseInt(playerdetail[4]);
				hometotalfreethrows=hometotalfreethrows+Integer.parseInt(playerdetail[8]);
				hometotalturnovers=hometotalturnovers+Integer.parseInt(playerdetail[15]);
				hometotalrebounds=hometotalrebounds+Integer.parseInt(playerdetail[9])+Integer.parseInt(playerdetail[10]);
				hometotaloffend=hometotaloffend+Integer.parseInt(playerdetail[9]);
				hometotaltwoshots=hometotaltwoshots+Integer.parseInt(playerdetail[4])-Integer.parseInt(playerdetail[6]);
				hometotalontargets=hometotalontargets+Integer.parseInt(playerdetail[3]);
				
			}
			
			for(int i=visitingposition+1;i<playerMatchInfo.size();i++){
				String playerdetail[]=playerMatchInfo.get(i).split(";");
				//处理空数据
				for(int j=2;j<playerdetail.length;j++){
					if(playerdetail[j].equals("")||playerdetail[j].equals("null")||playerdetail[j].equals("None")){
						if(j==2)
							playerdetail[j]="0:00";
						else
							playerdetail[j]="0";
					}
				}
				visitingtotaltime=visitingtotaltime+Integer.parseInt(playerdetail[2].split(":")[0]);
				visitingtotalshots=visitingtotalshots+Integer.parseInt(playerdetail[4]);
				visitingtotalfreethrows=visitingtotalfreethrows+Integer.parseInt(playerdetail[8]);
				visitingtotalturnovers=visitingtotalturnovers+Integer.parseInt(playerdetail[15]);
				visitingtotalrebounds=visitingtotalrebounds+Integer.parseInt(playerdetail[9])+Integer.parseInt(playerdetail[10]);
				visitingtotaloffend=visitingtotaloffend+Integer.parseInt(playerdetail[9]);
				visitingtotaltwoshots=visitingtotaltwoshots+Integer.parseInt(playerdetail[4])-Integer.parseInt(playerdetail[6]);
				visitingtotalontargets=visitingtotalontargets+Integer.parseInt(playerdetail[3]);

			}
			
			boolean ishome=true;//判断是否为主队
			
			for(int i=3;i<playerMatchInfo.size();i++){
				if (playerMatchInfo.get(i).length()==3){
					ishome=false;
					continue;
				}
				
				String playerdetail[]=playerMatchInfo.get(i).split(";");
				
				//处理空数据
				for(int j=2;j<playerdetail.length;j++){
					if(playerdetail[j].equals("")||playerdetail[j].equals("null")||playerdetail[j].equals("None")){
						if(j==2)
							playerdetail[j]="0:00";
						else
							playerdetail[j]="0";
					}
				}
				
				
				int singletime=Integer.parseInt(playerdetail[2].split(":")[0]);
				String singledetailtime=playerdetail[2];
				int singleshotontarget=Integer.parseInt(playerdetail[3]);
				int singletotalshots=Integer.parseInt(playerdetail[4]);
				int singlethreeontargets=Integer.parseInt(playerdetail[5]);
				int singlethreeshots=Integer.parseInt(playerdetail[6]);
				int singlefreethrowontarget=Integer.parseInt(playerdetail[7]);
				int singletotalfreethrow=Integer.parseInt(playerdetail[8]);
				int singleoffend=Integer.parseInt(playerdetail[9]);
				int singledefend=Integer.parseInt(playerdetail[10]);
				int singletotalrebounds=Integer.parseInt(playerdetail[11]);
				int singletotalassists=Integer.parseInt(playerdetail[12]);
				int singletotalsteals=Integer.parseInt(playerdetail[13]);
				int singletotalrejections=Integer.parseInt(playerdetail[14]);
				int singletotalturnovers=Integer.parseInt(playerdetail[15]);
				int singletotalfouls=Integer.parseInt(playerdetail[16]);
				int singletotalscores=Integer.parseInt(playerdetail[17]);

				if(singletime==0)
					singletime++;
				
				for(playerPO singleplayer:playerAllInfo){
					if(singleplayer.getName().equals(playerdetail[0])){
						playerInSingleMatchPO tempplayer=new playerInSingleMatchPO();
						
						/*if(singleplayer.getName().equals("Aaron Brooks")){
							System.out.println(singleplayer.getName());
							System.out.println(singlematch);
							System.out.println(ishome);
							System.out.println(singledetailtime);
						}*/
						
						
						
						singleplayer.setTotalMatches(singleplayer.getTotalMatches()+1);
						if(playerdetail[1].length()==1)
							singleplayer.setStartingMatches(singleplayer.getStartingMatches()+1);
						
						String[]matchbasicinfo=singlematch.split("_");
						
						tempplayer.setSeason(matchbasicinfo[0]);
						tempplayer.setDate(matchbasicinfo[1]);
						tempplayer.setName(matchbasicinfo[2]);
						tempplayer.setDetailTimeOnCourt(singledetailtime);
						tempplayer.setAssists(singletotalassists);
						tempplayer.setScore(singletotalscores);
						tempplayer.setRebounds(singletotalrebounds);
						tempplayer.setRejections(singletotalrejections);
						tempplayer.setSteals(singletotalsteals);
						tempplayer.setTotalShots(singletotalshots);
						tempplayer.setShotsOnTargets(singleshotontarget);
						tempplayer.setTotalThreePointShots(singlethreeshots);
						tempplayer.setThreePointShotsOnTargets(singlethreeontargets);
						tempplayer.setTotalFreeThrows(singletotalfreethrow);
						tempplayer.setFreeThrowOnTargets(singlefreethrowontarget);
						tempplayer.setShotPercent((double)singleshotontarget/(double)singletotalshots);
						tempplayer.setThreePointPercent((double)singlethreeontargets/(double)singlethreeshots);
						tempplayer.setFreeThrowPercent((double)singlefreethrowontarget/(double)singletotalfreethrow);
						
						singleplayer.setTimeOnCourt(singleplayer.getTimeOnCourt()+singletime);
						singleplayer.setShotsOnTargets(singleplayer.getShotsOnTargets()+singleshotontarget);
						singleplayer.setTotalShots(singleplayer.getTotalShots()+singletotalshots);
						singleplayer.setThreePointShotsOnTargets(singleplayer.getThreePointShotsOnTargets()+singlethreeontargets);
						singleplayer.setTotalThreePointShots(singleplayer.getTotalThreePointShots()+singlethreeshots);
						singleplayer.setFreeThrowOnTargets(singleplayer.getFreeThrowOnTargets()+singlefreethrowontarget);
						singleplayer.setTotalFreeThrows(singleplayer.getTotalFreeThrows()+singletotalfreethrow);
						singleplayer.setTotalOffend(singleplayer.getTotalOffend()+singleoffend);
						singleplayer.setTotalDefend(singleplayer.getTotalDefend()+singledefend);
						singleplayer.setTotalRebounds(singleplayer.getTotalRebounds()+singletotalrebounds);
						singleplayer.setTotalAssists(singleplayer.getTotalAssists()+singletotalassists);
						singleplayer.setTotalSteals(singleplayer.getTotalSteals()+singletotalsteals);
						singleplayer.setTotalRejection(singleplayer.getTotalRejection()+singletotalrejections);
						singleplayer.setTotalTurnovers(singleplayer.getTotalTurnovers()+singletotalturnovers);
						singleplayer.setTotalFouls(singleplayer.getTotalFouls()+singletotalfouls);
						singleplayer.setTotalScores(singleplayer.getTotalScores()+singletotalscores);
						
						//检查0
						if(singletotalshots!=0)
							singleplayer.setShotEfficiency(singleplayer.getShotEfficiency()+(double)(singleshotontarget+0.5*singlethreeontargets)/(double)singletotalshots);

						if(new Float((double)singletotalturnovers/(double)(singletotalshots-singlethreeshots+0.44*singletotalfreethrow+singletotalturnovers)).isNaN()){
							
						}else{
							singleplayer.setTurnoverPercent(singleplayer.getTurnoverPercent()+(double)singletotalturnovers/(double)((singletotalshots-singlethreeshots)+0.44*singletotalfreethrow+singletotalturnovers));
						}
						
						if(singletotalshots==0 && singletotalfreethrow==0){
							
						}else
							singleplayer.setRealShotPercent(singleplayer.getRealShotPercent()+(double)singletotalscores/(2*(double)singletotalshots+0.88*(double)singletotalfreethrow));
						
						singleplayer.setEfficiency(singleplayer.getTotalScores()+singletotalscores+singletotalrebounds+singletotalassists+singletotalsteals+singletotalrejections-singletotalshots+singleshotontarget-singletotalfreethrow+singlefreethrowontarget-singletotalturnovers);
						singleplayer.setGmsc(singleplayer.getGmsc()+singletotalscores+0.4*singleshotontarget-0.7*singletotalshots-0.4*(singletotalfreethrow-singlefreethrowontarget)+0.7*singleoffend+0.3*singledefend+singletotalsteals+0.7*singletotalassists+0.7*singletotalrejections-0.4*singletotalfouls-singletotalturnovers);
						if((singletotalscores>=10)&&(singletotalrebounds>=10)||((singletotalscores>=10)&&(singletotalassists>=10))||((singletotalscores>=10)&&(singletotalsteals>=10))||((singletotalscores>=10)&&(singletotalrejections>=10))||((singletotalrebounds>=10)&&(singletotalassists>=10))||((singletotalrebounds>=10)&&(singletotalsteals>=10))||((singletotalrebounds>=10)&&(singletotalrejections>=10))||((singletotalassists>=10)&&(singletotalsteals>=10))||((singletotalassists>=10)&&(singletotalrejections>=10))||((singletotalrejections>=10)&&(singletotalsteals>=10)))
							singleplayer.setPairs(singleplayer.getPairs()+1);
						
						if (ishome){							
							singleplayer.setTeam(hometeam);
							singleplayer.setReboundPercent(singleplayer.getReboundPercent()+(double)singletotalrebounds*((double)hometotaltime/5.0)/(double)singletime/(double)(hometotalrebounds+visitingtotalrebounds));
							singleplayer.setOffendReboundPercent(singleplayer.getOffendReboundPercent()+(double)singleoffend*((double)hometotaltime/5.0)/(double)singletime/(double)(hometotalrebounds+visitingtotalrebounds));
							singleplayer.setDefendReboundPercent(singleplayer.getDefendReboundPercent()+(double)singledefend*((double)hometotaltime/5.0)/(double)singletime/(double)(hometotalrebounds+visitingtotalrebounds));
							singleplayer.setAssistPercent(singleplayer.getAssistPercent()+(double)singletotalassists/((double)singletime/((double)hometotaltime/5.0)*(double)hometotalontargets-(double)singleshotontarget));
							singleplayer.setStealPercent(singleplayer.getStealPercent()+(double)singletotalsteals*((double)hometotaltime/5.0)/singletime/visitingtotaloffend);
							
						
							singleplayer.setRejectionPercent(singleplayer.getRejectionPercent()+(double)singletotalrejections*((double)hometotaltime/5.0)/(double)singletime/(double)visitingtotaltwoshots);
							singleplayer.setUsePercent(singleplayer.getUsePercent()+((double)singletotalshots+0.44*(double)singletotalfreethrow+(double)singletotalturnovers)*((double)hometotaltime/5)/(double)singletime/((double)hometotalshots+0.44*(double)hometotalfreethrows+(double)hometotalturnovers));
						}else{							
							singleplayer.setTeam(visitingteam);
							singleplayer.setReboundPercent(singleplayer.getReboundPercent()+(double)singletotalrebounds*((double)visitingtotaltime/5.0)/(double)singletime/((double)visitingtotalrebounds+(double)hometotalrebounds));
							singleplayer.setOffendReboundPercent(singleplayer.getOffendReboundPercent()+(double)singleoffend*((double)visitingtotaltime/5.0)/(double)singletime/(double)(visitingtotalrebounds+hometotalrebounds));
							singleplayer.setDefendReboundPercent(singleplayer.getDefendReboundPercent()+(double)singledefend*((double)visitingtotaltime/5.0)/(double)singletime/(double)(visitingtotalrebounds+hometotalrebounds));
							singleplayer.setAssistPercent(singleplayer.getAssistPercent()+(double)singletotalassists/((double)singletime/((double)visitingtotaltime/5.0)*(double)visitingtotalontargets-(double)singleshotontarget));
							singleplayer.setStealPercent(singleplayer.getStealPercent()+(double)singletotalsteals*((double)visitingtotaltime/5.0)/(double)singletime/(double)hometotaloffend);
							
							
							singleplayer.setRejectionPercent(singleplayer.getRejectionPercent()+(double)singletotalrejections*((double)hometotaltime/5.0)/(double)singletime/(double)visitingtotaltwoshots);
							singleplayer.setUsePercent(singleplayer.getUsePercent()+((double)singletotalshots+0.44*(double)singletotalfreethrow+(double)singletotalturnovers)*((double)visitingtotaltime/5.0)/(double)singletime/((double)hometotalshots+0.44*(double)hometotalfreethrows+(double)hometotalturnovers));
						} 
						
						if(singleplayer.getRecentFive().size()<5){
							ArrayList<playerInSingleMatchPO> temp=singleplayer.getRecentFive();
							temp.add(tempplayer);
							singleplayer.setRecentFive(temp);
						}else{
							ArrayList<playerInSingleMatchPO> temp=singleplayer.getRecentFive();
							
							temp.remove(0);
							temp.add(tempplayer);
							singleplayer.setRecentFive(temp);
						}
						
						if(singleplayer.getName().equals("Aaron Brooks")){
							System.out.println(singleplayer.getName());
							System.out.println(singlematch);
							System.out.println(ishome);
							System.out.println(singledetailtime);
							for(playerInSingleMatchPO s:singleplayer.getRecentFive()){
								System.out.println(s.getDetailTimeOnCourt());
							}
						}
						
						
						
						if(Constant.ATLANTIC_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("ATLANTIC");
							continue;
						}
						if(Constant.CENTRAL_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("CENTRAL");
							continue;
						}
						if(Constant.NORTHWEST_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("NORTHWEST");
							continue;
						}
						if(Constant.PACIFIC_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("PACIFIC");
							continue;
						}
						if(Constant.SOUTHEAST_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("SOUTHEAST");
							continue;
						}
						if(Constant.SOUTHWEST_TEAM.indexOf(singleplayer.getTeam().toLowerCase())>=0){
							singleplayer.setLeague("SOUTHWEST");
							continue;
						}

						
					}
				}
				
			}
			
		}
		//计算命中率等信息
		for(playerPO playerlist:playerAllInfo){
			
			System.out.println(playerlist.getTotalShots());
			
			if(playerlist.getTotalShots()==0){
				playerlist.setShotPercent(0.0);
				continue;
			}
			if(playerlist.getTotalThreePointShots()==0){
				playerlist.setThreePointPercent(0.0);
				continue;
			}
			if(playerlist.getFreeThrowOnTargets()==0){
				playerlist.setFreeThrowPercent(0.0);
				continue;
			}
			playerlist.setShotPercent((double)playerlist.getShotsOnTargets()/(double)playerlist.getTotalShots());
			playerlist.setThreePointPercent((double)playerlist.getThreePointShotsOnTargets()/(double)playerlist.getTotalThreePointShots());
			playerlist.setFreeThrowPercent((double)playerlist.getTotalFreeThrows()/(double)playerlist.getFreeThrowOnTargets());
		}
		
		DataBaseServiceImpl dataBaseServiceWrite=new DataBaseServiceImpl<playerPO>("tempdata/player"+season+".tpd",playerAllInfo);
		dataBaseServiceWrite.write();

	}

	@Override
	public ArrayList<playerPO> finaAll(String season) {
		File file=new File("tempdata");
		String[] tempdata=file.list();
		boolean exist=false;//判断当赛季数据是否已经存在
		
		for(String datafile:tempdata){
			if(datafile.contains("player")&&datafile.contains(season))
				exist=true;
		}
		
		if(!exist)
			initialData(season);
		
		DataBaseServiceImpl databaseservice=new DataBaseServiceImpl<playerPO>("tempdata/player"+season+".tpd",playerAllInfo);
		databaseservice.readFromTemp();
		/*for(playerPO singleplayer:playerAllInfo){
			System.out.println(singleplayer.getTotalShots());
		}*/
		// TODO Auto-generated method stub
		return playerAllInfo;
	}
	
	public ArrayList<playerInSingleMatchPO> findByDate(String date){
		ArrayList<playerInSingleMatchPO> dailyplayer=new ArrayList<playerInSingleMatchPO>();
		File file=new File("E:/JavaWorkbench/NBAData/matches");
		ArrayList<String> filetoopen=new ArrayList<String>();
		
		for(String singlefile:file.list()){
			if(singlefile.contains(date))
				filetoopen.add(singlefile);
		}
		
		
		for(String dailyfile:filetoopen){
			ArrayList<String> singlematchinfo=new ArrayList<String>();
			
			DataBaseServiceImpl databaseservice=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/matches/"+dailyfile,singlematchinfo);
			databaseservice.readFromfile();
						
			for(int i=3;i<singlematchinfo.size();i++){
				if(singlematchinfo.get(i).length()>3){
					playerInSingleMatchPO tempplayer=new playerInSingleMatchPO();
					String[] playerinfo=singlematchinfo.get(i).split(";");
					String[] matchinfo=dailyfile.split("_");
					tempplayer.setSeason(matchinfo[0]);
					tempplayer.setDate(matchinfo[1]);
					tempplayer.setName(matchinfo[2]);
					tempplayer.setPlayer(playerinfo[0]);
					tempplayer.setDetailTimeOnCourt(playerinfo[2]);
					tempplayer.setScore(Integer.parseInt(playerinfo[17]));
					tempplayer.setAssists(Integer.parseInt(playerinfo[12]));
					tempplayer.setRebounds(Integer.parseInt(playerinfo[11]));
					tempplayer.setRejections(Integer.parseInt(playerinfo[14]));
					tempplayer.setSteals(Integer.parseInt(playerinfo[13]));
					tempplayer.setShotsOnTargets(Integer.parseInt(playerinfo[3]));
					tempplayer.setTotalShots(Integer.parseInt(playerinfo[4]));
					tempplayer.setThreePointShotsOnTargets(Integer.parseInt(playerinfo[5]));
					tempplayer.setTotalThreePointShots(Integer.parseInt(playerinfo[6]));
					tempplayer.setFreeThrowOnTargets(Integer.parseInt(playerinfo[7]));
					tempplayer.setTotalFreeThrows(Integer.parseInt(playerinfo[8]));
					tempplayer.setShotPercent((double)Integer.parseInt(playerinfo[3])/(double)Integer.parseInt(playerinfo[4]));
					tempplayer.setThreePointPercent((double)Integer.parseInt(playerinfo[5])/(double)Integer.parseInt(playerinfo[6]));
					tempplayer.setFreeThrowPercent((double)Integer.parseInt(playerinfo[7])/(double)Integer.parseInt(playerinfo[8]));
					
					dailyplayer.add(tempplayer);
				}
			}
		}
		return dailyplayer;
		
	}

}
