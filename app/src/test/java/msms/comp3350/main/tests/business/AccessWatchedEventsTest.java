package msms.comp.main.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import msms.comp.application.Services;
import msms.comp.business.AccessWatchedEvents;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.Movie;
import msms.comp.objects.User;
import msms.comp.objects.WatchedEvent;
import msms.comp.persistence.DataAccessor;

public class AccessWatchedEventsTest extends TestCase{

    private DataAccessor testData;
    private AccessWatchedEvents list;

    public AccessWatchedEventsTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        TempData newData = new TempData();
        testData = Services.createDataAccess(newData);
        testData.open("temp");
        list = new AccessWatchedEvents();
    }

    public void tearDown()
    {
        testData.close();
        System.out.println("Finished an AccessWatchedEvent Test");
    }

    public void testWatchedEventsAccessMovies()
    {
        ArrayList<WatchedEvent> watched = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();

        Movie movie;
        WatchedEvent watch;

        System.out.println("\nStarting an AccessWatchedEvent Accessing Movie Test");

        testData.getMoviesAll(movies);

        // Check the first movie
        movie = movies.get(0);
        list.getMoviesUsers(watched, movie);
        assertEquals(2, watched.size());

        // Check the second movie
        movie = movies.get(1);
        list.getMoviesUsers(watched, movie);
        assertEquals(0, watched.size());

        // Check the third movie
        movie = movies.get(2);
        list.getMoviesUsers(watched, movie);
        assertEquals(1, watched.size());

        // Check the fourth Movie
        movie = movies.get(3);
        list.getMoviesUsers(watched, movie);
        assertEquals(1, watched.size());

        // Check the fifth movie
        movie = movies.get(4);
        list.getMoviesUsers(watched, movie);
        assertEquals(0,watched.size());

        // Check the sixth movie
        movie = movies.get(5);
        list.getMoviesUsers(watched, movie);
        assertEquals(2, watched.size());

        // Check the seventh movie
        movie = movies.get(6);
        list.getMoviesUsers(watched, movie);
        assertEquals(1, watched.size());

        // Check the eighth movie
        movie = movies.get(7);
        list.getMoviesUsers(watched, movie);
        assertEquals(1, watched.size());

        // Check the last movie
        movie =  movies.get(8);
        list.getMoviesUsers(watched, movie);
        assertEquals(2, watched.size());
    }

    public void testWatchedEventsAccessUsers()
    {
        ArrayList<WatchedEvent> watched = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        User user;
        WatchedEvent watch;

        System.out.println("\nStarting an AccessWatchedEvent Accessing Movie Test");

        testData.getUsersAll(users);

        // Check the first user
        user = users.get(0);
        list.getUsersMovies(watched, user);
        assertEquals(2, watched.size());

        // Check the second user
        user = users.get(1);
        list.getUsersMovies(watched, user);
        assertEquals(2, watched.size());

        // Check the third user
        user = users.get(2);
        list.getUsersMovies(watched, user);
        assertEquals(2, watched.size());

        // Check the fourth user
        user = users.get(3);
        list.getUsersMovies(watched, user);
        assertEquals(0, watched.size());

        // Check the fifth user
        user = users.get(4);
        list.getUsersMovies(watched, user);
        assertEquals(2, watched.size());

        // Check the sixth user
        user = users.get(5);
        list.getUsersMovies(watched, user);
        assertEquals(2, watched.size());
    }
}
