package msms.comp3350.persistence;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;

public interface DataAccessor
{
    void open(String dbName);
    void close();

    String getMoviesAll(ArrayList<Movie> currentMovies);
    String insertMovie(Movie currentMovie);
    String updateMovie(Movie currentMovie);
    String deleteMovie(Movie currentMovie);

    String getUsersAll(ArrayList<User> currentUsers);
    String insertUser(User currentUser);
    String updateUser(User currentUser);
    String deleteUser(User currentUser);
}
