package msms.comp.main.tests.objects;

import junit.framework.TestCase;

import msms.comp.objects.WatchedEvent;

public class WatchedEventTest extends TestCase{

    public WatchedEventTest(String arg0)
    {
        super(arg0);
    }

    public void tearDown(){
        System.out.println("Finished a WatchedEvent Test");
    }

    public void testWatchedEventAccess()
    {
        WatchedEvent testEvent = new WatchedEvent(13, 12,"testUser", "testMovie", 8);

        System.out.println("\nStarting WatchedEvent Accessing Test");

        // Test getters
        assertEquals(13, testEvent.getuID());
        assertEquals(12, testEvent.getmID());
        assertEquals("testUser", testEvent.getUserName());
        assertEquals("testMovie", testEvent.getMovieTitle());
        assertEquals(8, testEvent.getRating());

        assertEquals("testUser viewed testMovie and rated it 8/10.", testEvent.toString());

        // Test Setters
        testEvent.setMovieTitle("newMovie");
        assertEquals("newMovie", testEvent.getMovieTitle());
        testEvent.setUserName("newUser");
        assertEquals("newUser", testEvent.getUserName());
        assertEquals("newUser viewed newMovie and rated it 8/10.", testEvent.toString());
    }

    public void testWatchedEventEquals()
    {
        System.out.println("\nStarting WatchedEvent Equals Test");

        WatchedEvent testEvent = new WatchedEvent(15, 18, "testUser", "testMovie", 6);

        assertFalse(testEvent.equals(new WatchedEvent(16, 18, "testUser", "testMovie", 6)));
        assertFalse(testEvent.equals(new WatchedEvent(15, 17, "testUser", "testMovie", 6)));
        assertTrue(testEvent.equals(new WatchedEvent(15, 18, "newUser", "testMovie", 6)));
        assertTrue(testEvent.equals(new WatchedEvent(15, 18, "testUser", "newMovie", 6)));
        assertTrue(testEvent.equals(new WatchedEvent(15, 18, "testUser", "testMovie", 5)));

    }

}
