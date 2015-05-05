package dataservice.playerdataservice;

import java.util.ArrayList;

import po.playerInSingleMatchPO;
import po.playerPO;

public interface PlayerDataService {
	public void initialData(String season);
	public ArrayList<playerPO> finaAll(String season);
	public ArrayList<playerInSingleMatchPO> findByDate(String date);

}
