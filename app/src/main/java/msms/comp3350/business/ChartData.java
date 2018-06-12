package msms.comp3350.business;

public abstract class ChartData
{
    private static final String[] movieCharts =
    {
            "Movies by Category // pie // categories",
            "Movies by Decade // bar // decades",
            "Movies by Rating // bar // ratings"
    };
    private static final String[] userCharts =
    {
            "Users by Age Range // bar // ages",
            "Users by Gender // pie // genders",
            "Users by Rating // bar // ratings"
    };

    public static String[][] getGlobalLists()
    {
        int totalLength = movieCharts.length + userCharts.length;
        String[][] lists = new String[3][];
        lists[0] = new String[totalLength];
        lists[1] = new String[totalLength];
        lists[2] = new String[totalLength];
        for(int i = 0 ; i < movieCharts.length ; i++)
        {
            String[] temp = movieCharts[i].split(" // ");
            lists[0][i] = temp[0];
            lists[1][i] = temp[1];
            lists[2][i] = temp[2];
        }
        for(int i = movieCharts.length ; i < totalLength ; i++)
        {
            String[] temp = userCharts[i - movieCharts.length].split(" // ");
            lists[0][i] = temp[0];
            lists[1][i] = temp[1];
            lists[2][i] = temp[2];
        }
        return lists;
    }

    public static String[][] getMovieLists()
    {
        String[][] lists = new String[3][];
        lists[0] = new String[movieCharts.length];
        lists[1] = new String[movieCharts.length];
        lists[2] = new String[movieCharts.length];
        for(int i = 0 ; i < movieCharts.length ; i++)
        {
            String[] temp = movieCharts[i].split(" // ");
            lists[0][i] = temp[0];
            lists[1][i] = temp[1];
            lists[2][i] = temp[2];
        }
        return lists;
    }

    public static String[][] getUserLists()
    {
        String[][] lists = new String[3][];
        lists[0] = new String[userCharts.length];
        lists[1] = new String[userCharts.length];
        lists[2] = new String[userCharts.length];
        for(int i = 0 ; i < userCharts.length ; i++)
        {
            String[] temp = userCharts[i].split(" // ");
            lists[0][i] = temp[0];
            lists[1][i] = temp[1];
            lists[2][i] = temp[2];
        }
        return lists;
    }
}
