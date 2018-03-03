package msms.comp3350.presentation;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import msms.comp3350.business.AccessMovies;
import msms.comp3350.objects.Movie;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import msms.comp3350.main.R;

public class MovieSearchActivity extends Activity {
    private ArrayList<Movie> matches = null;
    private ArrayAdapter<Movie> adapter;
    private AccessMovies accessor;
    private int selectedMoviePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        // Search for movies matching the string provided with the intent:
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
             accessor = new AccessMovies();
             matches = accessor.searchMovie(query);

            if (matches != null)// Matches found: display them in the list view
            {
                 adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, matches)
                {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent)
                    {
                        View view = super.getView(position, convertView, parent);

                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                        text1.setText(matches.get(position).getTitle());

                        return view;
                    }
                };

                final ListView listView = (ListView) findViewById(R.id.listMovies);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                        if (position == selectedMoviePosition)
                        {
                            listView.setItemChecked(position, false);
                            selectedMoviePosition = -1;
                        }
                        else
                        {
                            listView.setItemChecked(position, true);
                            selectedMoviePosition = position;

                            Intent movieDisplay = new Intent(getApplicationContext(), MovieDisplayActivity.class);
                            // note class Movie must implement Serializable for this to work
                            movieDisplay.putExtra("SelectedMovie", matches.get(position));
                            MovieSearchActivity.this.startActivityForResult(movieDisplay, 1000);
                        }
                    }
                });
            }
            else// Nothing found: display error
            {
                //TODO: display error
            }
        }
    }
}
