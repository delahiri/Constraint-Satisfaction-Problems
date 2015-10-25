import java.util.ArrayList;
import java.util.HashSet;


public class Colors
{


	public static final String YELLOW = "yellow";
	public static final String IVORY = "ivory";
	public static final String BLUE = "blue";
	public static final String GREEN = "green";
	public static final String RED = "red";
	ArrayList<String> colors;
	
	public Colors()
	{
		colors = new ArrayList<String>();
		colors.add(RED);
		colors.add(GREEN);
		colors.add(BLUE);
		colors.add(IVORY);
		colors.add(YELLOW);
		
	}
	
	public int size()
	{
		return colors.size();
	}
	


}
