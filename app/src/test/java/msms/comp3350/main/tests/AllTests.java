package msms.comp3350.main.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import msms.comp3350.main.tests.business.AccessMoviesTest;
import msms.comp3350.main.tests.business.AccessUserTest;
import msms.comp3350.main.tests.objects.MovieTest;
import msms.comp3350.main.tests.objects.UserTest;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All Tests");
        testObjects();
        testBusiness();
        return suite;
    }

    public static void testObjects()
    {
        suite.addTestSuite(MovieTest.class);
        suite.addTestSuite(UserTest.class);
    }

    public static void testBusiness()
    {
        suite.addTestSuite(AccessMoviesTest.class);
        suite.addTestSuite(AccessUserTest.class);
    }
}
