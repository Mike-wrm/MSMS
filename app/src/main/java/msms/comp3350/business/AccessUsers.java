package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.DataAccessor;

public class AccessUsers
{
    private DataAccessor dataAccess;
    private static boolean currSorted = false;
    private static String currField = null;
    private static boolean currAscending = false;

    //TODO move to appropriate location and process such that only certain fields become input strings
    enum uSortField
    {
        USERNAME, GENDER
    }

    public AccessUsers()
    {
        dataAccess = Services.getDataAccess();
    }

    public String getUsers(ArrayList<User> users)
    {
        users.clear();

        if (currSorted)
        {
            return getSortedUsers(users, currField, currAscending);
        }
        return dataAccess.getUsersAll(users);
    }

    //TODO create something in UserDisplay/ListActivity to allow for toggle of sort
    public String getSortedUsers(ArrayList<User> users, String sortBy, boolean ascending)
    {
        users.clear();
        currSorted = true;
        currField = sortBy;
        currAscending = ascending;
        return dataAccess.getUsersAllSorted(users, currField, currAscending);
    }

    public String insertUser(User currentUser)
    {
        return dataAccess.insertUser(currentUser);
    }

    public String updateUser(User currentUser)
    {
        return dataAccess.updateUser(currentUser);
    }

    public String deleteUser(User currentUser)
    {
        return dataAccess.deleteUser(currentUser);
    }
}
