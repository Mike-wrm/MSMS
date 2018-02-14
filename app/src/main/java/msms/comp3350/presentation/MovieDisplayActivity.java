package msms.comp3350.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieDisplayActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    private Movie inputMovie;

    private String name = "";
    private String selectedDay = "";
    private String selectedMonth = "";
    private String expYear = "";
    private String[] selectedCategories = {"", "", ""};
    private String releaseYear = "";
    private String selectedScore = "";
    private String description = "";

    private Spinner daySpinner = null;
    private Spinner monthSpinner = null;
    private Spinner categorySpinner = null;
    private Spinner categorySpinner2 = null;
    private Spinner categorySpinner3 = null;
    private Spinner scoreSpinner = null;

    private EditText movieNameText = null;
    private EditText expYearText = null;
    private EditText releaseYearText = null;
    private EditText descriptionText = null;

    // For populating the day spinner based on the month spinner's selection:
    private String[] monthsOf30Days = {"4", "6", "9", "11"};
    private String[] myMonthsDays = null;
    private ArrayAdapter<CharSequence> dayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        // Get the passed Movie
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            //The key argument here must match that used in the other activity
            inputMovie = (Movie) extras.getSerializable("SelectedMovie");
        }
        else
        {
            //Error getting extra value
            finish();
        }

        // Set the "back" button to go back to the list of movies
        Button backButton = (Button) findViewById(R.id.cancel_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        // Setup month spinner:
        monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        ArrayAdapter<CharSequence> monthAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, SpinnerArrays.getMonths());
        monthSpinner.setAdapter(monthAdapter);

        daySpinner = (Spinner) findViewById(R.id.day_spinner);

        // Setup category spinners:
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        categorySpinner2 = (Spinner) findViewById(R.id.category_spinner2);
        categorySpinner3 = (Spinner) findViewById(R.id.category_spinner3);
        ArrayAdapter<CharSequence> categoryAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, SpinnerArrays.getCategories());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner2.setAdapter(categoryAdapter);
        categorySpinner3.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(this);
        categorySpinner2.setOnItemSelectedListener(this);
        categorySpinner3.setOnItemSelectedListener(this);

        // Setup score spinner:
        scoreSpinner = (Spinner) findViewById(R.id.score_spinner);
        ArrayAdapter<CharSequence> scoreAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, SpinnerArrays.getScores());
        scoreSpinner.setAdapter(scoreAdapter);
        scoreSpinner.setOnItemSelectedListener(this);

        // Setup textEdit boxes:
        movieNameText = (EditText) findViewById(R.id.movie_name_text);
        expYearText = (EditText) findViewById(R.id.exp_year_text);
        releaseYearText = (EditText) findViewById(R.id.release_year_text);
        descriptionText = (EditText) findViewById(R.id.description_text);

        displayData();
    }

    // Handles a selection from the genre spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        System.out.println("Run onItemSelected.");
        switch(parent.getId())// Which spinner was changed?
        {
            case R.id.month_spinner:
                System.out.println("Change the month spinner.");
                selectedMonth = (String) monthSpinner.getItemAtPosition(pos);

                // Populate the day spinner based on the month selected:
                if (Arrays.asList(monthsOf30Days).contains(selectedMonth))
                    myMonthsDays = Arrays.copyOfRange(SpinnerArrays.getDays(), 0, 30);
                else if (selectedMonth.equals("2"))
                    myMonthsDays = Arrays.copyOfRange(SpinnerArrays.getDays(), 0, 28);
                else
                    myMonthsDays = SpinnerArrays.getDays();

                dayAdapter = new ArrayAdapter<CharSequence>(this,
                        android.R.layout.simple_spinner_dropdown_item, myMonthsDays);
                daySpinner.setAdapter(dayAdapter);
                daySpinner.setOnItemSelectedListener(this);
                break;

            case R.id.day_spinner:
                selectedDay = (String) daySpinner.getItemAtPosition(pos);
                break;

            case R.id.category_spinner:
                selectedCategories[0] = (String) categorySpinner.getItemAtPosition(pos);
                break;

            case R.id.category_spinner2:
                selectedCategories[1] = (String) categorySpinner2.getItemAtPosition(pos);
                break;

            case R.id.category_spinner3:
                selectedCategories[2] = (String) categorySpinner3.getItemAtPosition(pos);
                break;

            case R.id.score_spinner:
                selectedScore = (String) scoreSpinner.getItemAtPosition(pos);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    private void displayData()
    {
        if (inputMovie != null)
        {
            movieNameText.setText(inputMovie.getTitle());
            releaseYearText.setText(Integer.toString(inputMovie.getReleaseYear()));
            scoreSpinner.setSelection(inputMovie.getUserScore() - 1);
            ArrayList<String> categories = inputMovie.getCategory();

            for (int i = 0; i < categories.size(); i++)
            {
                switch(i)
                {
                    case 0:
                        categorySpinner.setSelection(java.util.Arrays.asList(SpinnerArrays.getCategories()).indexOf(categories.get(i)));
                        break;
                    case 1:
                        categorySpinner2.setSelection(java.util.Arrays.asList(SpinnerArrays.getCategories()).indexOf(categories.get(i)));
                        break;
                    case 2:
                        categorySpinner3.setSelection(java.util.Arrays.asList(SpinnerArrays.getCategories()).indexOf(categories.get(i)));
                        break;
                }
            }

            Calendar expDate = inputMovie.getEndDate();

            // We must ensure our daySpinner adapter is set first before we set what day of the month it is
            selectedMonth = (String) monthSpinner.getItemAtPosition(expDate.get(Calendar.MONTH) - 1);
            // Populate the day spinner based on the month selected:
            if (Arrays.asList(monthsOf30Days).contains(selectedMonth))
                myMonthsDays = Arrays.copyOfRange(SpinnerArrays.getDays(), 0, 30);
            else if (selectedMonth.equals("2"))
                myMonthsDays = Arrays.copyOfRange(SpinnerArrays.getDays(), 0, 28);
            else
                myMonthsDays = SpinnerArrays.getDays();
            dayAdapter = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_dropdown_item, myMonthsDays);
            daySpinner.setAdapter(dayAdapter);

            monthSpinner.setSelection(expDate.get(Calendar.MONTH) - 1, false);

            selectedDay = (String) daySpinner.getItemAtPosition(expDate.get(Calendar.DAY_OF_MONTH) - 1);
            daySpinner.setSelection(expDate.get(Calendar.DAY_OF_MONTH) - 1, false);

            daySpinner.setOnItemSelectedListener(this);
            monthSpinner.setOnItemSelectedListener(this);

            expYearText.setText(Integer.toString(expDate.get(Calendar.YEAR)));

            descriptionText.setText(inputMovie.getDescription());
        }
        else
        {
            Messages.fatalError(this, "Movie was not found.");
        }
    }

    public void buttonMovieDeleteOnClick(View v)
    {
        if (inputMovie != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieDisplayActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Are you sure you want to delete this Movie ?\n" + inputMovie.getTitle());
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    //Starting the previous Intent
                    Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
                    previousScreen.putExtra("DeleteMovieKey", inputMovie);
                    setResult(1000, previousScreen);
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
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
        expYear = expYearText.getText().toString();
        releaseYear = releaseYearText.getText().toString();
        description = descriptionText.getText().toString();

        // error checking
        if(null == name)
        {
            Messages.warning(this, "You need to name your movie.");
            return;
        }

        try
        {
            Integer.parseInt(expYear);
            Integer.parseInt(releaseYear);
        }
        catch (NumberFormatException e) {
            Messages.warning(this, "Year must be a number.");
            return;
        }

        int checkExpYear = Integer.parseInt(expYear);

        if (checkExpYear < Calendar.getInstance().get(Calendar.YEAR))
        {
            Messages.warning(this, "Invalid year entry. Can't enter movie with expired rights");
            return;
        }
        else if (checkExpYear > Calendar.getInstance().get(Calendar.YEAR) + 5)
        {
            Messages.warning(this, "Invalid year entry. Can't acquire movie rights beyond 5 years.");
            return;
        }

        int checkReleaseYear = Integer.parseInt(releaseYear);

        if (checkReleaseYear < 1900)
        {
            Messages.warning(this, "Invalid year entry. Movies did not exist during this time.");
            return;
        }
        else if (checkReleaseYear > Calendar.getInstance().get(Calendar.YEAR))
        {
            Messages.warning(this, "Invalid year entry. Can't add movies from beyond current year.");
            return;
        }

        // Convert selectedCategories into ArrayList:
        ArrayList<String> categoriesAL = new ArrayList<String>();
        for (int i=0; i<3; i++)
            if (!selectedCategories[i].equals(""))
                categoriesAL.add(selectedCategories[i]);

        Calendar expDate = Calendar.getInstance();
        expDate.set(Integer.parseInt(expYear), Integer.parseInt(selectedMonth), Integer.parseInt(selectedDay));

        // Movie is updated here:
        inputMovie.setTitle(name);
        inputMovie.setReleaseYear(Integer.parseInt(releaseYear));
        inputMovie.setUserScore(Integer.parseInt(selectedScore));
        inputMovie.setCategory(categoriesAL);
        inputMovie.setEndDate(expDate);
        inputMovie.setDescription(description);

        //Starting the previous Intent
        Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
        previousScreen.putExtra("UpdateMovieKey", inputMovie);
        setResult(1000, previousScreen);
        finish();
    }

    public void buttonCancelOnClick(View v)
    {
        finish();
    }

}
