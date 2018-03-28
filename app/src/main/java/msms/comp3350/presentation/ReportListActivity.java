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

public class ReportListActivity extends Activity
{
    private ListView reportList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        // get the list bundle
        Bundle listArgs = getIntent().getExtras();
        String[] listInfo = listArgs.getStringArray("listinfo");
        final String[] types = listArgs.getStringArray("types");
        final String[] subjects = listArgs.getStringArray("subjects");
        ArrayList<String> reports = new ArrayList<>(Arrays.asList(listInfo));

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
                reportList.setItemChecked(position, true);
                String title = "no data";
                String[][] data = { { "none" }, { "1" } };

                // Launch a new intent:
                Intent chart = null;
                switch(types[position])
                {
                    case "bar":
                        chart = new Intent(ReportListActivity.this, BarChartActivity.class);
                        break;
                    case "pie":
                        chart = new Intent(ReportListActivity.this, PieChartActivity.class);
                        break;
                }
                if(null != chart)
                {
                    switch(subjects[position])
                    {
                        case "ages":
                            title = "User Ages";
                            data = UserCharts.getUserAges();
                            break;
                        case "categories":
                            title = "Movie Categories";
                            data = MovieCharts.getMovieCategories();
                            break;
                        case "decades":
                            title = "Movie Release Years";
                            data = MovieCharts.getMovieDecades();
                            break;
                        case "genders":
                            title = "User Genders";
                            data = UserCharts.getUserGenders();
                            break;
                        case "ratings":
                            title = "Ratings";
                            data = MovieCharts.getMovieRatings();
                            break;
                    }

                    Bundle chartArgs = new Bundle();
                    chartArgs.putString("title", title);
                    chartArgs.putStringArray("labels", data[0]);
                    chartArgs.putStringArray("data", data[1]);
                    chart.putExtras(chartArgs);
                    ReportListActivity.this.startActivity(chart);
                }
            }
        });
    }
}
