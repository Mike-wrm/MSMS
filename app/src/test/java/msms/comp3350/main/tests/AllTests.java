package msms.comp3350.main.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import msms.comp3350.main.tests.business.AccessMoviesTest;
import msms.comp3350.main.tests.business.AccessUserTest;
import msms.comp3350.main.tests.business.AccessWatchedEventsTest;
import msms.comp3350.main.tests.business.MovieChartsTest;
import msms.comp3350.main.tests.business.UserChartsTest;
import msms.comp3350.main.tests.objects.MovieTest;
import msms.comp3350.main.tests.objects.UserTest;
import msms.comp3350.main.tests.objects.WatchedEventTest;
import msms.comp3350.main.tests.persistence.DataAccessTest;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All Tests");
        testPersistence();
        testObjects();
        testBusiness();
        return suite;
    }

    public static void testPersistence()
    {
        suite.addTestSuite(DataAccessTest.class);
    }

    public static void testObjects()
    {
        suite.addTestSuite(MovieTest.class);
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(WatchedEventTest.class);
    }

    public static void testBusiness()
    {
        suite.addTestSuite(AccessMoviesTest.class);
        suite.addTestSuite(AccessUserTest.class);
        suite.addTestSuite(AccessWatchedEventsTest.class);
        suite.addTestSuite(MovieChartsTest.class);
        suite.addTestSuite(UserChartsTest.class);
    }
}
