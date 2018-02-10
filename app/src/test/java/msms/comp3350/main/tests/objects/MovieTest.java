

package msms.comp3350.main.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;

public class MovieTest extends TestCase{

    public MovieTest(String arg0) {super(arg0);}

    public void testMovie(){
        Movie movie;
        ArrayList<String> categories = new ArrayList<>();

        System.out.println("\nStarting Movie tests");

        movie = new Movie("Shrek", 2001, 84, categories, 5, 2, 2020, "Ogre Saves Princess but gets unexpected suprise");




        assertNotNull(movie);

        assertEquals("Shrek", movie.getTitle());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals(84, movie.getUserScore());
        assertEquals("5/2/2020", movie.getEnd());
        assertEquals("Ogre Saves Princess but gets unexpected suprise", movie.getDescription());

        assertEquals(categories, movie.getCategory());


        // assertTrue(movie.compareTo(movie.getTitle()));
        //assertFalse(movie.compareTo(comedy.getTitle()));

    }

}
