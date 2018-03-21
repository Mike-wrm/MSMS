package msms.comp3350.application;

import msms.comp3350.persistence.DataAccessor;
import msms.comp3350.persistence.DataAccessorObject;
import msms.comp3350.presentation.MainActivity;


public class Services
{
    public static DataAccessor dataAccessService = null;

    public static DataAccessor createDataAccess(String dbName)
        {
            if(null == dataAccessService)
            {
                dataAccessService = new DataAccessorObject(dbName);
                dataAccessService.open(MainActivity.getDBPathName());
            }
            return dataAccessService;
    }

    public static DataAccessor createDataAccess(DataAccessor altDataAccess)
    {
        if(null == dataAccessService)
        {
           dataAccessService = altDataAccess;
            dataAccessService.open("temp");
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
