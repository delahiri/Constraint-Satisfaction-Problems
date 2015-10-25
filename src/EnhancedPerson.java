

public class EnhancedPerson extends Person 
{
	Colors colors;
	Drinks drinks;
	Foods foods;
	Nationalities nationalities;
	Pets pets;
	Positions positions;
	
	
	public EnhancedPerson()
	{
		colors = new Colors();
		drinks = new Drinks();
		foods = new Foods();
		nationalities = new Nationalities();
		pets = new Pets();
		positions = new Positions();
	}
	
	public void nullify()
	{
		super.nullify();
		colors = new Colors();
		drinks = new Drinks();
		foods = new Foods();
		nationalities = new Nationalities();
		pets = new Pets();
		positions = new Positions();
	}
	
}
