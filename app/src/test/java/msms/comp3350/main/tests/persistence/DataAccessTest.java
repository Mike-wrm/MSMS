package msms.comp.main.tests.persistence;


import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.objects.Movie;
import msms.comp.business.SortEnums;
import msms.comp.objects.User;
import msms.comp.objects.WatchedEvent;
import msms.comp.persistence.DataAccessor;

public class DataAccessTest extends TestCase
{
    private DataAccessor testData;

    public DataAccessTest (String arg0)
    {
        super(arg0);
    }

    public static void dataAccessTest(DataAccessor dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.testData = dataAccess;
        dataAccessTest.rebuild();
        dataAccessTest.testDataAccessMovie();
        dataAccessTest.testDataAccessUsers();
        dataAccessTest.testGetAllWatchedEvents();
        dataAccessTest.rebuild();
        dataAccessTest.testGetMovieViews();
        dataAccessTest.testGetUserViews();
        dataAccessTest.rebuild();
        dataAccessTest.testGetMovieSublist();
        dataAccessTest.testGetUserSublist();
        dataAccessTest.rebuild();
        dataAccessTest.testAccessMovieSort();
        dataAccessTest.testAccessUserSort();
        dataAccessTest.rebuild();
        dataAccessTest.testMovieAccessChange();
        dataAccessTest.rebuild();
        dataAccessTest.testUserAccessChange();
        dataAccessTest.rebuild();
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

    public void rebuild()
    {
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<WatchedEvent> events = new ArrayList<>();
        Movie movie;
        User user;

        testData.getMoviesAll(movies);
        testData.getUsersAll(users);
        testData.getAllWatchedEvents(events);

        if(movies.size() != 9)
        {
            for(int i = 0; i < movies.size(); i++)
            {
                movie = movies.get(i);
                testData.deleteMovie(movie);
            }

            Calendar date1 = Calendar.getInstance();
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

            testData.insertMovie(new Movie(111, "South Park: Bigger, Longer & Uncut", 1999,"Comedy", date1, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(222,"Eddie Murphy: Raw", 1987,"Comedy", date2, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(333, "Toy Story", 1995,"Family", date3, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(444, "Shrek", 2001,"Family", date4, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(555, "Friday the 13th", 2009,"Horror", date5, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(666,"The Ring", 2002,"Horror", date6, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(777,"Mission Impossible: Rogue Nation", 2015, "Action", date7, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(888, "Transformers: The Last Knight", 2017,"Action", date8, "Ipsum Lorem..."));
            testData.insertMovie(new Movie(999, "Terminator 2: Judgement Day", 1991,"Action", date9, "Ipsum Lorem..."));
        }

        if(users.size() != 6)
        {
            for(int i = 0; i < users.size(); i++)
            {
                user = users.get(i);
                testData.deleteUser(user);
            }

            Calendar userDate1 = Calendar.getInstance();
            userDate1.set(2018,3,30);
            Calendar userDate2 = Calendar.getInstance();
            userDate2.set(2020,11,31);

            testData.insertUser(new User(111, "Miggles", "anime4life", 21, 'M', userDate1));
            testData.insertUser(new User(222, "Smoo", "getoffmylawn", 32, 'M', userDate1));
            testData.insertUser(new User(333, "Andrew_Sempai", "iheartmybeard", 23, 'M', userDate1));
            testData.insertUser(new User(444, "TestBoi", "supertester", 24, 'M', userDate1));
            testData.insertUser(new User(555, "JiffyPB", "walmartisevil", 21, 'M', userDate1));
            testData.insertUser(new User(666, "Wonder_Woman", "DCU", 82, 'F', userDate2));
        }

        if(events.size() == 0)
        {
            testData.insertWatchedEvent(new WatchedEvent(111,111,"Miggles", "South Park: Bigger, Longer & Uncut", 7));
            testData.insertWatchedEvent(new WatchedEvent(111,999,"Miggles", "Terminator 2: Judgement Day", 2));
            testData.insertWatchedEvent(new WatchedEvent(222,111,"Smoo", "South Park: Bigger, Longer & Uncut", 5));
            testData.insertWatchedEvent(new WatchedEvent(222,999,"Smoo", "Terminator 2: Judgement Day", 4));
            testData.insertWatchedEvent(new WatchedEvent(333,444,"Andrew_Sempai", "Shrek", 2));
            testData.insertWatchedEvent(new WatchedEvent(333,666,"Andrew_Sempai", "The Ring", 8));
            testData.insertWatchedEvent(new WatchedEvent(555,333,"JiffyPB", "Toy Story", 3));
            testData.insertWatchedEvent(new WatchedEvent(555,777,"JiffyPB", "Mission Impossible: Rogue Nation", 7));
            testData.insertWatchedEvent(new WatchedEvent(666,666,"Wonder_Woman", "The Ring", 9));
            testData.insertWatchedEvent(new WatchedEvent(666,888,"Wonder_Woman", "Transformers: The Last Knight", 2));
        }

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

    public void testAccessMovieSort()
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

    public void testAccessUserSort()
    {
        ArrayList<User> users;
        User user;

        System.out.println("\nTesting Accessing the Sort function in AccessUser");

        users = new ArrayList<User>();

        // Lets try adding someone who is a JR
        testData.insertUser(new User(11, "Miggles JR", "anime4life", 21, 'M', Calendar.getInstance()));

        users.clear();
        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, true));

        user = users.get(2);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        user = users.get(3);
        assertEquals(11, user.getuID());
        assertEquals("Miggles JR", user.getName());

        // Lets try adding a similar name
        testData.insertUser(new User(12, "Miggled", "anime4life", 21, 'M', Calendar.getInstance()));

        users.clear();
        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, true));

        user = users.get(2);
        assertEquals(12, user.getuID());
        assertEquals("Miggled", user.getName());

        user = users.get(3);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        // Lets try adding a user with no name
        testData.insertUser(new User(13, "", "anime4life", 21, 'M', Calendar.getInstance()));

        users.clear();
        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, true));

        user = users.get(0);
        assertEquals(13, user.getuID());
        assertEquals("", user.getName());
    }

    public void testDataAccessUsers()
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

        movie = new Movie(1100, "Test Movie", 2018, "Comedy", date, "test.");
        assertNotNull(testData.deleteMovie(movie)); // delete something that isnt there.

        assertNull(testData.insertMovie(movie)); // test basic add

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

        movie.setDescription("New Description"); // update the description
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals("New Description", movie.getDescription());

        assertNull(testData.deleteMovie(movie)); // testing basic delete
        resetMovies(movies);
        assertEquals(9, movies.size());

        assertNotNull(testData.updateMovie(movie)); // try to update something that doesnt exist

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

        assertNotNull(testData.deleteMovie(movie)); // try to delete on an empty list

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
        user =  new User(1100, "Tester", "pass", 21, 'M', date);

        assertNull(testData.insertUser(user)); // test basic add

        assertNull(testData.getUsersAll(users));

        assertEquals(7, users.size()); // making sure the right size

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

        assertNull(testData.deleteUser(user)); // test basic delete
        resetUsers(users);
        assertEquals(6, users.size());

        assertNotNull(testData.deleteUser(user)); // delete something that doesnt

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

        assertNotNull(testData.deleteUser(user)); // try to delete on an empty list
        assertNotNull(testData.updateUser(user)); // try to update on an empty list
        assertNull(testData.insertUser(user)); // adding to an empty list

        assertNull(testData.deleteUser(user));
        assertNull(testData.insertUser(user)); // re-adding a movie that should have been deleted
        assertNull(testData.deleteUser(user));
    }

    public void testGetAllWatchedEvents()
    {
        ArrayList<WatchedEvent> events = new ArrayList<>();
        WatchedEvent event;

        testData.getAllWatchedEvents(events);
        assertEquals(10, events.size());

        event = events.get(0);
        assertEquals(111, event.getuID());
        assertEquals(111, event.getmID());
        assertEquals("Miggles", event.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", event.getMovieTitle());
        assertEquals(7, event.getRating());

        event = events.get(1);
        assertEquals(111, event.getuID());
        assertEquals(999, event.getmID());
        assertEquals("Miggles", event.getUserName());
        assertEquals("Terminator 2: Judgement Day", event.getMovieTitle());
        assertEquals(2, event.getRating());

        event = events.get(2);
        assertEquals(222, event.getuID());
        assertEquals(111, event.getmID());
        assertEquals("Smoo", event.getUserName());
        assertEquals("South Park: Bigger, Longer & Uncut", event.getMovieTitle());
        assertEquals(5, event.getRating());

        event = events.get(3);
        assertEquals(222, event.getuID());
        assertEquals(999, event.getmID());
        assertEquals("Smoo", event.getUserName());
        assertEquals("Terminator 2: Judgement Day", event.getMovieTitle());
        assertEquals(4, event.getRating());

        event = events.get(4);
        assertEquals(333, event.getuID());
        assertEquals(444, event.getmID());
        assertEquals("Andrew_Sempai", event.getUserName());
        assertEquals("Shrek", event.getMovieTitle());
        assertEquals(2, event.getRating());

        event = events.get(5);
        assertEquals(333, event.getuID());
        assertEquals(666, event.getmID());
        assertEquals("Andrew_Sempai", event.getUserName());
        assertEquals("The Ring", event.getMovieTitle());
        assertEquals(8, event.getRating());

        event = events.get(6);
        assertEquals(555, event.getuID());
        assertEquals(333, event.getmID());
        assertEquals("JiffyPB", event.getUserName());
        assertEquals("Toy Story", event.getMovieTitle());
        assertEquals(3, event.getRating());

        event = events.get(7);
        assertEquals(555, event.getuID());
        assertEquals(777, event.getmID());
        assertEquals("JiffyPB", event.getUserName());
        assertEquals("Mission Impossible: Rogue Nation", event.getMovieTitle());
        assertEquals(7, event.getRating());

        event = events.get(8);
        assertEquals(666, event.getuID());
        assertEquals(666, event.getmID());
        assertEquals("Wonder_Woman", event.getUserName());
        assertEquals("The Ring", event.getMovieTitle());
        assertEquals(9, event.getRating());

        event = events.get(9);
        assertEquals(666, event.getuID());
        assertEquals(888, event.getmID());
        assertEquals("Wonder_Woman", event.getUserName());
        assertEquals("Transformers: The Last Knight", event.getMovieTitle());
        assertEquals(2, event.getRating());
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
        watched.clear();
        movie = movies.get(1);
        System.out.println(movie.getmID());
        testData.getMovieViews(watched, movie);
        assertEquals(0, watched.size());

        // Check the third movie
        watched.clear();
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
        watched.clear();
        movie = movies.get(3);
        testData.getMovieViews(watched, movie);
        assertEquals(1, watched.size());

        watch = watched.get(0);

        assertEquals(333, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        // Check the fifth movie
        watched.clear();
        movie = movies.get(4);
        testData.getMovieViews(watched, movie);
        assertEquals(0,watched.size());

        // Check the sixth movie
        watched.clear();
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
        watched.clear();
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
        watched.clear();
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
        watched.clear();
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
        watched.clear();
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
        watched.clear();
        user = users.get(2);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(333, watch.getuID());
        assertEquals(444, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("Shrek", watch.getMovieTitle());
        assertEquals(2, watch.getRating());

        watch = watched.get(1);

        assertEquals(333, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Andrew_Sempai", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(8, watch.getRating());

        // Check the fourth user
        watched.clear();
        user = users.get(3);
        testData.getUserViews(watched, user);
        assertEquals(0, watched.size());

        // Check the fifth user
        watched.clear();
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
        watched.clear();
        user = users.get(5);
        testData.getUserViews(watched, user);
        assertEquals(2, watched.size());

        watch = watched.get(0);

        assertEquals(666, watch.getuID());
        assertEquals(666, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("The Ring", watch.getMovieTitle());
        assertEquals(9, watch.getRating());

        watch = watched.get(1);

        assertEquals(666, watch.getuID());
        assertEquals(888, watch.getmID());
        assertEquals("Wonder_Woman", watch.getUserName());
        assertEquals("Transformers: The Last Knight", watch.getMovieTitle());
        assertEquals(2, watch.getRating());
    }

    public void testGetMovieSublist()
    {
        ArrayList<User> users =  new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();

        User user;
        Movie movie;

        testData.getUsersAll(users);
        assertEquals(6, users.size());

        user = users.get(0);
        testData.getMovieSublist(movies, user);
        assertEquals(2, movies.size());

        movie = movies.get(0);
        assertEquals("South Park: Bigger, Longer & Uncut", movie.getTitle());

        movie = movies.get(1);
        assertEquals("Terminator 2: Judgement Day", movie.getTitle());

        movies.clear();
        testData.getMovieSublist(movies, new User(8, "A", "B", 21, 'M', Calendar.getInstance()));
        assertEquals(0, movies.size());
    }

    public void testGetUserSublist()
    {
        ArrayList<User> users =  new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();

        User user;
        Movie movie;
        testData.getMoviesAll(movies);

        movie = movies.get(0);
        testData.getUserSublist(users, movie);

        assertEquals(2, users.size());

        user = users.get(0);
        assertEquals("Miggles", user.getName());

        user = users.get(1);
        assertEquals("Smoo", user.getName());
    }


}
