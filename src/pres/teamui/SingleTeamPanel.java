package pres.teamui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.swing.JSVGCanvas;

import constantinfo.Constant;
import bl.teambl.TeamBL;
import blservice.teamblservice.TeamBLService;
import po.teamInSingleMatchPO;
import pres.teamui.SeasonHotTeamPanel.tableadapter;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.teamVO;

public class SingleTeamPanel extends CommonPanel{
	String shortname;
	//JLabel portraits;
	//JComboBox choices;
	//CommonTable details;
	CommonTable recent;
	CommonButton back;
	JScrollPane scroll;
	JSVGCanvas canvas;
	
	JLabel fullname;
	JLabel league;
	JLabel city;
	//JLabel stadium;
	
	TeamBLService teamblservice;
	teamVO teamvo;
	
	//可以考虑传入teamvo
	public SingleTeamPanel(teamVO teamvo) {
		super("graphics/detailpanel/detail_background.png");
		teamblservice=new TeamBL();
		
		//Font font1=new Font("微软雅黑",Font.BOLD,12);
		Font font2=new Font("微软雅黑",Font.BOLD,25);
		
		fullname=new JLabel("球队全称:"+teamvo.fullname);
		fullname.setFont(font2);
		fullname.setBounds(512, 120,500,30);
		fullname.setVisible(true);
		functionlabel.add(fullname);
		
		league=new JLabel("所在联盟:"+teamvo.league);
		league.setFont(font2);
		league.setBounds(512, 160,500,30);
		league.setVisible(true);
		functionlabel.add(league);
		
		city=new JLabel("所在城市:"+teamvo.city);
		city.setFont(font2);
		city.setBounds(512, 200,500,30);
		city.setVisible(true);
		functionlabel.add(city);
		
		canvas=new JSVGCanvas();
		canvas.setURI("file:/E:/JavaWorkbench/NBAData/teams/"+teamvo.shortname+".svg");
		canvas.setBounds(100, 100, 200, 200);
		canvas.setVisible(true);
		functionlabel.add(canvas);
		
		
		
		Object[][] recentdetail=new Object[5][12];
		Object[] recentcolomn=new String[]{"赛季","日期","比赛双方","是否胜利","得分","总篮板","总助攻","总盖帽","总抢断","投篮命中率","罚球命中率","三分命中率"};
		int length=0;
		for(teamInSingleMatchPO teaminsinglematch:teamvo.recentFive){
			recentdetail[length][0]=teaminsinglematch.getSeason();
			recentdetail[length][1]=teaminsinglematch.getDate();
			recentdetail[length][2]=teaminsinglematch.getNames();
			recentdetail[length][3]=teaminsinglematch.getIsWin();
			recentdetail[length][4]=teaminsinglematch.getTotalScore();
			recentdetail[length][5]=teaminsinglematch.getTotalRebounds();
			recentdetail[length][6]=teaminsinglematch.getTotalAssists();
			recentdetail[length][7]=teaminsinglematch.getTotalRejections();
			recentdetail[length][8]=teaminsinglematch.getTotalSteals();
			recentdetail[length][9]=teaminsinglematch.getShotPercent();
			recentdetail[length][10]=teaminsinglematch.getFreeThrowPercent();
			recentdetail[length][11]=teaminsinglematch.getThreePointPercent();
			length++;
		}
		recent=new CommonTable(recentdetail,recentcolomn);
		recent.setPreferredScrollableViewportSize(new Dimension(500,180));
		recent.setRowHeight(30);
		recent.setFont(new Font("微软雅黑",Font.BOLD,15));
		
		/*for(int i=0;i<table.getColumnCount();i++){
			TableColumn tc=table.getColumn(table.getColumnName(i));  
	        if(i==0) 
		        tc.setMinWidth(150);
	        else
	        	tc.setMinWidth(80);

		}*/
		
		recent.addMouseListener(new tableadapter());
		recent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		recent.updateUI();
		
		scroll = new JScrollPane(recent);
		scroll.setLocation(262,350);
		scroll.setSize(500,180);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVisible(true);
		functionlabel.add(scroll);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(392, 650, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		// TODO Auto-generated constructor stub
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2){
				int index=recent.getSelectedRow();
				String s=recent.getValueAt(index, 0)+"_"+recent.getValueAt(index, 1)+"_"+recent.getValueAt(index, 2);
				Constant.mainframe.showSingleMatchPanel(s);
			}
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
	class backlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Constant.mainframe.showTeamPanel();
			// TODO Auto-generated method stub
			
		}
		
	}

}
