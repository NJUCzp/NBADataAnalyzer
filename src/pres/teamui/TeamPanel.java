package pres.teamui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import org.apache.batik.swing.JSVGCanvas;

import bl.teambl.TeamBL;
import blservice.teamblservice.TeamBLService;
import constantinfo.Constant;
import pres.teamui.SeasonHotTeamPanel.backlistener;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import vo.teamVO;

public class TeamPanel extends CommonPanel{
	CommonButton back;
	CommonButton total;
	CommonButton seasonhot;
	JComboBox seasons;
	ArrayList<JSVGCanvas> portraits;
	//ArrayList<CommonButton> buttons;
	
	ArrayList<teamVO> teamvolist;
	TeamBLService teamblservice;
	
	public TeamPanel() {
		super("graphics/detailpanel/detail_background.png");
		teamblservice=new TeamBL();
		portraits=new ArrayList<JSVGCanvas>();
		Font font1=new Font("Î¢ÈíÑÅºÚ",Font.BOLD,12);

		addPortraits("13-14");//Ä¬ÈÏÏÔÊ¾13-14Èü¼¾Çò¶Ó
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(40, 150, 100, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		
		seasonhot=new CommonButton("graphics/actionbutton/season_hot.png","graphics/actionbutton/season_hot.png","graphics/actionbutton/season_hot_pressed.png");
		seasonhot.setBounds(20, 680, 240, 60);
		seasonhot.setVisible(true);
		seasonhot.addActionListener(new seasonhotlistener());
		functionlabel.add(seasonhot);
		
		total=new CommonButton("graphics/actionbutton/viewall.png","graphics/actionbutton/viewall.png","graphics/actionbutton/viewall_pressed.png");
		total.setBounds(392, 680, 240, 60);
		total.setVisible(true);
		total.addActionListener(new totallistener());
		functionlabel.add(total);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(744, 680, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		// TODO Auto-generated constructor stub
	}
	
	public void addPortraits(String season){
		File file=new File("E:/JavaWorkbench/NBAData/teams");
		String[]filelist=file.list();
		
		int beginyear=Integer.parseInt(season.split("-")[0]);
		int length=0;
		for(String singlefile:filelist){
			if(singlefile.contains(".svg")){
				if(beginyear<13&&singlefile.contains("NOP"))
					continue;
				else{
					JSVGCanvas tempsvg=new JSVGCanvas();
					tempsvg.setURI("file:/E:/Javaworkbench/NBAData/teams/"+singlefile);
					//System.out.println(tempsvg.getURI());
					portraits.add(tempsvg);
				}
					
			}
		}
		
		length=0;
		for(JSVGCanvas svg:portraits){
			svg.setSize(100, 100);
			svg.setLocation(162+120*(length%6), 100+110*(length/6));
			svg.setVisible(true);
			svg.addMouseListener(new svgadapter(svg.getURI()));
			functionlabel.add(svg);
			length++;
		}
		
		//¿¼ÂÇ»Æ·ä
		if(beginyear<13){
			CommonButton NOH=new CommonButton("","","");
			NOH.setSize(100, 100);
			NOH.setLocation(862, 640);
			NOH.addActionListener(new NOHlistener());
			NOH.setVisible(true);
			functionlabel.add(NOH);
		}

	}
	
	class NOHlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(teamvolist==null)
				teamvolist=teamblservice.findAll();
			
			for(teamVO teamvo:teamvolist){
				if(teamvo.shortname.equals("NOH"))
					Constant.mainframe.showSingleTeamPanel(teamvo);
			}
			// TODO Auto-generated method stub
			
		}
		
	}

	class svgadapter implements MouseListener{
		String teamname;
		
		private svgadapter(String url){
			teamname=url.substring(37,40);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println(teamname);
			
			if(teamvolist==null)
				teamvolist=teamblservice.findAll();
			
			System.out.println(teamvolist.size());
			
			for(teamVO teamvo:teamvolist){
				if(teamvo.shortname.equals(teamname))
					Constant.mainframe.showSingleTeamPanel(teamvo);
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
	class seasonslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class seasonhotlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(teamvolist==null)
				teamvolist=teamblservice.findAll();
			
			Constant.mainframe.showSeasonHotTeamPanel(teamvolist);
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class totallistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Constant.mainframe.showTeamMainPanel();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class backlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Constant.mainframe.showMainPanel();
			// TODO Auto-generated method stub
			
		}
		
	}

}
