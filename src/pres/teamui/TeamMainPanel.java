package pres.teamui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import bl.teambl.TeamBL;
import blservice.teamblservice.TeamBLService;
import constantinfo.Constant;
import constantinfo.SortBy;
import vo.teamVO;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;

public class TeamMainPanel extends CommonPanel{
	CommonButton back;
	CommonButton sort_up;
	CommonButton sort_down;
	CommonTable table;
	CommonButton upgrade;
	Object[][] teamdata;
	ArrayList<teamVO> teamlist;
	JScrollPane scroll;
	JComboBox sortby;
	JComboBox seasons;
	JComboBox AT;//average or total
	JLabel teaminfo;
	JLabel AorT;
	JLabel chooseseason;
	
	String season="13-14";//默认13-14赛季
	boolean isUP=true;
	boolean isTotal=true;
	
	TeamBLService teamblservice;
	
	
	public TeamMainPanel(){
		super("graphics/detailpanel/detail_background.png");
		
		String[] sorts={"比赛场数","投篮命中数","投篮出手次数","三分命中数","三分出手数","罚球命中数","罚球出手数","进攻篮板数","防守篮板数","篮板数","助攻数","抢断数","盖帽数","失误数","犯规数","比赛得分","投篮命中率","三分命中率","罚球命中率","胜率","进攻回合","进攻效率","防守效率","进攻篮板效率","防守篮板效率","抢断效率","助攻率"};
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		Font font2=new Font("微软雅黑",Font.BOLD,15);
		
		teaminfo=new JLabel(new ImageIcon("graphics/detailpanel/teaminfo_label.png"));
		teaminfo.setBounds(500, 20, 180, 45);
		teaminfo.setVisible(true);
		functionlabel.add(teaminfo);
		
		AorT=new JLabel("总和/平均");
		AorT.setFont(font2);
		AorT.setBounds(40, 150, 100, 30);
		AorT.setVisible(true);
		functionlabel.add(AorT);
		
		chooseseason=new JLabel("选择赛季");
		chooseseason.setFont(font2);
		chooseseason.setBounds(45, 220, 100, 30);
		chooseseason.setVisible(true);
		functionlabel.add(chooseseason);
		
		AT=new JComboBox(new String[]{"总和","平均"});
		AT.setBounds(140, 150, 120, 35);
		AT.setFont(font1);
		AT.setBorder(BorderFactory.createEmptyBorder());
		AT.setSelectedItem(null);
		AT.addActionListener(new ATlistener());
		AT.setVisible(true);
		functionlabel.add(AT);
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(140, 220, 120, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		
		/*sortby=new JComboBox(sorts);
		sortby.setBounds(140, 150, 120, 35);
		sortby.setFont(font1);
		sortby.setBorder(BorderFactory.createEmptyBorder());
		sortby.setSelectedItem(null);
		sortby.setVisible(true);
		functionlabel.add(sortby);*/
		
		upgrade=new CommonButton("graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade_pressed.png");
		upgrade.setBounds(20, 300, 240, 60);
		upgrade.addActionListener(new upgradelistener());
		upgrade.setVisible(true);
		functionlabel.add(upgrade);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(400+320, 600, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		
		/*sort_up=new CommonButton("graphics/actionbutton/up.png","graphics/actionbutton/up_pressed.png","graphics/actionbutton/up_pressed.png");
		sort_up.setBounds(400, 150, 240, 60);
		sort_up.setVisible(true);
		sort_up.addActionListener(new upsortlistener());
		functionlabel.add(sort_up);*/
		
		/*sort_down=new CommonButton("graphics/actionbutton/down.png","graphics/actionbutton/down_pressed.png","graphics/actionbutton/down.png");
		sort_down.setBounds(400+320, 150, 240, 60);
		sort_down.setVisible(true);
		sort_down.addActionListener(new downsortlistener());
		functionlabel.add(sort_down);*/
		
		teamblservice=new TeamBL();
		teamlist=teamblservice.findAll(season);
		
		addTable(teamlist);
		
	}
	
	public void addTable(ArrayList<teamVO> teamlist){
		//Object[] colomn={"球队名称","比赛场数","投篮命中数","场均投篮命中数","投篮出手次数","场均投篮出手次数","三分命中数","场均三分命中数","三分出手数","场均三分出手数","罚球命中数","场均罚球命中数","罚球出手数","场均罚球出手数","进攻篮板数","场均进攻篮板数","防守篮板数","场均防守篮板数","篮板数","场均篮板数","助攻数","场均助攻数","抢断数","场均抢断数","盖帽数","场均盖帽数","失误数","场均失误数","犯规数","场均犯规数","比赛得分","场均比赛得分","投篮命中率","三分命中率","罚球命中率","胜率","进攻回合","场均进攻回合","进攻效率","场均进攻效率","防守效率","场均防守效率","进攻篮板效率","场均进攻篮板效率","防守篮板效率","场均防守篮板效率","抢断效率","场均抢断效率","助攻率","场均助攻率"};
		Object[] colomn={};
		int length=0;
		
		teamdata=new Object[30][28];

		if(isTotal){
			for(teamVO teamvo:teamlist){
				Object[] tempcolomn={"球队名称","比赛场数","投篮命中数","投篮出手次数","三分命中数","三分出手数","罚球命中数","罚球出手数","进攻篮板数","防守篮板数","篮板数","助攻数","抢断数","盖帽数","失误数","犯规数","比赛得分","投篮命中率","三分命中率","罚球命中率","胜率","进攻回合","进攻效率","防守效率","进攻篮板效率","防守篮板效率","抢断效率","助攻率"};
				colomn=tempcolomn;
				
				teamdata[length][0]=teamvo.fullname;
				teamdata[length][1]=teamvo.totalMatches;
				teamdata[length][2]=teamvo.shotsOnTargets;
				teamdata[length][3]=teamvo.totalShots;
				teamdata[length][4]=teamvo.threePointShotsOnTargets;
				teamdata[length][5]=teamvo.totalFreeThrows;
				teamdata[length][6]=teamvo.totalThreePointShots;
				teamdata[length][7]=teamvo.freeThrowOnTargets;
				teamdata[length][8]=teamvo.offensiveRebound;
				teamdata[length][9]=teamvo.defensiveRebound;
				teamdata[length][10]=teamvo.totalRebounds;
				teamdata[length][11]=teamvo.totalAssists;
				teamdata[length][12]=teamvo.totalSteals;
				teamdata[length][13]=teamvo.totalRejection;
				teamdata[length][14]=teamvo.totalTurnovers;
				teamdata[length][15]=teamvo.totalFouls;
				teamdata[length][16]=teamvo.totalScores;

				teamdata[length][17]=String.format("%.2f", teamvo.shotPercent);
				teamdata[length][18]=String.format("%.2f", teamvo.threePointPercent);
				teamdata[length][19]=String.format("%.2f", teamvo.freeThrowPercent);
				teamdata[length][20]=String.format("%.2f", teamvo.winPercent);
				
				teamdata[length][21]=String.format("%.2f", teamvo.offensiveRound);
				teamdata[length][22]=String.format("%.2f", teamvo.offendEfficiency);
				teamdata[length][23]=String.format("%.2f", teamvo.defendEfficiency);
				teamdata[length][24]=String.format("%.2f", teamvo.offendReboundEfficiency);
				teamdata[length][25]=String.format("%.2f", teamvo.defendReboundEfficiency);
				teamdata[length][26]=String.format("%.2f", teamvo.stealEfficiency);
				teamdata[length][27]=String.format("%.2f", teamvo.assistEfficiency);

				length++;
			}
			
			
		}else{
			for(teamVO teamvo:teamlist){
				Object[] tempcolomn={"球队名称","比赛场数","场均投篮命中数","场均投篮出手次数","场均三分命中数","场均三分出手数","场均罚球命中数","场均罚球出手数","场均进攻篮板数","场均防守篮板数","场均篮板数","场均助攻数","场均抢断数","场均盖帽数","场均失误数","场均犯规数","场均比赛得分","投篮命中率","三分命中率","罚球命中率","胜率","场均进攻回合","场均进攻效率","场均防守效率","场均进攻篮板效率","场均防守篮板效率","场均抢断效率","场均助攻率"};
				colomn=tempcolomn;
				
				teamdata[length][0]=teamvo.fullname;
				teamdata[length][1]=teamvo.totalMatches;
				teamdata[length][2]=String.format("%.2f", (double)teamvo.shotsOnTargets/(double)teamvo.totalMatches);
				teamdata[length][3]=String.format("%.2f", (double)teamvo.totalShots/(double)teamvo.totalMatches);
				teamdata[length][4]=String.format("%.2f", (double)teamvo.threePointShotsOnTargets/(double)teamvo.totalMatches);
				teamdata[length][5]=String.format("%.2f", (double)teamvo.totalThreePointShots/(double)teamvo.totalMatches);
				teamdata[length][6]=String.format("%.2f", (double)teamvo.freeThrowOnTargets/(double)teamvo.totalMatches);
				teamdata[length][7]=String.format("%.2f", (double)teamvo.totalFreeThrows/(double)teamvo.totalMatches);
				teamdata[length][8]=String.format("%.2f", (double)teamvo.offensiveRebound/(double)teamvo.totalMatches);
				teamdata[length][9]=String.format("%.2f", (double)teamvo.defensiveRebound/(double)teamvo.totalMatches);
				teamdata[length][10]=String.format("%.2f", (double)teamvo.totalRebounds/(double)teamvo.totalMatches);
				teamdata[length][11]=String.format("%.2f", (double)teamvo.totalAssists/(double)teamvo.totalMatches);
				teamdata[length][12]=String.format("%.2f", (double)teamvo.totalSteals/(double)teamvo.totalMatches);
				teamdata[length][13]=String.format("%.2f", (double)teamvo.totalRejection/(double)teamvo.totalMatches);
				teamdata[length][14]=String.format("%.2f", (double)teamvo.totalTurnovers/(double)teamvo.totalMatches);
				teamdata[length][15]=String.format("%.2f", (double)teamvo.totalFouls/(double)teamvo.totalMatches);
				teamdata[length][16]=String.format("%.2f", (double)teamvo.totalScores/(double)teamvo.totalMatches);

				teamdata[length][17]=String.format("%.2f", teamvo.shotPercent);
				teamdata[length][18]=String.format("%.2f", teamvo.threePointPercent);
				teamdata[length][19]=String.format("%.2f", teamvo.freeThrowPercent);
				teamdata[length][20]=String.format("%.2f", teamvo.winPercent);
				
				teamdata[length][21]=String.format("%.2f", teamvo.offensiveRound/teamvo.totalMatches);
				teamdata[length][22]=String.format("%.2f", teamvo.offendEfficiency/teamvo.totalMatches);
				teamdata[length][23]=String.format("%.2f", teamvo.defendEfficiency/teamvo.totalMatches);
				teamdata[length][24]=String.format("%.2f", teamvo.offendReboundEfficiency/teamvo.totalMatches);
				teamdata[length][25]=String.format("%.2f", teamvo.defendReboundEfficiency/teamvo.totalMatches);
				teamdata[length][26]=String.format("%.2f", teamvo.stealEfficiency/teamvo.totalMatches);
				teamdata[length][27]=String.format("%.2f", teamvo.assistEfficiency/teamvo.totalMatches);

				length++;
			}
			
		}		
		
		/*teamdata=new Object[30][50];
				
		for(teamVO teamvo:teamlist){
			teamdata[length][0]=teamvo.fullname;
			teamdata[length][1]=teamvo.totalMatches;
			teamdata[length][2]=teamvo.shotsOnTargets;
			teamdata[length][3]=String.format("%.2f", (double)teamvo.shotsOnTargets/(double)teamvo.totalMatches);
			teamdata[length][4]=teamvo.totalShots;
			teamdata[length][5]=String.format("%.2f", (double)teamvo.totalShots/(double)teamvo.totalMatches);
			teamdata[length][6]=teamvo.threePointShotsOnTargets;
			teamdata[length][7]=String.format("%.2f", (double)teamvo.threePointShotsOnTargets/(double)teamvo.totalMatches);
			teamdata[length][8]=teamvo.totalThreePointShots;
			teamdata[length][9]=String.format("%.2f", (double)teamvo.totalThreePointShots/(double)teamvo.totalMatches);
			teamdata[length][10]=teamvo.freeThrowOnTargets;
			teamdata[length][11]=String.format("%.2f", (double)teamvo.freeThrowOnTargets/(double)teamvo.totalMatches);
			teamdata[length][12]=teamvo.totalFreeThrows;
			teamdata[length][13]=String.format("%.2f", (double)teamvo.totalFreeThrows/(double)teamvo.totalMatches);
			teamdata[length][14]=teamvo.offensiveRebound;
			teamdata[length][15]=String.format("%.2f", (double)teamvo.offensiveRebound/(double)teamvo.totalMatches);
			teamdata[length][16]=teamvo.defensiveRebound;
			teamdata[length][17]=String.format("%.2f", (double)teamvo.defensiveRebound/(double)teamvo.totalMatches);
			teamdata[length][18]=teamvo.totalRebounds;
			teamdata[length][19]=String.format("%.2f", (double)teamvo.totalRebounds/(double)teamvo.totalMatches);
			teamdata[length][20]=teamvo.totalAssists;
			teamdata[length][21]=String.format("%.2f", (double)teamvo.totalAssists/(double)teamvo.totalMatches);
			teamdata[length][22]=teamvo.totalSteals;
			teamdata[length][23]=String.format("%.2f", (double)teamvo.totalSteals/(double)teamvo.totalMatches);
			teamdata[length][24]=teamvo.totalRejection;
			teamdata[length][25]=String.format("%.2f", (double)teamvo.totalRejection/(double)teamvo.totalMatches);
			teamdata[length][26]=teamvo.totalTurnovers;
			teamdata[length][27]=String.format("%.2f", (double)teamvo.totalTurnovers/(double)teamvo.totalMatches);
			teamdata[length][28]=teamvo.totalFouls;
			teamdata[length][29]=String.format("%.2f", (double)teamvo.totalFouls/(double)teamvo.totalMatches);
			teamdata[length][30]=teamvo.totalScores;
			teamdata[length][31]=String.format("%.2f", (double)teamvo.totalScores/(double)teamvo.totalMatches);
			
			teamdata[length][32]=String.format("%.2f", teamvo.shotPercent);
			teamdata[length][33]=String.format("%.2f", teamvo.threePointPercent);
			teamdata[length][34]=String.format("%.2f", teamvo.freeThrowPercent);
			teamdata[length][35]=String.format("%.2f", teamvo.winPercent);
			
			teamdata[length][36]=String.format("%.2f", teamvo.offensiveRound);
			teamdata[length][37]=String.format("%.2f", teamvo.offensiveRound/teamvo.totalMatches);
			teamdata[length][38]=String.format("%.2f", teamvo.offendEfficiency);
			teamdata[length][39]=String.format("%.2f", teamvo.offendEfficiency/teamvo.totalMatches);
			teamdata[length][40]=String.format("%.2f", teamvo.defendEfficiency);
			teamdata[length][41]=String.format("%.2f", teamvo.defendEfficiency/teamvo.totalMatches);
			teamdata[length][42]=String.format("%.2f", teamvo.offendReboundEfficiency);
			teamdata[length][43]=String.format("%.2f", teamvo.offendReboundEfficiency/teamvo.totalMatches);
			teamdata[length][44]=String.format("%.2f", teamvo.defendReboundEfficiency);
			teamdata[length][45]=String.format("%.2f", teamvo.defendReboundEfficiency/teamvo.totalMatches);
			teamdata[length][46]=String.format("%.2f", teamvo.stealEfficiency);
			teamdata[length][47]=String.format("%.2f", teamvo.stealEfficiency/teamvo.totalMatches);
			teamdata[length][48]=String.format("%.2f", teamvo.assistEfficiency);
			teamdata[length][49]=String.format("%.2f", teamvo.assistEfficiency/teamvo.totalMatches);
			
			length++;
		}*/
		
		table=new CommonTable(teamdata,colomn);
		table.setPreferredScrollableViewportSize(new Dimension(500,300));
		table.setRowHeight(20);
		table.setFont(new Font("微软雅黑",Font.BOLD,15));
		//FitTableColumns(table);
		
		for(int i=0;i<table.getColumnCount();i++){
			TableColumn tc=table.getColumn(table.getColumnName(i));  
	        if(i==0) 
		        tc.setMinWidth(150);
	        else
	        	tc.setMinWidth(80);

		}
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setPreferredSize(new Dimension (table.getTableHeader().getMinimumSize().width,30));
		
		JTableHeader header=table.getTableHeader();
		header.addMouseListener(new tableadapter());
		table.updateUI();

		scroll = new JScrollPane();
		scroll.setViewportView(table);
		scroll.setLocation(400,250);
		scroll.setSize(500, 301);
		scroll.setVisible(true);
		functionlabel.add(scroll);
		
	}
	

		/*public void FitTableColumns(JTable myTable){
			JTableHeader header = myTable.getTableHeader();
			int rowCount = myTable.getRowCount();  
			Enumeration columns = myTable.getColumnModel().getColumns();
			while(columns.hasMoreElements()){
				TableColumn column = (TableColumn)columns.nextElement();
				int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
				int width = (int)myTable.getTableHeader().getDefaultRenderer()
						.getTableCellRendererComponent(myTable, column.getIdentifier()
                		 , false, false, -1, col).getPreferredSize().getWidth();
         for(int row = 0; row<rowCount; row++){
             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
             width = Math.max(width, preferedWidth);
         }
         header.setResizingColumn(column); // 此行很重要
         column.setWidth(width+myTable.getIntercellSpacing().width);
     }
		}*/
 
	class backlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showTeamPanel();// TODO Auto-generated method stub
			
		}
	}
	
	class upgradelistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			functionlabel.remove(table);
			functionlabel.remove(scroll);
			
			teamblservice.deleteTemp(season);
			teamlist=teamblservice.findAll(season);
			addTable(teamlist);
			
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class seasonslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			season=seasons.getSelectedItem().toString();
			
			functionlabel.remove(table);
			functionlabel.remove(scroll);
			
			teamlist.clear();
			teamlist=teamblservice.findAll(season);
			
			addTable(teamlist);
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ATlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(AT.getSelectedIndex()==0)
				isTotal=true;
			else
				isTotal=false;
			
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			
			addTable(teamlist);
			
			Constant.mainframe.repaint();
		}
		
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.TEAM_SHOTSONTARGETS;
			
			if(e.getSource()==table.getTableHeader()){
				int i=table.columnAtPoint(e.getPoint());
				//int j=table.convertColumnIndexToModel(i);
								
				switch(i-1){
				case 0:
					sort=SortBy.TEAM_TOTALMATCHES;break;
				case 1:{
					if (isTotal){
						sort=SortBy.TEAM_SHOTSONTARGETS;break;
					}
					else{
						sort=SortBy.TEAM_AVERAGESHOTSONTARGETS;break;
					}
				}
				case 2:{
					if (isTotal){
						sort=SortBy.TEAM_TOTALSHOTS;break;
					}else{
						sort=SortBy.TEAM_AVERAGESHOTS;break;
					}
				}
				case 3:{
					if (isTotal){
						sort=SortBy.TEAM_THREEPOINTSHOTSONTARGETS;break;
					}else{
						sort=SortBy.TEAM_AVERAGETHREEPOINTSHOTSONTARGETS;break;
					}
				}
				case 4:
					sort=SortBy.TEAM_TOTALTHREEPOINTSHOTS;break;
				case 5:
					sort=SortBy.TEAM_FREETHROWONTARGETS;break;
				case 6:
					sort=SortBy.TEAM_TOTALFREETHROWS;break;
				case 7:
					sort=SortBy.TEAM_OFFENSIVEREBOUND;break;
				case 8:
					sort=SortBy.TEAM_DEFENSIVEREBOUND;break;
				case 9:
					sort=SortBy.TEAM_TOTALREBOUNDS;break;
				case 10:
					sort=SortBy.TEAM_TOTALASSISTS;break;
				case 11:
					sort=SortBy.TEAM_TOTALSTEALS;break;
				case 12:
					sort=SortBy.TEAM_TOTALREJECTION;break;
				case 13:
					sort=SortBy.TEAM_TOTALTURNOVERS;break;
				case 14:
					sort=SortBy.TEAM_TOTALFOULS;break;
				case 15:
					sort=SortBy.TEAM_TOTALSCORES;break;
				case 16:
					sort=SortBy.TEAM_SHOTPERCENT;break;
				case 17:
					sort=SortBy.TEAM_THREEPOINTPERCENT;break;
				case 18:
					sort=SortBy.TEAM_FREETHROWPERCENT;break;
				case 19:
					sort=SortBy.TEAM_WINPERCENT;break;
				case 20:
					sort=SortBy.TEAM_OFFENSIVEROUND;break;
				case 21:
					sort=SortBy.TEAM_OFFENDEFFICIENCY;break;
				case 22:
					sort=SortBy.TEAM_DEFENDEFFICIENCY;break;
				case 23:
					sort=SortBy.TEAM_OFFENDREBOUNDEFFICIENCY;break;
				case 24:
					sort=SortBy.TEAM_DEFENDREBOUNDEFFICIENCY;break;
				case 25:
					sort=SortBy.TEAM_STEALEFFICIENCY;break;
				case 26:
					sort=SortBy.TEAM_ASSISTEFFICIENCY;break;
				default:{}
				}
				
				
				addTable(teamblservice.sortBy(sort,isUP,teamlist));
				isUP=!isUP;
				
				Constant.mainframe.repaint();				
			};// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class upsortlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.TEAM_SHOTSONTARGETS;//默认排序

			switch (sortby.getSelectedIndex()){
			case 0:
				sort=SortBy.TEAM_TOTALMATCHES;break;
			case 1:
				sort=SortBy.TEAM_SHOTSONTARGETS;break;
			case 2:
				sort=SortBy.TEAM_TOTALSHOTS;break;
			case 3:
				sort=SortBy.TEAM_THREEPOINTSHOTSONTARGETS;break;
			case 4:
				sort=SortBy.TEAM_TOTALTHREEPOINTSHOTS;break;
			case 5:
				sort=SortBy.TEAM_FREETHROWONTARGETS;break;
			case 6:
				sort=SortBy.TEAM_TOTALFREETHROWS;break;
			case 7:
				sort=SortBy.TEAM_OFFENSIVEREBOUND;break;
			case 8:
				sort=SortBy.TEAM_DEFENSIVEREBOUND;break;
			case 9:
				sort=SortBy.TEAM_TOTALREBOUNDS;break;
			case 10:
				sort=SortBy.TEAM_TOTALASSISTS;break;
			case 11:
				sort=SortBy.TEAM_TOTALMATCHES;break;
			case 12:
				sort=SortBy.TEAM_TOTALSTEALS;break;
			case 13:
				sort=SortBy.TEAM_TOTALREJECTION;break;
			case 14:
				sort=SortBy.TEAM_TOTALTURNOVERS;break;
			case 15:
				sort=SortBy.TEAM_TOTALFOULS;break;
			case 16:
				sort=SortBy.TEAM_TOTALSCORES;break;
			case 17:
				sort=SortBy.TEAM_SHOTPERCENT;break;
			case 18:
				sort=SortBy.TEAM_THREEPOINTPERCENT;break;
			case 19:
				sort=SortBy.TEAM_FREETHROWPERCENT;break;
			case 20:
				sort=SortBy.TEAM_WINPERCENT;break;
			case 21:
				sort=SortBy.TEAM_OFFENSIVEROUND;break;
			case 22:
				sort=SortBy.TEAM_OFFENDEFFICIENCY;break;
			case 23:
				sort=SortBy.TEAM_DEFENDEFFICIENCY;break;
			case 24:
				sort=SortBy.TEAM_OFFENDREBOUNDEFFICIENCY;break;
			case 25:
				sort=SortBy.TEAM_DEFENDREBOUNDEFFICIENCY;break;
			case 26:
				sort=SortBy.TEAM_STEALEFFICIENCY;break;
			case 27:
				sort=SortBy.TEAM_ASSISTEFFICIENCY;break;
			default:{}

			}
			
			addTable(teamblservice.sortBy(sort,true,teamlist));
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class downsortlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.TEAM_TOTALSCORES;//默认以总分排序
			
			addTable(teamblservice.sortBy(sort,false,teamlist));
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
		
	}

}
