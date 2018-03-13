package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Calendar;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;

public class MovieTest extends TestCase
{
    public MovieTest(String arg0)
    {
        super(arg0);
    }

    public void testMovie()
    {
        Movie family;
        Movie comedy;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,2,20);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2021,3,15);

        System.out.println("\nTesting the Movie object in Movie");

        family = new Movie("Shrek", 2001, 84, "family", endDate, "Ogre Saves Princess but gets unexpected suprise");
        comedy = new Movie("Shrek 2", 2004, 76, "comedy", endDate, "Ogre saves princess again..");

        assertNotNull(family);

        //Testing the getters

        assertEquals("Shrek", family.getTitle());
        assertEquals(2001, family.getReleaseYear());
        assertEquals(84, family.getUserScore());
        assertEquals(endDate, family.getEndDate());
        assertEquals("Ogre Saves Princess but gets unexpected suprise", family.getDescription());
        assertEquals("family", family.getCategory());

        //Testing the setters
        family.setmID(999);
        assertEquals(999, family.getmID());
        family.setTitle("newMovie");
        assertEquals("newMovie", family.getTitle());
        family.setReleaseYear(2002);
        assertEquals(2002, family.getReleaseYear());
        family.setUserScore(85);
        assertEquals(85, family.getUserScore());
        family.setEndDate(newDate);
        assertEquals(newDate, family.getEndDate());
        family.setDescription("New Description");
        assertEquals("New Description", family.getDescription());
        family.setCategory("family");
        assertEquals("family", family.getCategory());

        // testing the equals
        // it should be equal unless the id is not equal
        Movie idChanged = new Movie("Shrek 2", 2004, 76, "comedy", endDate, "Ogre saves princess again..");
        Movie nameChanged = new Movie("newMovie", 2004, 76, "comedy", endDate, "Ogre saves princess again..");
        nameChanged.setmID(comedy.getmID());
        Movie passChanged = new Movie("Shrek 2", 2006, 99, "comedy", endDate, "Ogre saves princess again..");
        passChanged.setmID(comedy.getmID());
        Movie ageChanged = new Movie("Shrek 2", 2004, 76, "comedy", endDate, "Ogre saves princess again..");
        ageChanged.setmID(comedy.getmID());
        Movie genderChanged = new Movie( "Shrek 2", 2004, 76, "comedy", newDate, "Ogre saves princess again..");
        genderChanged.setmID(comedy.getmID());
        Movie dateChanged = new Movie("Shrek 2", 2004, 76, "comedy", endDate, "newDescription");
        dateChanged.setmID(comedy.getmID());

        assertTrue(family.equals(family));

        assertFalse(comedy.equals(idChanged));
        assertTrue(comedy.equals(nameChanged));
        assertTrue(comedy.equals(passChanged));
        assertTrue(comedy.equals(ageChanged));
        assertTrue(comedy.equals(genderChanged));
        assertTrue(comedy.equals(dateChanged));

        assertFalse(family.equals(comedy));

        System.out.println("Finished a Movie Test");
    }
}
