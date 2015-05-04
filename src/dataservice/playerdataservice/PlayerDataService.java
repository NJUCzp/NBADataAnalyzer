package dataservice.playerdataservice;

import java.util.ArrayList;

import po.playerInSingleMatchPO;
import po.playerPO;

public interface PlayerDataService {
	public void initialData();
	public ArrayList<playerPO> finaAll();
	public ArrayList<playerInSingleMatchPO> findByDate(String date);

}
