package msms.comp3350.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;

public class MovieDisplayActivity extends Activity {
    Movie inputMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        // Get the passed Movie
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            inputMovie = (Movie) extras.getParcelable("SelectedMovie");
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

        displayData(inputMovie);
    }

    private void displayData(Movie inputMovie){
        String outputText;

        outputText = "Title: " + inputMovie.getTitle() + "\n";
        outputText = outputText + "Year: " + inputMovie.getReleaseYear() + "\n";
        outputText = outputText + "User Score: " + inputMovie.getUserScore() + " out of 100\n";
        ArrayList<String> categories = inputMovie.getCategory();
        for (int i = 0; i < categories.size(); i++){
            outputText = outputText + "Category " + i + " : " + categories.get(i) + "\n";
        }
        outputText = outputText + "End Date: " + inputMovie.getEndDate().getTime() + "\n\n";
        outputText = outputText + inputMovie.getDescription();

        TextView output = (TextView) findViewById(R.id.movie_info_text);
        output.setText((CharSequence) outputText);
    }
}
