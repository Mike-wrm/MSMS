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

public class UserListActivity extends Activity {

    private ArrayList<User> userList;
    private AccessUsers userAccessor;
    private ArrayAdapter<User> userArrayAdapter;

    private int selectedUserPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userList = new ArrayList<User>();
        userAccessor = new AccessUsers();
        String result = userAccessor.getUsers(userList);

        // Perform error handling on getting the list of users
        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, userList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    text1.setText(userList.get(position).getName());

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listUsers);
            listView.setAdapter(userArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == selectedUserPosition) {
                        listView.setItemChecked(position, false);
                        selectedUserPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        selectedUserPosition = position;

                        Intent userDisplay = new Intent(getApplicationContext(), UserDisplayActivity.class);
                        // note class User must implement Serializable for this to work
                        userDisplay.putExtra("SelectedUser", userList.get(position));
                        UserListActivity.this.startActivity(userDisplay);
                    }
                }
            });
        }
    }
}
