package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.application.Services;
import msms.comp3350.objects.User;
import msms.comp3350.persistence.DataAccessor;

public class AccessUsers
{
    private DataAccessor dataAccess;

    public AccessUsers()
    {
        dataAccess = Services.getDataAccess();
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
