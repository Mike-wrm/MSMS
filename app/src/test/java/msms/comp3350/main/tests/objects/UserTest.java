package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;
import msms.comp3350.objects.User;

public class UserTest extends TestCase{

        public UserTest(String arg0) {super(arg0);}

        public void testUser1(){

            User user;
            User user2;

            System.out.println("\nStarting testUser");

            user = new User("Alex Carriere", "password", 21, "male", 11, 21, 2019);
            user2 = new User("Alexa Carriere", "pass123", 21, "female", 12, 15, 2020);

            assertNotNull(user);
            assertEquals("Alex Carriere", user.getName());
            assertEquals("password", user.getPass());
            assertEquals(21, user.getAge());
            assertEquals("male", user.getGender());
            assertEquals("female", user2.getGender());
            assertEquals("11/21/2019", user.getEndDate());

            assertTrue(user.compareTo(user.getName()));
            assertFalse(user.compareTo(user2.getName()));



        }
}
