package pres.playerui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import constantinfo.Constant;
import bl.playerbl.PlayerBL;
import bl.teambl.TeamBL;
import blservice.playerblservice.PlayerBLService;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.playerVO;
import vo.teamVO;

public class MostProgressedPanel extends CommonPanel{
	JComboBox ScreenBy;
	JLabel playerinfo;
	JScrollPane scroll;
	CommonButton back;
	CommonButton screen;
	CommonTable result;
	
	ArrayList<playerVO> firstfive;
	ArrayList<playerVO> playervolist;
	PlayerBLService playerblservice;

	public MostProgressedPanel(ArrayList<playerVO> playervolist) {
		super("graphics/detailpanel/detail_background.png");
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		this.playervolist=playervolist;
		firstfive=new ArrayList<playerVO>();
		playerblservice=new PlayerBL();
		
		String[] screenby={"场均得分","场均篮板","场均助攻"};
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
