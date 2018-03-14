package msms.comp3350.application;

import msms.comp3350.persistence.DataAccessor;
import msms.comp3350.persistence.DataAccessorObject;
import msms.comp3350.persistence.TempData;
import msms.comp3350.presentation.MainActivity;


public class Services
{
    public static DataAccessor dataAccessService = null;
    public static final String dbName = "temp";//change to DB for database
    private static String dbPathName = "database/DB";

    public static DataAccessor createDataAccess()
    {
        if(null == dataAccessService)
        {
            if (dbName.equals("temp"))
            {
                dataAccessService = new TempData(dbName);
                dataAccessService.open(dbName);
            }
            else
            {
                dataAccessService = new DataAccessorObject(dbName);
                dataAccessService.open(getDBPathName());
            }
        }
        return dataAccessService;
    }

    public static DataAccessor getDataAccess()
    {
        if(null == dataAccessService)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if(null != dataAccessService)
        {
            dataAccessService.close();
        }
    }

    public static String getDBPathName()
    {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName)
    {
        pathName += "/" + dbName;
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
