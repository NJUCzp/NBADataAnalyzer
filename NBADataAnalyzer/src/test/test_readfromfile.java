package test;

import java.util.ArrayList;

import po.playerPO;
import po.teamPO;
import databaseservice.DataBaseService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.PlayerDataServiceImpl;
import dataservice.teamdataservice.TeamDataService;
import dataservice.teamdataservice.TeamDataServiceImpl;

public class test_readfromfile {
	static ArrayList<teamPO> teampolist=new ArrayList<teamPO>();
	static ArrayList<playerPO> playerpolist=new ArrayList<playerPO>(); 
	static PlayerDataService playerdataservice=new PlayerDataServiceImpl();
	static TeamDataService teamdataservice=new TeamDataServiceImpl();
	public static void main(String[] args) {
		//teamdataservice.initialData();
		//teampolist=teamdataservice.findAll();
		/*System.out.println(teampolist.size());
		System.out.println(teampolist.get(0).getTotalMatches());
		System.out.println(teampolist.get(0).getOffensiveRound());*/
		playerdataservice.initialData();
		playerpolist=playerdataservice.finaAll();
		System.out.println(playerpolist.size());
		System.out.println(playerpolist.get(0).getTeam());
	}

}
