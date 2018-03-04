package msms.comp3350.charts;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;

import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.*;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import msms.comp3350.main.R;

public class BarChartActivity extends Activity {

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        String title;
        final String[] labels, data;
        Number[] intData;

        // initialize our XYPlot reference:
        plot = findViewById(R.id.bar);

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

        // convert data to numbers
        intData = new Number[data.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            intData[i] = Integer.parseInt(data[i]);
        }

        // turn the above arrays into XYSeries':
        XYSeries barData = new SimpleXYSeries(
                Arrays.asList(intData),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, null);
        // (Y_VALS_ONLY means use the element index as the x value)

        // bar formatting
        BarFormatter bf = new BarFormatter(Color.RED, Color.WHITE);

        // add a new series' to the xyplot:
        plot.addSeries(barData, bf);
        plot.setRangeStep(StepMode.INCREMENT_BY_FIT, 1);
        plot.setDomainStep(StepMode.SUBDIVIDE, data.length);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).setFormat(new DecimalFormat("#"));
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format()
        {
            @Override
            public StringBuffer format(Object obj, StringBuffer buffer, FieldPosition field)
            {
                int i = Math.round(((Number) obj).floatValue());
                return buffer.append(labels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos)
            {
                return null;
            }
        });

        BarRenderer renderer = plot.getRenderer(BarRenderer.class);
        renderer.setBarGroupWidth(BarRenderer.BarGroupWidthMode.FIXED_WIDTH, 100);
    }
}
