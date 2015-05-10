package pres.teamui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import bl.teambl.TeamBL;
import blservice.teamblservice.TeamBLService;
import constantinfo.Constant;
import constantinfo.SortBy;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.teamVO;

public class SeasonHotTeamPanel extends CommonPanel {
	JComboBox ScreenBy;
	JLabel teaminfo;
	JScrollPane scroll;
	CommonButton back;
	CommonButton screen;
	CommonTable result;
	JLabel options;
	
	ArrayList<teamVO> firstfive;
	ArrayList<teamVO> teamvolist;
	TeamBLService teamblservice;
	//可以考虑传入teamvolist
	public SeasonHotTeamPanel(ArrayList<teamVO> teamvolist) {
		super("graphics/detailpanel/detail_background.png");
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		this.teamvolist=teamvolist;
		firstfive=new ArrayList<teamVO>();
		teamblservice=new TeamBL();
		
		Font font2=new Font("微软雅黑",Font.BOLD,15);
		
		teaminfo=new JLabel(new ImageIcon("graphics/detailpanel/teaminfo_label.png"));
		teaminfo.setBounds(500, 20, 180, 45);
		teaminfo.setVisible(true);
		functionlabel.add(teaminfo);
		
		options=new JLabel("筛选条件:");
		options.setFont(font2);
		options.setBounds(330, 150, 100, 30);
		options.setVisible(true);
		functionlabel.add(options);
		
		String[] screenby={"场均得分","场均篮板","场均助攻","场均盖帽","场均抢断","三分命中率","投篮命中率","罚球命中率"};
		ScreenBy=new JComboBox(screenby);
		ScreenBy.setBounds(452, 150, 120, 35);
		ScreenBy.setFont(font1);
		ScreenBy.setBorder(BorderFactory.createEmptyBorder());
		ScreenBy.setSelectedItem(null);
		ScreenBy.setVisible(true);
		functionlabel.add(ScreenBy);
		
		
		addTable(0);//默认以场均得分排序
				
		screen=new CommonButton("graphics/actionbutton/screen.png","graphics/actionbutton/screen_dark_pressed.png","graphics/actionbutton/screen_dark_pressed.png");
		screen.setBounds(600, 150, 240, 60);
		screen.addActionListener(new screenlistener());
		screen.setVisible(true);
		functionlabel.add(screen);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(392, 600, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		// TODO Auto-generated constructor stub
	}
	
	public void addTable(int index){
		SortBy sortby=SortBy.TEAM_AVERAGESCORES;
		switch(index){
		case 0:{sortby=SortBy.TEAM_AVERAGESCORES;break;}
		case 1:{sortby=SortBy.TEAM_AVERAGEREBOUNDS;break;}
		case 2:{sortby=SortBy.TEAM_AVERAGEASSISTS;break;}
		case 3:{sortby=SortBy.TEAM_AVERAGEREJECTION;break;}
		case 4:{sortby=SortBy.TEAM_AVERAGESTEALS;break;}
		case 5:{sortby=SortBy.TEAM_THREEPOINTPERCENT;break;}
		case 6:{sortby=SortBy.TEAM_SHOTPERCENT;break;}
		case 7:{sortby=SortBy.TEAM_FREETHROWPERCENT;break;}
		default:{}
		}
		
		System.out.println("tosort");
		System.out.println(teamvolist.size());
		teamvolist=teamblservice.sortBy(sortby, false,teamvolist);
		
		if(firstfive.size()==0){
			for(int i=0;i<5;i++){
				firstfive.add(teamvolist.get(i));
			}
		}
		
		//firstfive=(ArrayList<teamVO>) teamblservice.sortBy(sortby, false,teamvolist).subList(0, 5);
		Object[] resultcolomn={"球队名称","球队全称","球队所属联盟","场均得分","场均篮板","场均助攻","场均盖帽","场均抢断","三分命中率","投篮命中率","罚球命中率"};
		Object[][] resultdetail=new Object[5][11];
		
		int length=0;
		for(teamVO singleteam:firstfive){
			resultdetail[length][0]=singleteam.shortname;
			resultdetail[length][1]=singleteam.fullname;
			resultdetail[length][2]=singleteam.league;
			resultdetail[length][3]=String.format("%.2f", (double)singleteam.totalScores/(double)singleteam.totalMatches);
			resultdetail[length][4]=String.format("%.2f", (double)singleteam.totalRebounds/(double)singleteam.totalMatches);
			resultdetail[length][5]=String.format("%.2f", (double)singleteam.totalAssists/(double)singleteam.totalMatches);
			resultdetail[length][6]=String.format("%.2f", (double)singleteam.totalRejection/(double)singleteam.totalMatches);
			resultdetail[length][7]=String.format("%.2f", (double)singleteam.totalSteals/(double)singleteam.totalMatches);
			resultdetail[length][8]=String.format("%.2f", singleteam.shotPercent);
			resultdetail[length][9]=String.format("%.2f", singleteam.threePointPercent);
			resultdetail[length][10]=String.format("%.2f", singleteam.freeThrowPercent);

			length++;
		}
		result=new CommonTable(resultdetail,resultcolomn);
		result.setPreferredScrollableViewportSize(new Dimension(550,180));
		result.setRowHeight(30);
		result.setFont(new Font("微软雅黑",Font.BOLD,15));
		//FitTableColumns(table);
		
		/*for(int i=0;i<allmatches.getColumnCount();i++){
			TableColumn tc=allmatches.getColumn(allmatches.getColumnName(i));  
	        tc.setMinWidth(150);

		}*/
		result.addMouseListener(new tableadapter());
		result.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//result.getTableHeader().setPreferredSize(new Dimension (result.getTableHeader().getMinimumSize().width,30));
		result.updateUI();

		scroll = new JScrollPane();
		scroll.setViewportView(result);
		scroll.setLocation(312,350);
		scroll.setSize(550,180);
		scroll.setVisible(true);
		functionlabel.add(scroll);
		
	}
	
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2){
				int index=result.getSelectedRow();
				//System.out.println(firstfive.get(index).city);
				Constant.mainframe.showSingleTeamPanel(firstfive.get(index));
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
	
	class screenlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(result);
			functionlabel.remove(scroll);
			
			addTable(ScreenBy.getSelectedIndex());
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class backlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showTeamPanel();
			// TODO Auto-generated method stub
			
		}
		
	}

}
