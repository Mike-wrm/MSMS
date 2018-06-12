package msms.comp.main.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Buisness tests");
        suite.addTestSuite(AccessMoviesTest.class);
        suite.addTestSuite(AccessUserTest.class);
        suite.addTestSuite(AccessWatchedEventsTest.class);
        suite.addTestSuite(CalculateTest.class);
        suite.addTestSuite(ChartDataTest.class);
        suite.addTestSuite(MovieChartsTest.class);
        suite.addTestSuite(UserChartsTest.class);
        return suite;
    }
}




