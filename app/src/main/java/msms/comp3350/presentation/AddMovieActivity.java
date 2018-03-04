package msms.comp3350.presentation;

import msms.comp3350.objects.Movie;

import msms.comp3350.main.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddMovieActivity extends Activity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener
{
    private String name = "";
    private int expYear = 0;
    private int expMonth = 0;
    private int expDay = 0;
    private String[] selectedCategories = {"", "", ""};
    private String releaseYear = "";
    private String selectedScore = "";
    private String description = "";

    private Spinner categorySpinner = null;
    private Spinner categorySpinner2 = null;
    private Spinner categorySpinner3 = null;
    private Spinner scoreSpinner = null;

    private EditText movieNameText = null;
    private TextView expDateText = null;
    private EditText releaseYearText = null;
    private EditText descriptionText = null;

    private Button pickDateButton = null;

    private DatePickerDialog datePickerDialog;
    public void onDateSet(DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
        expYear = year;
        expMonth = monthOfYear;
        expDay = dayOfMonth;
        displayData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

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
        expDateText = (TextView) findViewById(R.id.exp_year_text);
        releaseYearText = (EditText) findViewById(R.id.release_year_text);
        descriptionText = (EditText) findViewById(R.id.description_text);

        // Setup expiry date textbox and button
        Calendar defaultDate = Calendar.getInstance();
        expYear = defaultDate.get(Calendar.YEAR);
        expMonth = defaultDate.get(Calendar.MONTH);
        expDay = defaultDate.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(AddMovieActivity.this, AddMovieActivity.this, expYear, expMonth, expDay);
        // add a click listener to the select a date button
        pickDateButton = (Button) findViewById(R.id.pickDate);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        displayData();
    }

    // Handles a selection from the genre spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        switch(parent.getId())// Which spinner was changed?
        {
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

        datePickerDialog = new DatePickerDialog(AddMovieActivity.this, AddMovieActivity.this, expYear, expMonth, expDay);
        // set expDate in EditText
        expDateText.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(expMonth + 1).append("/")
                        .append(expDay).append("/")
                        .append(expYear).append(" "));
    }

    // Called when add_movie_button is pressed
    public void addMovie(View view)
    {
        // Grab text input:
        name = movieNameText.getText().toString();
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
            Integer.parseInt(releaseYear);
        }
        catch (NumberFormatException e) {
            Messages.warning(this, "Year must be a number.");
            return;
        }

        int checkExpYear = expYear;

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
        expDate.set(expYear, expMonth, expDay);

        // New movie is created and added here:
        Movie newMovie = new Movie(name, Integer.parseInt(releaseYear), Integer.parseInt(selectedScore),
                categoriesAL, expDate, description);

        //Starting the previous Intent
        Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
        previousScreen.putExtra("AddKey", newMovie);
        setResult(1001, previousScreen);
        finish();
    }

    public void cancel(View view)
    {
        finish();
    }
}