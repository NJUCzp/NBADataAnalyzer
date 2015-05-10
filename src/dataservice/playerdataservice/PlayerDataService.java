package dataservice.playerdataservice;

import java.util.ArrayList;

import po.playerInSingleMatchPO;
import po.playerPO;

public interface PlayerDataService {
	public void initialData(String season);
	public ArrayList<playerPO> finaAll(String season);
	public void deleteTemp(String season);
	public ArrayList<playerInSingleMatchPO> findByDate(String season,String date);

}
