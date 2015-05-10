package dataservice.teamdataservice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import databaseservice.DataBaseServiceImpl;
import po.playerPO;
import po.teamInSingleMatchPO;
import po.teamPO;

public class TeamDataServiceImpl implements TeamDataService{
	ArrayList<teamPO> teamAllInfo=new ArrayList<teamPO>();
	
	public void initialData(String season){
		//读入球队基本信息
		ArrayList<String> teamBasicInfo=new ArrayList<String>();
		teamAllInfo.clear();
		DataBaseServiceImpl dataBaseService=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/teams/teams",teamBasicInfo);
		dataBaseService.readFromfile();
	    
				for(String basicinfo:teamBasicInfo){
					String[] basicdetailinfo=basicinfo.split("│");
										
					teamPO tempPO=new teamPO(basicdetailinfo[1]);
					tempPO.setFullName(basicdetailinfo[0]);
					tempPO.setCity(basicdetailinfo[2]);
					tempPO.setEorW(basicdetailinfo[3]);
					tempPO.setLeague(basicdetailinfo[4]);
					teamAllInfo.add(tempPO);
				}
				//考虑12-13赛季无鹈鹕
				if(Integer.parseInt(season.split("-")[0])<13){
					for(teamPO t:teamAllInfo){
						if(t.getShortName().equals("NOP")){
							t.setShortName("NOH");
							t.setFullName("Hornets");
							//teamAllInfo.remove(t);
							break;
						}
							
					}
				}
				
				//读入球队的比赛信息
				//dataBaseService.setArr(matchInfo);
				//dataBaseService.setFilepath("E:/JavaWorkbench/NBAData/matches");
				File file=new File("E:/JavaWorkbench/NBAData/matches");
				String[] filelist=file.list();
				ArrayList<String> seasonmatchlist=new ArrayList<String>();
				ArrayList<String> realseasonmatch=new ArrayList<String>();
				
				for(String files:filelist){
					if(files.split("_")[0].contains(season))
						seasonmatchlist.add(files);
				}
				//对比赛信息进行重新排序
				int startposition=0;
				
				for(int i=0;i<seasonmatchlist.size()-1;i++){
					int month1=Integer.parseInt(seasonmatchlist.get(i).split("_")[1].split("-")[0]);
					int month2=Integer.parseInt(seasonmatchlist.get(i+1).split("_")[1].split("-")[0]);
					if((month2-month1)>2){
						startposition=i+1;
						break;
					}
				}
				
				System.out.println(startposition);
				
				for(int i=startposition;i<seasonmatchlist.size();i++){
					realseasonmatch.add(seasonmatchlist.get(i));
				}
				
				for(int i=0;i<startposition;i++){
					realseasonmatch.add(seasonmatchlist.get(i));
				}
				
				for(String files:realseasonmatch){					
					ArrayList<String> singleMatchInfo=new ArrayList<String>();
					DataBaseServiceImpl tempDataBaseService=new DataBaseServiceImpl<String>("E:/JavaWorkbench/NBAData/matches"+"/"+files,singleMatchInfo);
					
					tempDataBaseService.readFromfile();
					String[] names=singleMatchInfo.get(0).split(";")[1].split("-");
					
					
					System.out.println(singleMatchInfo.get(0).split(";")[1]);
					
					//找到对应球队
					for(teamPO teampo:teamAllInfo){
						boolean ishome=true;
						int position=0;
						int homeMatchScore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[0]);
						int visitMatchScore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[1]);
						int myscore=0;
						
						teamInSingleMatchPO tempteam=new teamInSingleMatchPO();
						//if(files.contains(teampo.getShortName())){

						if(teampo.getShortName().equals(names[0])){
							
							position=3;
							//singleMatchScore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[0]);
							//tempteam.setTotalScore(homeMatchScore);
							teampo.setTotalScores(teampo.getTotalScores()+homeMatchScore);
						}
						
						if(teampo.getShortName().equals(names[1])){
							ishome=false;
							for(int i=3;i<singleMatchInfo.size();i++){
								if(singleMatchInfo.get(i).length()==3)
									position=i+1;
							}
							//singleMatchScore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[1]);
							teampo.setTotalScores(teampo.getTotalScores()+visitMatchScore);
						}
						
						if(position==0)
							continue;
						
						
						teampo.setTotalMatches(teampo.getTotalMatches()+1);
						//计算对手的一部分信息
						int oppopos=0;
						int opposcore=0;
						int opposhots=0;
						int oppofreethrows=0;
						int opposhotsontarget=0;
						int oppoturnovers=0;
						int oppodefensiverebound=0;
						int oppooffensiverebound=0;
						
						teamPO oppoteam=new teamPO();
						
						if(position==3){
							for(int i=3;i<singleMatchInfo.size();i++){
								if(singleMatchInfo.get(i).length()==3)
									oppopos=i+1;
							}
							opposcore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[1]);
							myscore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[0]);
							oppoteam.setTotalScores(opposcore);
						}else{
							oppopos=3;
							opposcore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[0]);
							myscore=Integer.parseInt(singleMatchInfo.get(0).split(";")[2].split("-")[1]);
							oppoteam.setTotalScores(opposcore);
						}
						
						
						if(oppopos==3){
							for(int i=oppopos;i<position-1;i++){
								String[] personDetail=singleMatchInfo.get(i).split(";");
								opposhots=opposhots+Integer.parseInt(personDetail[4]);
								oppofreethrows=oppofreethrows+Integer.parseInt(personDetail[8]);
								opposhotsontarget=opposhotsontarget+Integer.parseInt(personDetail[3]);
								oppoturnovers=oppoturnovers+Integer.parseInt(personDetail[15]);
								oppodefensiverebound=oppodefensiverebound+Integer.parseInt(personDetail[10]);
								oppooffensiverebound=oppooffensiverebound+Integer.parseInt(personDetail[9]);
							}
							
						}else{
							for(int i=oppopos;i<singleMatchInfo.size();i++){
								String[] personDetail=singleMatchInfo.get(i).split(";");
																
								opposhots=opposhots+Integer.parseInt(personDetail[4]);
								oppofreethrows=oppofreethrows+Integer.parseInt(personDetail[8]);
								opposhotsontarget=opposhotsontarget+Integer.parseInt(personDetail[3]);
								oppoturnovers=oppoturnovers+Integer.parseInt(personDetail[15]);
								oppodefensiverebound=oppodefensiverebound+Integer.parseInt(personDetail[10]);
								oppooffensiverebound=oppooffensiverebound+Integer.parseInt(personDetail[9]);
							}
						}
						
						
						if(homeMatchScore>opposcore)
							teampo.setWinMatches(teampo.getWinMatches()+1);
							
							
						
						
						//读取本队的比赛信息
						int ShotsOnTargets=0;
						int TotalShots=0;
						int ThreePointShotsOnTargets=0;
						int TotalThreePointShots=0;
						int FreeThrowOnTargets=0;
						int TotalFreeThrows=0;
						int OffensiveRebound=0;
						int DefensiveRebound=0;
						int TotalRebounds=0;
						int TotalAssists=0;
						int TotalSteals=0;
						int TotalRejection=0;
						int TotalTurnovers=0;
						int TotalFouls=0;
						//累加每一个球员的数据
						while(position<singleMatchInfo.size()){
							if(singleMatchInfo.get(position).length()==3)
								break;
							
							String[] personDetail=singleMatchInfo.get(position).split(";");
							ShotsOnTargets=ShotsOnTargets+Integer.parseInt(personDetail[3]);
							TotalShots=TotalShots+Integer.parseInt(personDetail[4]);
							ThreePointShotsOnTargets=ThreePointShotsOnTargets+Integer.parseInt(personDetail[5]);
							TotalThreePointShots=TotalThreePointShots+Integer.parseInt(personDetail[6]);
							FreeThrowOnTargets=FreeThrowOnTargets+Integer.parseInt(personDetail[7]);
							TotalFreeThrows=TotalFreeThrows+Integer.parseInt(personDetail[8]);
							OffensiveRebound=OffensiveRebound+Integer.parseInt(personDetail[9]);
							DefensiveRebound=DefensiveRebound+Integer.parseInt(personDetail[10]);
							TotalRebounds=TotalRebounds+Integer.parseInt(personDetail[11]);
							TotalAssists=TotalAssists+Integer.parseInt(personDetail[12]);
							TotalSteals=TotalSteals+Integer.parseInt(personDetail[13]);
							TotalRejection=TotalRejection+Integer.parseInt(personDetail[14]);
							TotalTurnovers=TotalTurnovers+Integer.parseInt(personDetail[15]);
							TotalFouls=TotalFouls+Integer.parseInt(personDetail[16]);

							position++;
						}
						
						tempteam.setSeason(files.split("_")[0]);
						tempteam.setDate(files.split("_")[1]);
						tempteam.setNames(files.split("_")[2]);
						tempteam.setTotalScore(myscore);
						tempteam.setIsWin(myscore>opposcore);
						tempteam.setTotalAssists(TotalAssists);
						tempteam.setTotalRebounds(TotalRebounds);
						tempteam.setTotalRejections(TotalRejection);
						tempteam.setTotalSteals(TotalSteals);
						tempteam.setShotPercent((double)ShotsOnTargets/(double)TotalShots);
						tempteam.setThreePointPercent((double)ThreePointShotsOnTargets/(double)TotalThreePointShots);
						tempteam.setFreeThrowPercent((double)FreeThrowOnTargets/(double)TotalFreeThrows);
						
						teampo.setShotsOnTargets(teampo.getShotsOnTargets()+ShotsOnTargets);
						teampo.setTotalShots(teampo.getTotalShots()+TotalShots);
						teampo.setThreePointShotsOnTargets(teampo.getThreePointShotsOnTargets()+ThreePointShotsOnTargets);
						teampo.setTotalThreePointShots(teampo.getTotalThreePointShots()+TotalThreePointShots);
						teampo.setFreeThrowOnTargets(teampo.getFreeThrowOnTargets()+FreeThrowOnTargets);
						teampo.setTotalFreeThrows(teampo.getTotalFreeThrows()+TotalFreeThrows);
						teampo.setOffensiveRebound(teampo.getOffensiveRebound()+OffensiveRebound);
						teampo.setDefensiveRebound(teampo.getDefensiveRebound()+DefensiveRebound);
						teampo.setTotalRebounds(teampo.getTotalRebounds()+TotalRebounds);
						teampo.setTotalAssists(teampo.getTotalAssists()+TotalAssists);
						teampo.setTotalSteals(teampo.getTotalSteals()+TotalSteals);
						teampo.setTotalRejection(teampo.getTotalRejection()+TotalRejection);
						teampo.setTotalTurnovers(teampo.getTotalTurnovers()+TotalTurnovers);
						teampo.setTotalFouls(teampo.getTotalFouls()+TotalFouls);
						
						double OffensiveRound=TotalShots+0.4*TotalFreeThrows-1.07*((double)OffensiveRebound/(double)(OffensiveRebound+oppoteam.getDefensiveRebound())*(TotalShots-ShotsOnTargets))+1.07*TotalTurnovers;
						double oppoOffensiveRound=opposhots+0.4*oppofreethrows-1.07*((double)oppodefensiverebound/(double)(oppodefensiverebound+oppooffensiverebound))*(opposhots-opposhotsontarget);
						
						teampo.setOffensiveRound(teampo.getOffensiveRebound()+OffensiveRound);
						teampo.setOffendEfficiency(teampo.getOffendEfficiency()+100.0*(double)myscore/(double)OffensiveRound);
						teampo.setDefendEfficiency(teampo.getDefendEfficiency()+100.0*(double)myscore/(double)oppoOffensiveRound);
						
						teampo.setOffendReboundEfficiency(teampo.getOffendReboundEfficiency()+(double)OffensiveRebound/(double)(OffensiveRebound+oppoteam.getDefensiveRebound()));
						teampo.setDefendReboundEfficiency(teampo.getDefendReboundEfficiency()+(double)DefensiveRebound/(double)(DefensiveRebound+oppoteam.getOffensiveRebound()));
						teampo.setStealEfficiency(teampo.getStealEfficiency()+100.0*(double)TotalSteals/(double)oppoOffensiveRound);
						teampo.setAssistEfficiency(teampo.getAssistEfficiency()+100.0*(double)TotalAssists/(double)OffensiveRound);
						
						/*teamInSingleMatchPO tempteam=new teamInSingleMatchPO();
						
						tempteam.setSeason(files.split("_")[0]);
						tempteam.setDate(files.split("_")[1]);
						tempteam.setNames(files.split("_")[2]);
						if(ishome){
							tempteam.setTotalScore(homeMatchScore);
							tempteam.setTotalAssists(TotalAssists);
							tempteam.setTotalRebounds(TotalRebounds);
							tempteam.setTotalRejections(TotalRejection);
							tempteam.setTotalSteals(TotalSteals);
							tempteam.setShotPercent((double)ShotsOnTargets/(double)TotalShots);
							tempteam.setThreePointPercent((double)ThreePointShotsOnTargets/(double)TotalThreePointShots);
							tempteam.setFreeThrowPercent((double)FreeThrowOnTargets/(double)TotalFreeThrows);
							tempteam.setIsWin(homeMatchScore>visitMatchScore);
							//后续补充所有球队信息
						}else{
							tempteam.setTotalScore(visitMatchScore);
							tempteam.setTotalAssists(totalAssists);
						}*/
						
						if(teampo.getRecentFive().size()<5){
							ArrayList<teamInSingleMatchPO> temp=teampo.getRecentFive();
							temp.add(tempteam);
							teampo.setRecentFive(temp);
						}else{
							ArrayList<teamInSingleMatchPO> temp=teampo.getRecentFive();
							temp.remove(0);
							temp.add(tempteam);
							teampo.setRecentFive(temp);
						}
							
					}
				}
				
				//计算其他信息
				for(teamPO teampo:teamAllInfo){
					teampo.setShotPercent((double)teampo.getShotsOnTargets()/(double)teampo.getTotalShots());
					teampo.setThreePointPercent((double)teampo.getThreePointShotsOnTargets()/(double)teampo.getTotalThreePointShots());
					teampo.setFreeThrowPercent((double)teampo.getFreeThrowOnTargets()/(double)teampo.getTotalFreeThrows());
					teampo.setWinPercent((double)teampo.getWinMatches()/(double)teampo.getTotalMatches());
				}
		
		DataBaseServiceImpl dataBaseServiceWrite=new DataBaseServiceImpl<teamPO>("tempdata/team"+season+".tpd",teamAllInfo);
		dataBaseServiceWrite.write();

	}
	public ArrayList<teamPO> findAll(String season){
		File file=new File("tempdata");
		String[] tempdata=file.list();
		boolean exist=false;//判断当赛季数据是否已经存在
		
		for(String datafile:tempdata){
			if(datafile.contains("team")&&datafile.contains(season))
				exist=true;
		}
		
		if(!exist)
			initialData(season);
		
		DataBaseServiceImpl dataBaseService=new DataBaseServiceImpl<teamPO>("tempdata/team"+season+".tpd",teamAllInfo);
		dataBaseService.readFromTemp();
		
		return teamAllInfo;
		
	}
	
	public void deleteTemp(String season){
		File file=new File("tempdata");
		String[] tempdata=file.list();
		//boolean exist=false;//判断当赛季数据是否已经存在
		
		for(String datafile:tempdata){
			if(datafile.contains("team")&&datafile.contains(season)){
				File tempfile=new File("tempdata/"+datafile);
				tempfile.delete();
				break;
			}
		}
	}
}
