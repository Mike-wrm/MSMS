package msms.comp3350.main.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import msms.comp3350.main.R;

public class MovieDisplayActivity extends Activity {
    // TODO change this from an int to a Movie
    int inputMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        // Get the passed Movie
        // TODO note class Movie must implement Parcelable for this to work
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            inputMovie = extras.getInt("SelectedMovie");
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

    // TODO change from int to a Movie
    private void displayData(int inputMovie){
        String outputText;
        outputText = "You have pressed movie: " + inputMovie;

        TextView output = (TextView) findViewById(R.id.movie_info_text);
        output.setText((CharSequence) outputText);
    }
}
