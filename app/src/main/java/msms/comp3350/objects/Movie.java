package msms.comp3350.objects;

import java.util.ArrayList;
import java.util.Calendar;

public class Movie
{

	private int mID;
	private String title;
	private int releaseYear;
	private int userScore;
	private ArrayList<String> category = new ArrayList<>();
	private Calendar endDate;
	private String description;


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

	public Boolean compareTo(String test)
	{
		if (test.equalsIgnoreCase(title))
		{
			return true;
		}

		return false;
	}

	//Prints just the title (when clicking to see entire list of movies)
	public void printTitle()
	{
		System.out.println(title);
	}

	//Prints all movie details (when movie clicked on in UI)
	public void print()
	{
		printTitle();
		System.out.println("Released in " + releaseYear + "\tLicense Expires: " + endDate);
		System.out.println("User Score: " + userScore + "\tCategory: " + category);
		System.out.println("Description: " + description + "\n");
		System.out.println();
	}
}
