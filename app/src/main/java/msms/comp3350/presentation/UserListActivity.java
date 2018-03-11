package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.business.AccessUsers;
import msms.comp3350.main.R;
import msms.comp3350.objects.User;

public class UserListActivity extends Activity
{
    private ArrayList<User> userList;
    private AccessUsers userAccessor;
    private ArrayAdapter<User> userArrayAdapter;

    private int selectedUserPosition = -1;

    public static final int DELETE_USER_CODE = 1003;
    public static final int UPDATE_USER_CODE = 1004;
    public static final int ADD_USER_CODE = 1005;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

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
                        UserListActivity.this.startActivityForResult(userDisplay, 1002);
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
        if (data != null)
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
}
