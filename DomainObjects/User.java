package DomainObjects;

public class User
{
	enum Gender 
	{
	    MALE, FEMALE
	}
	
	private String name;
	private String password;
	private int age;
	private Gender userGender;
	private int subscriptionEndDate;
	
	//Constructor
	public User (String name, String password, int age, String gender, int subscriptionEndDate)
	{
		this.name = name;
		this.password = password;
		this.age = age;
		this.userGender = getGender(gender);
		this.subscriptionEndDate = subscriptionEndDate;
	}
	
	public Gender getGender(String test)
	{
		if (test.equalsIgnoreCase("male"))
		{
			return Gender.MALE;
		}
		else if (test.equalsIgnoreCase("female"))
		{
			return Gender.FEMALE;
		}
	
		return null;
	}
	
	public Boolean compareTo(String test)
	{
		if (test.equalsIgnoreCase(name))
		{
			return true;
		}
		
		return false;
	}
	
	//Prints just the title (when clicking to see entire list of movies)
	public void printUserName()
	{
		System.out.println(name);
	}
	
	//Prints all movie details (when movie clicked on in UI)
	public void print()
	{
		printUserName();
		System.out.println("Age: " + age + "/t" +"Gender: " + userGender);
		System.out.println("Password: " + password);
		System.out.println("Subscription expries: " + subscriptionEndDate);
		System.out.println();
	}
}
