package msms.comp3350.objects;

import java.io.Serializable;
import java.util.Calendar;

public class Movie implements Serializable
{
	private int mID;
	private String title;
	private int releaseYear;
	private String category;
	private Calendar endDate;
	private int endMonth;
	private int endDay;
	private int endYear;
	private String description;

    public Movie (int mID, String title, int releaseYear, String category, Calendar endDate, String description)
    {
        this.mID = mID;
        this.title = title;
        this.releaseYear = releaseYear;
		this.category = category;
        this.endDate = endDate;
        processDate();
        this.description = description;
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
	public String getCategory()
    {
	    return category;
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
	public void setCategory(String category)
	{
	    this.category = category;
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
