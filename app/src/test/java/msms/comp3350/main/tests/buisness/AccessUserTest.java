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

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        User user = new User(999,"John Doe", "password", 21, 'm', endDate);
        User user2 = new User(999,"Jane Doe", "password", 21, 'f', endDate);

        assertNull(list.insertUser(user));
        assertEquals("'John Doe' is already added.", list.insertUser(user));

        assertNull(list.updateUser(user));
        assertEquals("'Jane Doe' cannot be found.", list.updateUser(user2));

        assertNull(list.deleteUser(user));
        assertEquals("'John Doe' cannot be found.", list.deleteUser(user));
    }

}
