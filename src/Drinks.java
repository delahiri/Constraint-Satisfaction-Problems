import java.util.ArrayList;


public class Drinks 
{
	public static final String ORANGE_JUICE = "orange juice";
	public static final String MILK = "milk";
	public static final String COFFEE = "coffee";
	public static final String TEA = "tea";
	public static final String WATER = "water";
	ArrayList<String> drinks;
	
	public Drinks()
	{
		drinks = new ArrayList<String>();
		drinks.add(WATER);
		drinks.add(TEA);
		drinks.add(COFFEE);
		drinks.add(MILK);
		drinks.add(ORANGE_JUICE);
	}
	
	public int size()
	{
		return drinks.size();
	}

}
