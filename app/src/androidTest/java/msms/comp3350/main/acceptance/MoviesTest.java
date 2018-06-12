package msms.comp.main.acceptance;


import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.objects.Movie;
import msms.comp.persistence.DataAccessor;
import msms.comp.presentation.MainActivity;


public class MoviesTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;
    private DataAccessor cleanUp;

    public MoviesTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
        cleanUp = Services.getDataAccess();
    }

    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    // Big Story we want to be able to change movies
    public void testMovieAdd()
    {
        // We wanted to be able to add a user
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchButton("Add Movie"));
        solo.clickOnButton("Add Movie");

        solo.enterText(0, "TestMovie");
        solo.enterText(1, "Ipsum Lorem...");
        solo.enterText(2, "1995");
        solo.enterText(3, "5");
        solo.pressSpinnerItem(0,3);

        solo.clickOnButton("Change Date");
        solo.setDatePicker(0, 2019, 11, 21);
        solo.clickOnButton(7);

        solo.clickOnButton("Add");

        solo.goBack();

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("TestMovie"));

        cleanUp.deleteMovie(new Movie(5, "TestMovie", 1995, "Comedy", Calendar.getInstance(), "Ipsum Lorem..."));
    }

    public void testMovieInvalid()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchButton("Add Movie"));
        solo.clickOnButton("Add Movie");

        solo.enterText(0, "TestMovie");
        solo.enterText(1, "Ipsum Lorem...");
        solo.enterText(2, "1995");
        solo.enterText(3, "test");
        solo.pressSpinnerItem(0,3);

        solo.clickOnButton("Change Date");
        solo.setDatePicker(0, 2019, 11, 21);
        solo.clickOnButton(7);

        solo.clickOnButton("Add");

        Assert.assertTrue(solo.searchText("Warning"));
        Assert.assertTrue(solo.searchText("Movie ID must be a number."));
    }

    public void testMovieDelete()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("Eddie Murphy: Raw"));
        solo.clickOnText("Eddie Murphy: Raw");

        solo.clickOnButton("Delete");
        solo.clickOnButton("Delete Me");

        solo.goBack();

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertFalse(solo.searchText("Eddie Murphy: Raw"));

        Calendar cleanCal = Calendar.getInstance();
        cleanCal.set(2018,6,23);
        cleanUp.insertMovie(new Movie(222,"Eddie Murphy: Raw", 1987,"Comedy", cleanCal, "Ipsum Lorem..."));
    }

    public void testMovieEdit()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("Toy Story"));
        solo.clickOnText("Toy Story");

        solo.clickOnButton("Change Date");
        solo.setDatePicker(0, 2019, 11, 21);
        solo.clickOnButton(7);

        solo.clearEditText(0);
        solo.enterText(0, "new movie");
        solo.clearEditText(1);
        solo.enterText(1, "new description");
        solo.clearEditText(2);
        solo.enterText(2, "2002");
        solo.pressSpinnerItem(0, 1);
        solo.clickOnButton("Update");

        // Lets go back home
        solo.goBack();

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");


        Assert.assertTrue(solo.searchText("new movie"));
        solo.clickOnText("new movie");


        // Check Everything is changed
        Assert.assertTrue(solo.searchText("new movie"));
        Assert.assertTrue(solo.searchText("new description"));
        Assert.assertTrue(solo.searchText("2002"));
        Assert.assertTrue(solo.isSpinnerTextSelected(0, "Comedy"));

        solo.clickOnButton("Change Date");
        solo.setDatePicker(0, 2018, 3, 11);
        solo.clickOnButton(7);

        solo.clearEditText(0);
        solo.enterText(0, "Toy Story");
        solo.clearEditText(1);
        solo.enterText(1, "Ipsum Lorem...");
        solo.clearEditText(2);
        solo.enterText(2, "1995");
        solo.pressSpinnerItem(0, -1);
        solo.clickOnButton("Update");
    }

    // Big Story we want to be able to view movies
    public void testViewMovies()
    {
        // We wanted to be able to see all movies, so lets make sure the basic ones are there
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("South Park: Bigger, Longer & Uncut"));
        Assert.assertTrue(solo.searchText("Eddie Murphy: Raw"));
        Assert.assertTrue(solo.searchText("Toy Story"));
        Assert.assertTrue(solo.searchText("Shrek"));
        Assert.assertTrue(solo.searchText("Friday the 13th"));
        Assert.assertTrue(solo.searchText("The Ring"));
        Assert.assertTrue(solo.searchText("Mission Impossible: Rogue Nation"));
        Assert.assertTrue(solo.searchText("Transformers: The Last Knight"));
        Assert.assertTrue(solo.searchText("Terminator 2: Judgement Day"));
    }

    public void testViewMovie()
    {
        // We wanted to see a movies details by clicking on them
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("Shrek"));
        solo.clickOnText("Shrek");
        Assert.assertTrue(solo.searchText("Shrek"));
        Assert.assertTrue(solo.searchText("7/7/2019"));
        Assert.assertTrue(solo.searchText("444"));
        Assert.assertTrue(solo.searchText("2001"));
        Assert.assertTrue(solo.searchText("Ipsum Lorem..."));
        Assert.assertTrue(solo.isSpinnerTextSelected(0, "Family"));
    }

    //Big Story - Search Functionality
    public void testMovieSearch()
    {
        // Wanted to be able to search movies
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        solo.clickOnScreen(1042, 168);

        solo.typeText(0, "South Park");

        Assert.assertTrue(solo.searchText("South Park: Bigger, Longer & Uncut"));
        Assert.assertFalse(solo.searchText("Eddie Murphy: Raw"));
        Assert.assertFalse(solo.searchText("Toy Story"));
        Assert.assertFalse(solo.searchText("Shrek"));
        Assert.assertFalse(solo.searchText("Friday the 13th"));
        Assert.assertFalse(solo.searchText("The Ring"));
        Assert.assertFalse(solo.searchText("Mission Impossible: Rogue Nation"));
        Assert.assertFalse(solo.searchText("Transformers: The Last Knight"));
        Assert.assertFalse(solo.searchText("Terminator 2: Judgement Day"));
    }

    // Big Story - Sort Functionality
    public void testMovieSort()
    {
        // Want to be able to sort the movies
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        // Check the Ascending sort
        solo.clickOnScreen(1101, 148);
        solo.clickOnScreen(1101, 225);

        solo.clickOnScreen(150, 240);
        Assert.assertTrue(solo.searchText("222"));

        solo.goBack();
        solo.goBack();

        // Check the descending sort
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        solo.clickOnScreen(1101, 148);
        solo.clickOnScreen(1101, 345);

        solo.clickOnScreen(150, 240);
        Assert.assertTrue(solo.searchText("888"));
    }

}