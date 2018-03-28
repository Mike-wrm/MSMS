package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import msms.comp3350.business.AccessWatchedEvents;
import msms.comp3350.business.ChartData;
import msms.comp3350.business.MovieCharts;
import msms.comp3350.business.UserCharts;
import msms.comp3350.charts.BarChartActivity;
import msms.comp3350.charts.PieChartActivity;
import msms.comp3350.main.R;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;


public class DataDisplayActivity extends Activity
{
    private ArrayList<WatchedEvent> currentViews;
    private AccessWatchedEvents accessEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        User inputUser = null;
        Movie inputMovie = null;
        String[][] listInfo;

        // Are we to display movie data, or user data?
        if (extras.getSerializable("Movie") != null)
        {
            inputMovie = (Movie) extras.getSerializable("Movie");
            listInfo = ChartData.getUserLists();
        }
        else if (extras.getSerializable("User") != null)
        {
            inputUser = (User) extras.getSerializable("User");
            listInfo = ChartData.getMovieLists();
        }
        else
        {
            System.out.println("ERROR: No extras sent to DataDisplayActivity");
            Messages.fatalError(this, "No movie or user detected");
            finish();
            return;
        }

        ArrayList<String> reports = new ArrayList<>(Arrays.asList(listInfo[0]));
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, reports);
        final String[] types = listInfo[1];
        final String[] subjects = listInfo[2];
        final Movie movie = inputMovie;
        final User user = inputUser;

        // Initialize UI widgets:
        TextView historyInfoText = (TextView) findViewById(R.id.history_info_text);
        TextView historyText = (TextView) findViewById(R.id.history_text);
        historyText.setMovementMethod(new ScrollingMovementMethod());
        final ListView reportsList = (ListView) findViewById(R.id.listSpecificReports);
        reportsList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            reportsList.setItemChecked(position, true);
            String title = "no data";
            String[][] data = { { "none" }, { "1" } };

            // Launch a new intent:
            Intent chart = null;
            switch(types[position])
            {
                case "bar":
                    chart = new Intent(DataDisplayActivity.this, BarChartActivity.class);
                    break;
                case "pie":
                    chart = new Intent(DataDisplayActivity.this, PieChartActivity.class);
                    break;
            }

            if(null != chart)
            {
                switch(subjects[position])
                {
                    case "ages":
                        title = "User Ages";
                        data = UserCharts.getUserAges(movie);
                        break;
                    case "categories":
                        title = "Movie Categories";
                        data = MovieCharts.getMovieCategories(user);
                        break;
                    case "decades":
                        title = "Movie Release Years";
                        data = MovieCharts.getMovieDecades(user);
                        break;
                    case "genders":
                        title = "User Genders";
                        data = UserCharts.getUserGenders(movie);
                        break;
                    case "ratings":
                        title = "Ratings";
                        if(user != null) data = MovieCharts.getMovieRatings(user);
                        else data = MovieCharts.getMovieRatings(movie);
                        break;
                }

                Bundle chartArgs = new Bundle();
                chartArgs.putString("title", title);
                chartArgs.putStringArray("labels", data[0]);
                chartArgs.putStringArray("data", data[1]);
                chart.putExtras(chartArgs);
                DataDisplayActivity.this.startActivity(chart);
            }
            }
        });

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
                historyText.append(currentViews.get(i) + "\n");
            }

            reportsList.setAdapter(adapter);
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
                historyText.append(currentViews.get(i) + "\n");
            }

            reportsList.setAdapter(adapter);
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
