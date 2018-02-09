package msms.comp3350.main.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.main.R;
//import msms.comp3350.main.business.AccessMovies; --> something like this
//import msms.comp3350.main.objects.Movie;

public class MovieListActivity extends Activity {

    // TODO change this to our "Movie" type later
    private ArrayList<Movie> movieList;
    private ArrayAdapter<Movie> movieArrayAdapter;
    private int selectedMoviePosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieList = new ArrayList<Movie>();
        // TODO change this to the method that will grab the list of movies from BUSINESS
        String result = null;
        for (int i = 0; i < 20; i++){
            movieList.add("Movie Title " + i);
        }

        // Perform error handling on getting the list of movies
        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            movieArrayAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, movieList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    // TODO change this to corresponding "Get Movie Title" method from our "Movie" class
                    text1.setText(movieList.get(position));

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listMovies);
            listView.setAdapter(movieArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == selectedMoviePosition) {
                        listView.setItemChecked(position, false);
                        selectedMoviePosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        selectedMoviePosition = position;
                        // TODO move to new activity, pass the the movie that we have selected
                        //selectMovieAtPosition(position);
                    }
                }
            });
        }
    }

}