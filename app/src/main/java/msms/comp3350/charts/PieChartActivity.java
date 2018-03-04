package msms.comp3350.charts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;

import msms.comp3350.main.R;

public class PieChartActivity extends Activity {

    private PieChart pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        String title;
        String[] labels;
        int[] data;
        Segment[] segments;
        SegmentFormatter[] formats;

        // create view
        pie = findViewById(R.id.pie);

        // extract passed data
        Bundle args = getIntent().getExtras();
        if(!args.isEmpty())
        {
            title = args.getString("title");
            labels = args.getStringArray("labels");
            data = args.getIntArray("data");
            segments = new Segment[labels.length];
            formats = new SegmentFormatter[labels.length];
        }
        else
        {
            title = "Unknown Chart";
            labels = new String[] { "empty list" };
            data = new int[] { 1 };
            segments = new Segment[1];
            formats = new SegmentFormatter[1];
        }
        setTitle(title);

        // calculate number of colors needed
        int numColors = 3;
        while(labels.length % numColors == 1)
        {
            numColors++;
        }
        int[] colors = new int[labels.length];
        for(int i = 0 ; i < colors.length ; i++)
        {
            switch(i % numColors)
            {
                case 0: colors[i] = Color.RED; break;
                case 1: colors[i] = Color.BLUE; break;
                case 2: colors[i] = Color.GREEN; break;
                case 3: colors[i] = Color.MAGENTA; break;
                case 4: colors[i] = Color.CYAN; break;
                default: colors[i] = Color.BLACK;
            }
        }

        // create segments
        for(int i = 0 ; i < labels.length ; i++)
        {
            if(data[i] > 0)
            {
                segments[i] = new Segment(labels[i], data[i]);
                formats[i] = new SegmentFormatter(colors[i]);
                formats[i].getLabelPaint().setTextSize(50);
                pie.addSegment(segments[i], formats[i]);
            }
        }

        // fill donut hole
        pie.getRenderer(PieRenderer.class).setDonutSize(0, PieRenderer.DonutMode.PERCENT);
    }
}
