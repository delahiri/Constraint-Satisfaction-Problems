import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class RunEinsteinPuzzle {
	
	public static final Colors ALL_COLORS = new Colors();
	public static final Drinks ALL_DRINKS = new Drinks();
	public static final Foods ALL_FOODS = new Foods();
	public static final Nationalities ALL_NATIONALITIES = new Nationalities();
	public static final Pets ALL_PETS = new Pets();
	public static final Positions ALL_POSITIONS = new Positions();
	public static final Numbers ALL_NUMBERS = new Numbers();
	public static int count=0;

	public static void main(String[] args) 
	{
		
		Map<Integer, Person> solution = new HashMap<Integer, Person>();
		
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person();
		Person p5 = new Person();
		
		solution.put(1, p1);
		solution.put(2, p2);
		solution.put(3, p3);
		solution.put(4, p4);
		solution.put(5, p5);
		long start = System.currentTimeMillis();
		
		if(!solve(solution,1))
		{
			System.out.println("No Solution");
		}
		else
		{
		
			for(Map.Entry<Integer, Person> entry : solution.entrySet())
			{
				Person person = entry.getValue();
				person.print();
			}
		}
		System.out.println("Time taken "+(System.currentTimeMillis()-start)/1000.0);
		System.out.println("Count "+count);
		
	}

	
	/**
	 * 
	 * @param solution
	 * @param startIndex
	 * @return
	 */
	private static boolean solve(Map<Integer, Person> solution, int startIndex) 
	{
		if(startIndex > solution.size())
		{
			return true;
		}
		else
		{
			Person current = solution.get(startIndex);
			
			for(String i:ALL_COLORS.colors)
			{
				current.color = i;
				if(!consistencyCheck(solution))
					continue;
				for(String j :ALL_DRINKS.drinks)
				{
					current.drink = j;
					if(!consistencyCheck(solution))
						continue;
					
					for(String k:ALL_FOODS.foods)
					{
						current.food = k;
						if(!consistencyCheck(solution))
							continue;
						for(String l:ALL_NATIONALITIES.nationalities)
						{
							current.nationality = l;
							if(!consistencyCheck(solution))
								continue;
							for(String m :ALL_PETS.pets)
							{
								current.pet  = m;
								if(!consistencyCheck(solution))
									continue;
								for(String n :ALL_POSITIONS.positions)
								{
									current.color = i;
									current.drink = j;
									current.food = k;
									current.nationality = l;
									current.pet = m;
									current.position = n;
									
									if(consistencyCheck(solution) && solve(solution,startIndex+1))
									{
										return true;
									}
									else
									{	
										nullify(solution,startIndex);
									}				
								}
							}
						}
					}
				}
			}
		}
		
		
		
		
		return false;
	}


	private static void nullify(Map<Integer, Person> solution, int startIndex) 
	{
		for(int i =startIndex;i<=solution.size();i++)
		{
			Person p = solution.get(i);
			p.nullify();
		}
	}


	private static boolean consistencyCheck(Map<Integer, Person> solution) 
	{
		count++;
		if(isDuplication(solution) || !nodalConsistency(solution))
		{
			return false;
		}
		return true;
	
	}

	/**
	 * 
	 * @param solution
	 * @return
	 */
	private static boolean nodalConsistency(Map<Integer, Person> solution) 
	{
		for(int i=1;i<=solution.size();i++)
		{
			Person p = solution.get(i);
			
			// Englishman lives in a red house
			if(p.nationality != null && p.color != null && p.nationality == Nationalities.ENGLISHMAN && p.color != Colors.RED)
			{
				return false;
			}
			
			// Spaniard owns the dog
			if(p.nationality != null && p.pet != null && p.nationality == Nationalities.SPANIARD && p.pet != Pets.DOG)
			{
				return false;
			}
			
			// Norwegian lives in the first house on the left
			if(p.nationality != null && p.position != null && p.nationality == Nationalities.NORWEGIAN && p.position != Positions.ONE)
			{
			//	p.print();
				return false;
			}
			
			// Green house is immediately to the right of the ivory house
			if(!greenHouseIsImmediatelyToTheRightOfIvoryHouse(solution,p))
			{
				return false;
			}
			
			// The man who eats Hershey bars lives in the house next to the man with the fox
			if(!manWhoEatsHersheyLivesNextToManWithFox(solution,p))
			{
				return false;
			}
			
			// Kits Kats are eaten in the yellow house
			if(p.food != null && p.color != null && p.food == Foods.KITS_KATS && p.color != Colors.YELLOW)
			{
				return false;
			}
			
			// The Norwegian lives next to the blue house
			if(!norwegianLivesNextToTheBLueHouse(solution,p))
			{
				return false;
			}
			
			// The Smarties eater owns snails
			if(p.food != null && p.pet != null && p.food == Foods.SMARTIES && p.pet != Pets.SNAILS)
			{
				return false;
			}
			
			// The Snickers eater drinks orange juice
			if(p.food != null && p.drink != null && p.food == Foods.SNICKERS && p.drink != Drinks.ORANGE_JUICE)
			{
				
				return false;
			}
			
			// The Ukranian drinks tea
			if(p.nationality != null && p.drink != null && p.nationality == Nationalities.UKRANIAN && p.drink != Drinks.TEA)
			{
				return false;
			}
			
			// The japanese person eats Milky ways
			if(p.nationality != null && p.food != null &&p.nationality == Nationalities.JAPANESE && p.food != Foods.MILKY_WAYS)
			{
				
				return false;
			}
			
			// Kit Kats are eaten in a house next to the house where the horse is kept
			if(!kitKatsEatenInHouseNextToHorseKept(solution,p))
			{
				return false;
			}
			
			// Coffee is drunk in the green house
			if(p.drink != null && p.color != null && p.drink == Drinks.COFFEE && p.color != Colors.GREEN)
			{
				return false;
			}
			
			// Milk is drunk in the middle house
			if(p.drink != null && p.position != null && p.drink == Drinks.MILK && p.position != Positions.THREE)
			{
				return false;
			}		
		}
		
		
		return true;
	}


	/**
	 * 
	 * @param solution
	 * @param p
	 * @return
	 */
	private static boolean kitKatsEatenInHouseNextToHorseKept(Map<Integer, Person> solution, Person p) {
		if(p.food == null || p.position == null || p.food != Foods.KITS_KATS)
		{
			return true;
		}
		else
		{
			Person hasAHorse = null;
			for(int i=1;i<=solution.size();i++)
			{
				Person temp = solution.get(i);
				if(temp.pet == Pets.HORSE)
				{
					hasAHorse = temp;
					break;
				}
			}
			
			if (hasAHorse != null
					&& hasAHorse.position != null
					&& Math.abs(ALL_NUMBERS.numbers.get(hasAHorse.position)
							- ALL_NUMBERS.numbers.get(p.position)) != 1.0) 
			{
				return false;
			}
			return true;
		}
	}

	/**
	 * 
	 * @param solution
	 * @param p
	 * @return
	 */
	private static boolean manWhoEatsHersheyLivesNextToManWithFox(Map<Integer, Person> solution, Person p) 
	{
		if(p.position == null || p.food == null || p.food != Foods.HERSHEY_BARS)
		{
			return true;
		}
		else
		{

			Person personWithFox = null;
			for(int i=1;i<=solution.size();i++)
			{
				Person temp = solution.get(i);
				if(temp.pet == Pets.FOX)
				{
					personWithFox = temp;
					break;
				}
			}
			
			if (personWithFox != null
					&& personWithFox.position != null
					&& (ALL_NUMBERS.numbers.get(p.position)
							- ALL_NUMBERS.numbers
									.get(personWithFox.position) != 1.0)) 
			{
				return false;
			}
			return true;
		
			
		}

	}


	private static boolean greenHouseIsImmediatelyToTheRightOfIvoryHouse(Map<Integer, Person> solution, Person p) 
	{
		if(p.position == null || p.color == null || p.color != Colors.GREEN)
		{
			return true;
		}
		else
		{
			Person personWithIvoryHouse = null;
			for(int i=1;i<=solution.size();i++)
			{
				Person temp = solution.get(i);
				if(temp.color == Colors.IVORY)
				{
					personWithIvoryHouse = temp;
					break;
				}
			}
			
			if (personWithIvoryHouse != null
					&& personWithIvoryHouse.position != null
					&& (ALL_NUMBERS.numbers.get(p.position)
							- ALL_NUMBERS.numbers
									.get(personWithIvoryHouse.position) != 1.0)) 
			{
				return false;
			}
			return true;
		}
		
	}


	private static boolean norwegianLivesNextToTheBLueHouse(Map<Integer, Person> solution, Person p) {
		if(p.position == null || p.nationality == null || p.nationality != Nationalities.NORWEGIAN)
		{
			return true;
		}
		else
		{
			Person personWithBlueHouse = null;
			for(int i=1;i<=solution.size();i++)
			{
				Person temp = solution.get(i);
				if(temp.color == Colors.BLUE)
				{
					personWithBlueHouse = temp;
					break;
				}
			}
			if (personWithBlueHouse != null
					&& personWithBlueHouse.position != null
					&& Math.abs(ALL_NUMBERS.numbers
							.get(personWithBlueHouse.position)
							- ALL_NUMBERS.numbers.get(p.position)) != 1.0)
			{
				return false;
			}
			return true;
		}
	}


	/**
	 * Check duplication
	 * @param solution
	 * @return
	 */
	private static boolean isDuplication(Map<Integer, Person> solution) 
	{
		HashSet<String> colorsTaken = new HashSet<String>();
		HashSet<String> petsTaken = new HashSet<String>();
		HashSet<String> foodsTaken = new HashSet<String>();
		HashSet<String> drinksTaken = new HashSet<String>();
		HashSet<String> nationalitiesTaken = new HashSet<String>();
		HashSet<String> positionsTaken = new HashSet<String>();
		
		for(int i=1;i<=solution.size();i++)
		{
			Person p = solution.get(i);
			if (colorsTaken.contains(p.color) || petsTaken.contains(p.pet)
					|| foodsTaken.contains(p.food)
					|| drinksTaken.contains(p.drink)
					|| nationalitiesTaken.contains(p.nationality)
					|| positionsTaken.contains(p.position)) 
			{
				return true;
			}
			else
			{
				if(p.color != null)
				{
					colorsTaken.add(p.color);
				}
				if(p.pet != null)
				{
					petsTaken.add(p.pet);
				}
				if(p.food != null)
				{
					foodsTaken.add(p.food);
				}
				if(p.drink != null)
				{
					drinksTaken.add(p.drink);
				}
				if(p.nationality != null)
				{
					nationalitiesTaken.add(p.nationality);
				}
				if(p.position != null)
				{
					positionsTaken.add(p.position);
				}
			}
		}	
		return false;
	}

}
