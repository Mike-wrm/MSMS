package msms.comp3350.application;

public class Main
{
    //At this stage, to use this either use "temp" for stub
    //or use "DB" to use database
    //other strings will not work for dbName
    public static final String dbName = "DB";
    private static String dbPathName = "database/DB";

    public static void main(String[] args)
    {
        startUp();

        // A command line interface has currently not been implemented
        //CLI.run();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }

    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
