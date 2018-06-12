package msms.comp.main.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.business.AccessMovies;
import msms.comp.business.SortEnums;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.Movie;
import msms.comp.persistence.DataAccessor;

public class AccessMoviesTest extends TestCase
{
    private DataAccessor testData;
    private AccessMovies list;

    public AccessMoviesTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        TempData newData = new TempData();
        testData = Services.createDataAccess(newData);
        testData.open("temp");
        list = new AccessMovies();
    }

    public void tearDown()
    {
        testData.close();
        System.out.println("Finished an AccessMovies Test");
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
        assertEquals("Comedy", movie.getCategory());
        assertEquals(5, movie.getEndMonth());
        assertEquals(3, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(1);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());
        assertEquals(1987, movie.getReleaseYear());
        assertEquals("Comedy", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(2);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());
        assertEquals(1995, movie.getReleaseYear());
        assertEquals("Family", movie.getCategory());
        assertEquals(4, movie.getEndMonth());
        assertEquals(11, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...",movie.getDescription());

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals("Family", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(7, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(4);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());
        assertEquals(2009, movie.getReleaseYear());
        assertEquals("Horror", movie.getCategory());
        assertEquals(5, movie.getEndMonth());
        assertEquals(1, movie.getEndDay());
        assertEquals(2021, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(5);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());
        assertEquals(2002, movie.getReleaseYear());
        assertEquals("Horror", movie.getCategory());
        assertEquals(7, movie.getEndMonth());
        assertEquals(23, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(6);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());
        assertEquals(2015, movie.getReleaseYear());
        assertEquals("Action", movie.getCategory());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2018, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(7);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());
        assertEquals(2017, movie.getReleaseYear());
        assertEquals("Action", movie.getCategory());
        assertEquals(12, movie.getEndMonth());
        assertEquals(31, movie.getEndDay());
        assertEquals(2019, movie.getEndYear());
        assertEquals("Ipsum Lorem...", movie.getDescription());

        movie = movies.get(8);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());
        assertEquals(1991, movie.getReleaseYear());
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

        System.out.println("\nTesting Accessing the Sort fucntion in AccessMovie");

        movies = new ArrayList<Movie>();


        //Lets see if I add something like a sequel
        testData.insertMovie(new Movie(23, "Shrek 2",2008,"Family", Calendar.getInstance(), "Ipsum Lorem..."));

        movies.clear();
        testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true);

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(4);
        assertEquals(23, movie.getmID());
        assertEquals("Shrek 2", movie.getTitle());

        // Lets see if I add something that is spelt almost the same
        testData.insertMovie(new Movie(535, "Shrec",2008,"Family", Calendar.getInstance(), "Ipsum Lorem..."));

        movies.clear();
        testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true);

        movie = movies.get(3);
        assertEquals(535, movie.getmID());
        assertEquals("Shrec", movie.getTitle());

        movie = movies.get(4);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        // I add a movie that has no name
        // Lets see if I add something that is spelt almost the same
        testData.insertMovie(new Movie(25, "",2008,"Family", Calendar.getInstance(), "Ipsum Lorem..."));

        movies.clear();
        testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true);

        movie = movies.get(0);
        assertEquals(25, movie.getmID());
        assertEquals("", movie.getTitle());

        // What if I add two movies with the same name
        testData.insertMovie(new Movie(26, "Shrek",2008,"Family", Calendar.getInstance(), "Ipsum Lorem..."));

        movies.clear();
        testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true);

        movie = movies.get(5);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(6);
        assertEquals(26, movie.getmID());
        assertEquals("Shrek", movie.getTitle());
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

        Movie testMovie1 = new Movie(55, "testMovie", 1999,"Family", endDate, "testing");
        Movie testMovie2 = new Movie(56, "testMovie2", 1998, "Family", endDate, "testing");

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

        System.out.println("\nTesting Validate Features in AccessMovies");

        // Valid movie
        assertNull(AccessMovies.validateMovie("testMovie", 1999, "Family", dayInFuture, "testing"));
        // Invalid title
        assertEquals("You need to name your movie.", AccessMovies.validateMovie("", 1999,"Family", dayInFuture, "testing") );
        assertEquals("You need to name your movie.", AccessMovies.validateMovie(null, 1999,"Family", dayInFuture, "testing"));
        // Invalid year
        assertEquals("Invalid year entry. Movies did not exist during this time.", AccessMovies.validateMovie("testMovie", 1899,"Family", dayInFuture, "testing"));
        assertEquals("Invalid year entry. Can't add movies from beyond current year.", AccessMovies.validateMovie("testMovie", 9001,"Family", dayInFuture, "testing"));
        // Invalid category
        assertEquals("You need to select a category.", AccessMovies.validateMovie("testMovie", 1999,null, dayInFuture, "testing"));
        //assertEquals("Invalid category entry. Enter at least one category.", AccessMovies.validateMovie("testMovie", 1999, 5, "", dayInFuture, "testing"));
        // Invalid date
        assertEquals("Invalid date entry. Can't acquire movie rights beyond 5 years.", AccessMovies.validateMovie("testMovie", 1999,"Family", yearInFarFuture, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999,"Family", yearInPast, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999,"Family", monthInPast, "testing"));
        assertEquals("Invalid date entry. Can't enter movie with expired rights", AccessMovies.validateMovie("testMovie", 1999,"Family", dayInPast, "testing"));
        // Invalid description
        assertEquals("You need to enter a description.", AccessMovies.validateMovie("testMovie", 1999,"Family", dayInFuture, ""));
        assertEquals("You need to enter a description.", AccessMovies.validateMovie("testMovie", 1999,"Family", dayInFuture, null));

        // Test mID uniqueness
        assertFalse(AccessMovies.mIDUnique(111));
        assertTrue(AccessMovies.mIDUnique(112));

        // If we add it, it cant be unique
        assertTrue(AccessMovies.mIDUnique(113));
        list.insertMovie(new Movie(113,"testMovie", 1999,"Family", dayInFuture, ""));
        assertFalse(AccessMovies.mIDUnique(113));

        // If we delete it, the mID can be unique again.
        assertFalse(AccessMovies.mIDUnique(113));
        list.deleteMovie(new Movie(113,"testMovie", 1999,"Family", dayInFuture, ""));
        assertTrue(AccessMovies.mIDUnique(113));
        }

    public void testSearching()
    {
        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);

        Movie testMovie1 = new Movie(1, "testMovie", 1999,"Family", endDate, "testing");
        Movie testMovie2 = new Movie(2, "testMovie2", 1998,"Family", endDate, "testing");

        System.out.println("\nTesting Search Feature in AccessMovies");

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
