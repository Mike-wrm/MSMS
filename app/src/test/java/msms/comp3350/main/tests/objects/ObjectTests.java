package msms.comp3350.main.tests.objects;

import junit.framework.Test;
import junit.framework.TestSuite;

import msms.comp3350.objects.WatchedEvent;

public class ObjectTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Object Tests");
        suite.addTestSuite(MovieTest.class);
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(WatchedEventTest.class);
        return suite;
    }
}
