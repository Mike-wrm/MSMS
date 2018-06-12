package msms.comp.main.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Main;
import msms.comp.application.Services;
import msms.comp.business.AccessMovies;
import msms.comp.business.AccessUsers;
import msms.comp.business.AccessWatchedEvents;
import msms.comp.business.SortEnums;
import msms.comp.objects.Movie;
import msms.comp.objects.User;
import msms.comp.objects.WatchedEvent;

public class BusinessPersistenceSeamTest extends TestCase {

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessUser()
    {
        AccessUsers au;
        ArrayList<User> users = new ArrayList<User>();
        User user;
        User newUser;
        Movie movie;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration Test of AccessUsers to persistence");

        Services.createDataAccess(Main.dbName);

        au = new AccessUsers();

        result = au.getUsers(users);
        assertNull(result);
        assertEquals(6, users.size());
        user = users.get(0);
        assertEquals(111, user.getuID());

        movie = new Movie(222,"Eddie Murphy: Raw", 1987,"Comedy", Calendar.getInstance(), "Ipsum Lorem...");

        result = au.getUsers(users, movie);
        assertNull(result);
        assertEquals(0, users.size());

        result = au.getSortedUsers(users, SortEnums.UserSortField.USERNAME, true);
        assertNull(result);
        assertEquals(6, users.size());
        user = users.get(0);
        assertEquals("Andrew_Sempai", user.getName()); // Make sure its alphabetical order

        result = au.getSortedUsers(users, SortEnums.UserSortField.USERNAME, false);
        assertNull(result);
        assertEquals(6, users.size());
        user = users.get(0);
        assertEquals("Wonder_Woman", user.getName());

        newUser = new User(1, "newUser", "newPass", 22, 'M', Calendar.getInstance());

        result = au.insertUser(newUser);
        assertNull(result);
        au.getUsers(users);
        assertEquals(7, users.size());

        newUser.setPass("newnewPass");
        result = au.updateUser(newUser);
        assertNull(result);
        au.cancelSort();
        au.getUsers(users);
        user = users.get(0);
        assertEquals("newnewPass", user.getPass());

        result = au.deleteUser(newUser);
        assertNull(result);
        au.getUsers(users);
        assertEquals(6, users.size());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessStudents to persistence");
        }

    public void testAccessMovie()
    {
        AccessMovies am;
        ArrayList<Movie> movies;
        Movie movie;
        User user;
        Movie newMovie;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration Test of AccessMovies to persistence");

        Services.createDataAccess(Main.dbName);

        movies = new ArrayList<>();
        am = new AccessMovies();

        result = am.getMovies(movies);
        assertNull(result);
        assertEquals(9, movies.size());
        movie = movies.get(0);
        assertEquals(111, movie.getmID());

        user = new User(111, "Miggles", "anime4life", 21, 'M', Calendar.getInstance());
        result = am.getMovies(movies, user);
        assertNull(result);
        assertEquals(2, movies.size());

        result = am.getSortedMovies(movies, SortEnums.MovieSortField.TITLE, true);
        assertNull(result);
        assertEquals(9, movies.size());
        movie = movies.get(0);
        assertEquals("Eddie Murphy: Raw", movie.getTitle());

        result = am.getSortedMovies(movies, SortEnums.MovieSortField.TITLE, false);
        assertNull(result);
        assertEquals(9, movies.size());
        movie = movies.get(0);
        assertEquals("Transformers: The Last Knight", movie.getTitle());

        newMovie = new Movie(1, "newMovie", 1989, "Comedy", Calendar.getInstance(), "new description");

        result = am.insertMovie(newMovie);
        assertNull(result);
        am.getMovies(movies);
        assertEquals(10, movies.size());

        newMovie.setDescription("new new description");
        result = am.updateMovie(newMovie);
        assertNull(result);
        am.cancelSort();
        am.getMovies(movies);
        movie = movies.get(0);
        assertEquals("new new description", movie.getDescription());

        result = am.deleteMovie(newMovie);
        assertNull(result);
        am.getMovies(movies);
        assertEquals(9, movies.size());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessMovies to persistence");
    }

    public void testAccessWatchedEvents()
    {
        AccessWatchedEvents awe;
        ArrayList<WatchedEvent> events;
        Movie movie;
        User user;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration Test of AccessMovies to persistence");

        Services.createDataAccess(Main.dbName);

        awe = new AccessWatchedEvents();
        events = new ArrayList<WatchedEvent>();

        result = awe.getEvents(events);
        assertNull(result);
        assertEquals(10, events.size());

        movie = new Movie(111, "South Park: Bigger, Longer & Uncut", 1999,"Comedy", Calendar.getInstance(), "Ipsum Lorem...");

        result = awe.getMoviesUsers(events, movie);
        assertNull(result);
        assertEquals(2, events.size());

        user = new User(111, "Miggles", "anime4life", 21, 'M', Calendar.getInstance());

        result = awe.getUsersMovies(events, user);
        assertNull(result);
        assertEquals(2, events.size());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessMovies to persistence");
    }
}
