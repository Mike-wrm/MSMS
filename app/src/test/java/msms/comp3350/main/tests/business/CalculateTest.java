package msms.comp.main.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import msms.comp.application.Services;
import msms.comp.business.Calculate;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.Movie;
import msms.comp.persistence.DataAccessor;

public class CalculateTest extends TestCase{

    private DataAccessor testData;

    public CalculateTest(String arg0)
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
        testData.close();
        System.out.println("Finished an AccessMovies Test");
    }

    public void testCalculate()
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        testData.getMoviesAll(movies);

        movie = movies.get(0);
        assertEquals("6.0/10", Calculate.avgRating(movie));

        movie = movies.get(1);
        assertEquals("N/A", Calculate.avgRating(movie));

        movie = movies.get(2);
        assertEquals("3.0/10", Calculate.avgRating(movie));

        movie = movies.get(3);
        assertEquals("2.0/10", Calculate.avgRating(movie));

        movie = movies.get(4);
        assertEquals("N/A", Calculate.avgRating(movie));

        movie = movies.get(5);
        assertEquals("8.5/10", Calculate.avgRating(movie));

        movie = movies.get(6);
        assertEquals("7.0/10", Calculate.avgRating(movie));

        movie = movies.get(7);
        assertEquals("2.0/10", Calculate.avgRating(movie));

        movie = movies.get(8);
        assertEquals("3.0/10", Calculate.avgRating(movie));
    }

}
