package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.persistence.DataAccessor;

public class AccessMovies
{
    private DataAccessor dataAccess;
    private static boolean currSorted = false;
    private static String currField = null;
    private static boolean currAscending = true;


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
    public String getSortedMovies(ArrayList<Movie> movies, String sortBy, boolean ascending)
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
}