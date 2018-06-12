package msms.comp3350.business;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.DataAccessor;

public class AccessMovies
{
    private DataAccessor dataAccess;
    private static boolean currSorted = false;
    private static SortEnums.MovieSortField currField = null;
    private static boolean currAscending = true;


    public static final String[] CATEGORIES =
    {
            "None", "Action", "Family", "Comedy", "Drama", "Fantasy", "Horror", "Sci-Fi",
            "Recent", "Trending"
    };

    public AccessMovies()
    {
        dataAccess = Services.getDataAccess();
    }

    public void cancelSort()
    {
        currSorted = false;
        currField = null;
        currAscending = true;
    }

    public String getMovies(ArrayList<Movie> movies)
    {
        movies.clear();
        if (currSorted)
        {
            return getSortedMovies(movies, currField, currAscending);
        }
        return dataAccess.getMoviesAll(movies);
    }

    public String getMovies(ArrayList<Movie> movies, User user)
    {
        movies.clear();
        return dataAccess.getMovieSublist(movies, user);
    }

    public String getSortedMovies(ArrayList<Movie> movies, SortEnums.MovieSortField sortBy, boolean ascending)
    {
        movies.clear();
        currSorted = true;
        currField = sortBy;
        currAscending = ascending;
        return dataAccess.getMoviesAllSorted(movies, currField, currAscending);
    }

    public String insertMovie(Movie currentMovie) {
        return dataAccess.insertMovie(currentMovie);
    }

    public String updateMovie(Movie currentMovie) {
        return dataAccess.updateMovie(currentMovie);
    }

    public String deleteMovie(Movie currentMovie) {
        return dataAccess.deleteMovie(currentMovie);
    }

    public ArrayList<Movie> searchMovie(String targetStr)
    {
        /* Searches all movies, and returns an ArrayList<Movie> referencing movies whose titles contain
        * targetStr (partially or fully; case insensitive).
        * returns null if targetstr is null, and returns an empty arraylist if it isnt found.*/
        ArrayList<Movie> results = null;
        if (targetStr != null)
        {
            targetStr = targetStr.trim();
            results = new ArrayList<>();
            ArrayList<Movie> allMovies = new ArrayList<>();
            dataAccess.getMoviesAll(allMovies);

            // Add movies whose titles contain targetStr:
            if (!targetStr.isEmpty())
            {
                for (Movie currMovie : allMovies)
                {
                    String currMovieTitle = currMovie.getTitle().toLowerCase();
                    if (currMovieTitle.contains(targetStr.toLowerCase()))
                        results.add(currMovie);// Shallow copy
                }
            }
        }
        return results;
    }

    public static boolean mIDUnique(int mID)// Checks that mID is unique
    {
        ArrayList<Movie> allMovies = new ArrayList<Movie>();
        DataAccessor accessor = Services.getDataAccess();
        accessor.getMoviesAll(allMovies);
        for(Movie currMovie : allMovies)
        {
            if (currMovie.getmID() == mID)// mID already exists
            {
                return false;
            }
        }
        return true;// mID is unique
    }


    public static String validateMovie(String title, int releaseYear, String category, Calendar expDate, String description)
    /* Checks that user input is safe; if not, an error is returned as a String
     * Note that mID is not checked here: it only
     */
    {
        int expYear = expDate.get(Calendar.YEAR);
        int expMonth = expDate.get(Calendar.MONTH);
        int expDay = expDate.get(Calendar.DAY_OF_MONTH);

        String errorString = null;

        // error checking
        if(null == title || title.equals(""))
        {
            errorString = "You need to name your movie.";
        }

        if (expYear <= Calendar.getInstance().get(Calendar.YEAR))
        {
            if (expYear < Calendar.getInstance().get(Calendar.YEAR))
            {
                errorString = "Invalid date entry. Can't enter movie with expired rights";
            }
            else if (expYear == Calendar.getInstance().get(Calendar.YEAR) && expMonth <= Calendar.getInstance().get(Calendar.MONTH))
            {
                if (expMonth < Calendar.getInstance().get(Calendar.MONTH))
                {
                    errorString = "Invalid date entry. Can't enter movie with expired rights";
                }
                else if (expMonth == Calendar.getInstance().get(Calendar.MONTH) && expDay <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                {
                    errorString = "Invalid date entry. Can't enter movie with expired rights";
                }
            }

        }
        else if (expYear > Calendar.getInstance().get(Calendar.YEAR) + 5)
        {
            errorString = "Invalid date entry. Can't acquire movie rights beyond 5 years.";
        }

        if(category == null || category.equals(CATEGORIES[0]))
        {
            errorString = "You need to select a category.";
        }

        if (releaseYear < 1900)
        {
            errorString = "Invalid year entry. Movies did not exist during this time.";
        }
        else if (releaseYear > Calendar.getInstance().get(Calendar.YEAR))
        {
            errorString = "Invalid year entry. Can't add movies from beyond current year.";
        }

        if(null == description || description.equals(""))
        {
            errorString = "You need to enter a description.";
        }

        return errorString;
    }
}