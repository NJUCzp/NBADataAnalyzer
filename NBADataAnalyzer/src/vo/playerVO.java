package vo;

public class playerVO {
	public String name;
	public String team;
	public String position;
	public String league;
	public String eorw;
	public int totalMatches;
	public int startingMatches;
	public int totalRebounds;
	public int totalAssists;
	public int timeOnCourt;
	public int totalshots;
	public int totalThreePointShots;
	public int totalFreeThrows;
	public double shotPercent;
	public double threePointPercent;
	public double freeThrowPercent;
	public int totalOffend;
	public int totalDefend;
	public int totalSteals;
	public int totalRejection;//¸ÇÃ±
	public int totalTurnovers;//Ê§Îó
	public int totalFouls;//·¸¹æ
	public int totalScores;
	public int efficiency;
	public int pairs;
	public double gmsc;
	public double realShotPercent;
	public double shotEfficiency;
	public double reboundPercent;
	public double offendReboundPercent;
	public double defendReboundPercent;
	public double assistPercent;
	public double stealPercent;
	public double rejectionPercent;
	public double turnoverPercent;
	public double usePercent;
	
	public playerVO(){
		
	}
	
	public playerVO(String name){
		this.name=name;
	}
	
	public playerVO(String name,String team){
		this.name=name;
		this.team=team;
	}

}
