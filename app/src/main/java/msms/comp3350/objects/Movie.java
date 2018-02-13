package msms.comp3350.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;

public class Movie implements Parcelable
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

	// Parcelable implementation code
	protected Movie(Parcel in)
	{
		mID = in.readInt();
		title = in.readString();
		releaseYear = in.readInt();
		userScore = in.readInt();
		in.readStringList(category);
		endDate = (Calendar) in.readValue(Calendar.class.getClassLoader());
		description = in.readString();
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(mID);
		dest.writeString(title);
		dest.writeInt(releaseYear);
		dest.writeInt(userScore);
		dest.writeStringList(category);
		dest.writeValue(endDate);
		dest.writeString(description);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>()
	{
		@Override
		public Movie createFromParcel(Parcel in)
		{
			return new Movie(in);
		}

		@Override
		public Movie[] newArray(int size)
		{
			return new Movie[size];
		}
	};
}
