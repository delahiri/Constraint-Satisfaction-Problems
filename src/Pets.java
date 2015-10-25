import java.util.ArrayList;


public class Pets 
{
	public static final String HORSE = "horse";
	public static final String ZEBRA = "zebra";
	public static final String SNAILS = "snails";
	public static final String FOX = "fox";
	public static final String DOG = "dog";
	ArrayList<String> pets;
	
	public Pets()
	{
		pets = new ArrayList<String>();
		pets.add(DOG);
		pets.add(FOX);
		pets.add(SNAILS);
		pets.add(ZEBRA);
		pets.add(HORSE);
		
	}
	
	public int size()
	{
		return pets.size();
	}

}
