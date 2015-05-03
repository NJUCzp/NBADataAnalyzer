package pres.matchui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;

import po.playerInSingleMatchPO;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.matchVO;
import bl.matchbl.MatchBL;
import blservice.matchblservice.MatchBLService;
import constantinfo.Constant;

public class SingleMatchPanel extends CommonPanel{
	String basicmatchinfo;
	CommonButton back;
	JComboBox spes;//技术指标
	CommonTable quarters;
	CommonTable detailspes;
	JScrollPane scroll;
	JScrollPane quascroll;
	JLabel homescore;
	JLabel visitscore;
	JLabel home;
	JLabel visitor;
	JLabel homeportrait=new JLabel();
	JLabel visitportrait=new JLabel();
	
	Object[]homeSpesDetail;
	Object[]visitSpesDetail;
	
	MatchBLService matchblservice;
	matchVO matchinfo;
	
	public SingleMatchPanel(String basicmatchinfo) {
		// TODO Auto-generated constructor stub
		super("graphics/detailpanel/detail_background.png");
		this.basicmatchinfo=basicmatchinfo;
		
		matchblservice=new MatchBL();
		matchinfo=matchblservice.findSingleMatchInfo(basicmatchinfo);
		
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		Font font2=new Font("微软雅黑",Font.BOLD,25);
		
		JSVGCanvas canvashome=new JSVGCanvas();
		JSVGCanvas canvasvisit=new JSVGCanvas();
		
		canvashome.setURI("file:/E:/JavaWorkbench/NBAData/teams/"+matchinfo.home+".svg");
		canvashome.setBounds(150, 90, 150, 150);
		canvashome.setVisible(true);
		functionlabel.add(canvashome);
		
		canvasvisit.setURI("file:/E:/JavaWorkbench/NBAData/teams/"+matchinfo.visiting+".svg");
		canvasvisit.setBounds(720, 90, 150, 150);
		canvasvisit.setVisible(true);
		functionlabel.add(canvasvisit);
		
		spes=new JComboBox(new String[]{"得分","篮板数","抢断数","盖帽数","助攻数","命中率","投篮命中数/出手数","三分命中数/出手数","罚球命中数/出手数","进攻/防守篮板数"});
		spes.setBounds(452, 260, 120, 35);
		spes.setFont(font1);
		spes.setBorder(BorderFactory.createEmptyBorder());
		spes.setSelectedItem(null);
		spes.addActionListener(new speslistener());
		spes.setVisible(true);
		functionlabel.add(spes);
		
		home=new JLabel(matchinfo.home);
		home.setFont(font2);
		home.setBounds(312, 120,100,30);
		home.setVisible(true);
		functionlabel.add(home);
		
		visitor=new JLabel(matchinfo.visiting);
		visitor.setFont(font2);
		visitor.setBounds(642, 120,100,30);
		visitor.setVisible(true);
		functionlabel.add(visitor);
		
		homescore=new JLabel(matchinfo.homeScore+"");
		homescore.setFont(font2);
		homescore.setBounds(312, 150,100,30);
		homescore.setVisible(true);
		functionlabel.add(homescore);
		
		visitscore=new JLabel(matchinfo.visitingScore+"");
		visitscore.setFont(font2);
		visitscore.setBounds(642, 150,100,30);
		visitscore.setVisible(true);
		functionlabel.add(visitscore);
		
		
		
		Object scorecolomn[]={"队伍","第一节","第二节","第三节","第四节"};
		Object score[][]=new Object[2][5];
		score[0][0]=matchinfo.home;
		score[1][0]=matchinfo.visiting;
		for(int i=1;i<5;i++){
			score[0][i]=matchinfo.homeDetailScore[i-1];
			score[1][i]=matchinfo.visitingDetailScore[i-1];
		}
		//获取数据
		quarters=new CommonTable(score,scorecolomn);
		quarters.setRowHeight(20);
		quarters.setFont(new Font("微软雅黑",Font.BOLD,15));
		quarters.setBounds(312,180,400,60);

		quascroll=new JScrollPane(quarters);
		quascroll.setBounds(312,180,400,61);
		//quascroll.setVerticalScrollBarPolicy(policy);
		quascroll.setVisible(true);
		functionlabel.add(quascroll);
		
		back=new CommonButton("graphics/actionbutton/back_pressed.png","graphics/actionbutton/back_pressed.png","graphics/actionbutton/back.png");
		back.setBounds(392, 600, 240, 60);
		back.setVisible(true);
		back.addActionListener(new backlistener());
		functionlabel.add(back);
		
		addDetailTable("得分");//默认显示各队员得分
		
	}
	
	public void addDetailTable(String spes){
		Object detailcolomn[]={matchinfo.home,"","",matchinfo.visiting};
		Object detail[][]=new Object[15][4];
		
		switch(spes){
		case "得分":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getScore();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getScore();
				length++;
			}
			break;
		}
		
		case "投篮命中数/出手数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getShotsOnTargets()+"/"+singleplayer.getTotalShots();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getShotsOnTargets()+"/"+singleplayer.getTotalShots();
				length++;
			}
			break;
		}
		
		case "命中率":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=String.format("%.2f",singleplayer.getShotPercent());
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=String.format("%.2f",singleplayer.getShotPercent());
				length++;
			}
			break;
		}
		
		case "抢断数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getSteals();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getSteals();
				length++;
			}
			break;
		}
		
		case "盖帽数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getRejections();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getRejections();
				length++;
			}
			break;
		}
		
		case "助攻数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getAssists();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getAssists();
				length++;
			}
			break;
		}
		
		case "三分命中数/出手数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getThreePointShotsOnTargets()+"/"+singleplayer.getTotalThreePointShots();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getThreePointShotsOnTargets()+"/"+singleplayer.getTotalThreePointShots();
				length++;
			}
			break;
		}
		
		case "罚球命中数/出手数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getFreeThrowOnTargets()+"/"+singleplayer.getTotalFreeThrows();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getFreeThrowOnTargets()+"/"+singleplayer.getTotalFreeThrows();
				length++;
			}
			break;
		}
		
		case "篮板数":{
			int length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.hostPlayerInfo){
				detail[length][0]=singleplayer.getName();
				detail[length][1]=singleplayer.getRebounds();
				length++;
			}
			
			length=0;
			for(playerInSingleMatchPO singleplayer:matchinfo.visitPlayerInfo){
				detail[length][3]=singleplayer.getName();
				detail[length][2]=singleplayer.getRebounds();
				length++;
			}
			break;
		}
		
		default:{}
		}
		//获取数据
		detailspes=new CommonTable(detail,detailcolomn);
		detailspes.setPreferredScrollableViewportSize(new Dimension(460,250));
		detailspes.setRowHeight(20);
		detailspes.setFont(new Font("微软雅黑",Font.BOLD,15));
		//FitTableColumns(table);
		
		for(int i=0;i<detailspes.getColumnCount();i++){
			TableColumn tc=detailspes.getColumn(detailspes.getColumnName(i));  
	        if(i==0||i==3) 
		        tc.setMinWidth(150);
	        else
	        	tc.setMinWidth(80);

		}
		
		detailspes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		detailspes.getTableHeader().setPreferredSize(new Dimension (detailspes.getTableHeader().getMinimumSize().width,30));
		detailspes.updateUI();

		scroll = new JScrollPane();
		scroll.setViewportView(detailspes);
		scroll.setLocation(282,300);
		scroll.setSize(450,250);
		scroll.setVisible(true);
		functionlabel.add(scroll);
	}
	
	class speslistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			functionlabel.remove(detailspes);
			functionlabel.remove(scroll);
			
			addDetailTable(spes.getSelectedItem().toString());
			
			/*switch(spes.getSelectedIndex()){
			case 0:{
				addDetailTable("score");
				break;
			}
			
			case 1:{
				addDetailTable("rebound");
				break;
			}
			
			case 2:{
				addDetailTable("steal");
				break;
			}
			
			case 3:{
				addDetailTable("rejection");
				break;
			}
			
			case 4:{
				addDetailTable("");
				break;
			}
			
			case 5:{
				addDetailTable("");
				break;
			}
			
			case 6:{
				addDetailTable("");
				break;
			}
			
			case 7:{
				addDetailTable("");
				break;
			}
			
			case 8:{
				addDetailTable("");
				break;
			}
			
			case 9:{
				addDetailTable("");
				break;
			}
				
			}*/
			// TODO Auto-generated method stub
			Constant.mainframe.repaint();
		}
		
	}
	
	class backlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Constant.mainframe.showMatchMainPanel();
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
