package msms.comp3350.objects;

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
	private int endYear;
	private int endMonth;
	private int endDay;
	
	//Constructor
	public User (String name, String password, int age, String gender, int endMonth, int endDay, int endYear)
	{
		this.name = name;
		this.password = password;
		this.age = age;
		this.userGender = getGender(gender);
		this.endMonth = endMonth;
		this.endDay = endDay;
		this.endYear = endYear;
	}
	
	private Gender getGender(String test)
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

	public String getName()     {return name;}
	public String getPass()     {return password;}
	public int getAge()         {return age;}
	public Gender getGender()   {return userGender;}
	public String getEndDate()  {return endMonth + "/" + endDay + "/" + endYear;}


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
		System.out.println("Age: " + age + "\t" +"Gender: " + userGender);
		System.out.println("Password: " + password);
		System.out.println("Subscription expires: " + endMonth + "/" + endDay + "/" + endYear);
		System.out.println();
	}
}
