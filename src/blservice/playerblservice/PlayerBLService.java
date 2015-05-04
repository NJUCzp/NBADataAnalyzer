package blservice.playerblservice;

import java.util.ArrayList;

import po.playerInSingleMatchPO;
import constantinfo.ScreenBy;
import constantinfo.SortBy;
//import po.playerPO;
import vo.playerVO;

public interface PlayerBLService {
	public ArrayList<playerVO> findAll();
	public ArrayList<playerInSingleMatchPO> findByDate(String date);
	public ArrayList<playerVO> sortBy(SortBy sortby,boolean isUP);
	public ArrayList<playerVO> sortBy(SortBy sortby,boolean isUP,ArrayList<playerVO> playervolist);
	public ArrayList<playerInSingleMatchPO> sortByDaily(SortBy sortby,ArrayList<playerInSingleMatchPO> dailyplayerlist);
	public ArrayList<playerVO> screen(ArrayList<ScreenBy> screenby,SortBy sortby);

}
