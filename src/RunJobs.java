import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class RunJobs {

	private static final String PETE = "Pete";
	private static final String STEVE = "Steve";
	private static final String THELMA = "Thelma";
	private static final String ROBERTA = "Roberta";
	private static final Jobs ALL_JOBS = new Jobs();

	public static void main(String[] args) {
		Map<Integer, Person> solution = new HashMap<Integer, Person>();
		
		Person Roberta = new Person(ROBERTA,Gender.FEMALE);
		Person Thelma = new Person(THELMA,Gender.FEMALE);
		Person Steve = new Person(STEVE,Gender.MALE);
		Person Pete = new Person(PETE,Gender.MALE);
		
		
		solution.put(1, Roberta);
		solution.put(2, Thelma);
		solution.put(3, Steve);
		solution.put(4, Pete);
		
		if(!solve(solution,1,new HashSet<String>()))
		{
			System.out.println("No Solution");
		}
		else
		{
		
			for(Map.Entry<Integer, Person> entry : solution.entrySet())
			{
				Person person = entry.getValue();
				System.out.println(person.name + " is a "+person.job1+" and a "+person.job2);
			}
		}
	}

	/**
	 * Solve and return true if successful
	 * @param solution
	 * @param startIndex
	 * @return
	 */
	private static boolean solve(Map<Integer, Person> solution, int startIndex, HashSet<String> alreadyTaken) 
	{
		if(startIndex > solution.size())
		{
			return true;
		}
		else
		{
			Person current = solution.get(startIndex);
			for(String i : ALL_JOBS.jobs)
			{
				for(String j : ALL_JOBS.jobs)
				{
					if(i!=j)
					{	
						current.job1 = i;
						current.job2 = j;
						if(alreadyTaken.contains(current.job1) || alreadyTaken.contains(current.job2))
						{
							return false;
						}
						else if(consistencyCheck(solution) && solve(solution,startIndex+1,alreadyTaken))
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
			
			return false;	
		}
		
	}

	/**
	 * Backtrack and nullify from startIndex
	 * @param solution
	 * @param startIndex
	 */
	private static void nullify(Map<Integer, Person> solution, int startIndex) 
	{
		for(int i =startIndex;i<=solution.size();i++)
		{
			Person p = solution.get(i);
			p.nullify();
		}
	}

	/**
	 * check consistency
	 * @param solution
	 * @return
	 */
	private static boolean consistencyCheck(Map<Integer, Person> solution) 
	{
		if(isJobDuplication(solution) || !nodalConsistency(solution))
		{
			return false;
		}
		return true;
	}

	/**
	 * Check nodal consistency
	 * @param solution
	 * @return
	 */
	private static boolean nodalConsistency(Map<Integer, Person> solution) {

		for(int i=1;i<=solution.size();i++)
		{
			Person person = solution.get(i);
			if(person.job1 != null || person.job2 == null)
			{
				// Job of nurse is held by male
				if(person.gender==Gender.FEMALE && (person.job1 == Jobs.NURSE || person.job2 == Jobs.NURSE))
				{
					return false;
				}
				
				// Husband of the chef is a clerk(i.e clerk is male and chef is female)
				// Clerk is a male
				if(person.gender == Gender.FEMALE && (person.job1 == Jobs.CLERK || person.job2 == Jobs.CLERK))
				{
					return false;
				}
			
				// Chef is a female
				if(person.gender == Gender.MALE && (person.job1 == Jobs.CHEF || person.job2 == Jobs.CHEF))
				{
					return false;
				}
				
				// Roberta is not a boxer
				if(person.name == ROBERTA && (person.job1 == Jobs.BOXER || person.job2 == Jobs.BOXER))
				{
					return false;
				}
				
				// Pete cannot be a nurse, teacher or police officer
				if(person.name == PETE)
				{
					if(person.job1 == Jobs.NURSE || person.job2 == Jobs.NURSE)
					{
						return false;
					}
					if(person.job1 == Jobs.TEACHER || person.job2 == Jobs.TEACHER)
					{
						return false;
					}
					if(person.job1 == Jobs.POLICE_OFFICER || person.job2 == Jobs.POLICE_OFFICER)
					{
						return false;
					}
				}
				
				// Roberta, the chef, and the police officer went golfing together
				if(person.name == ROBERTA)
				{
					if(person.job1 == Jobs.CHEF || person.job2 == Jobs.CHEF)
					{
						return false;
					}
					if(person.job1 == Jobs.POLICE_OFFICER || person.job2 == Jobs.POLICE_OFFICER)
					{
						return false;
					}
				}
				
				// The chef and police officer are not the same person
				if(person.job1 == Jobs.CHEF && person.job2 == Jobs.POLICE_OFFICER)
				{
					return false;
				}
				
				if(person.job2 == Jobs.CHEF && person.job1 == Jobs.POLICE_OFFICER)
				{
					return false;
				}	
				
				if(person.gender == Gender.FEMALE && (person.job1 == Jobs.ACTOR || person.job2 == Jobs.ACTOR))
				{
					return false;
				}
			}	
		}
		return true;
	}

	
	/**
	 * Check job duplication
	 * @param solution
	 * @return
	 */
	private static boolean isJobDuplication(Map<Integer, Person> solution) 
	{
		HashSet<String> jobsTaken = new HashSet<String>();
		for(int i = 1;i<=solution.size();i++)
		{
			
			Person p= solution.get(i);
			if(jobsTaken.contains(p.job1) || jobsTaken.contains(p.job2))
			{
				return true;
			}
			else
			{
				if(p.job1 != null)
				jobsTaken.add(p.job1);
				if(p.job2 != null)
				jobsTaken.add(p.job2);
			}
			
		}

		return false;
	}

}
