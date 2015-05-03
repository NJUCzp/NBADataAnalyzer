package po;

import java.io.Serializable;

public class teamInSingleMatchPO implements Serializable{
	private String season;
	private String names;
	private String date;
	private boolean isWin;
	private int totalScore;
	private int totalRebounds;
	private int totalAssists;
	private int totalRejections;
	private int totalSteals;
	private double shotPercent;
	private double threePointPercent;
	private double freeThrowPercent;
	
	public void setSeason(String season){
		this.season=season;
	}
	
	public String getSeason(){
		return season;
	}
	
	public void setNames(String names){
		this.names=names;
	}
	
	public String getNames(){
		return names;
	}
	
	public void setDate(String date){
		this.date=date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setIsWin(boolean isWin){
		this.isWin=isWin;
	}
	
	public boolean getIsWin(){
		return isWin;
	}
	
	public void setTotalRebounds(int totalRebounds){
		this.totalRebounds=totalRebounds;
	}

	public int getTotalRebounds(){
		return totalRebounds;
	}

	public void setTotalAssists(int totalAssists){
		this.totalAssists=totalAssists;
	}

	public int getTotalAssists(){
		return totalAssists;
	}
	
	public void setTotalRejections(int totalRejections){
		this.totalRejections=totalRejections;
	}

	public int getTotalRejections(){
		return totalRejections;
	}
	
	public void setTotalSteals(int totalSteals){
		this.totalSteals=totalSteals;
	}

	public int getTotalSteals(){
		return totalSteals;
	}
	
	public void setTotalScore(int totalScore){
		this.totalScore=totalScore;
	}

	public int getTotalScore(){
		return totalScore;
	}
	
	public void setShotPercent(double shotPercent){
		this.shotPercent=shotPercent;
	}

	public double getShotPercent(){
		return shotPercent;
	}
	
	public void setThreePointPercent(double threePointPercent){
		this.threePointPercent=threePointPercent;
	}

	public double getThreePointPercent(){
		return threePointPercent;
	}
	
	public void setFreeThrowPercent(double freeThrowPercent){
		this.freeThrowPercent=freeThrowPercent;
	}

	public double getFreeThrowPercent(){
		return freeThrowPercent;
	}
	
}
