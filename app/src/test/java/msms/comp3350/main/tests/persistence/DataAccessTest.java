package msms.comp3350.main.tests.persistence;

import junit.framework.TestCase;
import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.DataAccessor;
import msms.comp3350.persistence.DataAccessorObject;


public class DataAccessTest extends TestCase
{
    private DataAccessor testData;

    public DataAccessTest (String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        System.out.println("Starting Persistence Test");
        testData = Services.createDataAccess("test");
    }

    public void tearDown()
    {
        System.out.println("Finishing Persistence Test");
    }

    public void testDataAccessReal()
    {
        ArrayList<Movie> movies;
        ArrayList<User> users;

        Movie movie;
        User user;

        String result;

        // Movie Testing ---------------------------------------
        movies = new ArrayList<Movie>();

        result = testData.getMoviesAll(movies);
        assertNull(result);
        assertEquals(9, movies.size());

        movie = movies.get(0);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());
        assertEquals(1999, movie.getReleaseYear());
        assertEquals(7, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory1());
        assertNull(movie.getCategory2());
        assertEquals(5, movie.getEndMonth());
        assertEquals(3, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(1);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());
        assertEquals(1987, movie.getReleaseYear());
        assertEquals(5, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory1());
        assertNull(movie.getCategory2());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(2);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());
        assertEquals(1995, movie.getReleaseYear());
        assertEquals(10, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory1());
        assertEquals("Family", movie.getCategory2());
        assertEquals(4, movie.getEndMonth());
        assertEquals(11, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory1());
        assertEquals("Family", movie.getCategory2());
        assertEquals(7, movie.getEndMonth());
        assertEquals(7, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(4);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());
        assertEquals(2009, movie.getReleaseYear());
        assertEquals(3, movie.getUserScore());
        assertEquals("Horror", movie.getCategory1());
        assertNull(movie.getCategory2());
        assertEquals(5, movie.getEndMonth());
        assertEquals(1, movie.getEndDay());
        assertEquals(2021, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(5);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());
        assertEquals(2002, movie.getReleaseYear());
        assertEquals(6, movie.getUserScore());
        assertEquals("Horror", movie.getCategory1());
        assertNull(movie.getCategory2());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(6);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());
        assertEquals(2015, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Action", movie.getCategory1());
        assertEquals("Trending", movie.getCategory2());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(7);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());
        assertEquals(2017, movie.getReleaseYear());
        assertEquals(2, movie.getUserScore());
        assertEquals("Action", movie.getCategory1());
        assertEquals("Recent", movie.getCategory2());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(8);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());
        assertEquals(1991, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Action", movie.getCategory1());
        assertEquals("Trending", movie.getCategory2());
        assertEquals(11, movie.getEndMonth());
        assertEquals(1, movie.getEndDay());
        assertEquals(2020, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());


        //Users Testing ---------------------------------------
        users = new ArrayList<User>();

        result = testData.getUsersAll(users);
        assertNull(result);
        assertEquals(6, users.size());

        user = users.get(0);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());
        assertEquals("anime4life", user.getPass());
        assertEquals(21, user.getAge());
        assertEquals('M', user.getGender());
        assertEquals(4, user.getEndMonth());
        assertEquals(30, user.getEndDay());
        assertEquals(2018, user.getEndYear());

        user = users.get(1);
        assertEquals(222, user.getuID());
        assertEquals("Smoo", user.getName());
        assertEquals("getoffmylawn", user.getPass());
        assertEquals(32, user.getAge());
        assertEquals('M', user.getGender());
        assertEquals(4, user.getEndMonth());
        assertEquals(30, user.getEndDay());
        assertEquals(2018, user.getEndYear());

        user = users.get(2);
        assertEquals(333, user.getuID());
        assertEquals("Andrew_Sempai", user.getName());
        assertEquals("iheartmybeard", user.getPass());
        assertEquals(23, user.getAge());
        assertEquals('M', user.getGender());
        assertEquals(4, user.getEndMonth());
        assertEquals(30, user.getEndDay());
        assertEquals(2018, user.getEndYear());

        user = users.get(3);
        assertEquals(444, user.getuID());
        assertEquals("TestBoi", user.getName());
        assertEquals("supertester", user.getPass());
        assertEquals(24, user.getAge());
        assertEquals('M', user.getGender());
        assertEquals(4, user.getEndMonth());
        assertEquals(30, user.getEndDay());
        assertEquals(2018, user.getEndYear());

        user = users.get(4);
        assertEquals(555, user.getuID());
        assertEquals("JiffyPB", user.getName());
        assertEquals("walmartisevil", user.getPass());
        assertEquals(21, user.getAge());
        assertEquals('M', user.getGender());
        assertEquals(4, user.getEndMonth());
        assertEquals(30, user.getEndDay());
        assertEquals(2018, user.getEndYear());

        user = users.get(5);
        assertEquals(666, user.getuID());
        assertEquals("Wonder_Woman", user.getName());
        assertEquals("DCU", user.getPass());
        assertEquals(82, user.getAge());
        assertEquals('F', user.getGender());
        assertEquals(12, user.getEndMonth());
        assertEquals(31, user.getEndDay());
        assertEquals(2020, user.getEndYear());

    }

}
