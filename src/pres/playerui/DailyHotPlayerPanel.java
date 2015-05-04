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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import constantinfo.Constant;
import constantinfo.SortBy;
import bl.playerbl.PlayerBL;
import blservice.playerblservice.PlayerBLService;
import po.playerInSingleMatchPO;
import pres.playerui.SeasonHotPlayerPanel.tableadapter;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.playerVO;

public class DailyHotPlayerPanel extends CommonPanel{
	JComboBox ScreenBy;
	//JComboBox date;
	JScrollPane scroll;
	CommonButton back;
	CommonButton screen;
	CommonTable result;
	
	String date="01-02";//先设成1月2日
	
	ArrayList<playerVO> playervolist;
	//ArrayList<playerVO> firstfive;
	PlayerBLService playerblservice;

	public DailyHotPlayerPanel(String date) {
		super("graphics/detailpanel/detail_background.png");
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		//this.date=date;
		//this.playervolist=playervolist;
		//firstfive=new ArrayList<playerVO>();
		playerblservice=new PlayerBL();
		
		ArrayList<String> days=new ArrayList<String>();
		File file=new File("E:/JavaWorkbench/NBAData/matches");
		String[] matchinfo=file.list();
		
		/*for(String singlematch:matchinfo){
			String date=singlematch.split(";")[1];
			if(days.indexOf(date)==-1)
				days.add(date);
		}*/
		
		/*String[] alldays=(String[]) days.toArray(new String[0]);
		date=new JComboBox(alldays);
		date.setBounds(452, 150, 120, 35);
		date.setFont(font1);
		date.setBorder(BorderFactory.createEmptyBorder());
		date.setSelectedItem(null);
		date.setVisible(true);
		functionlabel.add(date);*/
		
		String[] screenby={"得分","篮板","助攻","盖帽","抢断"};
		ScreenBy=new JComboBox(screenby);
		ScreenBy.setBounds(452, 150, 120, 35);
		ScreenBy.setFont(font1);
		ScreenBy.setBorder(BorderFactory.createEmptyBorder());
		ScreenBy.setSelectedItem(null);
		ScreenBy.setVisible(true);
		functionlabel.add(ScreenBy);
		
		addTable(0);//默认以得分排序
		
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
		SortBy sortby=SortBy.PLAYER_DAILY_SCORE;
		
		switch (index){
		case 0:{
			sortby=SortBy.PLAYER_DAILY_SCORE;
			break;
		}
		case 1:{
			sortby=SortBy.PLAYER_DAILY_REBOUND;
			break;
		}
		case 2:{
			sortby=SortBy.PLAYER_DAILY_ASSIST;
			break;
		}
		case 3:{
			sortby=SortBy.PLAYER_DAILY_REJECTION;
			break;
		}
		case 4:{
			sortby=SortBy.PLAYER_DAILY_STEAL;
			break;
		}
		default:{}
		}
		ArrayList<playerInSingleMatchPO> dailyPlayer=playerblservice.findByDate(date);
		//排序
		Object[] dailycolomn=new String[]{"球员姓名","赛季","比赛日期","比赛双方","上场时间","得分","助攻","篮板","盖帽","抢断","投篮命中率","三分命中率","罚球命中率"};
		Object[][] dailydetail=new Object[5][13];
		
		int length=0;
		for(playerInSingleMatchPO singleplayer:dailyPlayer){
			if(length>4)
				break;
			dailydetail[length][0]=singleplayer.getPlayer();
			dailydetail[length][1]=singleplayer.getSeason();
			dailydetail[length][2]=singleplayer.getDate();
			dailydetail[length][3]=singleplayer.getName();
			dailydetail[length][4]=singleplayer.getDetailTimeOnCourt();
			dailydetail[length][5]=singleplayer.getScore();
			dailydetail[length][6]=singleplayer.getAssists();
			dailydetail[length][7]=singleplayer.getRebounds();
			dailydetail[length][8]=singleplayer.getRejections();
			dailydetail[length][9]=singleplayer.getSteals();
			dailydetail[length][10]=singleplayer.getShotPercent();
			dailydetail[length][11]=singleplayer.getThreePointPercent();
			dailydetail[length][12]=singleplayer.getFreeThrowPercent();

			length++;
		}
		
		result=new CommonTable(dailydetail,dailycolomn);
		result.setPreferredScrollableViewportSize(new Dimension(550,200));
		result.setRowHeight(30);
		result.setFont(new Font("微软雅黑",Font.BOLD,15));
		//FitTableColumns(table);
		
		/*for(int i=0;i<allmatches.getColumnCount();i++){
			TableColumn tc=allmatches.getColumn(allmatches.getColumnName(i));  
	        tc.setMinWidth(150);

		}*/
		result.addMouseListener(new tableadapter());
		result.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		result.getTableHeader().setPreferredSize(new Dimension (result.getTableHeader().getMinimumSize().width,30));
		result.updateUI();

		scroll = new JScrollPane();
		scroll.setViewportView(result);
		scroll.setLocation(312,350);
		scroll.setSize(550, 200);
		scroll.setVisible(true);
		functionlabel.add(scroll);
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2){
				int index=result.getSelectedRow();
				String nametofind=result.getValueAt(index, 0).toString();
				ArrayList<playerVO> playervolist=playerblservice.findAll();
				for(playerVO player:playervolist){
					if(player.name.equals(nametofind))
						Constant.mainframe.showSinglePlayerPanel(player);
				}
				
			}
			// TODO Auto-generated method stub
			
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
