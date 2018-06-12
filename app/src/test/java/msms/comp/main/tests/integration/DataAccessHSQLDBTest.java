package msms.comp3350.main.tests.integration;

import junit.framework.TestCase;

import msms.comp3350.application.Main;
import msms.comp3350.application.Services;
import msms.comp3350.persistence.DataAccessor;
import msms.comp3350.main.tests.persistence.DataAccessTest;

public class DataAccessHSQLDBTest extends TestCase {

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccessor dataAccessor;

        Services.closeDataAccess();

        System.out.print("\nStarting Integration test DataAccess (using default DB)");

        Services.createDataAccess(Main.dbName);
        dataAccessor = Services.getDataAccess();

        DataAccessTest.dataAccessTest(dataAccessor);

        Services.closeDataAccess();


        System.out.println("Finished Integration test DataAccess (using default DB)");
    }

}
