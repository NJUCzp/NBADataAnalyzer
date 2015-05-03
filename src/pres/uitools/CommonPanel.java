package pres.uitools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constantinfo.Constant;

public class CommonPanel extends JPanel{
	public JLabel background;
	public JLabel functionlabel;
	public CommonButton minimize;
	public CommonButton close;

	public  CommonPanel(String bg){
		super();
		
		this.setLayout(null);
		this.setSize(1024, 768);
		
		background=new JLabel(new ImageIcon(bg));
		background.setSize(1024,768);
		background.setLocation(0, 0);
		background.setVisible(true);
		this.add(background);
		
		functionlabel=new JLabel();
		functionlabel.setBounds(0, 0, 1024, 768);
		//functionlabel.setBackground(Color.WHITE);
		background.add(functionlabel);
		
		minimize=new CommonButton("graphics/mainpanel/ring.png","graphics/mainpanel/minimize.png","graphics/mainpanel/minimize.png");
		minimize.setBounds(800, 20, 60, 60);
		minimize.addMouseListener(new minimizelistener());
		minimize.setVisible(true);
		functionlabel.add(minimize);
		
		close=new CommonButton("graphics/mainpanel/ring.png","graphics/mainpanel/close.png","graphics/mainpanel/close.png");
		close.setBounds(900, 20, 60, 60);
		close.addMouseListener(new closelistener());
		close.setVisible(true);
		functionlabel.add(close);
		
		this.setVisible(true);
	}
	
	public class minimizelistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Constant.mainframe.setExtendedState(JFrame.ICONIFIED);
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
	
	public class closelistener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
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
