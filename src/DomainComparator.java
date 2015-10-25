import java.util.Comparator;


public class DomainComparator implements Comparator<EnhancedPerson>{

	@Override
	public int compare(EnhancedPerson o1, EnhancedPerson o2) {
		int domain1 = o1.colors.size() + o1.drinks.size() + o1.positions.size() + o1.pets.size() +o1.drinks.size() + o1.positions.size();
		int domain2 = o2.colors.size() + o2.drinks.size() + o2.positions.size() + o2.pets.size() +o2.drinks.size() + o2.positions.size();
		
		if (domain1 > domain2)
		{
			return 1;
		}
		else if(domain1 < domain2)
		{
			return -1;
		}
		else 
		{
			return 0;
		}
		
		
	}

}
