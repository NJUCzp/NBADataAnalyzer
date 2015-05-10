package pres.playerui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import bl.playerbl.PlayerBL;
import blservice.playerblservice.PlayerBLService;
import constantinfo.Constant;
import constantinfo.ScreenBy;
import constantinfo.SortBy;
import pres.playerui.PlayerMainPanel.backlistener;
import pres.playerui.PlayerMainPanel.screenlistener;
import pres.playerui.PlayerMainPanel.seasonslistener;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;
import vo.playerVO;

public class PlayerScreenPanel extends CommonPanel{
	CommonButton screen;
	CommonButton back;
	JTextField minimum;
	JTextField maximum;
	/*ButtonGroup positions;
	ButtonGroup EW;
	ButtonGroup sortbys;*/
	JLabel min;
	JLabel max;
	JLabel playerinfo;
	JLabel position;
	JLabel EorW;
	JLabel league;
	JLabel sortby;
	JLabel range;
	JLabel chooseseason;
	
	JComboBox seasons;
	JComboBox positions;
	JComboBox EW;
	JComboBox Leagues;
	JComboBox sortbys;
	
	String season="13-14";
	PlayerBLService playerblservice;
	ArrayList<playerVO> screenedlist; 
	Object[][] screeneddata;
	
	/*JRadioButton F=new JRadioButton("前锋");
	JRadioButton C=new JRadioButton("中锋");
	JRadioButton G=new JRadioButton("后卫");
	
	JRadioButton E=new JRadioButton("东部");
	JRadioButton W=new JRadioButton("西部");
	
	JRadioButton score=new JRadioButton("得分");
	JRadioButton rebound=new JRadioButton("篮板");
	JRadioButton assist=new JRadioButton("助攻");
	JRadioButton sra=new JRadioButton("得分+篮板+助攻");
	JRadioButton rejection=new JRadioButton("盖帽");
	JRadioButton steal=new JRadioButton("抢断");
	JRadioButton foul=new JRadioButton("犯规");
	JRadioButton turnover=new JRadioButton("失误");
	JRadioButton time=new JRadioButton("在场时间");
	JRadioButton efficiency=new JRadioButton("效率");
	JRadioButton threepoint=new JRadioButton("三分（命中率）");
	JRadioButton freethrow=new JRadioButton("罚球（命中率）");
	JRadioButton pairs=new JRadioButton("两双");
	JRadioButton shots=new JRadioButton("出手");*/


	public PlayerScreenPanel() {
		super("graphics/detailpanel/detail_background.png");
		
		playerblservice=new PlayerBL();
		
		Font font1=new Font("微软雅黑",Font.BOLD,12);
		Font font2=new Font("微软雅黑",Font.BOLD,16);
		
		playerinfo=new JLabel(new ImageIcon("graphics/detailpanel/playerinfo_label.png"));
		playerinfo.setBounds(500, 20, 180, 45);
		playerinfo.setVisible(true);
		functionlabel.add(playerinfo);
		
		position=new JLabel("场上位置:");
		position.setFont(font2);
		position.setBounds(350, 150,100, 20);
		position.setVisible(true);
		functionlabel.add(position);
		
		EorW=new JLabel("东/西部:");
		EorW.setFont(font2);
		EorW.setBounds(350, 230, 100, 20);
		EorW.setVisible(true);
		functionlabel.add(EorW);
		
		league=new JLabel("联盟:");
		league.setFont(font2);
		league.setBounds(350, 310, 100, 20);
		league.setVisible(true);
		functionlabel.add(league);
		
		sortby=new JLabel("排序依据:");
		sortby.setFont(font2);
		sortby.setBounds(350, 390, 100, 20);
		sortby.setVisible(true);
		functionlabel.add(sortby);
		
		range=new JLabel("范围:");
		range.setFont(font2);
		range.setBounds(350, 470, 100, 20);
		range.setVisible(true);
		//functionlabel.add(range);
		
		min=new JLabel("最小值");
		min.setFont(font2);
		min.setBounds(470, 470, 100,20);
		min.setVisible(true);
		//functionlabel.add(min);
		
		max=new JLabel("最大值");
		max.setFont(font2);
		max.setBounds(670, 470, 100, 20);
		max.setVisible(true);
		//functionlabel.add(max);
		
		minimum=new JTextField(10);
		minimum.setBounds(550,470,100,20);
		minimum.setBorder(BorderFactory.createEmptyBorder());
		minimum.setVisible(true);
		//functionlabel.add(minimum);
		
		maximum=new JTextField(10);
		maximum.setBounds(750,470,100,20);
		maximum.setBorder(BorderFactory.createEmptyBorder());
		maximum.setVisible(true);
		//functionlabel.add(maximum);
		
		chooseseason=new JLabel("选择赛季");
		chooseseason.setFont(font2);
		chooseseason.setBounds(45, 220, 100, 30);
		chooseseason.setVisible(true);
		functionlabel.add(chooseseason);
		
		String[] allpositions={"前锋","中锋","后卫"};
		String[] eorw={"东部","西部"};
		String[] leagues={"Southeast","Atlantic","Central","Northwest","Pacific","Southwest"};
		String[] sorts={"得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
		
		positions=new JComboBox(allpositions);
		positions.setBounds(700, 150, 120, 35);
		positions.setFont(font1);
		positions.setBorder(BorderFactory.createEmptyBorder());
		positions.setSelectedItem(null);
		positions.setVisible(true);
		functionlabel.add(positions);
		
		EW=new JComboBox(eorw);
		EW.setBounds(700, 230, 120, 35);
		EW.setFont(font1);
		EW.setBorder(BorderFactory.createEmptyBorder());
		EW.setSelectedItem(null);
		EW.setVisible(true);
		functionlabel.add(EW);
		
		Leagues=new JComboBox(leagues);
		Leagues.setBounds(700, 310, 120, 35);
		Leagues.setFont(font1);
		Leagues.setBorder(BorderFactory.createEmptyBorder());
		Leagues.setSelectedItem(null);
		Leagues.setVisible(true);
		functionlabel.add(Leagues);
		
		sortbys=new JComboBox(sorts);
		sortbys.setBounds(700, 390, 120, 35);
		sortbys.setFont(font1);
		sortbys.setBorder(BorderFactory.createEmptyBorder());
		sortbys.setSelectedItem(null);
		sortbys.setVisible(true);
		functionlabel.add(sortbys);
		
		seasons=new JComboBox(new String[]{"12-13","13-14","14-15"});
		seasons.setBounds(140, 220, 120, 35);
		seasons.setFont(font1);
		seasons.setBorder(BorderFactory.createEmptyBorder());
		seasons.setSelectedItem(null);
		//seasons.addActionListener(new seasonslistener());
		seasons.setVisible(true);
		functionlabel.add(seasons);
		/*F.setBounds(300, 300, 80, 30);
		F.setVisible(true);
		functionlabel.add(F);
		
		F.setBounds(400, 300, 80, 30);
		F.setVisible(true);
		functionlabel.add(C);
		
		F.setBounds(500, 300, 80, 30);
		F.setVisible(true);
		functionlabel.add(G);
		
		positions.add(F);
		positions.add(C);
		positions.add(G);
		
		E.setBounds(300, 350, 80, 30);
		E.setVisible(true);
		functionlabel.add(E);
		
		W.setBounds(400, 350, 80, 30);
		W.setVisible(true);
		functionlabel.add(W);
		
		EW.add(E);
		EW.add(W);
		
		score.setBounds(300, 400, 50, 30);
		score.setVisible(true);
		functionlabel.add(score);
		
		rebound.setBounds(350, 400, 50, 30);
		rebound.setVisible(true);
		functionlabel.add(rebound);
		
		assist.setBounds(400, 400, 50, 30);
		assist.setVisible(true);
		functionlabel.add(assist);
		
		sra.setBounds(450, 400, 50, 30);
		sra.setVisible(true);
		functionlabel.add(sra);
		
		rejection.setBounds(500, 400, 50, 30);
		rejection.setVisible(true);
		functionlabel.add(rejection);
		
		steal.setBounds(550, 400, 50, 30);
		steal.setVisible(true);
		functionlabel.add(steal);
		
		foul.setBounds(600, 400, 50, 30);
		foul.setVisible(true);
		functionlabel.add(foul);
		
		turnover.setBounds(300, 400, 50, 30);
		turnover.setVisible(true);
		functionlabel.add(turnover);
		
		time.setBounds(350, 450, 50, 30);
		time.setVisible(true);
		functionlabel.add(time);
		
		efficiency.setBounds(400, 450, 50, 30);
		efficiency.setVisible(true);
		functionlabel.add(efficiency);
		
		threepoint.setBounds(450, 450, 50, 30);
		threepoint.setVisible(true);
		functionlabel.add(threepoint);
		
		freethrow.setBounds(500, 450, 50, 30);
		freethrow.setVisible(true);
		functionlabel.add(freethrow);
		
		pairs.setBounds(550, 450, 50, 30);
		pairs.setVisible(true);
		functionlabel.add(pairs);
		
		shots.setBounds(600, 450, 50, 30);
		shots.setVisible(true);
		functionlabel.add(shots);
		
		sortbys.add(rebound);
		sortbys.add(assist);
		sortbys.add(efficiency);
		sortbys.add(foul);
		sortbys.add(freethrow);
		sortbys.add(pairs);
		sortbys.add(rejection);
		sortbys.add(score);
		sortbys.add(shots);
		sortbys.add(sra);
		sortbys.add(steal);
		sortbys.add(threepoint);
		sortbys.add(time);
		sortbys.add(turnover);*/
		
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
		
		// TODO Auto-generated constructor stub
	}
	
	class screenlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int toscreenposition=positions.getSelectedIndex();
			int toscreeneorw=EW.getSelectedIndex();
			int toscreenleagues=Leagues.getSelectedIndex();
			int toscreensortbys=sortbys.getSelectedIndex();
			if(seasons.getSelectedItem()!=null){
				season=seasons.getSelectedItem().toString();
			}
			
			ArrayList<ScreenBy> toscreen=new ArrayList<ScreenBy>();
			SortBy sort=SortBy.SCREEN_SCORE;//默认按总得分筛选
			
			switch (toscreenposition){
			case 0:
				toscreen.add(ScreenBy.F);break;
			case 1:
				toscreen.add(ScreenBy.C);break;
			case 2:
				toscreen.add(ScreenBy.G);break;
			default:
				toscreen.add(ScreenBy.DEFAULT);
			}
			
			switch (toscreeneorw){
			case 0:
				toscreen.add(ScreenBy.E);break;
			case 1:
				toscreen.add(ScreenBy.W);break;
			default:
				toscreen.add(ScreenBy.DEFAULT);
		    }
			
			switch (toscreenleagues){
			case 0:
				toscreen.add(ScreenBy.SOUTHEAST);break;
			case 1:
				toscreen.add(ScreenBy.ATLANTIC);break;
			case 2:
				toscreen.add(ScreenBy.CENTRAL);break;
			case 3:
				toscreen.add(ScreenBy.NORTHWEST);break;
			case 4:
				toscreen.add(ScreenBy.PACIFIC);break;
			case 5:
				toscreen.add(ScreenBy.SOUTHWEST);break;
			default:
				toscreen.add(ScreenBy.DEFAULT);
			}
				
			switch (toscreensortbys){
			case 0:
				sort=SortBy.SCREEN_SCORE;break;
			case 1:
				sort=SortBy.SCREEN_REBOUND;break;
			case 2:
				sort=SortBy.SCREEN_ASSIST;break;
			case 3:
				sort=SortBy.SCREEN_SRA;break;
			case 4:
				sort=SortBy.SCREEN_REJECTION;break;
			case 5:
				sort=SortBy.SCREEN_STEAL;break;
			case 6:
				sort=SortBy.SCREEN_FOUL;break;
			case 7:
				sort=SortBy.SCREEN_TURNOVER;break;
			case 8:
				sort=SortBy.SCREEN_TIME;break;
			case 9:
				sort=SortBy.SCREEN_EFFICIENCY;break;
			case 10:
				sort=SortBy.SCREEN_SHOTS;break;
			case 11:
				sort=SortBy.SCREEN_THREEPOINT;break;
			case 12:
				sort=SortBy.SCREEN_FREETHROW;break;
			case 13:
				sort=SortBy.SCREEN_PAIRS;break;
			default:
				
			}
			
			functionlabel.remove(EW);
			functionlabel.remove(EorW);
			functionlabel.remove(league);
			functionlabel.remove(Leagues);
			functionlabel.remove(max);
			functionlabel.remove(maximum);
			functionlabel.remove(min);
			functionlabel.remove(minimum);
			functionlabel.remove(position);
			functionlabel.remove(positions);
			functionlabel.remove(range);
			functionlabel.remove(screen);
			functionlabel.remove(sortby);
			functionlabel.remove(sortbys);
			
			if(season==null)
				season="13-14";//默认13-14赛季
			screenedlist=playerblservice.screen(toscreen,sort,playerblservice.findAll(season));//返回一组已筛选的且大小符合要求的球员信息
						
			//String[] colomn = {"球员姓名","位置","联盟","得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
			String[] colomn = {"球员姓名","位置","得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};

			int length=0;
			screeneddata=new Object[50][16];
			
			
			for(playerVO list:screenedlist){
				screeneddata[length][0]=list.name;
				screeneddata[length][1]=list.position;
				screeneddata[length][2]=list.totalScores;
				screeneddata[length][3]=list.totalRebounds;
				screeneddata[length][4]=list.totalAssists;
				screeneddata[length][5]=list.totalAssists+list.totalRebounds+list.totalScores;
				screeneddata[length][6]=list.totalRejection;
				screeneddata[length][7]=list.totalSteals;
				screeneddata[length][8]=list.totalFouls;
				screeneddata[length][9]=list.totalTurnovers;
				screeneddata[length][10]=list.timeOnCourt;
				screeneddata[length][11]=list.efficiency;
				screeneddata[length][12]=list.totalshots;
				screeneddata[length][13]=list.totalThreePointShots;
				screeneddata[length][14]=list.totalFreeThrows;
				screeneddata[length][15]=list.pairs;

				length++;
			}
			
			JScrollPane scroll;

			CommonTable screenedtable=new CommonTable(screeneddata,colomn);
			screenedtable.setPreferredScrollableViewportSize(new Dimension(500,300));
			screenedtable.setRowHeight(20);
			screenedtable.setFont(new Font("微软雅黑",Font.BOLD,15));
			
			for(int i=0;i<screenedtable.getColumnCount();i++){
				TableColumn tc=screenedtable.getColumn(screenedtable.getColumnName(i));  
		        if(i==0) 
			        tc.setMinWidth(150);
		        else
		        	tc.setMinWidth(80);

			}
			
			screenedtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			screenedtable.getTableHeader().setPreferredSize(new Dimension (screenedtable.getTableHeader().getMinimumSize().width,30));
			screenedtable.updateUI();
			
			scroll = new JScrollPane(screenedtable);
			scroll.setLocation(400,250);
			scroll.setSize(500, 301);
			scroll.setVisible(true);
			functionlabel.add(scroll);
			
			Constant.mainframe.repaint();
	
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
