package msms.comp3350.business;

public abstract class ChartData
{
    public static String[][] getGlobal()
    {
        String[][] info = new String[3][];
        info[0] = new String[] {
            "Movies by Category",
            "Users by Age Range"
        };
        info[1] = new String[] {
            "pie",
            "bar"
        };
        info[2] = new String[] {
            "categories",
            "ages"
        };
        return info;
    }
}
