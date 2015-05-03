package pres.uitools;

import javax.swing.JTable;

public class CommonTable extends JTable{
	public CommonTable(Object[][]table,Object[]colomn){
		super(table,colomn);
	}
	
	public boolean isCellEditable(int row,int colomn){
		
		return false;
	}
}
