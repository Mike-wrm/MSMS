package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.objects.User;
import msms.comp3350.persistence.TempData;

/**
 * Created by Chris on 2018-02-03.
 */

public class AccessUsers {

    private TempData dataAccess;

    public AccessUsers() {
        dataAccess = new TempData();
    }

    public String getUsers(ArrayList<User> users) {
        users.clear();
        return dataAccess.getUsersAll(users);
    }
}
