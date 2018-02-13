package msms.comp3350.presentation;

import msms.comp3350.objects.SpinnerArrays;
import msms.comp3350.objects.Movie;
import msms.comp3350.business.AccessMovies;
import msms.comp3350.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddMovieActivity extends Activity implements AdapterView.OnItemSelectedListener
{
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
        setContentView(R.layout.activity_add_movie);

        // Setup month spinner:
        monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        ArrayAdapter<CharSequence> monthAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, SpinnerArrays.getMonths());
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(this);

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
    }

    // Handles a selection from the genre spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {

        switch(parent.getId())// Which spinner was changed?
        {
            case R.id.month_spinner:
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

    // Called when add_movie_button is pressed
    public void addMovie(View view)
    {
        // Grab text input:
        name = movieNameText.getText().toString();
        expYear = expYearText.getText().toString();
        releaseYear = releaseYearText.getText().toString();
        description = descriptionText.getText().toString();

        // TODO: add error handling
        // Convert selectedCategories into ArrayList:
        ArrayList<String> categoriesAL = new ArrayList<String>();
        for (int i=0; i<3; i++)
            if (!selectedCategories[i].equals(""))
                categoriesAL.add(selectedCategories[i]);

        Calendar expDate = Calendar.getInstance();
        expDate.set(Integer.parseInt(expYear), Integer.parseInt(selectedMonth), Integer.parseInt(selectedDay));

        // TODO: New movie is created and added here:
        Movie newMovie = new Movie(1, name, Integer.parseInt(releaseYear), Integer.parseInt(selectedScore),
                categoriesAL, expDate, description);
        AccessMovies accessor = new AccessMovies();
        accessor.insertMovie(newMovie);

        finish();
    }

    // Displays a dialogue with the provided message
    public void showWarning(String warningMsg)
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);


        builder.setMessage(warningMsg)
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do stuff...
            }
        })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
    }

    public void cancel(View view)
    {
        finish();
    }
}