package msms.comp3350.objects;

import java.util.ArrayList;
import java.util.Calendar;

public class Movie
{
	//Implementation of genres for now...
	private String title;
	private int releaseYear;
	private int userScore;
	private ArrayList<String> category = new ArrayList<>();
	private Calendar endDate;
	private String description;

	//Constructor 1 (only 1 category) --> need more for additional possibilities
	public Movie (String title, int releaseYear, int userScore, ArrayList<String> category, Calendar endDate, String description)
	{
		this.title = title;
		this.releaseYear = releaseYear;
		this.userScore = userScore;
		this.category = category;
		this.endDate = endDate;
		this.description = description;
	}

	public String getTitle(){return title;}
	public int getReleaseYear() {return releaseYear;}
	public int getUserScore() {return userScore;}
	public Calendar getEndDate() {return endDate;}
	public String getDescription() {return description;}
	public ArrayList<String> getCategory() {return category;}






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
		System.out.println("User Score: " + userScore + "\tCategory: ");
		System.out.println("Description: " + description + "\n");
		System.out.println();
	}
}
