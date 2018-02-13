package msms.comp3350.objects;

// Stores the arrays needed for populating spinners in the UI
public abstract class SpinnerArrays
{
    // Arrays:
    private static String[] days =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
        "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };

    private static String[] months =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
    };

    private static String[] scores =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    };

    private static String[] categories =
    {
            "none", "Action", "Children's", "Comedy", "Drama", "Fantasy", "Horror", "Sci-Fi",
            "Recent", "Trending"
    };

    // Getters:
    public static String[] getDays()
    {
        return days;
    }
    public static String[] getMonths()
    {
        return months;
    }
    public static String[] getScores()
    {
        return scores;
    }
    public static String[] getCategories()
    {
        return categories;
    }
}
