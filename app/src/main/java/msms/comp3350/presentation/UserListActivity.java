package msms.comp3350.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.main.R;
//TODO make business classes and object methods for this
//import msms.comp3350.main.business.AccessUsers; --> something like this
//import msms.comp3350.main.objects.User;

public class UserListActivity extends Activity {

    // TODO change this to our "User" type later
    private ArrayList<User> userList;
    private ArrayAdapter<User> userArrayAdapter;

    private int selectedUserPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO make a user list layout
        setContentView(R.layout.activity_user_list);

        userList = new ArrayList<User>();
        // TODO change this to the method that will grab the list of users
        String result = null;
        for (int i = 0; i < 20; i++){
            userList.add("User Name " + i);
        }

        // Perform error handling on getting the list of users
        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, userList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    // TODO change this to corresponding "Get User Name" method from our "User" class
                    text1.setText(userList.get(position));

                    return view;
                }
            };

            //TODO create ID for User list
            final ListView listView = (ListView) findViewById(R.id.ListUsers);
            listView.setAdapter(selectedUserPosition);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == selectedUserPosition) {
                        listView.setItemChecked(position, false);
                        selectedUserPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        selectedUserPosition = position;
                        // TODO move to new activity, pass the the user that we have selected
                        //selectUserAtPosition(position);
                    }
                }
            });
        }
    }
}
