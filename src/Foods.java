import java.util.ArrayList;


public class Foods 
{

	public static final String MILKY_WAYS = "milky ways";
	public static final String SNICKERS = "snickers";
	public static final String SMARTIES = "smarties";
	public static final String KITS_KATS = "kits kats";
	public static final String HERSHEY_BARS = "hershey bars";
	ArrayList<String> foods;
	
	public Foods()
	{
		foods = new ArrayList<String>();
		foods.add(HERSHEY_BARS);
		foods.add(KITS_KATS);
		foods.add(SMARTIES);
		foods.add(SNICKERS);
		foods.add(MILKY_WAYS);
		
	}
	
	public int size()
	{
		return foods.size();
	}
	
}
