package msms.comp.main.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.objects.User;
import msms.comp.persistence.DataAccessor;
import msms.comp.presentation.MainActivity;

public class UsersTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public UsersTest()
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

    // Big Story - Changing Users ---------------------
    public void testEditUser()
    {
        // We wanted to be able to change the users
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("Miggles"));
        solo.clickOnText("Miggles");

        // Update the entry
        solo.clearEditText(0);
        solo.enterText(0, "Micheal");
        solo.clearEditText(1);
        solo.enterText(1, "newPass");
        solo.clearEditText(2);
        solo.enterText(2, "28");
        solo.pressSpinnerItem(0, 1);
        solo.clickOnButton("Update");

        // Lets go back home
        solo.goBack();

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        // Check everything is right
        Assert.assertTrue(solo.searchText("Micheal"));
        solo.clickOnText("Micheal");
        Assert.assertTrue(solo.searchText("Micheal"));
        Assert.assertTrue(solo.searchText("newPass"));
        Assert.assertTrue(solo.searchText("28"));
        Assert.assertTrue(solo.isSpinnerTextSelected("Female"));


        // Clean up
        solo.clearEditText(0);
        solo.enterText(0, "Miggles");
        solo.clearEditText(1);
        solo.enterText(1, "anime4life");
        solo.clearEditText(2);
        solo.enterText(2, "21");
        solo.pressSpinnerItem(0, -1);
        solo.clickOnButton("Update");
    }

    public void testDeleteUser()
    {
        // We wanted to have the ability for the user to delete users.
        // Since we decided to not have an add button for users, we will just clean up directly by adding it back
        DataAccessor cleanUp = Services.getDataAccess();

        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("TestBoi"));
        solo.clickOnText("TestBoi");

        solo.clickOnButton("Delete");
        solo.clickOnButton("Delete Me");

        // Lets go back home
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertFalse(solo.searchText("TestBoi"));

        // Clean Up
        Calendar cleanCal = Calendar.getInstance();
        cleanCal.set(2018,3,30);
        cleanUp.insertUser(new User(444, "TestBoi", "supertester", 24, 'M', cleanCal));
    }

    // Big Story - View Users -------------------------
    public void testViewUsers()
    {
        // We wanted to be able to see all users, so lets make sure the basic ones are there
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("Miggles"));
        Assert.assertTrue(solo.searchText("Smoo"));
        Assert.assertTrue(solo.searchText("Andrew_Sempai"));
        Assert.assertTrue(solo.searchText("TestBoi"));
        Assert.assertTrue(solo.searchText("JiffyPB"));
        Assert.assertTrue(solo.searchText("Wonder_Woman"));
    }

    public void testViewUser()
    {
        // We wanted to see users details by clicking on them
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        Assert.assertTrue(solo.searchText("Miggles"));
        solo.clickOnText("Miggles");
        Assert.assertTrue(solo.searchText("Miggles"));
        Assert.assertTrue(solo.searchText("anime4life"));
        Assert.assertTrue(solo.searchText("21"));
        Assert.assertTrue(solo.searchText("4/30/2018"));
        Assert.assertTrue(solo.isSpinnerTextSelected("Male"));
    }

    // Big Story -  Sort ----------------------------------
    public void testSortUser()
    {
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        // Check the Ascending sort
        solo.clickOnScreen(1146, 65);
        solo.clickOnScreen(1146, 192);


        solo.clickOnScreen(107, 244);
        Assert.assertTrue(solo.searchText("333"));

        solo.goBack();
        solo.goBack();


        // Check the descending sort
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Users");
        solo.assertCurrentActivity("Expected activity UserListActivity", "UserListActivity");

        solo.clickOnScreen(1146, 65);
        solo.clickOnScreen(1146, 297);

        solo.clickOnScreen(150, 240);
        Assert.assertTrue(solo.searchText("666")); }


}
