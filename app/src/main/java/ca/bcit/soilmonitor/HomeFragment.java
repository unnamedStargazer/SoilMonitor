package ca.bcit.soilmonitor;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    HashMap<String, String> mapColor = new HashMap<>();
    private static LineChart mpLineChart;
    private static LineData data;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    private String tag = "HomeFragment";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.i(tag, "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("HomeFragment", "onCreateView");

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("HomeFragment", "onViewCreated");

        if (mpLineChart == null) {
            mpLineChart = requireActivity().findViewById(R.id.graph);

            mapColor.put("greenLeaf", "#7FB241");
            mapColor.put("brown", "#A07E63");
            mapColor.put("blue", "#1ecbe1");

            LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Temp");
            LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Humidity");
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet1);
            dataSets.add(lineDataSet2);
            data = new LineData(dataSets);
            mpLineChart.setData(data);
            mpLineChart.invalidate();

            configureMPChart();
            configureDataset(lineDataSet1, mapColor.get("greenLeaf"), mapColor.get("brown"));
            configureDataset(lineDataSet2, mapColor.get("blue"), mapColor.get("brown"));
            Log.i(tag, "1: " + String.valueOf(mpLineChart.getLineData()));
            mpLineChart.setNoDataText("Test");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            Log.i(tag, "onHiddenChanged");

            mpLineChart.setData(data);
            mpLineChart.invalidate();
//            mpLineChart.invalidate();
            Log.i(tag, "2: " + String.valueOf(mpLineChart.getLineData()));
//            mpLineChart.dr();
        } else {
            mpLineChart.postInvalidate();
        }
    }

    private void configureMPChart() {
        //no description text
        mpLineChart.getDescription().setEnabled(false);
        mpLineChart.setHardwareAccelerationEnabled(true);

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