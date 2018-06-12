package msms.comp.main;

import junit.framework.Test;
import junit.framework.TestSuite;

import msms.comp.main.acceptance.AcceptanceTests;

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
