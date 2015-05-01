package po;

import java.io.Serializable;

public class playerPO implements Serializable{
	private String name="";
	private String team="";
	private String position="";
	private String league="";
	private String eorw="";
	private int totalMatches=0;
	private int startingMatches=0;
	private int totalRebounds=0;
	private int totalAssists=0;
	private int timeOnCourt=0;
	private double shotPercent=0.0;
	private double threePointPercent=0.0;
	private double freeThrowPercent=0.0;
	
	private int totalShots=0;
	private int shotsOnTargets=0;
	private int totalThreePointShots=0;
	private int threePointShotsOnTargets=0;
	private int totalFreeThrows=0;
	private int freeThrowOnTargets=0;
	private int totalScores=0;

	private int totalOffend=0;
	private int totalDefend=0;
	private int totalSteals=0;
	private int totalRejection=0;//¸ÇÃ±
	private int totalTurnovers=0;//Ê§Îó
	private int totalFouls=0;//·¸¹æ
	private int efficiency=0;
	private int pairs=0;
	private double gmsc=0.0;
	private double realShotPercent=0.0;
	private double shotEfficiency=0.0;
	private double reboundPercent=0.0;
	private double offendReboundPercent=0.0;
	private double defendReboundPercent=0.0;
	private double assistPercent=0.0;
	private double stealPercent=0.0;
	private double rejectionPercent=0.0;
	private double turnoverPercent=0.0;
	private double usePercent=0.0;

	public playerPO(String name){
		this.name=name;
	}
	
	public playerPO(String name,String team){
		this.name=name;
		this.team=team;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	
	public void setTeam(String team){
		this.team=team;
	}
	public String getTeam(){
		return team;
	}
	
	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}
	
	public void setLeague(String league){
		this.league=league;
	}
	public String getLeague(){
		return league;
	}
	
	public void setEorw(String eorw){
		this.eorw=eorw;
	}
	public String getEorw(){
		return eorw;
	}
	
	public void setTotalMatches(int totalMatches){
		this.totalMatches=totalMatches;
	}
	public int getTotalMatches(){
		return totalMatches;
	}
	
	public void setStartingMatches(int startingMatches){
		this.startingMatches=startingMatches;
	}
	public int getStartingMatches(){
		return startingMatches;
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
	
	public void setTimeOnCourt(int timeOnCourt){
		this.timeOnCourt=timeOnCourt;
	}
	public int getTimeOnCourt(){
		return timeOnCourt;
	}
	
	public void pairs(int pairs){
		this.pairs=pairs;
	}
	public int pairs(){
		return pairs;
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
	
	public void setTotalOffend(int totalOffend){
		this.totalOffend=totalOffend;
	}
	public int getTotalOffend(){
		return totalOffend;
	}
	
	public void setTotalDefend(int totalDefend){
		this.totalDefend=totalDefend;
	}
	public int getTotalDefend(){
		return totalDefend;
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
	
	public void setEfficiency(int efficiency){
		this.efficiency=efficiency;
	}
	public int getEfficiency(){
		return efficiency;
	}
	
	public void setPairs(int pairs){
		this.pairs=pairs;
	}
	public int getPairs(){
		return pairs;
	}
	
	public void setGmsc(double gmsc){
		this.gmsc=gmsc;
	}
	public double getGmsc(){
		return gmsc;
	}
	
	public void setRealShotPercent(double realShotPercent){
		this.realShotPercent=realShotPercent;
	}
	public double getRealShotPercent(){
		return realShotPercent;
	}
	
	public void setShotEfficiency(double shotEfficiency){
		this.shotEfficiency=shotEfficiency;
	}
	public double getShotEfficiency(){
		return shotEfficiency;
	}
	
	public void setReboundPercent(double reboundPercent){
		this.reboundPercent=reboundPercent;
	}
	public double getReboundPercent(){
		return reboundPercent;
	}
	
	public void setOffendReboundPercent(double offendReboundPercent){
		this.offendReboundPercent=offendReboundPercent;
	}
	public double getOffendReboundPercent(){
		return offendReboundPercent;
	}
	
	public void setDefendReboundPercent(double defendReboundPercent){
		this.defendReboundPercent=defendReboundPercent;
	}
	public double getDefendReboundPercent(){
		return defendReboundPercent;
	}
	
	public void setAssistPercent(double assistPercent){
		this.assistPercent=assistPercent;
	}
	public double getAssistPercent(){
		return assistPercent;
	}
	
	public void setStealPercent(double stealPercent){
		this.stealPercent=stealPercent;
	}
	public double getStealPercent(){
		return stealPercent;
	}
	
	public void setRejectionPercent(double rejectionPercent){
		this.rejectionPercent=rejectionPercent;
	}
	public double getRejectionPercent(){
		return rejectionPercent;
	}
	
	public void setTurnoverPercent(double turnoverPercent){
		this.turnoverPercent=turnoverPercent;
	}
	public double getTurnoverPercent(){
		return turnoverPercent;
	}
	
	public void setUsePercent(double usePercent){
		this.usePercent=usePercent;
	}
	public double getUsePercent(){
		return usePercent;
	}

}
