package pres.mainui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constantinfo.Constant;
import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;

public class mainPanel extends CommonPanel{
	CommonButton teaminfo;
	CommonButton playerinfo;
	CommonButton matchinfo;

	public mainPanel() {
		super("graphics/mainpanel/main_background.png");
		
		playerinfo=new CommonButton("graphics/mainpanel/playerinfo.png","graphics/mainpanel/playerinfo.png","graphics/mainpanel/playerinfo_pressed.png");
		playerinfo.setBounds(150, 300, 200, 200);
		playerinfo.addActionListener(new playerinfolistener());
		playerinfo.setVisible(true);
		functionlabel.add(playerinfo);
		
		teaminfo=new CommonButton("graphics/mainpanel/teaminfo.png","graphics/mainpanel/teaminfo.png","graphics/mainpanel/teaminfo_pressed.png");
		teaminfo.setBounds(400,300, 200, 200);
		teaminfo.addActionListener(new teaminfolistener());
		teaminfo.setVisible(true);
		functionlabel.add(teaminfo);
		
		matchinfo=new CommonButton("graphics/mainpanel/matchinfo.png","graphics/mainpanel/matchinfo.png","graphics/mainpanel/matchinfo_pressed.png");
		matchinfo.setBounds(150,530, 550, 50);
		matchinfo.addActionListener(new matchinfolistener());
		matchinfo.setVisible(true);
		functionlabel.add(matchinfo);
		
		
		// TODO Auto-generated constructor stub
	}
	
	class playerinfolistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showPlayerMainPanel();
			// TODO Auto-generated method stub
			
		}
	}
	
	class teaminfolistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showTeamMainPanel();
			// TODO Auto-generated method stub
			
		}
	}
	
	class matchinfolistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Constant.mainframe.showMatchMainPanel();
			// TODO Auto-generated method stub
			
		}
	}
}
