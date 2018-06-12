package msms.comp.main.tests.objects;

import junit.framework.TestCase;
import java.util.Calendar;
import msms.comp.objects.User;

public class UserTest extends TestCase
{
    public UserTest(String arg0)
        {
            super(arg0);
        }

    public void tearDown()
    {
        System.out.println("Finishing a User Test");
    }

    public void testUserAccess()
    {
        User user;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2022,12,20);

        System.out.println("\nTesting the Movie Accessing in Movie");

        user = new User(1,"JohnDoe", "password", 21, 'm', endDate);

        assertNotNull(user);

        // testing the getters
        assertEquals(1, user.getuID());
        assertEquals("JohnDoe", user.getName());
        assertEquals("password", user.getPass());
        assertEquals(21, user.getAge());
        assertEquals('m', user.getGender());
        assertEquals(endDate, user.getEndDate());

        //testing the setters
        user.setuID(3);
        assertEquals(3, user.getuID());
        user.setName("newName");
        assertEquals("newName", user.getName());
        user.setPass("newPass");
        assertEquals("newPass", user.getPass());
        user.setAge(22);
        assertEquals(22, user.getAge());
        user.setGender('f');
        assertEquals('f', user.getGender());
        user.setEndDate(newDate);
        assertEquals(newDate, user.getEndDate());
    }

    public void testUserEquals()
    {
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,19);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2022,12,20);

        User user2 = new User(2, "JaneDoe", "pass", 22, 'f', endDate);

        // Test out the equals -- the id has to be the same
        User idChanged = new User(999, "JaneDoe", "pass", 22, 'f', endDate);
        User nameChanged = new User(2, "newName", "pass", 22, 'f', endDate);
        User passChanged = new User(2, "JaneDoe", "newPass", 2, 'f', endDate);
        User ageChanged = new User(2, "JaneDoe", "pass", 25, 'f', endDate);
        User genderChanged = new User(2, "JaneDoe", "pass", 22, 'm', endDate);
        User dateChanged = new User(2, "JaneDoe", "pass", 22, 'f', newDate);

        assertTrue(user2.equals(user2));

        assertFalse(user2.equals(idChanged));
        assertTrue(user2.equals(nameChanged));
        assertTrue(user2.equals(passChanged));
        assertTrue(user2.equals(ageChanged));
        assertTrue(user2.equals(genderChanged));
        assertTrue(user2.equals(dateChanged));
    }
}
