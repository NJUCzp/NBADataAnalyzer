package blservice.playerblservice;

import java.util.ArrayList;

import constantinfo.ScreenBy;
import constantinfo.SortBy;
//import po.playerPO;
import vo.playerVO;

public interface PlayerBLService {
	public ArrayList<playerVO> findAll();
	public ArrayList<playerVO> sortBy(SortBy sortby,boolean isUP);
	public ArrayList<playerVO> screen(ArrayList<ScreenBy> screenby,SortBy sortby);

}
