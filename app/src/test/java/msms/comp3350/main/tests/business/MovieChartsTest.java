package msms.comp.main.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.business.MovieCharts;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.Movie;
import msms.comp.objects.User;
import msms.comp.persistence.DataAccessor;

public class MovieChartsTest extends TestCase{

    DataAccessor testData;

    public MovieChartsTest(String arg0)
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
        System.out.println("Finished a MovieCharts Test");
        testData.close();
    }

    public void testMovieChartCategoriesGet()
    {
        // Lets just check and make sure everything is alright
        String[][] data = MovieCharts.getMovieCategories();

        System.out.println("\nTesting Accessing Categories MovieChart");

        assertEquals(2, data.length);
        assertEquals(10, data[0].length);
        assertEquals(10, data[1].length);

        assertEquals("None", data[0][0]);
        assertEquals("Action", data[0][1]);
        assertEquals("Family", data[0][2]);
        assertEquals("Comedy", data[0][3]);
        assertEquals("Drama", data[0][4]);
        assertEquals("Fantasy", data[0][5]);
        assertEquals("Horror", data[0][6]);
        assertEquals("Sci-Fi", data[0][7]);
        assertEquals("Recent", data[0][8]);
        assertEquals("Trending", data[0][9]);

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("2", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
    }

    public void testMovieChartCategoriesChange()
    {
        String[][] data;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        System.out.println("\nStarting a getCategories change test in MovieCharts");

        testData.getMoviesAll(movies);

        // We are going to delete all the movies in here first. making sure it works as we delete
        // and works if th list is empty
        // get rid of the first movie
        movie = movies.get(0);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]); // We have to check to make sure deleting  each type of movie doesnt also delete another
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the second movie
        movie = movies.get(1);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the third movie
        movie = movies.get(2);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the fourth movie
        movie = movies.get(3);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the fifth movie
        movie = movies.get(4);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the sixth movie
        movie = movies.get(5);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the seventh movie
        movie = movies.get(6);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("2", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the eighth movie
        movie = movies.get(7);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // get rid of the ninth movie
        movie = movies.get(8);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Lets add some movies!
        // Lets add an action movie
        testData.insertMovie(new Movie(1,"TestMovie", 2002,"Action", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]); // We have to check to make sure adding each type of movie doesnt add another
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Adding a family movie
        testData.insertMovie(new Movie(2, "TestMovie", 2002,"Family", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a comedy movie
        testData.insertMovie(new Movie(3, "TestMovie", 2002,"Comedy", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a drama movie
        testData.insertMovie(new Movie(4,"TestMovie", 2002,"Drama", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a fantasy movie
        testData.insertMovie(new Movie(5, "TestMovie", 2002,"Fantasy", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a horror movie
        testData.insertMovie(new Movie(6,"TestMovie", 2002,"Horror", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a Sci-Fi movie
        testData.insertMovie(new Movie(7, "TestMovie", 2002,"Sci-Fi", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a Recent movie
        testData.insertMovie(new Movie(8, "TestMovie", 2002,"Recent", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("0", data[1][9]);

        // Add a Trending movie
        testData.insertMovie(new Movie(9, "TestMovie", 2002,"Trending", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);

        //Add a blank string
        testData.insertMovie(new Movie(10,"TestMovie", 2002, "", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);

        //Add a null string
        testData.insertMovie(new Movie(11,"TestMovie", 2002,null, Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);

        //Add a string that isnt a category
        testData.insertMovie(new Movie(12, "TestMovie", 2002,"test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieCategories();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);
    }

    public void testMovieChartCategoriesUserGive()
    {
        String[][] data;
        ArrayList<User> users = new ArrayList<User>();
        User user;

        System.out.println("\nTesting Accessing Categories giving it a specific user MovieChart");

        testData.getUsersAll(users);

        user = users.get(0);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        user = users.get(1);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        user = users.get(2);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        user = users.get(3);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        user = users.get(4);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);

        user = users.get(5);

        data = MovieCharts.getMovieCategories(user);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
    }

    public void testMovieChartDecadesGet()
    {
        String[][] data = MovieCharts.getMovieDecades();

        System.out.println("\nTesting Accessing decades in MovieChart");

        assertEquals(2, data.length);
        assertEquals(9, data[0].length);
        assertEquals(9, data[1].length);

        assertEquals("<", data[0][0]);
        assertEquals("50s", data[0][1]);
        assertEquals("60s", data[0][2]);
        assertEquals("70s", data[0][3]);
        assertEquals("80s", data[0][4]);
        assertEquals("90s", data[0][5]);
        assertEquals("00s", data[0][6]);
        assertEquals("10s", data[0][7]);
        assertEquals(">", data[0][8]);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("3", data[1][5]);
        assertEquals("3", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);
    }

    public void testMovieChartDecadesChange()
    {
        String[][] data;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        System.out.println("\nStarting a getDecades change test in MovieCharts");

        testData.getMoviesAll(movies);

        // We are going to delete all the movies in here first. making sure it works as we delete
        // and works if th list is empty
        // get rid of the first movie
        movie = movies.get(0);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("3", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the second movie
        movie = movies.get(1);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("3", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the third movie
        movie = movies.get(2);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("3", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the fourth movie
        movie = movies.get(3);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the fifth movie
        movie = movies.get(4);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the sixth movie
        movie = movies.get(5);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the seventh movie
        movie = movies.get(6);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the eighth movie
        movie = movies.get(7);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // get rid of the ninth movie
        movie = movies.get(8);
        testData.deleteMovie(movie);
        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // Lets add some movies!
        // 50's Movie
        testData.insertMovie(new Movie(0, "TestMovie", 1950, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 60's Movie
        testData.insertMovie(new Movie(1, "TestMovie", 1960, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 70's Movie
        testData.insertMovie(new Movie(2, "TestMovie", 1970, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 80's Movie
        testData.insertMovie(new Movie(3, "TestMovie", 1980,  "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 90's Movie
        testData.insertMovie(new Movie(4, "TestMovie", 1990,"test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 00's Movie
        testData.insertMovie(new Movie(5, "TestMovie", 2000,"test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        // 10's Movie
        testData.insertMovie(new Movie(6, "TestMovie", 2010,"test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);

        // Add a date under the 50s
        testData.insertMovie(new Movie(7, "TestMovie", 1900, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("1", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);

        // Add a date in the future
        testData.insertMovie(new Movie(8, "TestMovie", 2030, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("1", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("1", data[1][8]);

        // Add a negative date
        testData.insertMovie(new Movie(9, "TestMovie", 2030, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

        data = MovieCharts.getMovieDecades();

        assertEquals("1", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("2", data[1][8]);
    }

    public void testMovieChartDecadesUserGive()
    {
        String[][] data;
        ArrayList<User> users = new ArrayList<User>();
        User user;

        System.out.println("\nTesting Accessing decades giving it a specific user in MovieChart");

        testData.getUsersAll(users);

        user = users.get(0);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        user = users.get(1);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        user = users.get(2);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        user = users.get(3);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);

        user = users.get(4);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);

        user = users.get(5);

        data = MovieCharts.getMovieDecades(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
    }

    public void testMovieChartRatingsGet()
    {
        String[][] data = MovieCharts.getMovieRatings();

        // Since all of these are taken from WatchedEvents, they cannot change.
        System.out.println("\nTesting Accessing ratings in MovieChart");

        assertEquals("0", data[0][0]);
        assertEquals("1", data[0][1]);
        assertEquals("2", data[0][2]);
        assertEquals("3", data[0][3]);
        assertEquals("4", data[0][4]);
        assertEquals("5", data[0][5]);
        assertEquals("6", data[0][6]);
        assertEquals("7", data[0][7]);
        assertEquals("8", data[0][8]);
        assertEquals("9", data[0][9]);
        assertEquals("10", data[0][10]);
        assertEquals("", data[0][11]);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("3", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("2", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);
    }

    public void testMovieChartRatingsUserGive()
    {
        String[][] data;
        ArrayList<User> users = new ArrayList<User>();
        User user;

        System.out.println("\nTesting Accessing ratings giving it a specific user MovieChart");

        testData.getUsersAll(users);

        user = users.get(0);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(1);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(2);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(3);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(3);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(4);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        user = users.get(5);
        data = MovieCharts.getMovieRatings(user);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("1", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);
    }

    public void testMovieChartRatingMovieGive()
    {
        String[][] data;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        System.out.println("\nStarting a getGender Access Test in UserCharts");

        testData.getMoviesAll(movies);
        movie = movies.get(0);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(1);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(2);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(3);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(4);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(5);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("1", data[1][8]);
        assertEquals("1", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(6);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("1", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(7);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);

        movie = movies.get(8);
        data = MovieCharts.getMovieRatings(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
        assertEquals("0", data[1][8]);
        assertEquals("0", data[1][9]);
        assertEquals("0", data[1][10]);
        assertEquals("0", data[1][11]);
    }


}
