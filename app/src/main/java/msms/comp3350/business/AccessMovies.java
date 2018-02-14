package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.persistence.TempData;
import msms.comp3350.presentation.MainActivity;


public class AccessMovies
{
    private TempData dataAccess;

    public AccessMovies()
    {
        dataAccess = (TempData) Services.getDataAccess(MainActivity.dbName);
    }

    public String getMovies(ArrayList<Movie> movies)
    {
        movies.clear();
        return dataAccess.getMoviesAll(movies);
    }

    public String insertMovie(Movie currentMovie)
    {
        return dataAccess.insertMovie(currentMovie);
    }

    public String updateMovie(Movie currentMovie)
    {
        return dataAccess.updateMovie(currentMovie);
    }

    public String deleteMovie(Movie currentMovie)
    {
        return dataAccess.deleteMovie(currentMovie);
    }
}
