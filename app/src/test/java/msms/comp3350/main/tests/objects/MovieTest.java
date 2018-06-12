package msms.comp.main.tests.objects;

import junit.framework.TestCase;

import java.util.Calendar;
import msms.comp.objects.Movie;

public class MovieTest extends TestCase
{
    public MovieTest(String arg0)
    {
        super(arg0);
    }

    public void tearDown()
    {
        System.out.println("Finished a Movie Test");
    }

    public void testAccessingMovie()
    {
        Movie family;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,2,20);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2021,3,15);

        System.out.println("\nTesting the Movie Acessing in Movie");

        family = new Movie(13, "Shrek", 2001,"family", endDate, "Ogre Saves Princess but gets unexpected suprise");

        assertNotNull(family);

        //Testing the getters

        assertEquals("Shrek", family.getTitle());
        assertEquals(2001, family.getReleaseYear());
        assertEquals(endDate, family.getEndDate());
        assertEquals("Ogre Saves Princess but gets unexpected suprise", family.getDescription());
        assertEquals("family", family.getCategory());

        //Testing the setters
        family.setmID(998);
        assertEquals(998, family.getmID());
        family.setTitle("newMovie");
        assertEquals("newMovie", family.getTitle());
        family.setReleaseYear(2002);
        assertEquals(2002, family.getReleaseYear());
        family.setEndDate(newDate);
        assertEquals(newDate, family.getEndDate());
        family.setDescription("New Description");
        assertEquals("New Description", family.getDescription());
        family.setCategory("family");
        assertEquals("family", family.getCategory());
    }

    public void testEqualsMovie()
    {
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,2,20);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2021,3,15);

        Movie testMovie = new Movie(22, "Shrek 2", 2004, "comedy", endDate, "Ogre saves princess again..");


        Movie idChanged = new Movie(556,"Shrek 2", 2004,"comedy", endDate, "Ogre saves princess again..");
        Movie nameChanged = new Movie(22,"newMovie", 2004,"comedy", endDate, "Ogre saves princess again..");
        Movie passChanged = new Movie(22,"Shrek 2", 2006,"comedy", endDate, "Ogre saves princess again..");
        Movie ageChanged = new Movie(22,"Shrek 2", 2004,"comedy", endDate, "Ogre saves princess again..");
        Movie genderChanged = new Movie(22, "Shrek 2", 2004,"comedy", newDate, "Ogre saves princess again..");
        Movie dateChanged = new Movie(22, "Shrek 2", 2004,"comedy", endDate, "newDescription");

        System.out.println("\nTesting the Movie Equals in Movie");

        assertTrue(testMovie.equals(testMovie));

        assertFalse(testMovie.equals(idChanged));
        assertTrue(testMovie.equals(nameChanged));
        assertTrue(testMovie.equals(passChanged));
        assertTrue(testMovie.equals(ageChanged));
        assertTrue(testMovie.equals(genderChanged));
        assertTrue(testMovie.equals(dateChanged));
    }
}

