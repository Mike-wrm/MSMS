package msms.comp3350.objects;

import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable
{
	private int uID;
	private String userName;
	private String password;
	private int age;
	private char gender;
	private Calendar endDate;


	public User (int uID, String userName, String password, int age, char gender, Calendar endDate)
	{
		this.uID = uID;
		this.userName = userName;
		this.password = password;
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
		return userName;
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
    public char getGender()
	{
  		return gender;
    }

    //setters
	public void setuID(int uID)
	{
		this.uID = uID;
	}
	public void setName(String userName)
	{
		this.userName = userName;
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
	public void setGender(char gender){
		this.gender = gender;
	}

	public boolean equals (Object object)
	{
		User test;
		boolean returnValue = false;

		if (object instanceof User)
		{
			test = (User) object;

			if (test.getuID() == uID)
			{
                    returnValue = true;
			}
		}
		return returnValue;
	}
}
