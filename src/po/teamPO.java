package po;

import java.io.Serializable;
import java.util.ArrayList;

public class teamPO implements Serializable{
	private String fullname="";
	private String shortname="";
	private String league="";
	private String city="";
	private String EorW="";
	private int totalMatches=0;
	private int winMatches=0;
	private int totalShots=0;
	private int shotsOnTargets=0;
	private int totalThreePointShots=0;
	private int threePointShotsOnTargets=0;
	private int totalFreeThrows=0;
	private int freeThrowOnTargets=0;
	private int offensiveRebound=0;
	private int defensiveRebound=0;
	private int totalRebounds=0;
	private int totalAssists=0;
	private int totalSteals=0;
	private int totalRejection=0;
	private int totalTurnovers=0;
	private int totalFouls=0;
	private int totalScores=0;
	private double shotPercent=0.0;
	private double threePointPercent=0.0;
	private double freeThrowPercent=0.0;
	private double winPercent=0.0;
	private double offensiveRound=0.0;
	private double offendEfficiency=0;
	private double defendEfficiency=0;
	private double offendReboundEfficiency=0.0;
	private double defendReboundEfficiency=0.0;
	private double stealEfficiency=0.0;
	private double assistEfficiency=0.0;
	
	private ArrayList<teamInSingleMatchPO> recentFive=new ArrayList<teamInSingleMatchPO>();

	public teamPO(String name){
		if (name.length()==3)
			this.shortname=name;
		else
			this.fullname=name;
	}
	
	public teamPO(){
		
	}
	
	public void setFullName(String fullname){
		this.fullname=fullname;
	}
	public String getFullName(){
		return fullname;
	}
	
	public void setEorW(String EorW){
		this.EorW=EorW;
	}
	public String getEorW(){
		return EorW;
	}
	
	public void setLeague(String league){
		this.league=league;
	}
	public String getLeague(){
		return league;
	}
	
	public void setShortName(String shortname){
		this.shortname=shortname;
	}
	public String getShortName(){
		return shortname;
	}
	
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}
	
	public void setTotalMatches(int totalMatches){
		this.totalMatches=totalMatches;
	}
	public int getTotalMatches(){
		return totalMatches;
	}
	
	public void setWinMatches(int winMatches){
		this.winMatches=winMatches;
	}
	public int getWinMatches(){
		return winMatches;
	}
	
	public void setTotalShots(int totalShots){
		this.totalShots=totalShots;
	}
	public int getTotalShots(){
		return totalShots;
	}
	
	public void setShotsOnTargets(int shotsOnTargets){
		this.shotsOnTargets=shotsOnTargets;
	}
	public int getShotsOnTargets(){
		return shotsOnTargets;
	}
	
	public void setTotalThreePointShots(int totalThreePointShots){
		this.totalThreePointShots=totalThreePointShots;
	}
	public int getTotalThreePointShots(){
		return totalThreePointShots;
	}
	
	public void setThreePointShotsOnTargets(int threePointShotsOnTargets){
		this.threePointShotsOnTargets=threePointShotsOnTargets;
	}
	public int getThreePointShotsOnTargets(){
		return threePointShotsOnTargets;
	}
	
	public void setTotalFreeThrows(int totalFreeThrows){
		this.totalFreeThrows=totalFreeThrows;
	}
	public int getTotalFreeThrows(){
		return totalFreeThrows;
	}
	
	public void setFreeThrowOnTargets(int freeThrowOnTargets){
		this.freeThrowOnTargets=freeThrowOnTargets;
	}
	public int getFreeThrowOnTargets(){
		return freeThrowOnTargets;
	}
	
	public void setOffensiveRebound(int offensiveRebound){
		this.offensiveRebound=offensiveRebound;
	}
	public int getOffensiveRebound(){
		return offensiveRebound;
	}
	
	public void setDefensiveRebound(int defensiveRebound){
		this.defensiveRebound=defensiveRebound;
	}
	public int getDefensiveRebound(){
		return defensiveRebound;
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
	
	public void setTotalSteals(int totalSteals){
		this.totalSteals=totalSteals;
	}
	public int getTotalSteals(){
		return totalSteals;
	}
	
	public void setTotalRejection(int totalRejection){
		this.totalRejection=totalRejection;
	}
	public int getTotalRejection(){
		return totalRejection;
	}
	
	public void setTotalTurnovers(int totalTurnovers){
		this.totalTurnovers=totalTurnovers;
	}
	public int getTotalTurnovers(){
		return totalTurnovers;
	}
	
	public void setTotalFouls(int totalFouls){
		this.totalFouls=totalFouls;
	}
	public int getTotalFouls(){
		return totalFouls;
	}
	
	public void setTotalScores(int totalScores){
		this.totalScores=totalScores;
	}
	public int getTotalScores(){
		return totalScores;
	}
	
	public void setShotPercent(double shotPercent){
		this.shotPercent=shotPercent;
	}
	public double getShotPercent(){
		return shotPercent;
	}
	
	public void setFreeThrowPercent(double freeThrowPercent){
		this.freeThrowPercent=freeThrowPercent;
	}
	public double getFreeThrowPercent(){
		return freeThrowPercent;
	}
	
	public void setThreePointPercent(double threePointPercent){
		this.threePointPercent=threePointPercent;
	}
	public double getThreePointPercent(){
		return threePointPercent;
	}
	
	public void setWinPercent(double winPercent){
		this.winPercent=winPercent;
	}
	public double getWinPercent(){
		return winPercent;
	}
	
	public void setOffensiveRound(double offensiveRound){
		this.offensiveRound=offensiveRound;
	}
	public double getOffensiveRound(){
		return offensiveRound;
	}
	
	public void setOffendEfficiency(double offendEfficiency){
		this.offendEfficiency=offendEfficiency;
	}
	public double getOffendEfficiency(){
		return offendEfficiency;
	}
	
	public void setDefendEfficiency(double defendEfficiency){
		this.defendEfficiency=defendEfficiency;
	}
	public double getDefendEfficiency(){
		return defendEfficiency;
	}
	
	public void setOffendReboundEfficiency(double offendReboundEfficiency){
		this.offendReboundEfficiency=offendReboundEfficiency;
	}
	public double getOffendReboundEfficiency(){
		return offendReboundEfficiency;
	}
	
	public void setDefendReboundEfficiency(double defendReboundEfficiency){
		this.defendReboundEfficiency=defendReboundEfficiency;
	}
	public double getDefendReboundEfficiency(){
		return defendReboundEfficiency;
	}
	
	public void setStealEfficiency(double stealEfficiency){
		this.stealEfficiency=stealEfficiency;
	}
	public double getStealEfficiency(){
		return stealEfficiency;
	}
	
	public void setAssistEfficiency(double assistEfficiency){
		this.assistEfficiency=assistEfficiency;
	}
	public double getAssistEfficiency(){
		return assistEfficiency;
	}
	
	public void setRecentFive(ArrayList<teamInSingleMatchPO> recentFive){
		this.recentFive=recentFive;
	}
	public ArrayList<teamInSingleMatchPO> getRecentFive(){
		return recentFive;
	}
		
}
