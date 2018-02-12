package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;
import java.util.Calendar;
import msms.comp3350.objects.User;

public class UserTest extends TestCase{

        public UserTest(String arg0) {super(arg0);}

        public void testUser1(){

            User user;
            User user2;

            Calendar endDate = Calendar.getInstance();
            endDate.set(2020,11,19);

            Calendar newDate = Calendar.getInstance();
            newDate.set(2022,12,20);

            System.out.println("\nStarting testUser");

            user = new User(1,"John Doe", "password", 21, 'm', endDate);
            user2 = new User(2, "Jane Doe", "pass", 22, 'f', endDate);

            assertNotNull(user);

            // testing the getters
            assertEquals(1, user.getuID());
            assertEquals("John Doe", user.getName());
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

            assertTrue(user.equals(user));
            assertFalse(user.equals(user2));

        }
}
