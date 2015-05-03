package bl.playerbl;

import java.util.ArrayList;

import po.playerPO;
import vo.playerVO;
import vo.teamVO;
import constantinfo.Constant;
import constantinfo.ScreenBy;
import constantinfo.SortBy;
import bl.teambl.TeamBLFind;
import blservice.playerblservice.PlayerBLService;

public class PlayerBL implements PlayerBLService{
	PlayerBLFind playerblfind=new PlayerBLFind();
	ArrayList<playerVO> playervolist=new ArrayList<playerVO>();

	@Override
	public ArrayList<playerVO> findAll() {
		playervolist=playerblfind.findAll();
		// TODO Auto-generated method stub
		
		return playervolist;
	}

	@Override
	public ArrayList<playerVO> sortBy(SortBy sortby,boolean isUP) {
		playervolist=playerblfind.findAll();
		PlayerBLSort sortplayer=new PlayerBLSort(playervolist,sortby,isUP);
		sortplayer.sort(playervolist,0, playervolist.size()-1);
		return playervolist;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<playerVO> screen(ArrayList<ScreenBy> screenby,SortBy sortby) {
		playervolist=playerblfind.findAll();
		ArrayList<playerVO> toscreenlist=playervolist;
		
		//初步筛选
		//筛选位置
		switch (screenby.get(0)){
		case F:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!toscreenlist.get(length).position.equals("F"))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case C:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!toscreenlist.get(length).position.equals("C"))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case G:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!toscreenlist.get(length).position.equals("G"))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case DEFAULT:{
			
		}
		}
		//筛选东/西部
		switch (screenby.get(1)){
		case E:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.EAST_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case W:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.WEST_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case DEFAULT:{}
			
		}
		//筛选联盟
		switch (screenby.get(2)){
		case SOUTHEAST:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.SOUTHEAST_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case ATLANTIC:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.ATLANTIC_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case CENTRAL:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.CENTRAL_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case NORTHWEST:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.NORTHWEST_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case PACIFIC:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.PACIFIC_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case SOUTHWEST:{
			int length=0;
			while(length<toscreenlist.size()){
				if(!Constant.SOUTHWEST_TEAM.contains(toscreenlist.get(length).team.toLowerCase()))
					toscreenlist.remove(length);
				else
					length++;
			}
			break;
		}
		case DEFAULT:{}
		}
		
		//System.out.println(toscreenlist.get(0).totalScores);
		
		PlayerBLSort sortplayer=new PlayerBLSort(toscreenlist,sortby,false);
		sortplayer.sort(toscreenlist,0, toscreenlist.size()-1);
		
		// TODO Auto-generated method stub
		if(toscreenlist.size()>50){
			while (toscreenlist.size()>50)
				toscreenlist.remove(50);
			return toscreenlist;
		}
			
		else
			return toscreenlist;
	}

}
