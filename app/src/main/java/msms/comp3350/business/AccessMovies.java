package msms.comp3350.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.persistence.DataAccessor;

public class AccessMovies
{
    private DataAccessor dataAccess;
    private static boolean currSorted = false;
    private static SortEnums.MovieSortField currField = null;
    private static boolean currAscending = true;

    public static final String[] SCORES =
            {
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
            };

    public static final String[] CATEGORIES =
            {
                    "None", "Action", "Childrens", "Comedy", "Drama", "Fantasy", "Horror", "Sci-Fi",
                    "Recent", "Trending"
            };

    public AccessMovies()
    {
        dataAccess = Services.getDataAccess();
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

    //TODO create something in MovieDisplay/ListActivity to allow for toggle of sort
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

    public static String validateMovie(String title, int releaseYear, int userScore, ArrayList<String> category, Calendar expDate, String description)
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
        if(null == description || description.equals(""))
        {
            errorString = "You need to enter a description.";
        }

        System.out.println("test: " + userScore);
        if(!Arrays.asList(SCORES).contains(Integer.toString(userScore)))
        {
            errorString = "Invalid user score entered.";
        }

        boolean atLeastOneCategory = false;
        for (int i = 0; i < category.size(); i++)
        {
            if (category.get(i) != null && !category.get(i).equals(CATEGORIES[0]))
            {
                atLeastOneCategory = true;
            }
        }
        if(!atLeastOneCategory)
        {
            errorString = "Invalid category entry. Enter at least one category.";
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

        if (releaseYear < 1900)
        {
            errorString = "Invalid year entry. Movies did not exist during this time.";
        }
        else if (releaseYear > Calendar.getInstance().get(Calendar.YEAR))
        {
            errorString = "Invalid year entry. Can't add movies from beyond current year.";
        }

        return errorString;
    }
}