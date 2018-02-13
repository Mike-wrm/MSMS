package msms.comp3350.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Movie implements Serializable
{
	private int mID;
	private String title;
	private int releaseYear;
	private int userScore;
	private ArrayList<String> category = new ArrayList<>();
	private Calendar endDate;
	private String description;

	private static String[] categories = {"None", "Action", "Children's", "Comedy", "Drama",
            "Fantasy", "Horror", "Sci-Fi", "Recent", "Trending"};

	public Movie (int mID, String title, int releaseYear, int userScore, ArrayList<String> category, Calendar endDate, String description)
	{
		this.mID = mID;
		this.title = title;
		this.releaseYear = releaseYear;
		this.userScore = userScore;
		this.category = category;
		this.endDate = endDate;
		this.description = description;
	}

	// getters
	public static String[] getCategories()
	{
		return categories;
	}

	public int getmID()
	{
		return mID;
	}
	public String getTitle()
	{
		return title;
	}
	public int getReleaseYear()
	{
		return releaseYear;
	}
	public int getUserScore()
	{
		return userScore;
	}
	public ArrayList<String> getCategory()
	{
		return category;
	}
	public Calendar getEndDate()
	{
		return endDate;
	}
	public String getDescription()
	{
		return description;
	}

	// setters
	public void setmID(int mID)
	{
		this.mID = mID;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setReleaseYear(int releaseYear)
	{
		this.releaseYear = releaseYear;
	}
	public void setUserScore(int userScore)
	{
		this.userScore = userScore;
	}
	public void setCategory(ArrayList<String> category){
		this.category = category;
	}
	public void setEndDate(Calendar endDate)
	{
		this.endDate = endDate;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean equals (Object object)
	{
		Movie test;
		boolean returnValue = false;

		if (object instanceof Movie)
		{
			test = (Movie) object;

			if (test.getmID() == mID)
			{
				returnValue = true;
			}
		}
		return returnValue;
	}
}
