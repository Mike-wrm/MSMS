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

import msms.comp3350.business.AccessMovies;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieListActivity extends Activity
{
    private ArrayList<Movie> movieList;
    private ArrayAdapter<Movie> movieArrayAdapter;
    private AccessMovies movieAccessor;
    private int selectedMoviePosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieList = new ArrayList<Movie>();
        // use accessor to grab the list of movies from BUSINESS
        movieAccessor = new AccessMovies();
        String result = movieAccessor.getMovies(movieList);

        // Perform error handling on getting the list of movies
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            movieArrayAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, movieList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                    text1.setText(movieList.get(position).getTitle());

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listMovies);
            listView.setAdapter(movieArrayAdapter);

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
                        // note class Movie must implement Parcelable for this to work
                        movieDisplay.putExtra("SelectedMovie", movieList.get(position));
                        MovieListActivity.this.startActivityForResult(movieDisplay, 1000);
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
        if (data != null) {
            Bundle extras = data.getExtras();
            Movie movieToDelete = null;
            Movie movieToUpdate = null;
            String result;

            if (extras != null) {
                //The key argument here must match that used in the other activity
                movieToDelete = (Movie) extras.getParcelable("DeleteKey");
                movieToUpdate = (Movie) extras.getParcelable("UpdateKey");
            }

            if (movieToDelete != null && resultCode == 1000) {
                result = movieAccessor.deleteMovie(movieToDelete);
                if (result != null) {
                    Messages.fatalError(this, result);
                } else {
                    int pos = movieList.indexOf(movieToDelete);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listMovies);
                        listView.setSelection(pos);
                    }
                    movieAccessor.getMovies(movieList);
                    movieArrayAdapter.notifyDataSetChanged();
                }
            } else if (movieToUpdate != null && resultCode == 1000) {
                result = movieAccessor.updateMovie(movieToUpdate);
                if (result != null) {
                    Messages.fatalError(this, result);
                } else {
                    int pos = movieList.indexOf(movieToUpdate);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listMovies);
                        listView.setSelection(pos);
                    }
                    movieAccessor.getMovies(movieList);
                    movieArrayAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void openAddMovie (View view)
    {
        Intent addMovieIntent = new Intent(this, AddMovieActivity.class);
        MovieListActivity.this.startActivity(addMovieIntent);
    }
}