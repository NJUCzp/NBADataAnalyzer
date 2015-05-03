package pres.uitools;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CommonButton extends JButton {
	public CommonButton(String path1,String path2,String path3){
		super();
		setIcon(new ImageIcon(path1));
		setRolloverIcon(new ImageIcon(path2));
		setPressedIcon(new ImageIcon(path3));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setVisible(true);
	}
}
