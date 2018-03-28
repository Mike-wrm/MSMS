package msms.comp3350.charts;

import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;

import msms.comp3350.main.R;

public class PieChartActivity extends ChartActivity
{
    private PieChart pie;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        int[] intData;
        Segment[] segments;
        SegmentFormatter[] formats;

        // create view
        pie = findViewById(R.id.pie);

        // convert pie chart data
        intData = new int[data.length];
        int movieCount = 0;
        for(int i = 0 ; i < data.length ; i++)
        {
            intData[i] = Integer.parseInt(data[i]);
            movieCount += intData[i];
        }
        if(0 == movieCount) intData[0]++;
        segments = new Segment[labels.length];
        formats = new SegmentFormatter[labels.length];

        // calculate number of colors needed
        int numColors = 7;
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
                case 5: colors[i] = Color.DKGRAY; break;
                case 6: colors[i] = Color.YELLOW; break;
                default: colors[i] = Color.BLACK;
            }
        }

        // create segments
        for(int i = 0 ; i < labels.length ; i++)
        {
            if(intData[i] > 0)
            {
                segments[i] = new Segment(labels[i], intData[i]);
                formats[i] = new SegmentFormatter(colors[i]);
                formats[i].getLabelPaint().setTextSize(50);
                if(colors[i] == Color.YELLOW) formats[i].getLabelPaint().setColor(Color.BLACK);
                pie.addSegment(segments[i], formats[i]);
            }
        }

        // fill donut hole
        pie.getRenderer(PieRenderer.class).setDonutSize(0, PieRenderer.DonutMode.PERCENT);
    }
}
