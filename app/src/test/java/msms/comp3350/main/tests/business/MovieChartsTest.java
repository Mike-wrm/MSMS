package msms.comp3350.main.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.business.MovieCharts;
import msms.comp3350.objects.Movie;
import msms.comp3350.persistence.DataAccessor;


public class MovieChartsTest extends TestCase{

    DataAccessor testData;

    public MovieChartsTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        testData = Services.createDataAccess("temp");
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

        System.out.println("\nTesting Accessing MovieChart");

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Action", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Family", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Comedy", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Drama", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Fantasy", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Horror", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Sci-Fi", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Recent", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "Trending", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "", Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, null, Calendar.getInstance(), "Ipsum Lorem..." ));

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
        testData.insertMovie(new Movie("TestMovie", 2002, 7, "test", Calendar.getInstance(), "Ipsum Lorem..." ));

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


}
