package msms.comp3350.objects;

public class WatchedEvent
{
    private int uID;
    private int mID;
    private String title;
    private String userName;
    private int rating;


    public WatchedEvent (int uID, int mID, String userName, String title, int rating)
    {
        this.uID = uID;
        this.mID = mID;
        this.userName = userName;
        this.title = title;
        this.rating = rating;
    }

    public int getuID()
    {
        return uID;
    }

    public int getmID()
    {
        return mID;
    }

    public String getMovieTitle()
    {
        return title;
    }

    public String getUserName()
    {
        return userName;
    }

    public int getRating()
    {
        return rating;
    }

    public void setMovieTitle(String title)
    {
        this.title = title;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String toString()
    {
        return userName + " viewed " + title + " and rated it " + rating + "/10.";
    }

    public boolean equals (Object object)
    {
        WatchedEvent test;
        boolean returnValue = false;

        if (object instanceof WatchedEvent)
        {
            test = (WatchedEvent) object;

            if ((test.getuID() == uID) && (test.getmID() == mID))
            {
                returnValue = true;
            }
        }
        return returnValue;
    }
}
