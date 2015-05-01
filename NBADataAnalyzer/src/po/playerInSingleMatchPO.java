package po;

public class playerInSingleMatchPO {
	private String name;
	private String date;
	private String detailTimeOnCourt;
	private int timeOnCourt;
	private int score;
	private int assists;
	private int rebounds;
	private int turnovers;
	private int rejections;
	private int steals;
	
	private int totalShots=0;
	private int shotsOnTargets=0;
	private int totalThreePointShots=0;
	private int threePointShotsOnTargets=0;
	private int totalFreeThrows=0;
	private int freeThrowOnTargets=0;
	
	private double shotPercent;
	private double threePointPercent;
	private double freeThrowPercent;
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDate(String date){
		this.date=date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDetailTimeOnCourt(String detailTimeOnCourt){
		this.detailTimeOnCourt=detailTimeOnCourt;
	}
	
	public String getDetailTimeOnCourt(){
		return detailTimeOnCourt;
	}
	
	public void setTimeOnCourt(int timeOnCourt){
		this.timeOnCourt=timeOnCourt;
	}

	public int getTimeOnCourt(){
		return timeOnCourt;
	}
	
	public void setRebounds(int rebounds){
		this.rebounds=rebounds;
	}

	public int getRebounds(){
		return rebounds;
	}

	public void setAssists(int assists){
		this.assists=assists;
	}

	public int getAssists(){
		return assists;
	}
	
	public void setRejections(int rejections){
		this.rejections=rejections;
	}

	public int getRejections(){
		return rejections;
	}
	
	public void setSteals(int steals){
		this.steals=steals;
	}

	public int getSteals(){
		return steals;
	}
	
	public void setScore(int score){
		this.score=score;
	}

	public int getScore(){
		return score;
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
