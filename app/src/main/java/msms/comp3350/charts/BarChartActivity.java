package msms.comp3350.charts;

import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.xy.*;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import msms.comp3350.main.R;

public class BarChartActivity extends ChartActivity
{
    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        // initialize our XYPlot reference:
        plot = findViewById(R.id.bar);

        // convert data to numbers
        Number[] intData = new Number[data.length];
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
