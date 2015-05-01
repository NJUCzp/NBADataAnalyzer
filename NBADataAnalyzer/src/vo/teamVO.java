package vo;

public class teamVO {
	public String fullname="";
	public String shortname="";
	public int totalMatches=0;
	public int totalShots=0;
	public int shotsOnTargets=0;
	public int totalThreePointShots=0;
	public int threePointShotsOnTargets=0;
	public int totalFreeThrows=0;
	public int freeThrowOnTargets=0;
	public int offensiveRebound=0;
	public int defensiveRebound=0;
	public int totalRebounds=0;
	public int totalAssists=0;
	public int totalSteals=0;
	public int totalRejection=0;
	public int totalTurnovers=0;
	public int totalFouls=0;
	public int totalScores=0;
	public double shotPercent=0.0;
	public double threePointPercent=0.0;
	public double freeThrowPercent=0.0;
	public double winPercent=0.0;
	public double offensiveRound=0.0;
	public double offendEfficiency=0;
	public double defendEfficiency=0;
	public double offendReboundEfficiency=0.0;
	public double defendReboundEfficiency=0.0;
	public double stealEfficiency=0.0;
	public double assistEfficiency=0.0;

	public teamVO(String name){
		if (name.length()==3)
			this.shortname=name;
		else
			this.fullname=name;
	}
	
	public teamVO(){
		
	}

}
