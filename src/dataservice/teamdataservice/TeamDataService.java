package dataservice.teamdataservice;

import java.util.ArrayList;
import po.teamPO;

public interface TeamDataService {
	public void initialData(String season);
	public ArrayList<teamPO> findAll(String season);

}
