package msms.comp3350.main.tests.buisness;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;
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
        AccessMovies list = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList<>();

        list.getMovies(movies);

        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        ArrayList<String> familyCat = new ArrayList<>();
        familyCat.add("family");

        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);

        Movie movie = new Movie(999, "testMovie", 1999, 84, familyCat, endDate, "testing");
        Movie movie2 = new Movie(999, "testMovie2", 1998, 84, familyCat, endDate, "testing");

        // All these methods do is call other methods in TempData, so all the major testing will be done there
        assertNull(list.insertMovie(movie));
        assertEquals("'testMovie' is already added.", list.insertMovie(movie));

        assertNull(list.updateMovie(movie));
        assertEquals("'testMovie2' cannot be found.", list.updateMovie(movie2));

        assertNull(list.deleteMovie(movie));
        assertEquals("'testMovie' cannot be found.", list.deleteMovie(movie));
    }



}
