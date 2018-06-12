package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;
import msms.comp3350.persistence.DataAccessor;

public class AccessWatchedEvents
{
    private DataAccessor dataAccess;

    public AccessWatchedEvents()
    {
        dataAccess = Services.getDataAccess();
    }

    public String getEvents(ArrayList<WatchedEvent> eventList)
    {
        eventList.clear();
        return dataAccess.getAllWatchedEvents(eventList);
    }

    public String getMoviesUsers(ArrayList<WatchedEvent> currentMovieViews, Movie curr)
    {
        currentMovieViews.clear();
        return dataAccess.getMovieViews(currentMovieViews, curr);
    }

    public String getUsersMovies(ArrayList<WatchedEvent> currentUserViews, User curr)
    {
        currentUserViews.clear();
        return dataAccess.getUserViews(currentUserViews, curr);
    }
}
