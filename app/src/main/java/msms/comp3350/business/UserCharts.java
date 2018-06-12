package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;

public abstract class UserCharts
{
    public static String[][] getUserAges()
    {
        AccessUsers access = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        access.getUsers(users);
        return getUserAges(users);
    }

    public static String[][] getUserAges(Movie movie)
    {
        AccessUsers access = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        access.getUsers(users, movie);
        return getUserAges(users);
    }

    private static String[][] getUserAges(ArrayList<User> users)
    {
        int numUsers = users.size();
        String[] categories = { "", "0-19", "20-29", "30-39", "40-49", "50-59", "60+", "" };

        int[] rawData = new int[8];
        rawData[0] = 0;
        rawData[7] = 0;

        for(int i = 0 ; i < numUsers ; i++)
        {
            int age = users.get(i).getAge();
            if(age < 20) rawData[1]++;
            else if(age < 30) rawData[2]++;
            else if(age < 40) rawData[3]++;
            else if(age < 50) rawData[4]++;
            else if(age < 60) rawData[5]++;
            else rawData[6]++;
        }

        String[] data = new String[8];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }

        return new String[][]{ categories, data };
    }

    public static String[][] getUserGenders()
    {
        AccessUsers access = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        access.getUsers(users);
        return getUserGenders(users);
    }

    public static String[][] getUserGenders(Movie movie)
    {
        AccessUsers access = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        access.getUsers(users, movie);
        return getUserGenders(users);
    }

    private static String[][] getUserGenders(ArrayList<User> users)
    {
        int numUsers = users.size();
        String[] categories = { "None", "Males", "Females", "Other" };

        int[] rawData = new int[categories.length];

        for(int i = 0 ; i < numUsers ; i++)
        {
            char gender = users.get(i).getGender();
            if(gender == 'm' || gender == 'M') rawData[1]++;
            else if(gender == 'f' || gender == 'F') rawData[2]++;
            else rawData[3]++;
        }

        String[] data = new String[rawData.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }

        return new String[][]{ categories, data };
    }
}
