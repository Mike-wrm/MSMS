package msms.comp3350.objects;

import java.util.Calendar;

public class User
{

	private int uID;
	private String name;
	private String password;
	private int age;
	private String gender;
	private Calendar endDate;


	public User (int uID, String name, String password, int age, String gender, Calendar endDate)
	{
		this.uID = uID;
		this.name = name;
		this.password = password;	// this is clearly just an abstraction
		this.age = age;
		this.gender = gender;
		this.endDate = endDate;
	}

	//getters
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
  		return gender;
    }

    //setters
	public void setuID(int uID)
	{
		this.uID = uID;
	}
	public void setName(String name)
	{
		this.name = name;
	}
    public void setPass(String password)
	{
		this.password = password;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setEndDate(Calendar endDate){
		this.endDate = endDate;
	}
	public void setGender(String gender){
		this.gender = gender;
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
