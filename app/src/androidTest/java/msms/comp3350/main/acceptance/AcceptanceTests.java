package msms.comp.main.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance Tests");
        suite.addTestSuite(UsersTest.class);
        suite.addTestSuite(MoviesTest.class);
        suite.addTestSuite(ReportsTest.class);
        return suite;
    }


}
