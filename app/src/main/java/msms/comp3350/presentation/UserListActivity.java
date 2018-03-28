package msms.comp3350.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.business.AccessUsers;
import msms.comp3350.business.SortEnums;
import msms.comp3350.main.R;
import msms.comp3350.objects.User;

public class UserListActivity extends AppCompatActivity
{
    private enum UserOrder
    {
        UNSORTED,
        ASCENDING,
        DESCENDING
    }

    private UserOrder userOrder = UserOrder.UNSORTED;
    private ArrayList<User> userList;
    private AccessUsers userAccessor;
    private ArrayAdapter<User> userArrayAdapter;

    private int selectedUserPosition = -1;

    public static final int USER_LIST_REQ_CODE = 998;
    public static final int DELETE_USER_CODE = 1003;
    public static final int UPDATE_USER_CODE = 1004;
    public static final int ADD_USER_CODE = 1005;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Setup the toolbar:
        Toolbar userListToolbar = (Toolbar) findViewById(R.id.user_list_toolbar);
        setSupportActionBar(userListToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);// Don't display activity title in toolbar

        userList = new ArrayList<User>();
        userAccessor = new AccessUsers();
        String result = userAccessor.getUsers(userList);

        // Perform error handling on getting the list of users
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, userList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    text1.setText(userList.get(position).getName());

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listUsers);
            listView.setAdapter(userArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    if (position == selectedUserPosition)
                    {
                        listView.setItemChecked(position, false);
                        selectedUserPosition = -1;
                    }
                    else
                    {
                        listView.setItemChecked(position, true);
                        selectedUserPosition = position;

                        Intent userDisplay = new Intent(getApplicationContext(), UserDisplayActivity.class);
                        // note class User must implement Serializable for this to work
                        userDisplay.putExtra("SelectedUser", userList.get(position));
                        UserListActivity.this.startActivityForResult(userDisplay, USER_LIST_REQ_CODE);
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // If we press the "back" button, then when we return to this activity input "Intent data" will be null.
        // This is expected behaviour, and in this case we do not need to check for any update or delete from the user.
        if (data != null && requestCode == USER_LIST_REQ_CODE)
        {
            Bundle extras = data.getExtras();
            User userToDelete = null;
            User userToUpdate = null;
            User userToAdd = null;
            String result;

            if (extras != null)
            {
                //The key argument here must match that used in the other activity
                userToDelete = (User) extras.getSerializable("DeleteUserKey");
                userToUpdate = (User) extras.getSerializable("UpdateUserKey");
                userToAdd = (User) extras.getSerializable("AddUserKey");
            }

            if (userToDelete != null && resultCode == DELETE_USER_CODE)
            {
                result = userAccessor.deleteUser(userToDelete);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = userList.indexOf(userToDelete);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listUsers);
                        listView.setSelection(pos);
                    }
                    userAccessor.getUsers(userList);
                    userArrayAdapter.notifyDataSetChanged();
                }
            }
            else if (userToUpdate != null && resultCode == UPDATE_USER_CODE)
            {
                result = userAccessor.updateUser(userToUpdate);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = userList.indexOf(userToUpdate);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listUsers);
                        listView.setSelection(pos);
                    }
                    userAccessor.getUsers(userList);
                    userArrayAdapter.notifyDataSetChanged();
                }
            }
            else if (userToAdd != null && resultCode == ADD_USER_CODE)
            {
                result = userAccessor.insertUser(userToAdd);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = userList.indexOf(userToAdd);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listUsers);
                        listView.setSelection(pos);
                    }
                    userAccessor.getUsers(userList);
                    userArrayAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate movie_list_action_bar.xml:
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_list_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    // Handles selections of option menu items in the ToolBar
    {
        switch (item.getItemId())
        {
            case R.id.option_unsorted:// Always default to unsorted
                userOrder = UserListActivity.UserOrder.UNSORTED; break;

            case R.id.option_ascending:
                if (userOrder == UserListActivity.UserOrder.ASCENDING)
                    userOrder = UserListActivity.UserOrder.UNSORTED;
                else
                    userOrder = UserListActivity.UserOrder.ASCENDING;
                break;

            case R.id.option_descending:
                if (userOrder == UserListActivity.UserOrder.DESCENDING)
                    userOrder = UserListActivity.UserOrder.UNSORTED;
                else
                    userOrder = UserListActivity.UserOrder.DESCENDING;
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        updateListView();
        return true;
    }

    private String updateListView()
    // Attempts to refresh the ListView based on sorting order; returns an status code on failure
    {
        String status = null;

        switch (userOrder)
        {
            case UNSORTED:
                userAccessor.cancelSort();
                status = userAccessor.getUsers(userList); break;
            case ASCENDING:
                status = userAccessor.getSortedUsers(userList, SortEnums.UserSortField.USERNAME, true); break;
            case DESCENDING:
                status = userAccessor.getSortedUsers(userList, SortEnums.UserSortField.USERNAME, false); break;
            default:
                status = "ERROR: userOrder value is invalid.";
        }

        if (status == null)// No errors: refresh ListView
            userArrayAdapter.notifyDataSetChanged();

        return status;
    }
}
