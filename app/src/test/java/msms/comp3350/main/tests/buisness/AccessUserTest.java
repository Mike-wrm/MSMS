package msms.comp3350.main.tests.buisness;

import junit.framework.TestCase;
import java.util.ArrayList;
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
    }

}
