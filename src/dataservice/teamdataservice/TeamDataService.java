package dataservice.teamdataservice;

import java.util.ArrayList;
import po.teamPO;

public interface TeamDataService {
	public void initialData();
	public ArrayList<teamPO> findAll();

}
