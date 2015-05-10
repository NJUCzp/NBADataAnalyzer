package pres.matchui;

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

import constantinfo.Constant;
import bl.matchbl.MatchBL;
import blservice.matchblservice.MatchBLService;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;

public class MatchMainPanel extends CommonPanel{
	CommonButton back;
	CommonButton upgrade;
	//CommonButton help;
	CommonTable allmatches;
	JScrollPane scroll;
	JLabel matchinfo;
	JComboBox seasons;
	String season="13-14";
	
	ArrayList<String> basicmatchinfo;
	ArrayList<String> currentmatchinfo;//供显示在表格中的比赛信息
	
	MatchBLService matchblservice;
	
	public MatchMainPanel() {
		super("graphics/detailpanel/detail_background.png");
		matchblservice=new MatchBL();
		Font font1=new Font("微软雅黑",Font.BOLD,12);

		// TODO Auto-generated constructor stub
		matchinfo=new JLabel(new ImageIcon("graphics/detailpanel/matchinfo_label.png"));
		matchinfo.setBounds(500, 20, 180, 45);
		matchinfo.setVisible(true);
		functionlabel.add(matchinfo);
		
		upgrade=new CommonButton("graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade.png","graphics/actionbutton/upgrade_pressed.png");
		upgrade.setBounds(20, 300, 240, 60);
		upgrade.addActionListener(new upgradelistener());
		upgrade.setVisible(true);
		functionlabel.add(upgrade);
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(140, 150, 120, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(400+320, 600, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		
		
		addTable(season);//默认显示13-14赛季
	}
	
	public void addTable(String season){
		Object[] colomn={"赛季","比赛日期","比赛队伍"};
		Object[][] basicmatchinfo=new Object[1500][3];
		
		ArrayList<String> allmatch=matchblservice.findMatchBasicInfo(season);
		
		int length=0;
		for(String matches:allmatch){
			basicmatchinfo[length][0]=season;
			basicmatchinfo[length][1]=matches.split("_")[1];
			basicmatchinfo[length][2]=matches.split("_")[2];
			length++;
		}
		
		allmatches=new CommonTable(basicmatchinfo,colomn);
		allmatches.setPreferredScrollableViewportSize(new Dimension(450,300));
		allmatches.setRowHeight(20);
		allmatches.setFont(new Font("微软雅黑",Font.BOLD,15));
		//FitTableColumns(table);
		
		for(int i=0;i<allmatches.getColumnCount();i++){
			TableColumn tc=allmatches.getColumn(allmatches.getColumnName(i));  
	        tc.setMinWidth(150);

		}
		allmatches.addMouseListener(new tableadapter());
		//allmatches.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//allmatches.getTableHeader().setPreferredSize(new Dimension (allmatches.getTableHeader().getMinimumSize().width,30));
		allmatches.updateUI();

		scroll = new JScrollPane();
		scroll.setViewportView(allmatches);
		scroll.setLocation(312,250);
		scroll.setSize(450, 300);
		scroll.setVisible(true);
		functionlabel.add(scroll);
	}
	
	class upgradelistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			functionlabel.remove(allmatches);
			functionlabel.remove(scroll);
			
			addTable(season);
			
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class seasonslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			functionlabel.remove(allmatches);
			functionlabel.remove(scroll);
			
			season=seasons.getSelectedItem().toString();
			
			addTable(season);
			
			Constant.mainframe.repaint();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			//双击显示详细比赛信息
			if(e.getClickCount()==2){
				int index=allmatches.getSelectedRow();
				String s=allmatches.getValueAt(index, 0).toString()+"_"+allmatches.getValueAt(index, 1).toString()+"_"+allmatches.getValueAt(index, 2).toString();
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
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showMainPanel();
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
