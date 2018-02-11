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
        User user;

        list.getUsers(users);

        assertNotNull(users);
        assertFalse(users.isEmpty());

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        user = new User(1,"Alex Carriere", "password", 21, "male", endDate);

        // All these methods do is call other methods in TempData, so all the major testing will be done there
        assertNotNull(list.insertUser(user));
        assertNull(list.updateUser(user));
        assertNull(list.deleteUser(user));
    }

}
