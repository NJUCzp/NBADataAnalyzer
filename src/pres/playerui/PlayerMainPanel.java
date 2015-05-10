package pres.playerui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import bl.playerbl.PlayerBL;
import bl.teambl.TeamBL;
import blservice.playerblservice.PlayerBLService;
import constantinfo.Constant;
import constantinfo.SortBy;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.playerVO;
import vo.teamVO;

public class PlayerMainPanel extends CommonPanel{
	CommonButton upsort;
	CommonButton downsort;
	CommonButton back;
	CommonButton screen;
	CommonButton mostprogressed;
	CommonButton seasonhot;
	CommonButton dailyhot;
	CommonButton upgrade;
	CommonTable table;
	String season="13-14";//Ĭ��չʾ13-14����
	Object[][] playerdata;
	ArrayList<playerVO> playerlist;
	JScrollPane scroll;
	JComboBox seasons;
	JComboBox AT;//average or total
	JLabel playerinfo;
	JLabel AorT;
	JLabel chooseseason;
	
	boolean isUP=true;
	boolean isTotal=true;
	
	PlayerBLService playerblservice;

	public PlayerMainPanel() {
		super("graphics/detailpanel/detail_background.png");
		
		//this.setLayout(null);
		//this.setSize(Constant.BG_WIDTH, Constant.BG_HEIGHT);
		
		String[] sorts={"��Ա����","�������","��������","�ȷ�����","������","������","�ڳ�ʱ��","Ͷ��������","����������","����������","������","������","������","��ñ��","ʧ����","������","�÷�","Ч��","GmScЧ��ֵ","��ʵ������","Ͷ��Ч��","������","����������","����������","������","������","��ñ��","ʧ����","ʹ����"};
		Font font1=new Font("΢���ź�",Font.BOLD,12);
		Font font2=new Font("΢���ź�",Font.BOLD,15);

		playerinfo=new JLabel(new ImageIcon("graphics/detailpanel/playerinfo_label.png"));
		playerinfo.setBounds(500, 20, 180, 45);
		playerinfo.setVisible(true);
		functionlabel.add(playerinfo);
		
		AorT=new JLabel("�ܺ�/ƽ��");
		AorT.setFont(font2);
		AorT.setBounds(40, 150, 100, 30);
		AorT.setVisible(true);
		functionlabel.add(AorT);
		
		chooseseason=new JLabel("ѡ������");
		chooseseason.setFont(font2);
		chooseseason.setBounds(45, 220, 100, 30);
		chooseseason.setVisible(true);
		functionlabel.add(chooseseason);
		
		/*sortby=new JComboBox(sorts);
		sortby.setBounds(140, 150, 120, 35);
		sortby.setFont(font1);
		sortby.setBorder(BorderFactory.createEmptyBorder());
		sortby.setSelectedItem(null);
		sortby.setVisible(true);
		functionlabel.add(sortby);*/
		
		AT=new JComboBox(new String[]{"�ܺ�","ƽ��"});
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
		
		
		/*upsort=new CommonButton("graphics/actionbutton/up.png","graphics/actionbutton/up_pressed.png","graphics/actionbutton/up_pressed.png");
		upsort.setBounds(400, 150, 240, 60);
		upsort.addActionListener(new upsortlistener());
		upsort.setVisible(true);
		functionlabel.add(upsort);
		
		downsort=new CommonButton("graphics/actionbutton/down.png","graphics/actionbutton/down_pressed.png","graphics/actionbutton/down_pressed.png");
		downsort.setBounds(400+320, 150, 240, 60);
		downsort.addActionListener(new downsortlistener());
		downsort.setVisible(true);
		functionlabel.add(downsort);*/
		
		upgrade=new CommonButton("graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade_pressed.png");
		upgrade.setBounds(20, 300, 240, 60);
		upgrade.addActionListener(new upgradelistener());
		upgrade.setVisible(true);
		functionlabel.add(upgrade);
		
		mostprogressed=new CommonButton("graphics/actionbutton/most_progressed.png","graphics/actionbutton/most_progressed.png","graphics/actionbutton/most_progressed_pressed.png");
		mostprogressed.setBounds(20, 400, 240, 60);
		mostprogressed.addActionListener(new mostprogressedlistener());
		mostprogressed.setVisible(true);
		functionlabel.add(mostprogressed);
		
		seasonhot=new CommonButton("graphics/actionbutton/season_hot.png","graphics/actionbutton/season_hot.png","graphics/actionbutton/season_hot_pressed.png");
		seasonhot.setBounds(20, 500, 240, 60);
		seasonhot.addActionListener(new seasonhotlistener());
		seasonhot.setVisible(true);
		functionlabel.add(seasonhot);
		
		dailyhot=new CommonButton("graphics/actionbutton/daily_hot.png","graphics/actionbutton/daily_hot.png","graphics/actionbutton/daily_hot_pressed.png");
		dailyhot.setBounds(20, 600, 240, 60);
		dailyhot.addActionListener(new dailyhotlistener());
		dailyhot.setVisible(true);
		functionlabel.add(dailyhot);
		
		screen=new CommonButton("graphics/actionbutton/screen.png","graphics/actionbutton/screen_dark_pressed.png","graphics/actionbutton/screen_dark_pressed.png");
		screen.setBounds(400, 600, 240, 60);
		screen.addActionListener(new screenlistener());
		screen.setVisible(true);
		functionlabel.add(screen);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png","graphics/actionbutton/back.png");
		back.setBounds(400+320, 600, 240, 60);
		back.addActionListener(new backlistener());
		back.setVisible(true);
		functionlabel.add(back);
		
		playerblservice=new PlayerBL();
		playerlist=playerblservice.findAll(season);//Ĭ��չʾ13-14����
		
		addTable(playerlist);

		// TODO Auto-generated constructor stub
	}
	
	public void addTable(ArrayList<playerVO> playerlist){
		//Object[]colomn={"��Ա���ƣ�Ӣ��ȫ�ƣ�","�������","��������","�ȷ�����","������","����������","������","����������","�ڳ�ʱ��","�����ڳ�ʱ��","Ͷ��������","����������","����������","������","����������","������","����������","������","����������","��ñ��","������ñ��","ʧ����","����ʧ����","������","����������","�÷�","�����÷�","Ч��","����Ч��","GmScЧ��ֵ","����GmScЧ��ֵ","��ʵ������","������ʵ������","Ͷ��Ч��","����Ͷ��Ч��","������","����������","����������","��������������","����������","��������������","������","����������","������","����������","��ñ��","������ñ��","ʧ����","����ʧ����","ʹ����","����ʹ����"};
		Object[] colomn={};
		playerdata=new Object[500][29];
		int length=0;
		
		if(isTotal){
			Object[] tempcolomn={"��Ա����","�������","��������","�ȷ�����","������","������","�ڳ�ʱ��","Ͷ��������","����������","����������","������","������","������","��ñ��","ʧ����","������","�÷�","Ч��","GmScЧ��ֵ","��ʵ������","Ͷ��Ч��","������","����������","����������","������","������","��ñ��","ʧ����","ʹ����"};
			colomn=tempcolomn;
			
			for(playerVO playervo:playerlist){
				playerdata[length][0]=playervo.name;
				playerdata[length][1]=playervo.team;
				playerdata[length][2]=playervo.totalMatches;
				playerdata[length][3]=playervo.startingMatches;
				playerdata[length][4]=playervo.totalRebounds;
				playerdata[length][5]=playervo.totalAssists;
				playerdata[length][6]=playervo.timeOnCourt;
				playerdata[length][7]=String.format("%.2f",(double)playervo.shotPercent);
				playerdata[length][8]=String.format("%.2f",(double)playervo.threePointPercent);
				playerdata[length][9]=String.format("%.2f",(double)playervo.freeThrowPercent);
				playerdata[length][10]=String.format("%.2f",(double)playervo.totalOffend);
				playerdata[length][11]=playervo.totalDefend;
				playerdata[length][12]=playervo.totalSteals;
				playerdata[length][13]=playervo.totalRejection;
				playerdata[length][14]=playervo.totalTurnovers;
				playerdata[length][15]=playervo.totalFouls;
				playerdata[length][16]=playervo.totalScores;
				playerdata[length][17]=playervo.efficiency;
				playerdata[length][18]=String.format("%.2f", playervo.gmsc);
				playerdata[length][19]=String.format("%.2f", playervo.realShotPercent);
				playerdata[length][20]=String.format("%.2f", playervo.shotEfficiency);
				playerdata[length][21]=String.format("%.2f", playervo.reboundPercent);
				playerdata[length][22]=String.format("%.2f", playervo.offendReboundPercent);
				playerdata[length][23]=String.format("%.2f", playervo.defendReboundPercent);
				playerdata[length][24]=String.format("%.2f", playervo.assistPercent);
				playerdata[length][25]=String.format("%.2f", playervo.stealPercent);
				playerdata[length][26]=String.format("%.2f", playervo.rejectionPercent);
				playerdata[length][27]=String.format("%.2f", playervo.turnoverPercent);
				playerdata[length][28]=String.format("%.2f", playervo.usePercent);
				
				length++;
			}
		}else{
			Object[] tempcolomn={"��Ա����","�������","��������","�ȷ�����","����������","����������","�����ڳ�ʱ��","Ͷ��������","����������","����������","����������","����������","����������","������ñ��","����ʧ����","����������","�����÷�","����Ч��","����GmScЧ��ֵ","������ʵ������","����Ͷ��Ч��","����������","��������������","��������������","����������","����������","������ñ��","����ʧ����","����ʹ����"};
			colomn=tempcolomn;

			for(playerVO playervo:playerlist){
				playerdata[length][0]=playervo.name;
				playerdata[length][1]=playervo.team;
				playerdata[length][2]=playervo.totalMatches;
				playerdata[length][3]=playervo.startingMatches;
				playerdata[length][4]=String.format("%.2f",((double)playervo.totalRebounds/(double)playervo.totalMatches));
				playerdata[length][5]=String.format("%.2f",((double)playervo.totalAssists/(double)playervo.totalMatches));
				playerdata[length][6]=String.format("%.2f",((double)playervo.timeOnCourt/(double)playervo.totalMatches));
				playerdata[length][7]=String.format("%.2f",(double)playervo.shotPercent);
				playerdata[length][8]=String.format("%.2f",(double)playervo.threePointPercent);
				playerdata[length][9]=String.format("%.2f",(double)playervo.freeThrowPercent);
				playerdata[length][10]=String.format("%.2f",((double)playervo.totalOffend/(double)playervo.totalMatches));
				playerdata[length][11]=String.format("%.2f",((double)playervo.totalDefend/(double)playervo.totalMatches));
				playerdata[length][12]=String.format("%.2f",((double)playervo.totalSteals/(double)playervo.totalMatches));
				playerdata[length][13]=String.format("%.2f",((double)playervo.totalRejection/(double)playervo.totalMatches));
				playerdata[length][14]=String.format("%.2f",((double)playervo.totalTurnovers/(double)playervo.totalMatches));
				playerdata[length][15]=String.format("%.2f",((double)playervo.totalFouls/(double)playervo.totalMatches));
				playerdata[length][16]=String.format("%.2f", (double)playervo.totalScores/(double)playervo.totalMatches);
				playerdata[length][17]=String.format("%.2f", (double)playervo.efficiency/playervo.totalMatches);
				playerdata[length][18]=String.format("%.2f", playervo.gmsc/playervo.totalMatches);
				playerdata[length][19]=String.format("%.2f", playervo.realShotPercent/playervo.totalMatches);
				playerdata[length][20]=String.format("%.2f", playervo.shotEfficiency/playervo.totalMatches);
				playerdata[length][21]=String.format("%.2f", playervo.reboundPercent/playervo.totalMatches);
				playerdata[length][22]=String.format("%.2f", playervo.offendReboundPercent/playervo.totalMatches);
				playerdata[length][23]=String.format("%.2f", playervo.defendReboundPercent/playervo.totalMatches);
				playerdata[length][24]=String.format("%.2f", playervo.assistPercent/playervo.totalMatches);
				playerdata[length][25]=String.format("%.2f", playervo.stealPercent/playervo.totalMatches);
				playerdata[length][26]=String.format("%.2f", playervo.rejectionPercent/playervo.totalMatches);
				playerdata[length][27]=String.format("%.2f", playervo.turnoverPercent/playervo.totalMatches);
				playerdata[length][28]=String.format("%.2f", playervo.usePercent/playervo.totalMatches);
				
				length++;
			}
		}
		
		
		/*for(playerVO playervo:playerlist){
			playerdata[length][0]=playervo.name;
			playerdata[length][1]=playervo.team;
			playerdata[length][2]=playervo.totalMatches;
			playerdata[length][3]=playervo.startingMatches;
			playerdata[length][4]=playervo.totalRebounds;
			playerdata[length][5]=String.format("%.2f",((double)playervo.totalRebounds/(double)playervo.totalMatches));
			playerdata[length][6]=playervo.totalAssists;
			playerdata[length][7]=String.format("%.2f",((double)playervo.totalAssists/(double)playervo.totalMatches));
			playerdata[length][8]=playervo.timeOnCourt;
			playerdata[length][9]=String.format("%.2f",((double)playervo.timeOnCourt/(double)playervo.totalMatches));
			playerdata[length][10]=String.format("%.2f",(double)playervo.shotPercent);
			playerdata[length][11]=String.format("%.2f",(double)playervo.threePointPercent);
			playerdata[length][12]=String.format("%.2f",(double)playervo.freeThrowPercent);
			playerdata[length][13]=String.format("%.2f",(double)playervo.totalOffend);
			playerdata[length][14]=String.format("%.2f",((double)playervo.totalOffend/(double)playervo.totalMatches));
			playerdata[length][15]=playervo.totalDefend;
			playerdata[length][16]=String.format("%.2f",((double)playervo.totalDefend/(double)playervo.totalMatches));
			playerdata[length][17]=playervo.totalSteals;
			playerdata[length][18]=String.format("%.2f",((double)playervo.totalSteals/(double)playervo.totalMatches));
			playerdata[length][19]=playervo.totalRejection;
			playerdata[length][20]=String.format("%.2f",((double)playervo.totalRejection/(double)playervo.totalMatches));
			playerdata[length][21]=playervo.totalTurnovers;
			playerdata[length][22]=String.format("%.2f",((double)playervo.totalTurnovers/(double)playervo.totalMatches));
			playerdata[length][23]=playervo.totalFouls;
			playerdata[length][24]=String.format("%.2f",((double)playervo.totalFouls/(double)playervo.totalMatches));
			playerdata[length][25]=playervo.totalScores;
			playerdata[length][26]=String.format("%.2f", (double)playervo.totalScores/(double)playervo.totalMatches);
			playerdata[length][27]=playervo.efficiency;
			playerdata[length][28]=String.format("%.2f", (double)playervo.efficiency/playervo.totalMatches);
			playerdata[length][29]=String.format("%.2f", playervo.gmsc);
			playerdata[length][30]=String.format("%.2f", playervo.gmsc/playervo.totalMatches);
			playerdata[length][31]=String.format("%.2f", playervo.realShotPercent);
			playerdata[length][32]=String.format("%.2f", playervo.realShotPercent/playervo.totalMatches);
			playerdata[length][33]=String.format("%.2f", playervo.shotEfficiency);
			playerdata[length][34]=String.format("%.2f", playervo.shotEfficiency/playervo.totalMatches);
			playerdata[length][35]=String.format("%.2f", playervo.reboundPercent);
			playerdata[length][36]=String.format("%.2f", playervo.reboundPercent/playervo.totalMatches);
			playerdata[length][37]=String.format("%.2f", playervo.offendReboundPercent);
			playerdata[length][38]=String.format("%.2f", playervo.offendReboundPercent/playervo.totalMatches);
			playerdata[length][39]=String.format("%.2f", playervo.defendReboundPercent);
			playerdata[length][40]=String.format("%.2f", playervo.defendReboundPercent/playervo.totalMatches);
			playerdata[length][41]=String.format("%.2f", playervo.assistPercent);
			playerdata[length][42]=String.format("%.2f", playervo.assistPercent/playervo.totalMatches);
			playerdata[length][43]=String.format("%.2f", playervo.stealPercent);
			playerdata[length][44]=String.format("%.2f", playervo.stealPercent/playervo.totalMatches);
			playerdata[length][45]=String.format("%.2f", playervo.rejectionPercent);
			playerdata[length][46]=String.format("%.2f", playervo.rejectionPercent/playervo.totalMatches);
			playerdata[length][47]=String.format("%.2f", playervo.turnoverPercent);
			playerdata[length][48]=String.format("%.2f", playervo.turnoverPercent/playervo.totalMatches);
			playerdata[length][49]=String.format("%.2f", playervo.usePercent);
			playerdata[length][50]=String.format("%.2f", playervo.usePercent/playervo.totalMatches);
			
			length++;
		}*/
		
		table=new CommonTable(playerdata,colomn);
		table.setPreferredScrollableViewportSize(new Dimension(500,300));
		table.setRowHeight(20);
		table.setFont(new Font("΢���ź�",Font.BOLD,15));
		
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
		
		scroll = new JScrollPane(table);
		scroll.setLocation(400,250);
		scroll.setSize(500, 301);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVisible(true);
		functionlabel.add(scroll);
	}
	
	class seasonslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(seasons.getSelectedItem()!=null)
				season=seasons.getSelectedItem().toString();
			else
				season="13-14";
			
			functionlabel.remove(table);
			functionlabel.remove(scroll);
			
			playerlist.clear();
			playerlist=playerblservice.findAll(season);

			addTable(playerlist);
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class upgradelistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			functionlabel.remove(table);
			functionlabel.remove(scroll);
			
			playerblservice.deleteTemp(season);
			playerlist=playerblservice.findAll(season);
			addTable(playerlist);
			
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class seasonhotlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Constant.mainframe.showSeasonHotPlayerPanel(playerlist);
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class mostprogressedlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Constant.mainframe.showMostProgressedPanel(playerlist);
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class dailyhotlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//String date="01-02";//����Ϊ1��2��
			String date;
			if(seasons.getSelectedItem()!=null)
				season=seasons.getSelectedItem().toString();
			
			
			File matchfile=new File("E:/JavaWorkbench/NBAData/matches");
			String[] matchlist=matchfile.list();
			ArrayList<String> seasonmatchlist=new ArrayList<String>();
			ArrayList<String> realseasonmatch=new ArrayList<String>();

			for(String singlematch:matchlist){
				if(singlematch.split("_")[0].contains(season))
					seasonmatchlist.add(singlematch);
			}
			
			//�Ա�����Ϣ������������
			int startposition=0;
			
			for(int i=0;i<seasonmatchlist.size()-1;i++){
				int month1=Integer.parseInt(seasonmatchlist.get(i).split("_")[1].split("-")[0]);
				int month2=Integer.parseInt(seasonmatchlist.get(i+1).split("_")[1].split("-")[0]);
				if((month2-month1)>2){
					startposition=i+1;
					break;
				}
			}
						
			for(int i=startposition;i<seasonmatchlist.size();i++){
				realseasonmatch.add(seasonmatchlist.get(i));
			}
			
			for(int i=0;i<startposition;i++){
				realseasonmatch.add(seasonmatchlist.get(i));
			}
			
			date=realseasonmatch.get(realseasonmatch.size()-1).split("_")[1];
			
			System.out.println(date);
			System.out.println(season);
			Constant.mainframe.showDailyHotPlayerPanel(season,date);
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
			
			addTable(playerlist);
			
			Constant.mainframe.repaint();
		}
		
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.PLAYER_TOTALSCORES;//Ĭ�����ܷ�����
			
			addTable(playerblservice.sortBy(sort,isUP,playerlist));
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*class upsortlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.PLAYER_TOTALSCORES;//Ĭ�����ܷ�����
			
			addTable(playerblservice.sortBy(sort,true));
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
	}
	
	class downsortlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(scroll);
			functionlabel.remove(table);
			SortBy sort=SortBy.PLAYER_TOTALSCORES;//Ĭ�����ܷ�����
			
			addTable(playerblservice.sortBy(sort,false));
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
	}*/
	
	class screenlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showPlayerScreenPanel();
			// TODO Auto-generated method stub
			
		}
	}
	
	class backlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showMainPanel();
			// TODO Auto-generated method stub
			
		}
	}

}
