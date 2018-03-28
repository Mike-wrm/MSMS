package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;

public abstract class MovieCharts
{
    public static String[][] getMovieCategories()
    {
        AccessMovies access = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList();
        access.getMovies(movies);
        return getMovieCategories(movies);
    }

    public static String[][] getMovieCategories(User user)
    {
        AccessMovies access = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList();
        access.getMovies(movies, user);
        return getMovieCategories(movies);
    }

    private static String[][] getMovieCategories(ArrayList<Movie> movies)
    {
        int numMovies = movies.size();

        String[] categories = AccessMovies.CATEGORIES;
        int[] rawData = new int[categories.length];

        for(int i = 0 ; i < numMovies ; i++)
        {
            Movie currMovie = movies.get(i);
            String catList = currMovie.getCategory();

            for(int k = 1 ; k < categories.length ; k++)
            {
                if(catList != null)
                {
                    if (catList.equals(categories[k]))
                    {
                        rawData[k]++;
                        break;
                    }
                }
            }
        }

        String[] data = new String[rawData.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }

        return new String[][]{ categories, data };
    }

    public static String[][] getMovieDecades()
    {
        AccessMovies access = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList();
        access.getMovies(movies);
        return getMovieDecades(movies);
    }

    public static String[][] getMovieDecades(User user)
    {
        AccessMovies access = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList();
        access.getMovies(movies, user);
        return getMovieDecades(movies);
    }

    private static String[][] getMovieDecades(ArrayList<Movie> movies)
    {
        int numMovies = movies.size();
        String[] categories = new String[] { "<", "50s", "60s", "70s", "80s", "90s", "00s", "10s", ">" };
        int[] rawData = new int[categories.length];

        for(int i = 0 ; i < numMovies ; i++)
        {
            Movie currMovie = movies.get(i);
            int year = currMovie.getReleaseYear();
            if(year < 1950) rawData[0]++;
            else if(year < 1960) rawData[1]++;
            else if(year < 1970) rawData[2]++;
            else if(year < 1980) rawData[3]++;
            else if(year < 1990) rawData[4]++;
            else if(year < 2000) rawData[5]++;
            else if(year < 2010) rawData[6]++;
            else if(year < 2020) rawData[7]++;
            else rawData[8]++;
        }

        String[] data = new String[rawData.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }

        return new String[][]{ categories, data };
    }

    public static String[][] getMovieRatings()
    {
        AccessWatchedEvents access = new AccessWatchedEvents();
        ArrayList<WatchedEvent> events = new ArrayList();
        access.getEvents(events);
        return getMovieRatings(events);
    }

    public static String[][] getMovieRatings(Movie movie)
    {
        AccessWatchedEvents access = new AccessWatchedEvents();
        ArrayList<WatchedEvent> events = new ArrayList();
        access.getMoviesUsers(events, movie);
        return getMovieRatings(events);
    }

    public static String[][] getMovieRatings(User user)
    {
        AccessWatchedEvents access = new AccessWatchedEvents();
        ArrayList<WatchedEvent> events = new ArrayList();
        access.getUsersMovies(events, user);
        return getMovieRatings(events);
    }

    private static String[][] getMovieRatings(ArrayList<WatchedEvent> events)
    {
        int numMovies = events.size();
        String[] categories = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "" };
        int[] rawData = new int[categories.length];

        for(int i = 0 ; i < numMovies ; i++)
        {
            WatchedEvent currEvent = events.get(i);
            rawData[currEvent.getRating()]++;
        }

        String[] data = new String[rawData.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }

        return new String[][]{ categories, data };
    }
}
