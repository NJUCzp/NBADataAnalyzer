package bl.playerbl;

import java.util.ArrayList;

import po.playerPO;
import vo.playerVO;
import vo.teamVO;
import constantinfo.SortBy;

public class PlayerBLSort {
	ArrayList<playerVO> playerlist;
	ArrayList<playerVO> freeplayerlist;
	SortBy sortby;
	boolean isUP; 
	public PlayerBLSort(ArrayList<playerVO> playerlist,SortBy sortby,boolean isUP){
		this.playerlist=playerlist;
		this.sortby=sortby;
		this.isUP=isUP;
		checkEmpty();
	}
	
	public void sort(ArrayList<playerVO> playerlist,int l,int r){
		int middle;
		if(l<r){
			middle=partition(playerlist,l,r);
			sort(playerlist,l,middle-1);
			sort(playerlist,middle+1,r);	
		}
		
		if(freeplayerlist.size()>0){
			for(playerVO freeplayer:freeplayerlist)
				playerlist.add(freeplayer);
			freeplayerlist.clear();
		}
			
	}
	
	public int partition(ArrayList<playerVO> playerlist,int l,int r){
		int i=l;
		int j=r;
		
		playerVO x=playerlist.get(i);
		
		switch (sortby){
		case SCREEN_SCORE:{
			
			while(i<j){
				if (isUP)
					while(i<j && playerlist.get(j).totalScores>=x.totalScores)
						j--;
				else
					while(i<j && playerlist.get(j).totalScores<=x.totalScores)
						j--;
				
				if(i<j){
					playerlist.set(i, playerlist.get(j));
				}
					
				if (isUP)
					while(i<j&& playerlist.get(i).totalScores<x.totalScores)
						i++;
				else
					while(i<j&& playerlist.get(i).totalScores>x.totalScores)
						i++;
				
				if(i<j){
					playerlist.set(j, playerlist.get(i));
				}
					
			}
			break;
		}
		
		}
		
		playerlist.set(i,x);
		return i;		
	}
	
	public void checkEmpty(){
		freeplayerlist=new ArrayList<playerVO>();
		
		int length=0;
		for(playerVO singleplayer:playerlist ){
			if(singleplayer.team.equals("")){
				freeplayerlist.add(singleplayer);
			}
			length++;
		}
		playerlist.removeAll(freeplayerlist);
	}

}
