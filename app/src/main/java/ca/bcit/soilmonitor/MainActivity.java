package ca.bcit.soilmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    LineChart mpLineChart;
    HashMap<String, String> mapColor = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapColor.put("greenLeaf", "#7FB241");
        mapColor.put("brown", "#A07E63");
        mapColor.put("blue","#1ecbe1");

        mpLineChart = findViewById(R.id.graph);

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Temp");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Humidity");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();

        configureMPChart();
        configureDataset(lineDataSet1, mapColor.get("greenLeaf"), mapColor.get("brown"));
        configureDataset(lineDataSet2, mapColor.get("blue"), mapColor.get("brown"));
    }
    
    private void configureMPChart() {
        //no description text
        mpLineChart.getDescription().setEnabled(false);

        //enable touch gesture
        mpLineChart.setTouchEnabled(true);

        //enable scaling and dragging
        mpLineChart.setDragEnabled(true);
        mpLineChart.setScaleEnabled(true);

        //remove gridline
        mpLineChart.getAxisRight().setDrawGridLines(false);
        mpLineChart.getAxisLeft().setDrawGridLines(false);
        mpLineChart.getXAxis().setDrawGridLines(false);

        //remove outer line
        mpLineChart.getAxisRight().setDrawAxisLine(false);
        mpLineChart.getAxisLeft().setDrawAxisLine(false);
        mpLineChart.getXAxis().setDrawAxisLine(false);

        //remove axis labels
        mpLineChart.getAxisRight().setDrawLabels(false);
        mpLineChart.getAxisLeft().setDrawLabels(false);

        //set X axis label to the bottom inside
        mpLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        mpLineChart.getXAxis().setTextColor(Color.parseColor("#A07E63"));
        mpLineChart.getXAxis().setTextSize(15);
        mpLineChart.getLegend().setTextColor(Color.parseColor("#A07E63"));
    }

    private void configureDataset(LineDataSet data, String lineColour, String textColour) {

        //set color of the data line
        data.setColor(Color.parseColor(lineColour));
        //enable color filled
        data.setDrawFilled(true);
        //set color filled
        data.setFillColor(Color.parseColor(lineColour));
        //set color of circle
        data.setCircleColor(Color.parseColor(lineColour));
        //set color of value
        data.setValueTextColor(Color.parseColor(textColour));
        data.setValueTextSize(12);
        data.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, 20));
        dataVals.add(new Entry(10, 21));
        dataVals.add(new Entry(20, 22));
        dataVals.add(new Entry(30, 23));
        dataVals.add(new Entry(40, 23));
        dataVals.add(new Entry(50, 24));
        dataVals.add(new Entry(60, 25));

        return dataVals;
    }

    private ArrayList<Entry> dataValues2(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, 12));
        dataVals.add(new Entry(5, 14));
        dataVals.add(new Entry(15, 16));
        dataVals.add(new Entry(25, 18));
        dataVals.add(new Entry(35, 20));
        dataVals.add(new Entry(45, 22));
        dataVals.add(new Entry(55, 24));

        return dataVals;
    }
}