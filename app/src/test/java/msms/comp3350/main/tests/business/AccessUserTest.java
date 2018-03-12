package msms.comp3350.main.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.User;
import msms.comp3350.business.AccessUsers;


public class AccessUserTest extends TestCase
{
    public AccessUserTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessUser()
    {
        Services.createDataAccess("temp");
        AccessUsers list = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();

        list.getUsers(users);

        assertNotNull(users);
        assertFalse(users.isEmpty());

        User user;
        User testUser1;
        User testUser2;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        Calendar userDate1 = Calendar.getInstance();
        userDate1.set(2018,3,30);
        Calendar userDate2 = Calendar.getInstance();
        userDate2.set(2020,11,31);

        System.out.println("\nStarting testAccessUser");

        testUser1 = new User(7, "John Doe", "pass",  25, 'M', endDate);
        testUser2 = new User(8, "Jane Doe", "pass1",  26, 'F', endDate);

        assertNull(list.insertUser(testUser1));
        assertEquals("'John Doe' is already added.", list.insertUser(testUser1));

        assertNull(list.updateUser(testUser1));
        assertEquals("'Jane Doe' cannot be found.", list.updateUser(testUser2));

        assertNull(list.deleteUser(testUser1));
        assertEquals("'John Doe' cannot be found.", list.deleteUser(testUser1));

        // empty out the list
        user = new User(111, "Miggles", "anime4life", 21, 'M', userDate1);
        assertNull(list.deleteUser(user));
        user = new User(222, "Smoo", "getoffmylawn", 32, 'M', userDate1);
        assertNull(list.deleteUser(user));
        user = new User(333, "Andrew_Sempai", "iheartmybeard", 23, 'M', userDate1);
        assertNull(list.deleteUser(user));
        user = new User(444, "TestBoi", "supertester", 24, 'M', userDate1);
        assertNull(list.deleteUser(user));
        user = new User(555, "JiffyPB", "walmartisevil", 21, 'M', userDate1);
        assertNull(list.deleteUser(user));
        user = new User(666, "Wonder_Woman", "DCU", 82, 'F', userDate2);
        assertNull(list.deleteUser(user));

        // see if they still work on an empty list

        assertEquals("'John Doe' cannot be found.",list.deleteUser(testUser1));

        assertEquals("'John Doe' cannot be found.", list.updateUser(testUser1));

        assertNull(list.insertUser(testUser1));

        // Testing: String validateUser (int uID, String userName, String password, int age, char gender, Calendar endDate)
        Calendar yearInPast = Calendar.getInstance();
        yearInPast.add(Calendar.YEAR, -1);

        Calendar monthInPast = Calendar.getInstance();
        monthInPast.add(Calendar.MONTH, -1);

        Calendar dayInPast = Calendar.getInstance();
        dayInPast.add(Calendar.DAY_OF_MONTH, -1);
        // Valid user
        assertNull(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', userDate1));
        // Invalid uID
        assertEquals(AccessUsers.validateUser(0, "Miggles", "anime4life", 21, 'M', userDate1), "Invalid user ID entered.");
        assertEquals(AccessUsers.validateUser(-9001, "Miggles", "anime4life", 21, 'M', userDate1), "Invalid user ID entered.");
        // Invalid username
        assertEquals(AccessUsers.validateUser(111, "", "anime4life", 21, 'M', userDate1), "You need to name your user.");
        assertEquals(AccessUsers.validateUser(111, null, "anime4life", 21, 'M', userDate1), "You need to name your user.");
        // Invalid password
        assertEquals(AccessUsers.validateUser(111, "Miggles", "", 21, 'M', userDate1), "You need to enter a password.");
        assertEquals(AccessUsers.validateUser(111, "Miggles", null, 21, 'M', userDate1), "You need to enter a password.");
        // Invalid age
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", 0, 'M', userDate1), "Invalid age entered.");
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", -9001, 'M', userDate1), "Invalid age entered.");
        // Invalid gender
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'q', userDate1), "Invalid gender entered.");
        // Invalid date
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', yearInPast), "Invalid date entry. Can't enter user with expired account");
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', monthInPast), "Invalid date entry. Can't enter user with expired account");
        assertEquals(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', dayInPast), "Invalid date entry. Can't enter user with expired account");

    }

}
