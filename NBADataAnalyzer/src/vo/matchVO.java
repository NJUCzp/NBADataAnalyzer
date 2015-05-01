package vo;

import java.util.ArrayList;

import po.playerInSingleMatchPO;

public class matchVO {
	public String season;
	public String date;
	public String home;
	public String visiting;
	public int homeScore;
	public int visitingScore;
	public int[] homeDetailScore;
	public int[] visitingDetailScore;
	
	public ArrayList<playerInSingleMatchPO> hostPlayerInfo;
	public ArrayList<playerInSingleMatchPO> visitPlayerInfo;
}
