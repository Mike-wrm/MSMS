package msms.comp3350.objects;

public class Movie
{
	//Implementation of genres for now...
	enum Category
	{
	    COMEDY, DRAMA, HORROR, ACTION, FANTASY, FAMILY, RECENT, TRENDING
	}

	private int mID;
	private String title;
	private int releaseYear;
	private int userScore;
	private Category cat1;
	private Category cat2;
	private Category cat3;
	private int endYear;
	private int endMonth;
	private int endDay;
	private String description;

	//Constructor 1 (only 1 category) --> need more for additional possibilities
	public Movie (String title, int releaseYear, int userScore, String category1, int endMonth, int endDay, int endYear, String description)
	{
		this.title = title;
		this.releaseYear = releaseYear;
		this.userScore = userScore;
		this.cat1 = getCategory(category1);
		cat2 = cat3 = null;						//for now...
		this.endMonth = endMonth;
		this.endDay = endDay;
		this.endYear = endYear;
		this.description = description;
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

	public String getEnd()
    {
        return endMonth + "/" + endDay + "/" + endYear;
    }

	public String getDescription()
    {
        return description;
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


	public boolean equals (Object object)
	{
		Movie test;

		if (object instanceof Movie)
		{
			test = (Movie) object;

			if (test.getmID() == mID)
			{
				if ((test.getTitle()).equals(title))
				{
				    if ((test.getReleaseYear()) == releaseYear)
				    {
                        return true;
                    }
				}
			}
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
		System.out.println("Released in " + releaseYear + "\tLicense Expires: " + endMonth + "/" + endDay + "/" + endYear);
		System.out.println("User Score: " + userScore + "\tCategory: " + cat1);
		System.out.println("Description: " + description + "\n");
		System.out.println();
	}
}
