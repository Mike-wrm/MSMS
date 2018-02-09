

package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;
import msms.comp3350.objects.Movie;

public class MovieTest extends TestCase{

    public MovieTest(String arg0) {super(arg0);}

    public void testMovie(){
        Movie family;
        Movie comedy;
        Movie drama;
        Movie horror;
        Movie action;
        Movie fantasy;
        Movie recent;
        Movie trending;

        System.out.println("\nStarting Movie tests");

        family = new Movie("Shrek", 2001, 84, "family", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        comedy = new Movie("Shrek 2", 2004, 75, "comedy", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise again");
        drama = new Movie("Shrek", 2001, 84, "drama", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        horror = new Movie("Shrek", 2001, 84, "horror", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        action = new Movie("Shrek", 2001, 84, "action", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        fantasy = new Movie("Shrek", 2001, 84, "fantasy", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        recent = new Movie("Shrek", 2001, 84, "recent", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");
        trending = new Movie("Shrek", 2001, 84, "trending", 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");



        assertNotNull(family);

        assertEquals("Shrek", family.getTitle());
        assertEquals(2001, family.getReleaseYear());
        assertEquals(84, family.getUserScore());
        assertEquals("5/2/2020", family.getEnd());
        assertEquals("Ogre Saves Princess but gets unexpected suprise", family.getDescription());

        assertEquals("family", family.getCategory());
        assertEquals("comedy", comedy.getCategory());
        assertEquals("drama", drama.getCategory());
        assertEquals("horror", horror.getCategory());
        assertEquals("action", action.getCategory());
        assertEquals("fantasy", fantasy.getCategory());
        assertEquals("recent", recent.getCategory());
        assertEquals("trending", trending.getCategory());

        assertTrue(family.compareTo(family.getTitle()));
        assertFalse(family.compareTo(comedy.getTitle()));

    }

}
