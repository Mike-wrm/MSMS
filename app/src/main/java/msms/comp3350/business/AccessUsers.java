package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.TempData;

public class AccessUsers
{
    private TempData dataAccess;

    public AccessUsers()
    {
        dataAccess = (TempData) Services.getDataAccess();
    }

    public String getUsers(ArrayList<User> users)
    {
        users.clear();
        return dataAccess.getUsersAll(users);
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
