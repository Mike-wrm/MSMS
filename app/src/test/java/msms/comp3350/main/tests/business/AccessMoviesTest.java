package msms.comp3350.main.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.business.AccessMovies;
import msms.comp3350.objects.Movie;

public class AccessMoviesTest extends TestCase
{
    public AccessMoviesTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessMovie()
    {
        Services.createDataAccess("temp");
        AccessMovies list = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList<>();

        list.getMovies(movies);

        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        ArrayList<String> familyCat = new ArrayList<>();
        familyCat.add("family");

        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);


        System.out.println("\nStarting testAccessMovie");

        Movie testMovie1 = new Movie("testMovie", 1999, 84, familyCat, endDate, "testing");
        Movie testMovie2 = new Movie("testMovie2", 1998, 84, familyCat, endDate, "testing");

        assertNull(list.insertMovie(testMovie1));
        assertEquals("'testMovie' is already added.", list.insertMovie(testMovie1));

        assertNull(list.updateMovie(testMovie1));
        assertEquals("'testMovie2' cannot be found.", list.updateMovie(testMovie2));

        assertNull(list.deleteMovie(testMovie1));
        assertEquals("'testMovie' cannot be found.", list.deleteMovie(testMovie1));

        list.insertMovie(testMovie1);
        list.insertMovie(testMovie2);

        assertNotNull(list.searchMovie("test"));
        assertEquals(2, list.searchMovie("test").size());
        assertEquals(0, list.searchMovie("noMovie").size());
        assertEquals(testMovie1, list.searchMovie("test").get(0));
        assertEquals(testMovie2, list.searchMovie("test").get(1));
        assertNull(list.searchMovie(null));
        assertEquals(0, list.searchMovie("").size());
        assertEquals(0, list.searchMovie(" ").size());
        assertEquals(0, list.searchMovie("                ").size());
        assertEquals(2, list.searchMovie(" test").size());
        assertEquals(2, list.searchMovie("test ").size());
        assertEquals(2, list.searchMovie(" test ").size());
        assertEquals(2, list.searchMovie("TEST").size());
        assertEquals(2, list.searchMovie("TeSt").size());
        assertEquals(2, list.searchMovie("estMovie").size());
        assertEquals(1, list.searchMovie("Movie2").size());
        assertEquals(6, list.searchMovie("a").size());
        assertEquals(0, list.searchMovie("tests").size());

        Calendar yearInPast = Calendar.getInstance();
        yearInPast.add(Calendar.YEAR, -1);

        Calendar monthInPast = Calendar.getInstance();
        monthInPast.add(Calendar.MONTH, -1);

        Calendar dayInFuture = Calendar.getInstance();
        dayInFuture.add(Calendar.DAY_OF_MONTH, 1);

        Calendar dayInPast = Calendar.getInstance();
        dayInPast.add(Calendar.DAY_OF_MONTH, -1);

        Calendar yearInFarFuture = Calendar.getInstance();
        yearInFarFuture.add(Calendar.YEAR, 20);

        ArrayList<String> badCat = new ArrayList<>();
        familyCat.add("THIS IS NOT A CATEGORY");

        //Testing: validateMovie(String title, int releaseYear, int userScore, ArrayList<String> category, Calendar expDate, String description)
        // Valid movie
        assertNull(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, dayInFuture, "testing"));
        // Invalid title
        assertEquals(AccessMovies.validateMovie("", 1999, 5, familyCat, dayInFuture, "testing"), "You need to name your movie.");
        assertEquals(AccessMovies.validateMovie(null, 1999, 5, familyCat, dayInFuture, "testing"), "You need to name your movie.");
        // Invalid year
        assertEquals(AccessMovies.validateMovie("testMovie", 1899, 5, familyCat, dayInFuture, "testing"), "Invalid year entry. Movies did not exist during this time.");
        assertEquals(AccessMovies.validateMovie("testMovie", 9001, 5, familyCat, dayInFuture, "testing"), "Invalid year entry. Can't add movies from beyond current year.");
        // Invalid score
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 0, familyCat, dayInFuture, "testing"), "Invalid user score entered.");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 9001, familyCat, dayInFuture, "testing"), "Invalid user score entered.");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, -9001, familyCat, dayInFuture, "testing"), "Invalid user score entered.");
        // Invalid category
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, null, dayInFuture, "testing"), "Invalid category entry. Enter at least one category.");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, badCat, dayInFuture, "testing"), "Invalid category entry. Enter at least one category.");
        // Invalid date
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, yearInFarFuture, "testing"), "Invalid date entry. Can't acquire movie rights beyond 5 years.");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, yearInPast, "testing"), "Invalid date entry. Can't enter movie with expired rights");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, monthInPast, "testing"), "Invalid date entry. Can't enter movie with expired rights");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, dayInPast, "testing"), "Invalid date entry. Can't enter movie with expired rights");
        // Invalid description
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, dayInFuture, ""), "You need to enter a description.");
        assertEquals(AccessMovies.validateMovie("testMovie", 1999, 5, familyCat, dayInFuture, null), "You need to enter a description.");
    }

}
