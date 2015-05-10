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
import javax.swing.table.TableColumn;

import constantinfo.Constant;
import po.playerInSingleMatchPO;
import pres.playerui.SeasonHotPlayerPanel.backlistener;
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
	JScrollPane scroll;
	CommonTable basicinfo;
	CommonTable recent;
	CommonButton back;
	JLabel playerinfo;
	
	playerVO playervo;

	public SinglePlayerPanel(playerVO playervo) {		
		super("graphics/detailpanel/detail_background2.jpeg");
		Font font1=new Font("΢���ź�",Font.BOLD,12);
		this.playervo=playervo;
		
		//System.out.print(playervo.recentFive.get(4).getDetailTimeOnCourt());

		
		playerinfo=new JLabel(new ImageIcon("graphics/detailpanel/playerinfo_label.png"));
		playerinfo.setBounds(500, 20, 180, 45);
		playerinfo.setVisible(true);
		functionlabel.add(playerinfo);
		
		portraits=new JLabel(new ImageIcon("E:/JavaWorkbench/NBAData/players/portrait/"+playervo.name+".png"));
		portraits.setBounds(300, 60, 200, 300);
		portraits.addMouseListener(new portraitadapter());
		portraits.setVisible(true);
		functionlabel.add(portraits);
		
		convert=new JComboBox(new String[]{"������Ϣ","������Ϣ"});
		convert.setBounds(600, 120, 120, 35);
		convert.setFont(font1);
		convert.setBorder(BorderFactory.createEmptyBorder());
		convert.setSelectedItem(null);
		convert.addActionListener(new convertlistener());
		convert.setVisible(true);
		functionlabel.add(convert);
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(750, 120, 120, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		
		addTable(0);//��ӻ�����Ϣ/������Ϣ,Ĭ�ϻ�����Ϣ
		
		Object[][] recentdetail=new Object[5][15];
		Object[] recentcolomn=new String[]{"����","����","����˫��","�ϳ�ʱ��","�÷�","������","������","�ܸ�ñ","������","Ͷ������/����","Ͷ��������","��������/����","����������","��������/����","����������"};
		//�������
		int length=0;
		for(playerInSingleMatchPO singleplayer:playervo.recentFive){
			recentdetail[length][0]=singleplayer.getSeason();
			recentdetail[length][1]=singleplayer.getDate();
			recentdetail[length][2]=singleplayer.getName();
			recentdetail[length][3]=singleplayer.getDetailTimeOnCourt();
			recentdetail[length][4]=singleplayer.getScore();
			recentdetail[length][5]=singleplayer.getRebounds();
			recentdetail[length][6]=singleplayer.getAssists();
			recentdetail[length][7]=singleplayer.getRejections();
			recentdetail[length][8]=singleplayer.getSteals();
			recentdetail[length][9]=singleplayer.getShotsOnTargets()+"/"+singleplayer.getTotalShots();
			recentdetail[length][10]=singleplayer.getShotPercent();
			recentdetail[length][11]=singleplayer.getThreePointShotsOnTargets()+"/"+singleplayer.getTotalThreePointShots();
			recentdetail[length][12]=singleplayer.getThreePointPercent();
			recentdetail[length][13]=singleplayer.getFreeThrowOnTargets()+"/"+singleplayer.getTotalFreeThrows();
			recentdetail[length][14]=singleplayer.getFreeThrowPercent();
			length++;
		}
		recent=new CommonTable(recentdetail,recentcolomn);
		recent.setPreferredScrollableViewportSize(new Dimension(290,400));
		recent.setRowHeight(30);
		recent.setFont(new Font("΢���ź�",Font.BOLD,15));
		recent.addMouseListener(new tableadapter());
		recent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		recent.updateUI();
		
		recentscroll = new JScrollPane(recent);
		recentscroll.setLocation(290,400);
		recentscroll.setSize(500, 160);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		recentscroll.setVisible(true);
		functionlabel.add(recentscroll);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(392, 600, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		// TODO Auto-generated constructor stub
	}
	
	public void addTable(int index){
		switch (index){
		case 0:{
			Object[] basiccolomn=new String[]{"������Ϣ",""};
			Object[][] basicdetail=new Object[4][2];
			
			basicdetail[0][0]="����";
			basicdetail[0][1]=playervo.name;
			basicdetail[1][0]="���";
			basicdetail[1][1]=playervo.team;
			basicdetail[2][0]="����λ��";
			basicdetail[2][1]=playervo.position;
			basicdetail[3][0]="����";
			basicdetail[3][1]=playervo.league;
			
			basicinfo=new CommonTable(basicdetail,basiccolomn);
			
			basicinfo.setPreferredScrollableViewportSize(new Dimension(600,180));
			basicinfo.setRowHeight(30);
			basicinfo.setFont(new Font("΢���ź�",Font.BOLD,15));
			//FitTableColumns(table);
			
			for(int i=0;i<basicinfo.getColumnCount();i++){
				TableColumn tc=basicinfo.getColumn(basicinfo.getColumnName(i));  
		        tc.setMinWidth(150);

			}
			basicinfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//basicinfo.getTableHeader().setPreferredSize(new Dimension (basicinfo.getTableHeader().getMinimumSize().width,30));
			basicinfo.updateUI();

			scroll = new JScrollPane();
			scroll.setViewportView(basicinfo);
			scroll.setLocation(600,180);
			scroll.setSize(300,130);
			scroll.setVisible(true);
			functionlabel.add(scroll);
			
			break;
		}
		case 1:{
			Object[] matchcolomn=new String[]{"������Ϣ",""};
			Object[][] matchdetail=new Object[9][2];
			
			matchdetail[0][0]="�ȷ�����/�ܳ�����";
			matchdetail[0][1]=playervo.startingMatches+"/"+playervo.totalMatches;
			matchdetail[1][0]="�����÷�";
			matchdetail[1][1]=(double)playervo.totalScores/(double)playervo.totalMatches;
			matchdetail[2][0]="��������";
			matchdetail[2][1]=(double)playervo.totalAssists/(double)playervo.totalMatches;
			matchdetail[3][0]="��������";
			matchdetail[3][1]=(double)playervo.totalRebounds/(double)playervo.totalMatches;
			matchdetail[4][0]="������ñ";
			matchdetail[4][1]=(double)playervo.totalRejection/(double)playervo.totalMatches;
			matchdetail[5][0]="��������";
			matchdetail[5][1]=(double)playervo.totalSteals/(double)playervo.totalMatches;
			matchdetail[6][0]="Ͷ��������";
			matchdetail[6][1]=playervo.shotPercent;
			matchdetail[7][0]="����������";
			matchdetail[7][1]=playervo.threePointPercent;
			matchdetail[8][0]="����������";
			matchdetail[8][1]=playervo.freeThrowPercent;
			
			basicinfo=new CommonTable(matchdetail,matchcolomn);
			
			basicinfo.setPreferredScrollableViewportSize(new Dimension(600,180));
			basicinfo.setRowHeight(30);
			basicinfo.setFont(new Font("΢���ź�",Font.BOLD,15));
			//FitTableColumns(table);
			
			for(int i=0;i<basicinfo.getColumnCount();i++){
				TableColumn tc=basicinfo.getColumn(basicinfo.getColumnName(i));  
		        tc.setMinWidth(150);

			}
			basicinfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//basicinfo.getTableHeader().setPreferredSize(new Dimension (basicinfo.getTableHeader().getMinimumSize().width,30));
			basicinfo.updateUI();

			scroll = new JScrollPane();
			scroll.setViewportView(basicinfo);
			scroll.setLocation(600,180);
			scroll.setSize(300,130);
			scroll.setVisible(true);
			functionlabel.add(scroll);
			break;
		}
		default:{}
		}
	}
	
	class convertlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(basicinfo);
			functionlabel.remove(scroll);
			
			addTable(convert.getSelectedIndex());
			
			Constant.mainframe.repaint();
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
			if(e.getClickCount()==2){
				int index=recent.getSelectedRow();
				Constant.mainframe.showSingleMatchPanel(recent.getValueAt(index, 0)+"_"+recent.getValueAt(index, 1)+"_"+recent.getValueAt(index, 2));
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
	
	class portraitadapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			portraits.setIcon(new ImageIcon("E:/JavaWorkbench/NBAData/players/action/"+playervo.name+".png"));
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			portraits.setIcon(new ImageIcon("E:/JavaWorkbench/NBAData/players/portrait/"+playervo.name+".png"));
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
			Constant.mainframe.showPlayerMainPanel();
			// TODO Auto-generated method stub
			
		}
		
	}

}
