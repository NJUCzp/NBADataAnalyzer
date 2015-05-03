package pres.playerui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.playerVO;

public class SinglePlayerPanel extends CommonPanel{
	//String name;
	JLabel portraits;
	JComboBox convert;
	JComboBox seasons;
	JScrollPane recentscroll;
	CommonTable basicinfo;
	CommonTable matchinfo;
	CommonTable recent;
	CommonButton back;

	public SinglePlayerPanel(playerVO playervo) {
		super("graphics/detailpanel/detail_background.png");
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		
		portraits=new JLabel(new ImageIcon("E:/JavaWorkbench/NBAData/players/portrait"+playervo.name+".png"));
		portraits.setBounds(150, 150, 200, 200);
		portraits.addMouseListener(new portraitadapter());
		portraits.setVisible(true);
		
		convert=new JComboBox(new String[]{"基本信息","比赛信息"});
		convert.setBounds(140, 150, 120, 35);
		convert.setFont(font1);
		convert.setBorder(BorderFactory.createEmptyBorder());
		convert.setSelectedItem(null);
		convert.addActionListener(new convertlistener());
		convert.setVisible(true);
		functionlabel.add(convert);
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(140, 150, 120, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		
		addTable();//添加基本信息/比赛信息
		
		Object[][] recentdetail=new Object[5][15];
		Object[] recentcolomn=new String[]{"赛季","日期","比赛双方","上场时间","得分","总篮板","总助攻","总盖帽","总抢断","投篮出手/命中","投篮命中率","罚球出手/命中","罚球命中率","三分出手/命中","三分命中率"};
		//添加数据
		recent=new CommonTable(recentdetail,recentcolomn);
		recent.setPreferredScrollableViewportSize(new Dimension(500,250));
		recent.setRowHeight(20);
		recent.setFont(new Font("微软雅黑",Font.BOLD,15));
		recent.addMouseListener(new tableadapter());
		recent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		recent.updateUI();
		
		recentscroll = new JScrollPane(recent);
		recentscroll.setLocation(450,250);
		recentscroll.setSize(500, 250);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		recentscroll.setVisible(true);
		functionlabel.add(recentscroll);
		// TODO Auto-generated constructor stub
	}
	
	public void addTable(){
		
	}
	
	class convertlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class seasonslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class tableadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
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
	
	class portraitadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
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

}
