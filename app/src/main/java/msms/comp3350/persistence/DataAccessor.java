package msms.comp3350.persistence;

import java.util.ArrayList;

import msms.comp3350.business.SortEnums;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;

public interface DataAccessor
{
    void open(String dbName);
    void close();

    String getMoviesAll(ArrayList<Movie> currentMovies);
    String getMoviesAllSorted(ArrayList<Movie> currentMovies, SortEnums.MovieSortField sortBy, boolean ascending);
    String insertMovie(Movie currentMovie);
    String updateMovie(Movie currentMovie);
    String deleteMovie(Movie currentMovie);

    String getUsersAll(ArrayList<User> currentUsers);
    String getUsersAllSorted(ArrayList<User> currentUsers, SortEnums.UserSortField sortBy, boolean ascending);
    String insertUser(User currentUser);
    String updateUser(User currentUser);
    String deleteUser(User currentUser);

    String getAllWatchedEvents(ArrayList<WatchedEvent> allEvents);
    String getUserSublist(ArrayList<User> userSublist, Movie currentMovie);
    String getMovieSublist(ArrayList<Movie> movieSublist, User currentUser);
    String getMovieViews(ArrayList<WatchedEvent> currentMovieViews, Movie currentMovie);
    String getUserViews(ArrayList<WatchedEvent> currentUserViews, User currentUser);
    String insertWatchedEvent(WatchedEvent currEvent);
}
