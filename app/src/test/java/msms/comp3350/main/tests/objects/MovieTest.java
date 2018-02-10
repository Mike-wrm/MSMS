

package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.objects.Movie;

public class MovieTest extends TestCase{

    public MovieTest(String arg0) {super(arg0);}

    public void testMovie(){
        Movie family;
        Movie comedy;

        ArrayList<String> familyCat = new ArrayList<>();
        familyCat.add("family");
        ArrayList<String> comedyCat = new ArrayList<>();
        comedyCat.add("comedy");

        Calendar endDate = Calendar.getInstance();
        endDate.set(5,2,20);


        System.out.println("\nStarting Movie tests");

        family = new Movie(1, "Shrek", 2001, 84, familyCat, endDate, "Ogre Saves Princess but gets unexpected suprise");
        comedy = new Movie(2, "Shrek 2", 2004, 76, comedyCat, endDate, "Ogre saves princess again..");

        assertNotNull(family);

        assertEquals(1, family.getmID());
        assertEquals("Shrek", family.getTitle());
        assertEquals(2001, family.getReleaseYear());
        assertEquals(84, family.getUserScore());
        assertEquals(endDate, family.getEndDate());
        assertEquals("Ogre Saves Princess but gets unexpected suprise", family.getDescription());

        assertEquals(familyCat, family.getCategory());


        assertTrue(family.compareTo(family.getTitle()));
        assertFalse(family.compareTo(comedy.getTitle()));

    }

}
