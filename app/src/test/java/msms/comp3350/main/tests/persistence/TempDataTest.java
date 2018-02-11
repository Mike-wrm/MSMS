package msms.comp3350.main.tests.persistence;

import junit.framework.TestCase;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.TempData;
import java.util.ArrayList;

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
        Movie movie;
        User user;

        list.getMoviesAll(movies);
        assertNotNull(movies);

        for(int i = 0; i < 5; i ++){
            movie = movies.get(i);
            assertNotNull(movie);
        }

        list.getUsersAll(users);
        assertNotNull(users);

        for(int i = 0; i < 6; i++){
           user = users.get(i);
            assertNotNull(user);
        }
    }

}
