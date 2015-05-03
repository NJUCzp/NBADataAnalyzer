package bl.teambl;

import java.util.ArrayList;

import po.teamPO;
import vo.playerVO;
import vo.teamVO;
import constantinfo.SortBy;

public class TeamBLSort {
	ArrayList<teamVO> teamlist;
	SortBy sortby;
	boolean isUP;
	
	public TeamBLSort(ArrayList<teamVO> teamlist,SortBy sortby,boolean isUP){
		this.teamlist=teamlist;
		this.sortby=sortby;
		this.isUP=isUP;
	}
	
	public void sort(ArrayList<teamVO> teamvolist,int l,int r){
		int middle;
		if(l<r){
			middle=partition(teamvolist,l,r);
			sort(teamvolist,l,middle-1);
			sort(teamvolist,middle+1,r);	
		}
	}
	
	public int partition(ArrayList<teamVO> teamvolist,int l,int r){
		int i=l;
		int j=r;
		
		teamVO x=teamvolist.get(i);
		
		switch (sortby){
		case TEAM_SHOTSONTARGETS:{
			
			while(i<j){
				if (isUP)
					while(i<j && teamvolist.get(j).shotsOnTargets>=x.shotsOnTargets)
						j--;
				else
					while(i<j && teamvolist.get(j).shotsOnTargets<=x.shotsOnTargets)
						j--;
				
				if(i<j){
					teamvolist.set(i, teamvolist.get(j));
				}
					
				if (isUP)
					while(i<j&& teamvolist.get(i).shotsOnTargets<x.shotsOnTargets)
						i++;
				else
					while(i<j&& teamvolist.get(i).shotsOnTargets>x.shotsOnTargets)
						i++;
				
				if(i<j){
					teamvolist.set(j, teamvolist.get(i));
				}
					
			}
			break;
		}
		
		case TEAM_AVERAGESHOTSONTARGETS:{
			while(i<j){
				if (isUP)
					while(i<j && (double)teamvolist.get(j).shotsOnTargets/(double)teamvolist.get(j).totalMatches >=(double)x.shotsOnTargets/(double)teamvolist.get(j).totalMatches)
						j--;
				else
					while(i<j && (double)teamvolist.get(j).shotsOnTargets/(double)teamvolist.get(j).totalMatches <=(double)x.shotsOnTargets/(double)teamvolist.get(j).totalMatches)
						j--;
				
				if(i<j){
					teamvolist.set(i, teamvolist.get(j));
				}
					
				if (isUP)
					while(i<j && (double)teamvolist.get(j).shotsOnTargets/(double)teamvolist.get(j).totalMatches < (double)x.shotsOnTargets/(double)teamvolist.get(j).totalMatches)
						i++;
				else
					while(i<j && (double)teamvolist.get(j).shotsOnTargets/(double)teamvolist.get(j).totalMatches > (double)x.shotsOnTargets/(double)teamvolist.get(j).totalMatches)
						i++;
				
				if(i<j){
					teamvolist.set(j, teamvolist.get(i));
				}
					
			}
			break;
		}
		
		case TEAM_AVERAGESCORES:{
			while(i<j){
				if (isUP)
					while(i<j && (double)teamvolist.get(j).totalScores/(double)teamvolist.get(j).totalMatches >=(double)x.totalScores/(double)teamvolist.get(j).totalMatches)
						j--;
				else
					while(i<j && (double)teamvolist.get(j).totalScores/(double)teamvolist.get(j).totalMatches <=(double)x.totalScores/(double)teamvolist.get(j).totalMatches)
						j--;
				
				if(i<j){
					teamvolist.set(i, teamvolist.get(j));
				}
					
				if (isUP)
					while(i<j && (double)teamvolist.get(j).totalScores/(double)teamvolist.get(j).totalMatches < (double)x.totalScores/(double)teamvolist.get(j).totalMatches)
						i++;
				else
					while(i<j && (double)teamvolist.get(j).totalScores/(double)teamvolist.get(j).totalMatches > (double)x.totalScores/(double)teamvolist.get(j).totalMatches)
						i++;
				
				if(i<j){
					teamvolist.set(j, teamvolist.get(i));
				}
					
			}
			break;
		}
		
	}
		
		teamvolist.set(i,x);
		return i;
	}
	

}
