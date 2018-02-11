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

        Movie newMovie = new Movie(1, "Shrek", 2001, 84, familyCat, endDate, "Ogre Saves Princess but gets unexpected suprise");

        // All these methods do is call other methods in TempData, so all the major testing will be done there
        assertNull(list.insertMovie(newMovie));
        assertNull(list.updateMovie(newMovie));
        assertNull(list.deleteMovie(newMovie));
    }



}
