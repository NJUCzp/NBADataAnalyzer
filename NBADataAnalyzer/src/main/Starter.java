package main;

import java.io.File;
import java.io.IOException;

import databaseservice.DataBaseServiceImpl;
import dataservice.playerdataservice.PlayerDataServiceImpl;
import dataservice.teamdataservice.TeamDataServiceImpl;
import pres.mainui.mainFrame;
public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File datafile=new File("tempdata");
		TeamDataServiceImpl teamdataservice;
		PlayerDataServiceImpl playerdataservice;
		if(datafile.list().length==0){
			playerdataservice=new PlayerDataServiceImpl();
			teamdataservice=new TeamDataServiceImpl();
			playerdataservice.initialData();
			teamdataservice.initialData();
		}
		
		mainFrame mainframe=new mainFrame();
		mainframe.showMainPanel();

	}

}
