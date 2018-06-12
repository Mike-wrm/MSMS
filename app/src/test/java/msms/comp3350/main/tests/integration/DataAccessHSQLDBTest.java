package msms.comp.main.tests.integration;

import junit.framework.TestCase;

import msms.comp.application.Main;
import msms.comp.application.Services;
import msms.comp.persistence.DataAccessor;
import msms.comp.main.tests.persistence.DataAccessTest;

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
