package msms.comp3350.objects;

// Stores the (read-only) arrays needed for populating spinners in the UI
public abstract class SpinnerArrays
{
    // Arrays:
    private static final String[] DAYS =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
        "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };

    private static final String[] MONTHS =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
    };

    private static final String[] SCORES =
    {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    };

    private static final String[] CATEGORIES =
    {
            "none", "Action", "Children's", "Comedy", "Drama", "Fantasy", "Horror", "Sci-Fi",
            "Recent", "Trending"
    };

    // Getters:
    public static String[] getDays() { return DAYS; }
    public static String[] getMonths()
    {
        return MONTHS;
    }
    public static String[] getScores()
    {
        return SCORES;
    }
    public static String[] getCategories()
    {
        return CATEGORIES;
    }
}
