package msms.comp3350.charts;

import android.app.Activity;
import android.os.Bundle;

public abstract class ChartActivity extends Activity
{
    String title;
    String[] labels, data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // extract the bundled data
        Bundle args = getIntent().getExtras();
        if(!args.isEmpty())
        {
            title = args.getString("title");
            labels = args.getStringArray("labels");
            data = args.getStringArray("data");
        }
        else
        {
            title = "Unknown Chart";
            labels = new String[] { "empty list" };
            data = new String[] { "1" };
        }
        setTitle(title);
    }
}
