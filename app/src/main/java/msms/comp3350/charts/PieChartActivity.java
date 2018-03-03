package msms.comp3350.charts;

import android.app.Activity;
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

        pie = (PieChart) findViewById(R.id.pie);

        int m = 35;
        int f = 30;

        Segment males = new Segment("Males", m);
        Segment females = new Segment("Females", f);

        SegmentFormatter formatM = new SegmentFormatter(Color.BLUE);
        SegmentFormatter formatF = new SegmentFormatter(Color.RED);
        formatM.getLabelPaint().setTextSize(80);
        formatF.getLabelPaint().setTextSize(80);

        pie.addSegment(males, formatM);
        pie.addSegment(females, formatF);

        pie.getRenderer(PieRenderer.class).setDonutSize(0, PieRenderer.DonutMode.PERCENT);
    }
}
