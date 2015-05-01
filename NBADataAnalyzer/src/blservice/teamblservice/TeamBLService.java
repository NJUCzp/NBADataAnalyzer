package blservice.teamblservice;

import java.util.ArrayList;

import constantinfo.SortBy;
import po.teamPO;
import vo.teamVO;

public interface TeamBLService {
	public ArrayList<teamVO> findAll();
	public ArrayList<teamVO> sortBy(SortBy sortby,boolean isUP);

}
