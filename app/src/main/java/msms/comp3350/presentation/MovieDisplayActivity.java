package msms.comp3350.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import msms.comp3350.business.AccessMovies;
import msms.comp3350.business.Calculate;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieDisplayActivity extends Activity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener
{
    private Movie inputMovie;

    // Movie data:
    private String name = "";
    private int expYear = 0;
    private int expMonth = 0;
    private int expDay = 0;
    private String selectedCategory = "";
    private String releaseYear = "";
    private String movieID = "";
    private String description = "";

    // UI Widget Objects:
    private Spinner categorySpinner = null;
    private EditText movieNameText = null;
    private TextView expDateText = null;
    private TextView movieIDText = null;
    private TextView scoreText = null;
    private EditText releaseYearText = null;
    private EditText descriptionText = null;
    private Button pickDateButton;
    private DatePickerDialog datePickerDialog;

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        expYear = year;
        expMonth = monthOfYear;
        expDay = dayOfMonth;
        updateDate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        // Disables keyboard auto-focusing when activity is started
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Get the passed Movie
        Bundle extras = getIntent().getExtras();
        inputMovie = null;
        if (extras != null)
        {
            //The key argument here must match that used in the other activity
            inputMovie = (Movie) extras.getSerializable("SelectedMovie");
            // Disable the "add" button
            Button addButton = (Button) findViewById(R.id.add_movie_button);
            addButton.setEnabled(false);
        }
        else
        {
            //No extra value: we are in "Add Movie" mode
            // Disable the "update" and "delete" buttons
            Button updateButton = (Button) findViewById(R.id.update_button);
            updateButton.setEnabled(false);
            Button deleteButton = (Button) findViewById(R.id.delete_button);
            deleteButton.setEnabled(false);
            Button dataButton = (Button) findViewById(R.id.user_data_button);
            dataButton.setEnabled(false);
            TextView averageScore = (TextView) findViewById(R.id.score_textview);
            averageScore.setText("");
        }

        // Set the "back" button to go back to the list of movies
        Button backButton = (Button) findViewById(R.id.cancel_button);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // Setup category spinners:
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> categoryAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, AccessMovies.CATEGORIES);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(this);

        // Setup text boxes:
        movieNameText = (EditText) findViewById(R.id.movie_name_text);
        expDateText = (TextView) findViewById(R.id.exp_year_text);
        movieIDText = (EditText) findViewById(R.id.movie_id_text);
        releaseYearText = (EditText) findViewById(R.id.release_year_text);
        descriptionText = (EditText) findViewById(R.id.description_text);
        scoreText = (TextView) findViewById(R.id.score_text);

        // Setup expiry date textbox and button
        Calendar expDate;
        if (inputMovie != null)
        {
            expDate = inputMovie.getEndDate();

            // Don't allow mID to be changed:
            movieIDText.setKeyListener(null);
            movieIDText.setEnabled(false);
        }
        else
        {
            expDate = Calendar.getInstance();
        }
        expYear = expDate.get(Calendar.YEAR);
        expMonth = expDate.get(Calendar.MONTH);
        expDay = expDate.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(MovieDisplayActivity.this, MovieDisplayActivity.this, expYear, expMonth, expDay);
        // add a click listener to the select a date button
        pickDateButton = (Button) findViewById(R.id.pickDate);
        pickDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                datePickerDialog.show();
            }
        });

        displayData();
    }

    // Handles a selection from a spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        System.out.println("Run onItemSelected.");
        switch(parent.getId())// Which spinner was changed?
        {
            case R.id.category_spinner:
                selectedCategory = (String) categorySpinner.getItemAtPosition(pos);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    private void displayData()
    {
        if (inputMovie != null)
        {
            // If we have an input movie, set all the entries to the input Movie's values
            movieNameText.setText(inputMovie.getTitle());
            releaseYearText.setText(Integer.toString(inputMovie.getReleaseYear()));
            String category = inputMovie.getCategory();
            categorySpinner.setSelection(java.util.Arrays.asList(AccessMovies.CATEGORIES).indexOf(category));

            datePickerDialog = new DatePickerDialog(MovieDisplayActivity.this, MovieDisplayActivity.this, expYear, expMonth, expDay);
            // set expDate in EditText
            expDateText.setText(
                    new StringBuilder()
                            // Month is 0 based so add 1
                            .append(expMonth + 1).append("/")
                            .append(expDay).append("/")
                            .append(expYear).append(" "));

            movieIDText.setText("" + inputMovie.getmID());
            descriptionText.setText(inputMovie.getDescription());
            scoreText.setText(Calculate.avgRating(inputMovie));
        }
        else
        {
            // Only do this if we are in "Add Movie"
            datePickerDialog = new DatePickerDialog(MovieDisplayActivity.this, MovieDisplayActivity.this, expYear, expMonth, expDay);
            // set expDate in EditText
            expDateText.setText(
                    new StringBuilder()
                            // Month is 0 based so add 1
                            .append(expMonth + 1).append("/")
                            .append(expDay).append("/")
                            .append(expYear).append(" "));
        }
    }

    public void updateDate()
    {
        datePickerDialog = new DatePickerDialog(MovieDisplayActivity.this, MovieDisplayActivity.this, expYear, expMonth, expDay);
        // set expDate in EditText
        expDateText.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(expMonth + 1).append("/")
                        .append(expDay).append("/")
                        .append(expYear).append(" "));
    }

    public void buttonMovieDeleteOnClick(View v)
    {
        if (inputMovie != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieDisplayActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Are you sure you want to delete this Movie ?\n" + inputMovie.getTitle());
            builder.setPositiveButton("Delete Me", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                    //Starting the previous Intent
                    Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
                    previousScreen.putExtra("DeleteMovieKey", inputMovie);
                    setResult(MovieListActivity.DELETE_MOVIE_CODE, previousScreen);
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else
        {
            Messages.fatalError(this, "Movie was not found.");
        }
    }

    public void buttonMovieUpdateOnClick(View v)
    {
        // Grab text input:
        name = movieNameText.getText().toString();
        releaseYear = releaseYearText.getText().toString();
        movieID = movieIDText.getText().toString();
        description = descriptionText.getText().toString();

        // Error checking Integer inputs:
        try
        {
            Integer.parseInt(movieID);
        }
        catch (NumberFormatException e)
        {
            Messages.warning(this, "Movie ID must be a number.");
            return;
        }

        try
        {
            Integer.parseInt(releaseYear);
        }
        catch (NumberFormatException e)
        {
            Messages.warning(this, "Year must be a number.");
            return;
        }

        String category = "";
        if (!selectedCategory.equals(""))
        {
            category = selectedCategory;
        }

        Calendar expDate = Calendar.getInstance();
        expDate.set(expYear, expMonth, expDay);

        String errorString = AccessMovies.validateMovie(name, Integer.parseInt(releaseYear),
                category, expDate, description);

        if (null == errorString && inputMovie == null && v.getId()==R.id.add_movie_button)// Add movie
        {
            if (!AccessMovies.mIDUnique(Integer.parseInt(movieID)))
            {
                Messages.warning(this, "Invalid movie ID entry. This ID already exists.");
                return;
            }

            // New movie is created and added here:
            Movie newMovie = new Movie(Integer.parseInt(movieID), name, Integer.parseInt(releaseYear),
                    category, expDate, description);

            //Starting the previous Intent
            Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
            previousScreen.putExtra("AddMovieKey", newMovie);
            setResult(MovieListActivity.ADD_MOVIE_CODE, previousScreen);
            finish();
        }
        else if (null == errorString && v.getId()==R.id.update_button)// Update movie
        {
            inputMovie.setTitle(name);
            inputMovie.setReleaseYear(Integer.parseInt(releaseYear));
            inputMovie.setCategory(category);
            inputMovie.setEndDate(expDate);
            inputMovie.setDescription(description);

            //Starting the previous Intent
            Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
            previousScreen.putExtra("UpdateMovieKey", inputMovie);
            setResult(MovieListActivity.UPDATE_MOVIE_CODE, previousScreen);
            finish();
        }
        else if(null != errorString)
        {
            Messages.warning(this, errorString);
        }
        else
        {
            Messages.fatalError(this, "Unknown button pressed.");
        }
    }

    public void buttonCancelOnClick(View v)
    {
        finish();
    }

    public void buttonUserDataOnClick(View v)
    {
        Intent dataDisplayIntent = new Intent(getApplicationContext(), DataDisplayActivity.class);
        dataDisplayIntent.putExtra("Movie", inputMovie);
        startActivity(dataDisplayIntent);
    }
}