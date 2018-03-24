package msms.comp3350.main.acceptance;

import junit.framework.Assert;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import msms.comp3350.presentation.MainActivity;


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

}
