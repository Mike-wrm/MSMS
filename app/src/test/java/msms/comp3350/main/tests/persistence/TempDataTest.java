package msms.comp3350.main.tests.persistence;

import junit.framework.TestCase;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.TempData;
import java.util.ArrayList;
import java.util.Calendar;

public class TempDataTest extends TestCase
{
    public TempDataTest(String arg0)
    {
        super(arg0);
    }

    public void testTempData()
    {
       // This test might be completely wrong, so hold it with a grain of salt at the moment
        TempData list = new TempData();
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        setup(list, movies, users);

        Movie movie;
        User user;

        assertNotNull(movies);

        for(int i = 0; i < 5; i ++){
            movie = movies.get(i);
            assertNotNull(movie);
        }

        assertNotNull(users);

        for(int i = 0; i < 6; i++){
           user = users.get(i);
            assertNotNull(user);
        }
    }

    public void testTempDataChange()
    {
        TempData list = new TempData();
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        ArrayList<String> familyCat = new ArrayList<>();
        familyCat.add("family");

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        Movie movie = new Movie(999, "testMovie", 1999, 84, familyCat, endDate, "testing");
        Movie movie2 = new Movie(999, "testMovie2", 1998, 84, familyCat, endDate, "testing");

        User user = new User(999,"John Doe", "password", 21, 'm', endDate);
        User user2 = new User(999,"Jane Doe", "password", 21, 'f', endDate);

        setup(list, movies, users);

        assertNull(list.insertMovie(movie));
        assertEquals("'testMovie' is already added.", list.insertMovie(movie));

        assertNull(list.updateMovie(movie));
        assertEquals("'testMovie2' cannot be found.", list.updateMovie(movie2));

        assertNull(list.deleteMovie(movie));
        assertEquals("'testMovie' cannot be found.", list.deleteMovie(movie));

        assertNull(list.insertUser(user));
        assertEquals("'John Doe' is already added.", list.insertUser(user));

        assertNull(list.updateUser(user));
        assertEquals("'Jane Doe' cannot be found.", list.updateUser(user2));

        assertNull(list.deleteUser(user));
        assertEquals("'John Doe' cannot be found.", list.deleteUser(user));
    }

    public void setup(TempData list, ArrayList<Movie> movies, ArrayList<User> users)
    {
        list.getMoviesAll(movies);
        list.getUsersAll(users);
    }
}
