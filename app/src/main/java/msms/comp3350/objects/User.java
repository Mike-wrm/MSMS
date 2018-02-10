package msms.comp3350.objects;

import java.util.Calendar;

public class User
{

	private int uID;
	private String name;
	private String password;
	private int age;
	private String userGender;
	private Calendar endDate;


	public User (int uID, String name, String password, int age, String gender, Calendar endDate)
	{
		this.uID = uID;
		this.name = name;
		this.password = password;	// this is clearly just an abstraction
		this.age = age;
		this.userGender = gender;
		this.endDate = endDate;
	}

	public int getuID()
	{
		return uID;
	}

	public String getName()
	{
		return name;
	}

	public String getPass()
	{
		return password;
	}

	public int getAge()
	{
		return age;
	}

	public Calendar getEndDate()
	{
		return endDate;
	}

    public String getGender()
	{
  		return userGender;
    }

    public boolean equals (Object object)
	{
		User test;

		if (object instanceof User)
		{
			test = (User) object;

			if (test.getuID() == uID)
			{
				if ((test.getName()).equals(name))
				{
					return true;
				}
			}
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
		System.out.println("Subscription expires: " + endDate);
		System.out.println();
	}
}
