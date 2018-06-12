package msms.comp3350.main.tests;

import junit.framework.Test;
import junit.framework.TestSuite;


import msms.comp3350.main.tests.business.BusinessTests;
import msms.comp3350.main.tests.objects.ObjectTests;
import msms.comp3350.main.tests.persistence.PersistenceTests;

public class RunUnitTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All Tests");
        suite.addTest(BusinessTests.suite());
        suite.addTest(ObjectTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }

}
