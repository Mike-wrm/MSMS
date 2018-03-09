package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import msms.comp3350.business.MovieCharts;
import msms.comp3350.business.UserCharts;
import msms.comp3350.charts.*;
import msms.comp3350.main.R;

public class ReportListActivity extends Activity {
    private int selectedPosition = -1;
    private ListView reportList = null;

    private ArrayList<String> reports = new ArrayList<>(Arrays.asList(
            "User Age Summary",
            "Movie Category Summary"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        // Setup the ListView:
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, reports);
        reportList = findViewById(R.id.listReports);
        reportList.setAdapter(adapter);

        // Called when an item is selected from the list
        reportList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                if (position == selectedPosition)// Same item selected: deselect this item
                {
                    reportList.setItemChecked(position, false);
                    selectedPosition = -1;
                }
                else// New item selected
                {
                    reportList.setItemChecked(position, true);
                    selectedPosition = position;
                    String[][] data;
                    Bundle args;

                    // Launch a new intent:
                    switch(position)
                    {
                        case 0: // User Age Summary
                            Intent userAges = new Intent(ReportListActivity.this, BarChartActivity.class);
                            data = UserCharts.getUserAges();
                            args = new Bundle();
                            args.putString("title", "Users by Age Range");
                            args.putStringArray("labels", data[0]);
                            args.putStringArray("data", data[1]);
                            userAges.putExtras(args);
                            ReportListActivity.this.startActivity(userAges);
                            break;
                        case 1: // Movie Category Summary
                            Intent movieCats = new Intent(ReportListActivity.this, PieChartActivity.class);
                            data = MovieCharts.getMovieCategories();
                            args = new Bundle();
                            args.putString("title", "Movies by Category");
                            args.putStringArray("labels", data[0]);
                            args.putStringArray("data", data[1]);
                            movieCats.putExtras(args);
                            ReportListActivity.this.startActivity(movieCats);
                            break;
                    }
                }
            }
        });
    }
}
