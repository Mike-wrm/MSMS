package msms.comp3350.business;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.application.Services;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.DataAccessor;

public class AccessUsers
{
    private DataAccessor dataAccess;
    private static boolean currSorted = false;
    private static SortEnums.UserSortField currField = null;
    private static boolean currAscending = false;

    public static final String[] GENDERS =
            {
                    "Male", "Female"
            };

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
    public String getSortedUsers(ArrayList<User> users, SortEnums.UserSortField sortBy, boolean ascending)
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

    public static String validateUser (int uID, String userName, String password, int age, char gender, int expYear, int expMonth, int expDay)
    {
        String errorString = null;
        // error checking
        if(null == userName || userName.equals(""))
        {
            errorString = "You need to name your user.";
        }
        if(null == password || password.equals(""))
        {
            errorString = "You need to enter a password.";
        }

        if(age < 1)
        {
            errorString = "Invalid age entered.";
        }

        if(uID < 1)
        {
            errorString = "Invalid user ID entered.";
        }

        if(!(gender == 'f' || gender == 'm'))
        {
            errorString = "Invalid gender entered.";
        }

        if (expYear <= Calendar.getInstance().get(Calendar.YEAR))
        {
            if (expYear < Calendar.getInstance().get(Calendar.YEAR))
            {
                errorString = "Invalid date entry. Can't enter user with expired account";
            }
            else if (expYear == Calendar.getInstance().get(Calendar.YEAR) && expMonth <= Calendar.getInstance().get(Calendar.MONTH))
            {
                if (expMonth < Calendar.getInstance().get(Calendar.MONTH))
                {
                    errorString = "Invalid date entry. Can't enter user with expired account";
                }
                else if (expMonth == Calendar.getInstance().get(Calendar.MONTH) && expDay <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                {
                    errorString = "Invalid date entry. Can't enter user with expired account";
                }
            }

        }


        return errorString;
    }
}
