package msms.comp3350.main.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.business.SortEnums;
import msms.comp3350.business.AccessMovies;
import msms.comp3350.objects.Movie;

public class AccessMoviesTest extends TestCase
{
    private AccessMovies list;

    public AccessMoviesTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        Services.createDataAccess("temp");
        list = new AccessMovies();
    }

    public void tearDown()
    {
        list = null;
        System.out.println("Finished an AccessMovies Test");
    }

    public void rebuildList()
    {
        Movie movie;

        Calendar date1 = Calendar.getInstance(); // January = 0, December = 11.
        date1.set(2018, 4, 3);
        Calendar date2 = Calendar.getInstance();
        date2.set(2018, 6, 23);
        Calendar date3 = Calendar.getInstance();
        date3.set(2018, 3, 11);
        Calendar date4 = Calendar.getInstance();
        date4.set(2019, 6, 7);
        Calendar date5 = Calendar.getInstance();
        date5.set(2021, 4, 1);
        Calendar date6 = Calendar.getInstance();
        date6.set(2018, 6, 23);
        Calendar date7 = Calendar.getInstance();
        date7.set(2018, 11, 31);
        Calendar date8 = Calendar.getInstance();
        date8.set(2019, 11, 31);
        Calendar date9 = Calendar.getInstance();
        date9.set(2020, 10, 1);

        movie = new Movie("South Park: Bigger, Longer & Uncut", 1999, 7, "Comedy", date1, "Ipsum Lorem...");
        movie.setmID(111);
        list.insertMovie(movie);

        movie = new Movie("Eddie Murphy: Raw", 1987, 5, "Comedy", date2, "Ipsum Lorem...");
        movie.setmID(222);
        list.insertMovie(movie);

        movie = new Movie("Toy Story", 1995, 10, "Family", date3, "Ipsum Lorem...");
        movie.setmID(333);
        list.insertMovie(movie);

        movie = new Movie("Shrek", 2001, 8, "Family", date4, "Ipsum Lorem...");
        movie.setmID(444);
        list.insertMovie(movie);

        movie = new Movie("Friday the 13th", 2009, 3, "Horror", date5, "Ipsum Lorem...");
        movie.setmID(555);
        list.insertMovie(movie);

        movie = new Movie("The Ring", 2002, 6, "Horror", date6, "Ipsum Lorem...");
        movie.setmID(666);
        list.insertMovie(movie);

        movie = new Movie("Mission Impossible: Rogue Nation", 2015, 8, "Action", date7, "Ipsum Lorem...");
        movie.setmID(777);
        list.insertMovie(movie);

        movie = new Movie("Transformers: The Last Knight", 2017, 2, "Action", date8, "Ipsum Lorem...");
        movie.setmID(888);
        list.insertMovie(movie);

        movie = new Movie("Terminator 2: Judgement Day", 1991, 8, "Action", date9, "Ipsum Lorem...");
        movie.setmID(999);
        list.insertMovie(movie);
    }

    public void resetMovies(ArrayList<Movie> movies)
    {
        movies.clear();
        assertNull(list.getMovies(movies));
    }

    public void testAccessMovieAccess()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Data in AccessMovies");

        movies = new ArrayList<Movie>();

        assertNull(list.getMovies(movies));
        assertEquals(9, movies.size());

        movie = movies.get(0);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());
        assertEquals(1999, movie.getReleaseYear());
        assertEquals(7, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory());
        assertEquals(5, movie.getEndMonth());
        assertEquals(3, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(1);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());
        assertEquals(1987, movie.getReleaseYear());
        assertEquals(5, movie.getUserScore());
        assertEquals("Comedy", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(2);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());
        assertEquals(1995, movie.getReleaseYear());
        assertEquals(10, movie.getUserScore());
        assertEquals("Family", movie.getCategory());
        assertEquals(4, movie.getEndMonth());
        assertEquals(11, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Family", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(7, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(4);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());
        assertEquals(2009, movie.getReleaseYear());
        assertEquals(3, movie.getUserScore());
        assertEquals("Horror", movie.getCategory());
        assertEquals(5, movie.getEndMonth());
        assertEquals(1, movie.getEndDay());
        assertEquals(2021, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(5);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());
        assertEquals(2002, movie.getReleaseYear());
        assertEquals(6, movie.getUserScore());
        assertEquals("Horror", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(6);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());
        assertEquals(2015, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Action", movie.getCategory());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(7);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());
        assertEquals(2017, movie.getReleaseYear());
        assertEquals(2, movie.getUserScore());
        assertEquals("Action", movie.getCategory());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(8);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());
        assertEquals(1991, movie.getReleaseYear());
        assertEquals(8, movie.getUserScore());
        assertEquals("Action", movie.getCategory());
        assertEquals(11, movie.getEndMonth());
        assertEquals(1, movie.getEndDay());
        assertEquals(2020, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());
    }

    public void testAccessMovieSort()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Sorted data in AccessMovies");

        movies = new ArrayList<Movie>();

        assertNull(list.getSortedMovies(movies, SortEnums.MovieSortField.TITLE, false));
        assertEquals(9, movies.size());

        movie = movies.get(0);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());

        movie = movies.get(1);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());

        movie = movies.get(2);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());

        movie = movies.get(3);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());

        movie = movies.get(4);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());

        movie = movies.get(5);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(6);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());

        movie = movies.get(7);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());

        movie = movies.get(8);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());

        // sort it in ascending
        movies.clear();
        assertNull(list.getSortedMovies(movies, SortEnums.MovieSortField.TITLE, true));

        movie = movies.get(0);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());

        movie = movies.get(1);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());

        movie = movies.get(2);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(4);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());

        movie = movies.get(5);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());

        movie = movies.get(6);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());

        movie = movies.get(7);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());

        movie = movies.get(8);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());

    }

    public void testAccessMovieChange()
    {
        Movie movie;
        ArrayList<Movie> movies = new ArrayList<>();

        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);

        System.out.println("\nTesting Changing the data in AccessMovies");

        list.getMovies(movies);

        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        Movie testMovie1 = new Movie("testMovie", 1999, 84, "Family", endDate, "testing");
        Movie testMovie2 = new Movie("testMovie2", 1998, 84, "Family", endDate, "testing");

        assertNull(list.insertMovie(testMovie1));
        resetMovies(movies);
        assertEquals(10, movies.size());
        assertEquals("'testMovie' is already added.", list.insertMovie(testMovie1));

        assertNull(list.updateMovie(testMovie1));
        resetMovies(movies);
        assertEquals(10, movies.size());
        assertEquals("'testMovie2' cannot be found.", list.updateMovie(testMovie2));

        assertNull(list.deleteMovie(testMovie1));
        resetMovies(movies);
        assertEquals(9, movies.size());
        assertEquals("'testMovie' cannot be found.", list.deleteMovie(testMovie1));

        resetMovies(movies);

        movie = movies.get(0); // empty out the list
        assertNull(list.deleteMovie(movie));
        movie = movies.get(1);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(2);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(3);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(4);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(5);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(6);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(7);
        assertNull(list.deleteMovie(movie));
        movie = movies.get(8);
        assertNull(list.deleteMovie(movie));

        assertEquals("'testMovie' cannot be found.", list.deleteMovie(testMovie1)); // delete on empty

        assertEquals("'testMovie' cannot be found.", list.updateMovie(testMovie1)); // update on empty

        assertNull(list.insertMovie(testMovie1)); // insert on empty
        assertNull(list.deleteMovie(testMovie1));

        rebuildList(); //have to rebuild them
    }

    public void testValidate()
    {
        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);

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

        System.out.println("\nStarting Validate test in AccessMovies");

        //Testing: validateMovie(String title, int releaseYear, int userScore, ArrayList<String> category, Calendar expDate, String description)
        // Valid movie
        assertNull(AccessMovies.validateMovie("testMovie", 1999, 5, "Family", dayInFuture, "testing"));
        // Invalid title
        assertEquals("You need to name your movie.", AccessMovies.validateMovie("", 1999, 5, "Family", dayInFuture, "testing") );
        assertEquals("You need to name your movie.", AccessMovies.validateMovie(null, 1999, 5, "Family", dayInFuture, "testing"));
        // Invalid year
        assertEquals("Invalid year entry. Movies did not exist during this time.", AccessMovies.validateMovie("testMovie", 1899, 5, "Family", dayInFuture, "testing"));
        assertEquals("Invalid year entry. Can't add movies from beyond current year.", AccessMovies.validateMovie("testMovie", 9001, 5, "Family", dayInFuture, "testing"));
        // Invalid score
        assertEquals("Invalid user score entered.", AccessMovies.validateMovie("testMovie", 1999, 0, "Family", dayInFuture, "testing"));
        assertEquals("Invalid user score entered.", AccessMovies.validateMovie("testMovie", 1999, 9001, "Family", dayInFuture, "testing"));
        assertEquals("Invalid user score entered.", AccessMovies.validateMovie("testMovie", 1999, -9001, "Family", dayInFuture, "testing"));
        // Invalid category
        assertEquals("Invalid category entry. Enter at least one category.", AccessMovies.validateMovie("testMovie", 1999, 5, null, dayInFuture, "testing"));
        //assertEquals("Invalid category entry. Enter at least one category.", AccessMovies.validateMovie("testMovie", 1999, 5, "", dayInFuture, "testing"));
        // Invalid date
        assertEquals("Invalid date entry. Can't acquire movie rights beyond 5 years.", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", yearInFarFuture, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", yearInPast, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", monthInPast, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", dayInPast, "testing"));
        // Invalid description
        assertEquals("You need to enter a description.", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", dayInFuture, ""));
        assertEquals("You need to enter a description.", AccessMovies.validateMovie("testMovie", 1999, 5, "Family", dayInFuture, null));
    }

    public void testSearching()
    {
        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);

        Movie testMovie1 = new Movie("testMovie", 1999, 84, "Family", endDate, "testing");
        Movie testMovie2 = new Movie("testMovie2", 1998, 84, "Family", endDate, "testing");

        System.out.println("\nStarting Test for Searching");

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

        list.deleteMovie(testMovie1);
        list.deleteMovie(testMovie2);
    }
}
