package msms.comp3350.main;

import junit.framework.Test;
import junit.framework.TestSuite;

import msms.comp3350.main.acceptance.AcceptanceTests;

public class RunAcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance Tests");
        suite.addTest(AcceptanceTests.suite());
        return  suite;
    }
}
