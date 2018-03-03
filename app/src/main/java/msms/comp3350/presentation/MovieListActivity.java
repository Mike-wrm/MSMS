package msms.comp3350.presentation;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import msms.comp3350.business.AccessMovies;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieListActivity extends AppCompatActivity
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

        // Setup the app bar:
        Toolbar movieListToolbar = (Toolbar) findViewById(R.id.movie_list_toolbar);
        setSupportActionBar(movieListToolbar);

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
            movieArrayAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, movieList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
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
                        // note class Movie must implement Serializable for this to work
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
        if (data != null)
        {
            Bundle extras = data.getExtras();
            Movie movieToDelete = null;
            Movie movieToUpdate = null;
            Movie movieToAdd = null;
            String result;

            if (extras != null)
            {
                //The key argument here must match that used in the other activity
                movieToDelete = (Movie) extras.getSerializable("DeleteMovieKey");
                movieToUpdate = (Movie) extras.getSerializable("UpdateMovieKey");
                movieToAdd = (Movie) extras.getSerializable("AddKey");
            }

            if (movieToDelete != null && resultCode == 1000)
            {
                result = movieAccessor.deleteMovie(movieToDelete);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = movieList.indexOf(movieToDelete);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listMovies);
                        listView.setSelection(pos);
                    }
                    movieAccessor.getMovies(movieList);
                    movieArrayAdapter.notifyDataSetChanged();
                }
            }
            else if (movieToUpdate != null && resultCode == 1000)
            {
                result = movieAccessor.updateMovie(movieToUpdate);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = movieList.indexOf(movieToUpdate);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listMovies);
                        listView.setSelection(pos);
                    }
                    movieAccessor.getMovies(movieList);
                    movieArrayAdapter.notifyDataSetChanged();
                }
            }
            else if (movieToAdd != null && resultCode == 1001)
            {
                result = movieAccessor.insertMovie(movieToAdd);
                if (result != null)
                {
                    Messages.fatalError(this, result);
                }
                else
                {
                    int pos = movieList.indexOf(movieToAdd);
                    if (pos >= 0)
                    {
                        ListView listView = (ListView) findViewById(R.id.listMovies);
                        listView.setSelection(pos);
                    }
                    movieAccessor.getMovies(movieList);
                    movieArrayAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /* Sets up the SearchView in the ToolBar
     * This setup method was taken (and modified) from:
     * https://developer.android.com/guide/topics/search/search-dialog.html
     * http://www.viralandroid.com/2016/03/implementing-searchview-in-android-actionbar.html
     */
    {
        /*
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;*/

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search_view);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)// Start MovieSearchActivity intent
            {
                Intent MovieSearchActivity = new Intent(MovieListActivity.this, MovieSearchActivity.class);
                MovieListActivity.this.startActivity(MovieSearchActivity);
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void openAddMovie (View view)
    {
        Intent addMovieIntent = new Intent(this, AddMovieActivity.class);
        MovieListActivity.this.startActivityForResult(addMovieIntent, 1001);
    }
}