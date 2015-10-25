
enum Gender {
	MALE,FEMALE
}

public class Person 
{
	String name;
	String job1;
	String job2;
	Gender gender;
	
	
	// Problem 2 attributes
	String color;
	String drink;
	String nationality;
	String pet;
	String position;
	String food;
	
	public Person(String name,Gender gender)
	{
		this.name = name;
		this.job1 = null;
		this.job2 = null;
		this.gender = gender;

	}
	
	public Person()
	{
		// Initialize the domains here.
	}
	
	public void nullify()
	{
		this.job1 = null;
		this.job2 = null;
		this.color = null;
		this.drink = null;
		this.nationality = null;
		this.pet = null;
		this.position = null;
		this.food = null;

	}
	
	public void print()
	{
		System.out.println(this.nationality + " owns a " + this.pet
				+ " drinks " + this.drink + " eats " + this.food
				+ " stays in a " + this.color + " colored house"
				+ " which is positioned " + this.position
				+ " from left");
	}
	
}
