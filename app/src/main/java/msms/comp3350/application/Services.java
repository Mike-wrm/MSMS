package msms.comp3350.application;

import msms.comp3350.persistence.DataAccessor;
import msms.comp3350.persistence.DataAccessorObject;


public class Services
{
    public static DataAccessor dataAccessService = null;

    public static DataAccessor createDataAccess(String dbName)
        {
            if(null == dataAccessService)
            {
                dataAccessService = new DataAccessorObject(dbName);
                dataAccessService.open(Main.getDBPathName());
            }
            return dataAccessService;
    }

    public static DataAccessor createDataAccess(DataAccessor altDataAccess)
    {
        if(null == dataAccessService)
        {
           dataAccessService = altDataAccess;
           dataAccessService.open(Main.getDBPathName());
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
        dataAccessService = null;
    }
}
