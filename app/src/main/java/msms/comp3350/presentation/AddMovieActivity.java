package msms.comp3350.presentation;

import msms.comp3350.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.DialogInterface;

public class AddMovieActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    String[] selectedGenres = {"", "", ""};
    Spinner genreSpinner = null;
    Spinner genreSpinner2 = null;
    Spinner genreSpinner3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        // Setup the spinners:
        genreSpinner = (Spinner) findViewById(R.id.genre_spinner);
        genreSpinner2 = (Spinner) findViewById(R.id.genre_spinner2);
        genreSpinner3 = (Spinner) findViewById(R.id.genre_spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);
        genreSpinner2.setAdapter(adapter);
        genreSpinner3.setAdapter(adapter);

        // Allows onItemSelected() and onNothingSelected to be called:
        genreSpinner.setOnItemSelectedListener(this);
        genreSpinner2.setOnItemSelectedListener(this);
        genreSpinner3.setOnItemSelectedListener(this);
    }

    // Handles a selection from the genre spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {

        switch(parent.getId())
        {
            case R.id.genre_spinner:
                selectedGenres[0] = (String) genreSpinner.getItemAtPosition(pos); break;
            case R.id.genre_spinner2:
                selectedGenres[1] = (String) genreSpinner2.getItemAtPosition(pos); break;
            case R.id.genre_spinner3:
                selectedGenres[2] = (String) genreSpinner3.getItemAtPosition(pos); break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    // Called when add_movie_button is pressed
    public void addMovie(View view)
    {
        String movieName = "";
        String movieLengthStr = "";
        int movieLength = -1;

        EditText movieNameText = (EditText) findViewById(R.id.movie_name_text);
        movieName = movieNameText.getText().toString();
        EditText movieLengthText = (EditText) findViewById(R.id.movie_length_text);
        movieLengthStr = movieLengthText.getText().toString();

        if (movieName.equals("") || movieLengthStr.equals(""))// ERROR: blank fields
            showWarning("Movie name and length are required fields");
        else
        {
            try
            {
                movieLength = Integer.parseInt(movieLengthStr);

                if (movieLength < 0)// ERROR: negative movieLength
                    showWarning("Movie length must be greater than 0");
                else
                {
                    if (selectedGenres[0].equals("None"))// ERROR: No genre selected
                        showWarning("Please select at least one genre");
                    else// Add new movie
                    {
                        openMovieList();
                    }
                }
            }
            catch (NumberFormatException e)// ERROR: movieLength is not an int
            {
                showWarning("Movie length must be an integer");
            }
        }
    }

    public void openMovieList()
    {
        Intent movieListIntent = new Intent(this, MovieListActivity.class);
        startActivity(movieListIntent);
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
}