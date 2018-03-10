package msms.comp3350.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Movie implements Serializable
{
	private static int currID = 1;
	private int mID;
	private String title;
	private int releaseYear;
	private int userScore;
	private ArrayList<String> category = new ArrayList<>();
	private String category1;
	private String category2;
	private Calendar endDate;
	private int endMonth;
	private int endDay;
	private int endYear;
	private String description;

	public Movie (String title, int releaseYear, int userScore, ArrayList<String> category, Calendar endDate, String description)
	{
		mID = currID++;
		this.title = title;
		this.releaseYear = releaseYear;
		this.userScore = userScore;
		this.category = category;
		processCategories();
		this.endDate = endDate;
        processDate();
		this.description = description;
	}

    public Movie (int mID, String title, int releaseYear, int userScore, ArrayList<String> category, Calendar endDate, String description)
    {
        this.mID = mID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.userScore = userScore;
        this.category = category;
        processCategories();
        this.endDate = endDate;
        processDate();
        this.description = description;
    }

	public void processCategories ()
    {
        if (category != null)
        {
            category1 = category.get(0);
            if (category.size() == 2)
            {
                category2 = category.get(1);
            }
        }
    }

    public void processDate()
    {
        endYear = endDate.get(Calendar.YEAR);
        endMonth = (endDate.get(Calendar.MONTH)) + 1;  //January = 0 in calendar
        endDay = endDate.get(Calendar.DAY_OF_MONTH);
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
	public String getCategory1()
    {
	    return category1;
    }
    public String getCategory2()
    {
        return category2;
    }
	public Calendar getEndDate()
	{
		return endDate;
	}
	public int getEndMonth()
    {
        return endMonth;
    }
    public int getEndDay()
    {
        return endDay;
    }
    public int getEndYear()
    {
        return endYear;
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
	public void setCategory(ArrayList<String> category)
	{
	    this.category = category;
	    processCategories();
	}
	public void setEndDate(Calendar endDate)
	{
		this.endDate = endDate;
		processDate();
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
