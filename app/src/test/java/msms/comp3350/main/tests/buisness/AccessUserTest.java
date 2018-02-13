package msms.comp3350.main.tests.buisness;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;
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

        System.out.println("\nStarting testAccessUser");

        testUser1 = new User(7, "John Doe", "pass",  25, 'm', endDate);
        testUser2 = new User(8, "Jane Doe", "pass1",  26, 'f', endDate);

        assertNull(list.insertUser(testUser1));
        assertEquals("'John Doe' is already added.", list.insertUser(testUser1));

        assertNull(list.updateUser(testUser1));
        assertEquals("'Jane Doe' cannot be found.", list.updateUser(testUser2));

        assertNull(list.deleteUser(testUser1));
        assertEquals("'John Doe' cannot be found.", list.deleteUser(testUser1));

        // empty out the list
        user = new User(1, "Mike McMahon", "anime4life", 21, 'm', endDate);
        assertNull(list.deleteUser(user));
        user = new User(2, "Chris Scatliff", "getoffmylawn", 82, 'm', endDate);
        assertNull(list.deleteUser(user));
        user = new User(3, "Andrew Kozar", "iheartmybeard", 23, 'm', endDate);
        assertNull(list.deleteUser(user));
        user = new User(4, "Alex Carriere", "supertester", 24, 'm', endDate);
        assertNull(list.deleteUser(user));
        user = new User(5, "Jaivir Bali", "walmartisevil", 25, 'm', endDate);
        assertNull(list.deleteUser(user));
        user = new User(6, "Diana Prince", "wonderwoman", 86, 'f', endDate);
        assertNull(list.deleteUser(user));

        // see if they still work on an empty list

        assertEquals("'John Doe' cannot be found.",list.deleteUser(testUser1));

        assertEquals("'John Doe' cannot be found.", list.updateUser(testUser1));

        assertNull(list.insertUser(testUser1));



    }

}
