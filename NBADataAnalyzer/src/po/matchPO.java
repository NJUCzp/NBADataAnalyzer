package po;

import java.util.ArrayList;

public class matchPO {
	private String season="";
	private String date="";
	private String home="";
	private String visiting="";
	private int homeScore=0;
	private int visitingScore=0;
	private int[] homeDetailScore={0,0,0,0};
	private int[] visitingDetailScore={0,0,0,0};
	
	private ArrayList<playerInSingleMatchPO> hostPlayerInfo=new ArrayList<playerInSingleMatchPO>();
	private ArrayList<playerInSingleMatchPO> visitPlayerInfo=new ArrayList<playerInSingleMatchPO>();
	
	public void setSeason(String season){
		this.season=season;
	}
	
	public String getSeason(){
		return season;
	}
	
	public void setDate(String date){
		this.date=date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setVisiting(String visiting){
		this.visiting=visiting;
	}
	
	public void setHome(String home){
		this.home=home;
	}
	
	public String getHome(){
		return home;
	}
	
	public String getVisiting(){
		return visiting;
	}
	
	public void setHomeScore(int homeScore){
		this.homeScore=homeScore;
	}
	
	public int getHomeScore(){
		return homeScore;
	}
	
	public void setVisitingScore(int visitingScore){
		this.visitingScore=visitingScore;
	}
	
	public int getVisitingScore(){
		return visitingScore;
	}
	
	public void setHomeDetailScore(int[] homeDetailScore){
		this.homeDetailScore=homeDetailScore;
	}
	
	public int[] getHomeDetailScore(){
		return homeDetailScore;
	}
	
	public void setVisitingDetailScore(int[] visitingDetailScore){
		this.visitingDetailScore=visitingDetailScore;
	}
	
	public int[] getVisitingDetailScore(){
		return visitingDetailScore;
	}
	
	public void setHostPlayerInfo(ArrayList<playerInSingleMatchPO> hostPlayerInfo){
		this.hostPlayerInfo=hostPlayerInfo;
	}
	
	public ArrayList<playerInSingleMatchPO> getHostPlayerInfo(){
		return hostPlayerInfo;
	}
	
	public void setVisitPlayerInfo(ArrayList<playerInSingleMatchPO> visitPlayerInfo){
		this.visitPlayerInfo=visitPlayerInfo;
	}
	
	public ArrayList<playerInSingleMatchPO> getVisitPlayerInfo(){
		return visitPlayerInfo;
	}
	
	
	//private ArrayList<String> twoPairsPlayer=new ArrayList<String>();
	//private String mostScore="";
	//private String mostAssist="";
	//private String mostSteals="";
	//private String mostRejection="";
		

}
