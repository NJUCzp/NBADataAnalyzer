package pres.mainui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constantinfo.Constant;
import pres.matchui.MatchMainPanel;
import pres.matchui.SingleMatchPanel;
import pres.playerui.PlayerMainPanel;
import pres.playerui.PlayerScreenPanel;
import pres.teamui.TeamMainPanel;
import pres.uitools.CommonButton;

public class mainFrame extends JFrame {
	CardLayout card=new CardLayout();
	
	CommonButton minimize;
	CommonButton close;
	
	mainPanel mainpanel;
	PlayerMainPanel playermainpanel;
	TeamMainPanel teammainpanel;
	PlayerScreenPanel playerscreenpanel;
	MatchMainPanel matchmainpanel;
	SingleMatchPanel singlematchpanel;
	
	int locationX;
	int locationY;
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	
	public mainFrame(){
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(card);
		
		this.setSize(1024, 768);
		this.setResizable(false);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		this.locationX=(screen.width-this.getWidth())/2;
		this.locationY=(screen.height-this.getHeight())/2;
		this.setLocation(this.locationX,this.locationY);
		
		
		mainpanel=new mainPanel();
		setDragable(mainpanel);
		this.getContentPane().add(mainpanel, "mainpanel");
		
		
		
		
		
		
		this.setVisible(true);
		
		Constant.mainframe=this;
	
	}
	
	public void showMainPanel(){
		
		card.show(this.getContentPane(), "mainpanel");
	}
	
	public void showMatchMainPanel(){
		if (matchmainpanel==null)
			matchmainpanel=new MatchMainPanel();
		
		setDragable(matchmainpanel);
		this.getContentPane().add(matchmainpanel,"matchmainpanel");
		card.show(this.getContentPane(),"matchmainpanel");
	}
	
	public void showSingleMatchPanel(String basicinfo){
		singlematchpanel=new SingleMatchPanel(basicinfo);
		setDragable(singlematchpanel);
		this.getContentPane().add(singlematchpanel,"singlematchpanel");
		card.show(this.getContentPane(),"singlematchpanel");
	}
	
	public void showPlayerMainPanel(){
		if (playermainpanel==null)
			playermainpanel=new PlayerMainPanel();
		
		setDragable(playermainpanel);
		this.getContentPane().add(playermainpanel,"playermainpanel");
		card.show(this.getContentPane(),"playermainpanel");
	}
	
	public void showTeamMainPanel(){
		teammainpanel=new TeamMainPanel();
		setDragable(teammainpanel);
		this.getContentPane().add(teammainpanel, "teammainpanel");
		card.show(this.getContentPane(), "teammainpanel");
	}
	
	public void showPlayerScreenPanel(){
		playerscreenpanel=new PlayerScreenPanel();
		setDragable(playerscreenpanel);
		this.getContentPane().add(playerscreenpanel, "playerscreenpanel");
		card.show(this.getContentPane(), "playerscreenpanel");
	}
		
	public void setDragable(JPanel panel) {
		final mainFrame jframe=this;
    	panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               //TODO �������ͼ��
            //   jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
             //TODO �������ͼ��
             //  jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
    	panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(jframe.getLocation().x + e.getX() - tmp.x,
                     jframe.getLocation().y + e.getY() - tmp.y);
                   jframe.setLocation(loc);
               }
            }
        });
 }
	
}
