package msms.comp3350.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class User implements Parcelable
{

	private int uID;
	private String name;
	private String password;
	private int age;
	private char gender;
	private Calendar endDate;


	public User (int uID, String name, String password, int age, char gender, Calendar endDate)
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
    public char getGender()
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
	public void setGender(char gender){
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
		System.out.println("Age: " + age + "\t" +"Gender: " + gender);
		System.out.println("Password: " + password);
		System.out.println("Subscription expires: " + endDate);
		System.out.println();
	}

	// Code for implementing Parcelable
    protected User(Parcel in)
    {
        uID = in.readInt();
        name = in.readString();
        password = in.readString();
        age = in.readInt();
        gender = (char) in.readValue(char.class.getClassLoader());
        endDate = (Calendar) in.readValue(Calendar.class.getClassLoader());
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(uID);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeInt(age);
        dest.writeValue(gender);
        dest.writeValue(endDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
}
