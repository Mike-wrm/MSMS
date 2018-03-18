package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import msms.comp3350.business.AccessWatchedEvents;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;


public class DataDisplayActivity extends Activity {

    private ArrayList<WatchedEvent> currentViews;
    private AccessWatchedEvents accessEvents;

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
        historyText.setMovementMethod(new ScrollingMovementMethod());
        ListView reportsList = (ListView) findViewById(R.id.listSpecificReports);

        currentViews = new ArrayList<WatchedEvent>();
        accessEvents = new AccessWatchedEvents();

        // Populate UI widgets with the appropriate data
        if (inputMovie != null && inputUser == null)
        {
            historyInfoText.setText("Watched by:");
            historyText.setText("");

            // populate historyText
            String errorMessage = accessEvents.getMoviesUsers(currentViews, inputMovie);
            if (errorMessage != null)
            {
                Messages.warning(this, errorMessage);
            }
            for (int i = 0; i < currentViews.size(); i++)
            {
                historyText.append(currentViews.get(i).getUserName() + "\n");
            }

            // TODO: populate reportsList here
        }
        else if (inputUser != null && inputMovie == null)
        {
            historyInfoText.setText("Movies watched:");
            historyText.setText("");
            // populate historyText
            String errorMessage = accessEvents.getUsersMovies(currentViews, inputUser);
            if (errorMessage != null)
            {
                Messages.warning(this, errorMessage);
            }
            for (int i = 0; i < currentViews.size(); i++)
            {
                historyText.append(currentViews.get(i).getMovieTitle() + "\n");
            }

            // TODO: populate reportsList here
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
