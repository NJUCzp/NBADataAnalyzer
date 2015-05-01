package pres.playerui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;

import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;

public class SinglePlayerPanel extends CommonPanel{
	String name;
	JLabel portraits;
	JComboBox choices;
	CommonTable details;
	CommonTable recent;
	CommonButton back;

	public SinglePlayerPanel(String name) {
		super("graphics/detailpanel/detail_background.png");
		this.name=name;
		// TODO Auto-generated constructor stub
	}

}
