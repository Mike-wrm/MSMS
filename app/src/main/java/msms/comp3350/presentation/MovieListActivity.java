package msms.comp3350.presentation;

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
import msms.comp3350.business.SortEnums;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieListActivity extends AppCompatActivity
{
    private enum MovieOrder
    {
        UNSORTED,
        ASCENDING,
        DESCENDING
    }

    private MovieOrder movieOrder = MovieOrder.UNSORTED;
    private ArrayList<Movie> movieList;// All movies
    private ArrayAdapter<Movie> movieArrayAdapter;
    private AccessMovies movieAccessor;
    private int selectedMoviePosition = -1;

    // Intent finish codes:
    public static final int MOVIE_LIST_REQ_CODE = 999;
    public static final int DELETE_MOVIE_CODE = 1000;
    public static final int UPDATE_MOVIE_CODE = 1001;
    public static final int ADD_MOVIE_CODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Setup the toolbar:
        Toolbar movieListToolbar = (Toolbar) findViewById(R.id.movie_list_toolbar);
        setSupportActionBar(movieListToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);// Don't display activity title in toolbar

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
                        MovieListActivity.this.startActivityForResult(movieDisplay, MOVIE_LIST_REQ_CODE);
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
        if (data != null && requestCode == MOVIE_LIST_REQ_CODE)
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
                movieToAdd = (Movie) extras.getSerializable("AddMovieKey");
            }

            if (movieToDelete != null && resultCode == DELETE_MOVIE_CODE)
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
                    updateListView();
                }
            }
            else if (movieToUpdate != null && resultCode == UPDATE_MOVIE_CODE)
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
                    updateListView();
                }
            }
            else if (movieToAdd != null && resultCode == ADD_MOVIE_CODE)
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
                    updateListView();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /* This method for setting up the search_view menu item was taken and modified from:
     * http://www.viralandroid.com/2016/03/implementing-searchview-in-android-actionbar.html
     */
    {
        // Inflate movie_list_action_bar.xml:
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_list_action_bar, menu);

        // Setup menu item Objects:
        MenuItem searchViewItem = menu.findItem(R.id.search_view);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchViewAndroidActionBar.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {
                updateListView();

                if (!query.equals(""))
                {
                    ArrayList<Movie> searchResults = movieAccessor.searchMovie(query);

                    if (searchResults != null)
                    {
                        movieList.clear();
                        movieList.addAll(searchResults);
                        movieArrayAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Messages.fatalError(MovieListActivity.this, "Search query string is null.");
                    }
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    // Handles selections of option menu items in the ToolBar
    {
        switch (item.getItemId())
        {
            case R.id.option_unsorted:// Always default to unsorted
                movieOrder = MovieOrder.UNSORTED; break;

            case R.id.option_ascending:
                if (movieOrder == MovieOrder.ASCENDING)
                    movieOrder = MovieOrder.UNSORTED;
                else
                    movieOrder = MovieOrder.ASCENDING;
                break;

            case R.id.option_descending:
                if (movieOrder == MovieOrder.DESCENDING)
                    movieOrder = MovieOrder.UNSORTED;
                else
                    movieOrder = MovieOrder.DESCENDING;
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        updateListView();
        return true;
    }

    public void openAddMovie (View view)
    {
        Intent addMovieIntent = new Intent(this, MovieDisplayActivity.class);
        MovieListActivity.this.startActivityForResult(addMovieIntent, MOVIE_LIST_REQ_CODE);
    }

    private String updateListView()
    // Attempts to refresh the ListView based on sorting order; returns an status code on failure
    {
        String status = null;

        switch (movieOrder)
        {
            case UNSORTED:
                movieAccessor.cancelSort();
                status = movieAccessor.getMovies(movieList); break;
            case ASCENDING:
                status = movieAccessor.getSortedMovies(movieList, SortEnums.MovieSortField.TITLE, true); break;
            case DESCENDING:
                status = movieAccessor.getSortedMovies(movieList, SortEnums.MovieSortField.TITLE, false); break;
            default:
                status = "ERROR: movieOrder value is invalid.";
        }

        if (status == null)// No errors: refresh ListView
            movieArrayAdapter.notifyDataSetChanged();

        return status;
    }
}