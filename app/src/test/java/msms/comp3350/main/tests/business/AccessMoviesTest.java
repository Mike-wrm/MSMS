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

        // We cant test on an empty list because we cant delete them without knowing thier mID which we would have to set manually, and because we set it manually, we are not sure if
        // the testMovie file ran first, which would creat different movies first, meaning that we would rely on the order of the methods were called, and that is a no-no
    }

}
