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

        System.out.println("\nStarting testAccessMovie");

        Movie testMovie1 = new Movie(999, "testMovie", 1999, 84, familyCat, endDate, "testing");
        Movie testMovie2 = new Movie(998, "testMovie2", 1998, 84, familyCat, endDate, "testing");
        Movie movie; //used for temporary movie

        assertNull(list.insertMovie(testMovie1));
        assertEquals("'testMovie' is already added.", list.insertMovie(testMovie1));

        assertNull(list.updateMovie(testMovie1));
        assertEquals("'testMovie2' cannot be found.", list.updateMovie(testMovie2));

        assertNull(list.deleteMovie(testMovie1));
        assertEquals("'testMovie' cannot be found.", list.deleteMovie(testMovie1));

        movie = new Movie(1, "The Shawshank Redemption", 1994, 9, familyCat, endDate, "ipsum lorem");
        assertNull(list.deleteMovie(movie));
        movie = new Movie(2, "The Lord of the Rings", 2001, 8, familyCat, endDate, "ipsum lorem");
        assertNull(list.deleteMovie(movie));
        movie = new Movie(3, "A Nightmare on Elm Street", 1984, 7, familyCat, endDate, "ipsum lorem");
        assertNull(list.deleteMovie(movie));
        movie = new Movie(4, "Raiders of the Lost Ark", 1981, 6, familyCat, endDate,"ipsum lorem");
        assertNull(list.deleteMovie(movie));
        movie = new Movie(5, "A Fish Called Wanda", 1988, 5, familyCat, endDate, "ipsum lorem");
        assertNull(list.deleteMovie(movie));

        assertEquals("'testMovie' cannot be found.", list.updateMovie(testMovie1));

        assertEquals("'testMovie' cannot be found.", list.deleteMovie(testMovie1));

        assertNull(list.insertMovie(testMovie1));

    }



}
