package msms.comp3350.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.business.AccessMovies;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieDisplayActivity extends Activity {
    private Movie inputMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        // Get the passed Movie
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            inputMovie = (Movie) extras.getSerializable("SelectedMovie");
        }
        else{
            //Error getting extra value
            finish();
        }

        // Set the "back" button to go back to the list of movies
        Button backButton = (Button) findViewById(R.id.buttonBackToMovieList);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        displayData();
    }

    private void displayData(){
        String outputText;

        if (inputMovie != null) {
            outputText = "Title: " + inputMovie.getTitle() + "\n";
            outputText = outputText + "Year: " + inputMovie.getReleaseYear() + "\n";
            outputText = outputText + "User Score: " + inputMovie.getUserScore() + " out of 100\n";
            ArrayList<String> categories = inputMovie.getCategory();
            for (int i = 0; i < categories.size(); i++) {
                outputText = outputText + "Category " + (i + 1) + " : " + categories.get(i) + "\n";
            }
            outputText = outputText + "Licence Expiry Date: " + inputMovie.getEndDate().getTime() + "\n\n";
            outputText = outputText + inputMovie.getDescription();

            TextView output = (TextView) findViewById(R.id.movie_info_text);
            output.setText((CharSequence) outputText);
        }
        else{
            Messages.fatalError(this, "Movie was not found.");
        }
    }

    public void buttonMovieDeleteOnClick(View v) {
        if (inputMovie != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieDisplayActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Are you sure you want to delete this Movie ?\n" + inputMovie.getTitle());
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    //Starting the previous Intent
                    Intent previousScreen = new Intent(getApplicationContext(), MovieListActivity.class);
                    //Sending the data to Activity_A
                    previousScreen.putExtra("DeleteKey", inputMovie);
                    setResult(1000, previousScreen);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            Messages.fatalError(this, "Movie was not found.");
        }
    }

    public void buttonMovieUpdateOnClick(View v){

    }
}
