import java.util.ArrayList;


public class Nationalities 
{
	public static final String JAPANESE = "Japanese";
	public static final String UKRANIAN = "Ukranian";
	public static final String NORWEGIAN = "Norwegian";
	public static final String SPANIARD = "Spaniard";
	public static final String ENGLISHMAN = "Englishman";
	ArrayList<String> nationalities;

	
	public Nationalities()
	{
		nationalities = new ArrayList<String>();
		nationalities.add(ENGLISHMAN);
		nationalities.add(SPANIARD);
		nationalities.add(NORWEGIAN);
		nationalities.add(UKRANIAN);
		nationalities.add(JAPANESE);
	}
	
	public int size()
	{
		return nationalities.size();
	}
}
