import java.util.ArrayList;


public class Positions 
{
	public static final String FIVE = "5";
	public static final String FOUR = "4";
	public static final String THREE = "3";
	public static final String TWO = "2";
	public static final String ONE = "1";
	ArrayList<String> positions;
	
	public Positions()
	{
		positions = new ArrayList<String>();
		positions.add(ONE);
		positions.add(TWO);
		positions.add(THREE);
		positions.add(FOUR);
		positions.add(FIVE);
		
	}
	
	public int size()
	{
		return positions.size();
	}
	




}
