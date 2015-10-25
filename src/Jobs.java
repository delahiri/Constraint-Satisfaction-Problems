import java.util.HashSet;


public class Jobs {
	
	public static final String BOXER = "boxer";
	public static final String ACTOR = "actor";
	public static final String TEACHER = "teacher";
	public static final String POLICE_OFFICER = "police officer";
	public static final String CLERK = "clerk";
	public static final String NURSE = "nurse";
	public static final String GUARD = "guard";
	public static final String CHEF = "chef";
	HashSet<String> jobs;
	
	
	
	public Jobs()
	{
		jobs = new HashSet<String>();
		jobs.add(CHEF);
		jobs.add(GUARD);
		jobs.add(NURSE);
		jobs.add(CLERK);
		jobs.add(POLICE_OFFICER);
		jobs.add(TEACHER);
		jobs.add(ACTOR);
		jobs.add(BOXER);
	}	
	
	
	public int size()
	{
		return this.jobs.size();
	}	

}
