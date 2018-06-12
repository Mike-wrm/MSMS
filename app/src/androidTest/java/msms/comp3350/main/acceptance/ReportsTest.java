package msms.comp.main.acceptance;

import junit.framework.Assert;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import msms.comp.presentation.MainActivity;


public class ReportsTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public ReportsTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    // Big Story - See Basic Demographics
    public void testSeeDemos()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Reports");
        solo.assertCurrentActivity("Expected activity ReportListActivity", "ReportListActivity");

        // Lets just make sure they are all there and click on them. We cant test that they are right, but we can make sure clicking on them
        // doesnt break the system.

        Assert.assertTrue(solo.searchText("Movies by Category"));
        solo.clickOnText("Movies by Category");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Movies by Decade"));
        solo.clickOnText("Movies by Decade");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Movies by Rating"));
        solo.clickOnText("Movies by Rating");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Users by Age Range"));
        solo.clickOnText("Users by Age Range");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Users by Gender"));
        solo.clickOnText("Users by Gender");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Users by Rating"));
        solo.clickOnText("Users by Rating");
        solo.goBack();
    }

    // Big Story - See History
    public void testSeeUserHistory()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("Miggles"));
        solo.clickOnText("Miggles");

        Assert.assertTrue(solo.searchButton("View Movie Data"));
        solo.clickOnButton("View Movie Data");

        Assert.assertTrue(solo.searchText("Miggles viewed South Park: Bigger, Longer & Uncut and rated it 7/10."));
        Assert.assertTrue(solo.searchText("Miggles viewed Terminator 2: Judgement Day and rated it 2/10."));
        Assert.assertFalse(solo.searchText("Miggles viewed Toy Story and rated it 5/10."));
    }

    public void testSeeMovieHistory()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("Toy Story"));
        solo.clickOnText("Toy Story");

        Assert.assertTrue(solo.searchButton("View User Data"));
        solo.clickOnButton("View User Data");

        Assert.assertTrue(solo.searchText("JiffyPB viewed Toy Story and rated it 3/10."));
        Assert.assertFalse(solo.searchText("TestBoi viewed Toy Story and rated it 9/10."));
    }

    // Big Story See individual Reports
    public void testSeeUserReports()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("Miggles"));
        solo.clickOnText("Miggles");

        Assert.assertTrue(solo.searchButton("View Movie Data"));
        solo.clickOnButton("View Movie Data");

        // We can't test to see if the graphs are correct, but we can test to see if getting the graph works
        // and doesnt kill the app

        Assert.assertTrue(solo.searchText("Reports:"));
        Assert.assertTrue(solo.searchText("Movies by Category"));
        solo.clickOnText("Movies by Category");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Movies by Decade"));
        solo.clickOnText("Movies by Decade");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Movies by Rating"));
        solo.clickOnText("Movies by Rating");
        solo.goBack();
    }

    public void testSeeMovieReports()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Movies");
        solo.assertCurrentActivity("Expected activity MovieListActivity", "MovieListActivity");

        Assert.assertTrue(solo.searchText("Transformers: The Last Knight"));
        solo.clickOnText("Transformers: The Last Knight");

        Assert.assertTrue(solo.searchButton("View User Data"));
        solo.clickOnButton("View User Data");


        Assert.assertTrue(solo.searchText("Reports:"));
        Assert.assertTrue(solo.searchText("Users by Age Range"));
        solo.clickOnText("Users by Age Range");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Users by Gender"));
        solo.clickOnText("Users by Gender");
        solo.goBack();

        Assert.assertTrue(solo.searchText("Users by Rating"));
        solo.clickOnText("Users by Rating");
        solo.goBack();

    }

}
