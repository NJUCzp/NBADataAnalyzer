package pres.teamui;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import pres.uitools.CommonButton;
import pres.uitools.CommonPanel;
import pres.uitools.CommonTable;

public class SingleTeamPanel extends CommonPanel{
	String shortname;
	JLabel portraits;
	JComboBox choices;
	CommonTable details;
	CommonTable recent;
	CommonButton back;
	
	public SingleTeamPanel(String shortname) {
		super("graphics/detailpanel/detail_background.png");
		this.shortname=shortname;
		// TODO Auto-generated constructor stub
	}

}
