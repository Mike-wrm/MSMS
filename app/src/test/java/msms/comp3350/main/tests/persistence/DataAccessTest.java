package msms.comp3350.main.tests.persistence;


import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.business.SortEnums;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;
import msms.comp3350.persistence.DataAccessor;

public class DataAccessTest extends TestCase
{
    private DataAccessor testData;

    public DataAccessTest (String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        TempData newData = new TempData();
        testData = Services.createDataAccess(newData);
        testData.open("temp");
    }

    public void tearDown()
    {
        System.out.println("Finishing a Persistence Test");
        testData.close();
    }

    public void resetMovies(ArrayList<Movie> movies)
    {
        movies.clear();
        assertNull(testData.getMoviesAll(movies));
    }

    public void resetUsers(ArrayList<User> users)
    {
        users.clear();
        assertNull(testData.getUsersAll(users));
    }

    public void testDataAccessMovie()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Data Access for the movies in DataAccessTest");

        movies = new ArrayList<Movie>();

        assertNull(testData.getMoviesAll(movies));
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

    public void testSortingAccessMovie()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Data Access for the movies in DataAccessTest");

        movies = new ArrayList<Movie>();

        assertNull(testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, false));
        assertEquals(9, movies.size());

        // Lets Make sure everything is in order.
        movie = movies.get(0);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());

        movie = movies.get(1);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());

        movie = movies.get(2);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());

        movie = movies.get(3);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());

        movie = movies.get(4);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());

        movie = movies.get(5);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(6);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());

        movie = movies.get(7);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());

        movie = movies.get(8);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());

        // sort it in ascending
        movies.clear();
        assertNull(testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true));

        movie = movies.get(0);
        assertEquals(222, movie.getmID());
        assertEquals("Eddie Murphy: Raw", movie.getTitle());

        movie = movies.get(1);
        assertEquals(555, movie.getmID());
        assertEquals("Friday the 13th", movie.getTitle());

        movie = movies.get(2);
        assertEquals(777, movie.getmID());
        assertEquals("Mission Impossible: Rogue Nation", movie.getTitle());

        movie = movies.get(3);
        assertEquals(444, movie.getmID());
        assertEquals("Shrek", movie.getTitle());

        movie = movies.get(4);
        assertEquals(111, movie.getmID());
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());

        movie = movies.get(5);
        assertEquals(999, movie.getmID());
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());

        movie = movies.get(6);
        assertEquals(666, movie.getmID());
        assertEquals("The Ring", movie.getTitle());

        movie = movies.get(7);
        assertEquals(333, movie.getmID());
        assertEquals("Toy Story", movie.getTitle());

        movie = movies.get(8);
        assertEquals(888, movie.getmID());
        assertEquals("Transformers: The Last Knight", movie.getTitle());
    }

    public void testDateAccessUsers()
    {
        ArrayList<User> users;
        User user;

        System.out.println("\nTesting Accessing the Data Access for the users");

        users = new ArrayList<User>();

        assertNull(testData.getUsersAll(users));
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

    public void testMovieAccessChange()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Changing the Data Access for the movies in DataAccessTest");

        Calendar date = Calendar.getInstance();
        date.set(2020, 11, 21);

        Calendar date2 = Calendar.getInstance();
        date2.set(2022,10, 8);

        movie = new Movie(778, "Test Movie", 2018,"Comedy", date, "test.");
        assertEquals("'Test Movie' cannot be found.", testData.deleteMovie(movie)); // delete something that isnt there.

        assertNull(testData.insertMovie(movie)); // test basic add
        assertEquals("'Test Movie' is already added.", testData.insertMovie(movie)); // test adding the same thing twice

        movies = new ArrayList<Movie>();
        assertNull(testData.getMoviesAll(movies));

        assertEquals(10, movies.size()); // making sure the right size

        movie.setTitle("NewTest"); //update the title
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals("NewTest", movie.getTitle());

        movie.setReleaseYear(2002); //update the release year
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals(2002, movie.getReleaseYear());

        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);

        movie.setCategory("Horror");
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals("Horror", movie.getCategory());

        movie.setEndDate(date2); //update the calendar
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals(date2, movie.getEndDate());

        movie.setDescription("New Description"); // update the description
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals("New Description", movie.getDescription());

        assertNull(testData.deleteMovie(movie)); // testing basic delete
        resetMovies(movies);
        assertEquals(9, movies.size());

        assertEquals("'NewTest' cannot be found.", testData.updateMovie(movie)); // try to update something that doesnt exist

        movie = movies.get(0); // lets clear it
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(1);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(2);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(3);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(4);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(5);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(6);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(7);
        assertNull(testData.deleteMovie(movie));
        movie = movies.get(8);
        assertNull(testData.deleteMovie(movie));

        resetMovies(movies);
        assertEquals(0, movies.size()); // make sure its empty

        assertEquals("'Terminator 2: Judgement Day' cannot be found.", testData.deleteMovie(movie)); // try to delete on an empty list
        assertEquals("'Terminator 2: Judgement Day' cannot be found.", testData.updateMovie(movie)); // try to update on an empty list

        assertNull(testData.insertMovie(movie)); // adding to an empty list
        assertNull(testData.deleteMovie(movie));

        assertNull(testData.insertMovie(movie)); // re-adding a movie that should have been deleted
        assertNull(testData.deleteMovie(movie));
    }

    public void testUserAccessChange()
    {
        ArrayList<User> users;
        User user;

        Calendar date = Calendar.getInstance();
        date.set(2020, 11, 21);

        Calendar date2 = Calendar.getInstance();
        date2.set(2022,10, 8);

        System.out.println("\nTesting Changing the Data Access for the users in DataAccessTest");

        users = new ArrayList<User>();
        user =  new User(50, "Tester", "pass", 21, 'M', date);

        assertNull(testData.insertUser(user)); // test basic add
        assertEquals("'Tester' is already added.", testData.insertUser(user)); // test adding the same thing twice

        assertNull(testData.getUsersAll(users));

        assertEquals(7, users.size()); // making sure the right size

        user.setuID(51); // update the id
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals(51, user.getuID());

        user.setName("newTester"); // update the name
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals("newTester", user.getName());

        user.setPass("newPass"); // update the password
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals("newPass", user.getPass());

        user.setAge(22); // update the age
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals(22, user.getAge());

        user.setGender('F'); // update the gender
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals('F', user.getGender());

        user.setEndDate(date2); // update the end date
        assertNull(testData.updateUser(user));
        resetUsers(users);
        user = users.get(6);
        assertEquals(date2, user.getEndDate());

        assertNull(testData.deleteUser(user)); // test basic delete
        resetUsers(users);
        assertEquals(6, users.size());

        assertEquals("'newTester' cannot be found.", testData.deleteUser(user)); // delete something that doesnt

        user = users.get(0); // delete everything
        assertNull(testData.deleteUser(user));
        user = users.get(1);
        assertNull(testData.deleteUser(user));
        user = users.get(2);
        assertNull(testData.deleteUser(user));
        user = users.get(3);
        assertNull(testData.deleteUser(user));
        user = users.get(4);
        assertNull(testData.deleteUser(user));
        user = users.get(5);
        assertNull(testData.deleteUser(user));

        resetUsers(users);
        assertEquals(0, users.size()); // make sure its empty

        assertEquals("'Wonder_Woman' cannot be found.", testData.deleteUser(user)); // try to delete on an empty list
        assertEquals("'Wonder_Woman' cannot be found.", testData.updateUser(user)); // try to update on an empty list
        assertNull(testData.insertUser(user)); // adding to an empty list

        assertNull(testData.deleteUser(user));
        assertNull(testData.insertUser(user)); // re-adding a movie that should have been deleted
        assertNull(testData.deleteUser(user));
    }

    public void testGetMovieViews()
    {
        ArrayList<WatchedEvent> watched = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();

        Movie movie;
        WatchedEvent watch;

        System.out.println("\nStarting an  getMoviesView test in DataAccessTest");

        testData.getMoviesAll(movies);

        // Check the first movie
        movie = movies.get(0);
        testData.getMovieViews(watched, movie);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(111, watch.getuID());
        assertEquals(111, watch.getmID());
        assertEquals("Miggles", watch.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", watch.getMovieTitle());
        assertEquals(7, watch.getRating());

        watch = watched.get(1);

        assertEquals(222, watch.getuID());
        assertEquals(111, watch.getmID());
        assertEquals("Smoo", watch.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", watch.getMovieTitle());
        assertEquals(5, watch.getRating());

        // Check the second movie
        movie = movies.get(1);
        testData.getMovieViews(watched, movie);
        assertEquals(1, watched.size());

        watch = watched.get(0);

        assertEquals(444, watch.getuID());
        assertEquals(222, watch.getmID());
        assertEquals("TestBoi", watch.getUserName());
        assertEquals("Eddie Murphy: Raw", watch.getMovieTitle());
        assertEquals(6, watch.getRating());

        // Check the third movie
        movie = movies.get(2);
        testData.getMovieViews(watched, movie);
        assertEquals(1, watched.size());

        watch = watched.get(0);

        assertEquals(555, watch.getuID());
        assertEquals(333, watch.getmID());
        assertEquals("JiffyPB", watch.getUserName());
        assertEquals("Toy Story", watch.getMovieTitle());
        assertEquals(3, watch.getRating());

        // Check the fourth Movie
        movie = movies.get(3);
        testData.getMovieViews(watched, movie);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(333, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        watch = watched.get(1);

        assertEquals(444, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("TestBoi", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(10, watch.getRating());

        // Check the fifth movie
        movie = movies.get(4);
        testData.getMovieViews(watched, movie);
        assertEquals(0,watched.size());

        // Check the sixth movie
        movie = movies.get(5);
        testData.getMovieViews(watched, movie);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(333, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(8, watch.getRating());

        watch = watched.get(1);

        assertEquals(666, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(9, watch.getRating());

        // Check the seventh movie
        movie = movies.get(6);
        testData.getMovieViews(watched, movie);
        assertEquals(1, watched.size());

        watch = watched.get(0);

        assertEquals(555, watch.getuID());
        assertEquals(777, watch.getmID());
        assertEquals("JiffyPB", watch.getUserName());
        assertEquals("Mission Impossible: Rogue Nation", watch.getMovieTitle());
        assertEquals(7, watch.getRating());

        // Check the eighth movie
        movie = movies.get(7);
        testData.getMovieViews(watched, movie);
        assertEquals(1, watched.size());

        watch = watched.get(0);

        assertEquals(666, watch.getuID());
        assertEquals(888, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("Transformers: The Last Knight", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        // Check the last movie
        movie =  movies.get(8);
        testData.getMovieViews(watched, movie);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(111, watch.getuID());
        assertEquals(999, watch.getmID());
        assertEquals("Miggles", watch.getUserName());
        assertEquals("Terminator 2: Judgement Day", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        watch = watched.get(1);

        assertEquals(222, watch.getuID());
        assertEquals(999, watch.getmID());
        assertEquals("Smoo", watch.getUserName());
        assertEquals("Terminator 2: Judgement Day", watch.getMovieTitle());
        assertEquals(4, watch.getRating());
    }

    public void testGetUserViews()
    {
        ArrayList<WatchedEvent> watched = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        User user;
        WatchedEvent watch;

        System.out.println("\nStarting an getUserViews Test in DataAccessTest");

        testData.getUsersAll(users);

        // Check the first user
        user = users.get(0);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(111, watch.getuID());
        assertEquals(111, watch.getmID());
        assertEquals("Miggles", watch.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", watch.getMovieTitle());
        assertEquals(7, watch.getRating());

        watch = watched.get(1);

        assertEquals(111, watch.getuID());
        assertEquals(999, watch.getmID());
        assertEquals("Miggles", watch.getUserName());
        assertEquals("Terminator 2: Judgement Day", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        // Check the second user
        user = users.get(1);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(222, watch.getuID());
        assertEquals(111, watch.getmID());
        assertEquals("Smoo", watch.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", watch.getMovieTitle());
        assertEquals(5, watch.getRating());

        watch = watched.get(1);

        assertEquals(222, watch.getuID());
        assertEquals(999, watch.getmID());
        assertEquals("Smoo", watch.getUserName());
        assertEquals("Terminator 2: Judgement Day", watch.getMovieTitle());
        assertEquals(4, watch.getRating());

        // Check the third user
        user = users.get(2);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(333, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(8, watch.getRating());

        watch = watched.get(1);

        assertEquals(333, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        // Check the fourth user
        user = users.get(3);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(444, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("TestBoi", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(10, watch.getRating());

        watch = watched.get(1);

        assertEquals(444, watch.getuID());
        assertEquals(222, watch.getmID());
        assertEquals("TestBoi", watch.getUserName());
        assertEquals("Eddie Murphy: Raw", watch.getMovieTitle());
        assertEquals(6, watch.getRating());

        // Check the fifth user
        user = users.get(4);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(555, watch.getuID());
        assertEquals(333, watch.getmID());
        assertEquals("JiffyPB", watch.getUserName());
        assertEquals("Toy Story", watch.getMovieTitle());
        assertEquals(3, watch.getRating());

        watch = watched.get(1);

        assertEquals(555, watch.getuID());
        assertEquals(777, watch.getmID());
        assertEquals("JiffyPB", watch.getUserName());
        assertEquals("Mission Impossible: Rogue Nation", watch.getMovieTitle());
        assertEquals(7, watch.getRating());

        // Check the sixth user
        user = users.get(5);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(666, watch.getuID());
        assertEquals(888, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("Transformers: The Last Knight", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        watch = watched.get(1);

        assertEquals(666, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(9, watch.getRating());
    }
}
