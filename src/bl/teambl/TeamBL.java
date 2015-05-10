package bl.teambl;

import java.util.ArrayList;

import vo.teamVO;
import constantinfo.SortBy;
import blservice.teamblservice.TeamBLService;

public class TeamBL implements TeamBLService{
	TeamBLFind teamblfind=new TeamBLFind();
	ArrayList<teamVO> teamvolist=new ArrayList<teamVO>();

	@Override
	public ArrayList<teamVO> findAll(String season) {
		teamvolist=teamblfind.finaAll(season);
		// TODO Auto-generated method stub
		return teamvolist;
	}
	
	public void deleteTemp(String season){
		teamblfind.deleteTemp(season);
	}

	/*@Override
	public ArrayList<teamVO> sortBy(SortBy sortby,boolean isUP) {
		teamvolist=teamblfind.finaAll();
		TeamBLSort sortteam=new TeamBLSort(teamvolist,sortby,isUP);
		sortteam.sort(teamvolist,0,teamvolist.size()-1);
		// TODO Auto-generated method stub
		return teamvolist;
	}*/

	@Override
	public ArrayList<teamVO> sortBy(SortBy sortby, boolean isUP,
			ArrayList<teamVO> teamvolist) {
		TeamBLSort sortteam=new TeamBLSort(teamvolist,sortby,isUP);
		sortteam.sort(teamvolist,0,teamvolist.size()-1);
		// TODO Auto-generated method stub
		return teamvolist;
	}

}
