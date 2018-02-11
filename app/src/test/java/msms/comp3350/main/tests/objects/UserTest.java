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

            System.out.println("\nStarting testUser");

            user = new User(1,"Alex Carriere", "password", 21, "male", endDate);
            user2 = new User(2,"Alexa Carriere", "pass123", 21, "female", endDate);

            assertNotNull(user);
            assertEquals(1, user.getuID());
            assertEquals("Alex Carriere", user.getName());
            assertEquals("password", user.getPass());
            assertEquals(21, user.getAge());
            assertEquals("male", user.getGender());
            assertEquals("female", user2.getGender());
            assertEquals(endDate, user.getEndDate());

            assertTrue(user.equals(user));
            assertFalse(user.equals(user2));



        }
}
