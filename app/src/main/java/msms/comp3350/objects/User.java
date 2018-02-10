package msms.comp3350.objects;

import java.util.Calendar;

public class User
{
	enum Gender 
	{
	    MALE, FEMALE
	}

	private int uID;
	private String name;
	private String password;
	private int age;
	private Gender userGender;
	private Calendar endDate;


	public User (int uID, String name, String password, int age, String gender, int endMonth, int endDay, int endYear)
	{
		this.uID = uID;
		this.name = name;
		this.password = password;	// this is clearly just an abstraction
		this.age = age;
		this.userGender = getGender(gender);
		this.endDate = endDate;
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
        if (userGender == Gender.MALE)
        {

            return "male";
        }
        else
		{
            return "female";
        }
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
