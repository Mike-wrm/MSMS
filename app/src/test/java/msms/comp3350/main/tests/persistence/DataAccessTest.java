package msms.comp3350.main.tests.persistence;


import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.Movie;
import msms.comp3350.business.SortEnums;
import msms.comp3350.objects.User;
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
        //testData = Services.createDataAccess("test");
        testData = Services.createDataAccess("temp");
    }

    public void tearDown()
    {
        System.out.println("Finishing a Persistence Test");
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

    public void rebuildMovie() //if we delete all of the items, we need to make sure it gets put back
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
        testData.insertMovie(movie);

        movie = new Movie("Eddie Murphy: Raw", 1987, 5, "Comedy", date2, "Ipsum Lorem...");
        movie.setmID(222);
        testData.insertMovie(movie);

        movie = new Movie("Toy Story", 1995, 10, "Family", date3, "Ipsum Lorem...");
        movie.setmID(333);
        testData.insertMovie(movie);

        movie = new Movie("Shrek", 2001, 8, "Family", date4, "Ipsum Lorem...");
        movie.setmID(444);
        testData.insertMovie(movie);

        movie = new Movie("Friday the 13th", 2009, 3, "Horror", date5, "Ipsum Lorem...");
        movie.setmID(555);
        testData.insertMovie(movie);

        movie = new Movie("The Ring", 2002, 6, "Horror", date6, "Ipsum Lorem...");
        movie.setmID(666);
        testData.insertMovie(movie);

        movie = new Movie("Mission Impossible: Rogue Nation", 2015, 8, "Action", date7, "Ipsum Lorem...");
        movie.setmID(777);
        testData.insertMovie(movie);

        movie = new Movie("Transformers: The Last Knight", 2017, 2, "Action", date8, "Ipsum Lorem...");
        movie.setmID(888);
        testData.insertMovie(movie);

        movie = new Movie("Terminator 2: Judgement Day", 1991, 8, "Action", date9, "Ipsum Lorem...");
        movie.setmID(999);
        testData.insertMovie(movie);
    }

    public void rebuildUser()
    {
        User user;

        Calendar userDate1 = Calendar.getInstance();
        userDate1.set(2018,3,30);
        Calendar userDate2 = Calendar.getInstance();
        userDate2.set(2020,11,31);

        user = new User(111, "Miggles", "anime4life", 21, 'M', userDate1);
        testData.insertUser(user);

        user = new User(222, "Smoo", "getoffmylawn", 32, 'M', userDate1);
        testData.insertUser(user);

        user = new User(333, "Andrew_Sempai", "iheartmybeard", 23, 'M', userDate1);
        testData.insertUser(user);

        user = new User(444, "TestBoi", "supertester", 24, 'M', userDate1);
        testData.insertUser(user);

        user = new User(555, "JiffyPB", "walmartisevil", 21, 'M', userDate1);
        testData.insertUser(user);

        user = new User(666, "Wonder_Woman", "DCU", 82, 'F', userDate2);
        testData.insertUser(user);
    }

    public void testDataAccessMovie()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Data Access for the movies");

        movies = new ArrayList<Movie>();

        assertNull(testData.getMoviesAll(movies));
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

    public void testSortingAccessMovie()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Accessing the Data Access for the movies");

        movies = new ArrayList<Movie>();

        assertNull(testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, false));
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
        assertNull(testData.getMoviesAllSorted(movies, SortEnums.MovieSortField.TITLE, true));

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

    public void testSortingAccessUser()
    {
        ArrayList<User> users;
        User user;

        System.out.println("\nTesting Accessing the Data Access for the users");

        users = new ArrayList<User>();

        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, false));
        assertEquals(6, users.size());

        user = users.get(0);
        assertEquals(666, user.getuID());
        assertEquals("Wonder_Woman", user.getName());

        user = users.get(1);
        assertEquals(555, user.getuID());
        assertEquals("JiffyPB", user.getName());

        user = users.get(2);
        assertEquals(444, user.getuID());
        assertEquals("TestBoi", user.getName());

        user = users.get(3);
        assertEquals(333, user.getuID());
        assertEquals("Andrew_Sempai", user.getName());

        user = users.get(4);
        assertEquals(222, user.getuID());
        assertEquals("Smoo", user.getName());

        user = users.get(5);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        // test ascending order
        users.clear();
        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, true));

        user = users.get(0);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        user = users.get(1);
        assertEquals(222, user.getuID());
        assertEquals("Smoo", user.getName());

        user = users.get(2);
        assertEquals(333, user.getuID());
        assertEquals("Andrew_Sempai", user.getName());

        user = users.get(3);
        assertEquals(444, user.getuID());
        assertEquals("TestBoi", user.getName());

        user = users.get(4);
        assertEquals(555, user.getuID());
        assertEquals("JiffyPB", user.getName());

        user = users.get(5);
        assertEquals(666, user.getuID());
        assertEquals("Wonder_Woman", user.getName());
    }

    public void testMovieAccessChange()
    {
        ArrayList<Movie> movies;
        Movie movie;

        System.out.println("\nTesting Changing the Data Access for the movies");

        Calendar date = Calendar.getInstance();
        date.set(2020, 11, 21);

        Calendar date2 = Calendar.getInstance();
        date2.set(2022,10, 8);

        movie = new Movie("Test Movie", 2018, 8, "Comedy", date, "test.");
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

        movie.setUserScore(7); // update the score
        assertNull(testData.updateMovie(movie));
        resetMovies(movies);
        movie = movies.get(9);
        assertEquals(7, movie.getUserScore());

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

        rebuildMovie();
    }

    public void testUserAccessChange(){
        ArrayList<User> users;
        User user;

        Calendar date = Calendar.getInstance();
        date.set(2020, 11, 21);

        Calendar date2 = Calendar.getInstance();
        date2.set(2022,10, 8);

        System.out.println("\nTesting Changing the Data Access for the users");

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

        rebuildUser();
    }

}
