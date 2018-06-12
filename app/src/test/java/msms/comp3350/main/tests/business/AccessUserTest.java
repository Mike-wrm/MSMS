package msms.comp.main.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.User;
import msms.comp.business.AccessUsers;
import msms.comp.business.SortEnums;
import msms.comp.persistence.DataAccessor;

public class AccessUserTest extends TestCase
{
    private DataAccessor testData;
    private AccessUsers list;

    public AccessUserTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        TempData newData = new TempData();
        testData = Services.createDataAccess(newData);
        testData.open("temp");
        list = new AccessUsers();
    }

    public void tearDown()
    {
        System.out.println("Finished an AccessUser Test");
        testData.close();
    }

    public void resetUsers(ArrayList<User> users)
    {
        users.clear();
        assertNull(list.getUsers(users));
    }

    public void testAccessUserAccess()
    {
     ArrayList<User> users;
     User user;

     // first lets just access them normally
        System.out.println("\nTesting Accessing the Data Access for the users");

        users = new ArrayList<User>();

        assertNull(list.getUsers(users));
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

    public void testAccessUserChange()
    {
        ArrayList<User> users = new ArrayList<User>();

        User user;

        User testUser1;
        User testUser2;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        System.out.println("\nTesting Accessing Data after change in AccessUser");

        list.getUsers(users);

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(6, users.size());

        testUser1 = new User(7, "John Doe", "pass",  25, 'm', endDate);
        testUser2 = new User(8, "Jane Doe", "pass1",  26, 'f', endDate);

        assertNull(list.insertUser(testUser1));
        resetUsers(users);
        assertEquals(7, users.size());
        assertEquals("'John Doe' is already added.", list.insertUser(testUser1));

        assertNull(list.updateUser(testUser1));
        resetUsers(users);
        assertEquals(7, users.size());
        assertEquals("'Jane Doe' cannot be found.", list.updateUser(testUser2));

        assertNull(list.deleteUser(testUser1));
        resetUsers(users);
        assertEquals(6, users.size());
        assertEquals("'John Doe' cannot be found.", list.deleteUser(testUser1));

        // empty out the list
        resetUsers(users);

        user = users.get(0);
        assertNull(list.deleteUser(user));
        user = users.get(1);
        assertNull(list.deleteUser(user));
        user = users.get(2);
        assertNull(list.deleteUser(user));
        user = users.get(3);
        assertNull(list.deleteUser(user));
        user = users.get(4);
        assertNull(list.deleteUser(user));
        user = users.get(5);
        assertNull(list.deleteUser(user));

        // see if they still work on an empty list
        assertEquals("'John Doe' cannot be found.",list.deleteUser(testUser1));

        assertEquals("'John Doe' cannot be found.", list.updateUser(testUser1));

        assertNull(list.insertUser(testUser1));
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
        assertEquals(444, user.getuID());
        assertEquals("TestBoi", user.getName());

        user = users.get(2);
        assertEquals(222, user.getuID());
        assertEquals("Smoo", user.getName());

        user = users.get(3);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        user = users.get(4);
        assertEquals(555, user.getuID());
        assertEquals("JiffyPB", user.getName());

        user = users.get(5);
        assertEquals(333, user.getuID());
        assertEquals("Andrew_Sempai", user.getName());

        // test ascending order
        users.clear();
        assertNull(testData.getUsersAllSorted(users, SortEnums.UserSortField.USERNAME, true));

        user = users.get(0);
        assertEquals(333, user.getuID());
        assertEquals("Andrew_Sempai", user.getName());

        user = users.get(1);
        assertEquals(555, user.getuID());
        assertEquals("JiffyPB", user.getName());

        user = users.get(2);
        assertEquals(111, user.getuID());
        assertEquals("Miggles", user.getName());

        user = users.get(3);
        assertEquals(222, user.getuID());
        assertEquals("Smoo", user.getName());

        user = users.get(4);
        assertEquals(444, user.getuID());
        assertEquals("TestBoi", user.getName());

        user = users.get(5);
        assertEquals(666, user.getuID());
        assertEquals("Wonder_Woman", user.getName());
    }

    public void testValidate()
    {
        // Testing: String validateUser (int uID, String userName, String password, int age, char gender, Calendar endDate)
        Calendar userDate1 = Calendar.getInstance();
        userDate1.set(2018,3,30);

        Calendar yearInPast = Calendar.getInstance();
        yearInPast.add(Calendar.YEAR, -1);

        Calendar monthInPast = Calendar.getInstance();
        monthInPast.add(Calendar.MONTH, -1);

        Calendar dayInPast = Calendar.getInstance();
        dayInPast.add(Calendar.DAY_OF_MONTH, -1);

        System.out.println("\nTesting Validate Features in AccessUsers");

        // Valid user
        assertNull(AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', userDate1));
        // Invalid uID
        assertEquals("Invalid user ID entered.", AccessUsers.validateUser(-9001, "Miggles", "anime4life", 21, 'M', userDate1));
        // Invalid username
        assertEquals("You need to name your user.", AccessUsers.validateUser(111, "", "anime4life", 21, 'M', userDate1));
        assertEquals("You need to name your user.", AccessUsers.validateUser(111, null, "anime4life", 21, 'M', userDate1));
        // Invalid password
        assertEquals("You need to enter a password.", AccessUsers.validateUser(111, "Miggles", "", 21, 'M', userDate1));
        assertEquals("You need to enter a password.", AccessUsers.validateUser(111, "Miggles", null, 21, 'M', userDate1));
        // Invalid age
        assertEquals("Invalid age entered.", AccessUsers.validateUser(111, "Miggles", "anime4life", 0, 'M', userDate1));
        assertEquals("Invalid age entered.", AccessUsers.validateUser(111, "Miggles", "anime4life", -9001, 'M', userDate1));
        // Invalid gender
        assertEquals("Invalid gender entered.", AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'q', userDate1));
        // Invalid date
        assertEquals("Invalid date entry. Can't enter user with expired account", AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', yearInPast));
        assertEquals("Invalid date entry. Can't enter user with expired account", AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', monthInPast));
        assertEquals("Invalid date entry. Can't enter user with expired account", AccessUsers.validateUser(111, "Miggles", "anime4life", 21, 'M', dayInPast));
    }

}
