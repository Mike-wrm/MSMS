package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;


public class DataDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        User inputUser = null;
        Movie inputMovie = null;

        // Are we to display movie data, or user data?
        if (extras.getSerializable("Movie") != null)
            inputMovie = (Movie) extras.getSerializable("Movie");
        else if (extras.getSerializable("User") != null)
            inputUser = (User) extras.getSerializable("User");
        else
        {
            System.out.println("ERROR: No extras sent to DataDisplayActivity");
            Messages.fatalError(this, "No movie or user detected");
            finish();
            return;
        }

        // Initialize UI widgets:
        TextView historyInfoText = (TextView) findViewById(R.id.history_info_text);
        TextView historyText = (TextView) findViewById(R.id.history_text);
        ListView reportsList = (ListView) findViewById(R.id.listSpecificReports);

        // Populate UI widgets with the appropriate data
        if (inputMovie != null && inputUser == null)
        {
            historyInfoText.setText("Watched by:");
            // TODO: populate historyText and reportsList here
        }
        else if (inputUser != null && inputMovie == null)
        {
            historyInfoText.setText("Movies watched:");
            // TODO: populate historyText and reportsList here
        }
        else
        {
            System.out.println("ERROR: only one of inputMovie or inputUser can be null");
            Messages.fatalError(this, "Must only accept one movie or user");
            finish();
            return;
        }
    }
}
