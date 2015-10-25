import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class RunEinsteinPuzzleMrv extends RunEinsteinPuzzle
{

	public static void main(String[] args) 
	{
		PriorityQueue<EnhancedPerson> q = new PriorityQueue<EnhancedPerson>(11, new DomainComparator());
		
		EnhancedPerson p1 = new EnhancedPerson();
		EnhancedPerson p2 = new EnhancedPerson();
		EnhancedPerson p3 = new EnhancedPerson();
		EnhancedPerson p4 = new EnhancedPerson();
		EnhancedPerson p5 = new EnhancedPerson();
		
		q.add(p1);
		q.add(p2);
		q.add(p3);
		q.add(p4);
		q.add(p5);
		
		Hashtable<Integer, EnhancedPerson> solution = new Hashtable<Integer, EnhancedPerson>();
		
		if(!solve(solution,q,1))
		{
			System.out.println("No Solution");
		}
		else
		{
		
			Set<Integer> keys = solution.keySet();
	        for(Integer key: keys){
	            solution.get(key).print();
	        }
		}
		
		
		
	}

	private static boolean solve(Hashtable<Integer, EnhancedPerson> solution,PriorityQueue<EnhancedPerson> q, int startIndex) 
	{
		
		if(startIndex > 5)
		{
			return true;
		}
		else
		{
		
		updateDomains(solution,q,startIndex);
		EnhancedPerson p = q.poll();
		solution.put(startIndex, p);
		nullify(solution,startIndex+1);
		for(String color : p.colors.colors)
		{
			for(String drink : p.drinks.drinks)
			{
				for(String food : p.foods.foods)
				{
					for(String nationality : p.nationalities.nationalities)
					{
						for(String pet : p.pets.pets)
						{
							for(String position : p.positions.positions)
							{
								p.color = color;
								p.drink = drink;
								p.food = food;
								p.nationality = nationality;
								p.pet = pet;
								p.position = position;
								if(consistencyCheck(solution) && solve(solution,q,startIndex+1))
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
		
		
		return true;
	}

	private static void updateDomains(Hashtable<Integer, EnhancedPerson> solution,PriorityQueue<EnhancedPerson> q,int startIndex) 
	{
		Queue<EnhancedPerson> copy = new LinkedList<EnhancedPerson>();
		while(!q.isEmpty())
		{
			EnhancedPerson temp = q.poll();
			copy.add(temp);
			solution.put(startIndex, temp);
		//	for(String i:temp.colors.colors)
			for(int i=0;i<temp.colors.colors.size();i++)
			{
				temp.color = temp.colors.colors.get(i);
				if(!consistencyCheck(solution))
				{
					temp.colors.colors.remove(i);
					i--;
					continue;
				}
		//		for(String j: temp.drinks.drinks)
				for(int j=0;j<temp.drinks.drinks.size();j++)
				{
					temp.drink = temp.drinks.drinks.get(j);
					if(!consistencyCheck(solution))
					{
						temp.drinks.drinks.remove(j);
						j--;
						continue;
					}
			//		for(String k : temp.foods.foods)
					for(int k=0;k<temp.foods.foods.size();k++)
					{
						temp.food = temp.foods.foods.get(k);
						if(!consistencyCheck(solution))
						{
							temp.foods.foods.remove(k);
							k--;
							continue;
						}
						
			//			for( String l : temp.nationalities.nationalities)
						for(int l=0;l<temp.nationalities.nationalities.size();l++)
						{
							temp.nationality = temp.nationalities.nationalities.get(l);
							if(!consistencyCheck(solution))
							{
								temp.nationalities.nationalities.remove(l);
								l--;
								continue;
							}
			//				for(String m : temp.pets.pets)
							for(int m=0; m<temp.pets.pets.size();m++)		
							{
								temp.pet = temp.pets.pets.get(m);
								if(!consistencyCheck(solution))
								{
									temp.pets.pets.remove(m);
									m--;
									continue;
								}
			//					for(String n: temp.positions.positions)
								for(int n=0;n<temp.positions.positions.size();n++)
								{
									temp.position = temp.positions.positions.get(n);
									if(!consistencyCheck(solution))
									{
										temp.positions.positions.remove(n);
										n--;
									}
								}
							}
						}
					}
						
				}
			}	
		}
		
		while(!copy.isEmpty())
		{
			q.add(copy.poll());
		}
		
		solution.remove(startIndex);		
	}
	
	
	
	private static boolean consistencyCheck(Hashtable<Integer, EnhancedPerson> solution) 
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
	private static boolean nodalConsistency(Hashtable<Integer, EnhancedPerson> solution) 
	{
		for(int i=1;i<=solution.size();i++)
		{
			EnhancedPerson p = solution.get(i);
			
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
	private static boolean kitKatsEatenInHouseNextToHorseKept(Hashtable<Integer, EnhancedPerson> solution, EnhancedPerson p) {
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
	private static boolean manWhoEatsHersheyLivesNextToManWithFox(Hashtable<Integer, EnhancedPerson> solution, EnhancedPerson p) 
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


	private static boolean greenHouseIsImmediatelyToTheRightOfIvoryHouse(Hashtable<Integer, EnhancedPerson> solution, EnhancedPerson p) 
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


	private static boolean norwegianLivesNextToTheBLueHouse(Hashtable<Integer, EnhancedPerson> solution, EnhancedPerson p) {
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
	private static boolean isDuplication(Hashtable<Integer, EnhancedPerson> solution) 
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
	
	private static void nullify(Hashtable<Integer, EnhancedPerson> solution, int startIndex) 
	{
		for(int i =startIndex;i<=solution.size();i++)
		{
			if(i>0)
			{
			EnhancedPerson p = solution.get(i);
			p.nullify();
			}
		}
	}

}
