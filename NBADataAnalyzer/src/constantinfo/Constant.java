package constantinfo;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import pres.mainui.mainFrame;

public class Constant {
	public static mainFrame mainframe;
	public static final int BG_WIDTH=1024;
	public static final int BG_HEIGHT=768;
	public static final ArrayList<String> EAST_TEAM=new ArrayList<String>(Arrays.asList("atl","bkn","cha","chi","cle","det","ind","mia","mil","nyk","orl","phi","tor","was","bos"));
	public static final ArrayList<String> WEST_TEAM=new ArrayList<String>(Arrays.asList("dal","den","hou","gsw","lac","lal","mem","min","nop","okc","phx","por","sac","sas","uta"));
	public static final ArrayList<String> SOUTHEAST_TEAM=new ArrayList<String>(Arrays.asList("atl","cha","mia","orl","was"));
	public static final ArrayList<String> ATLANTIC_TEAM=new ArrayList<String>(Arrays.asList("bkn","bos","nyk","phi","tor"));
	public static final ArrayList<String> CENTRAL_TEAM=new ArrayList<String>(Arrays.asList("chi","cle","det","ind","mil"));
	public static final ArrayList<String> NORTHWEST_TEAM=new ArrayList<String>(Arrays.asList("den","min","okc","por","uta"));
	public static final ArrayList<String> PACIFIC_TEAM=new ArrayList<String>(Arrays.asList("gsw","lac","lal","phx","sac"));
	public static final ArrayList<String> SOUTHWEST_TEAM=new ArrayList<String>(Arrays.asList("dal","hou","mem","nop","sas"));


}
