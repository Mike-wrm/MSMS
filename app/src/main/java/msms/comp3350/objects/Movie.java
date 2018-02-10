package msms.comp3350.objects;

import java.util.ArrayList;
import java.util.Calendar;

public class Movie
{
	//Implementation of genres for now...
<<<<<<< HEAD
=======
	enum Category
	{
	    COMEDY, DRAMA, HORROR, ACTION, FANTASY, FAMILY, RECENT, TRENDING
	}

	private int mID;
>>>>>>> objectV2.1
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
	public String getEnd()
    {
        return endMonth + "/" + endDay + "/" + endYear;
    }


	public String getCategory()
    {
        if (cat1 == Category.COMEDY)
        {
            return "comedy";
        }
        else if (cat1 == Category.DRAMA)
        {
            return "drama";
        }
        else if (cat1 == Category.HORROR)
        {
            return "horror";
        }
        else if (cat1 == Category.ACTION)
        {
            return "action";
        }
        else if (cat1 == Category.FANTASY)
        {
            return "fantasy";
        }
        else if (cat1 == Category.FAMILY)
        {
            return "family";
        }
        else if (cat1 == Category.RECENT)
        {
            return "recent";
        }
        else if (cat1 == Category.TRENDING)
        {
            return "trending";
        }

        return null;
	}

	private Category getCategory(String test)
	{
		if (test.equalsIgnoreCase("comedy"))
		{
			return Category.COMEDY;
		}
		else if (test.equalsIgnoreCase("drama"))
		{
			return Category.DRAMA;
		}
		else if (test.equalsIgnoreCase("horror"))
		{
			return Category.HORROR;
		}
		else if (test.equalsIgnoreCase("action"))
		{
			return Category.ACTION;
		}
		else if (test.equalsIgnoreCase("fantasy"))
		{
			return Category.FANTASY;
		}
		else if (test.equalsIgnoreCase("family"))
		{
			return Category.FAMILY;
		}
		else if (test.equalsIgnoreCase("recent"))
		{
			return Category.RECENT;
		}
		else if (test.equalsIgnoreCase("trending"))
		{
			return Category.TRENDING;
		}

		return null;
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
		System.out.println("User Score: " + userScore + "\tCategory: ");
		System.out.println("Description: " + description + "\n");
		System.out.println();
	}
}
