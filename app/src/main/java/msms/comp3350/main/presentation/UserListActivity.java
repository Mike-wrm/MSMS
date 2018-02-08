package msms.comp3350.main.presentation;

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

public class UserListActivity extends Activity {

    // TODO change this to our "User" type later
    private ArrayList<String> userList;
    private ArrayAdapter<String> userArrayAdapter;
    private int selectedUserPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        userList = new ArrayList<String>();
        // TODO change this to the method that will grab the list of users
        String result = null;
        for (int i = 0; i < 20; i++){
            userList.add("User Number " + i);
        }
        // Perform error handling on getting the list of movies
        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            userArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, userList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    // TODO change this to corresponding "Get User" method from our "User" class
                    text1.setText(userList.get(position));

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listMovies);
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
                        // TODO move to new activity, pass the the movie that we have selected
                        //selectMovieAtPosition(position);
                    }
                }
            });
        }
    }

}
