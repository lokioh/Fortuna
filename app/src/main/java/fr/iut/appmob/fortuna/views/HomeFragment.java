package fr.iut.appmob.fortuna.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import fr.iut.appmob.fortuna.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txtViewBalance;
    private TextView txtViewIncome;
    private TextView txtViewExpense;

    public HomeFragment() {
        // Required empty public constructor
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init(view);
        setContent();
        setChart(view);

        return view;

    }



    // init our needed variables
    private void init(View view) {
        txtViewBalance = (TextView) view.findViewById(R.id.balance);
        txtViewIncome = (TextView) view.findViewById(R.id.income);
        txtViewExpense = (TextView) view.findViewById(R.id.expense);

    }

    // set fragment content
    private void setContent() {
        SharedPreferences MONEY = this.getActivity().getSharedPreferences("MONEY", Context.MODE_PRIVATE);

        DecimalFormat df = new DecimalFormat("#.###");
        txtViewBalance.setText("$" + df.format(MONEY.getFloat("balance", 0)));
        txtViewIncome.setText("$" + df.format(MONEY.getFloat("deposit",0)));
        txtViewExpense.setText("$" + df.format( MONEY.getFloat("expense", 0)));

    }

    // set the chart
    private void setChart(View view) {
        // get chart data
        final SharedPreferences CHART = this.getActivity().getSharedPreferences("CHART", Context.MODE_PRIVATE);
        Map<String, ?> data = CHART.getAll();

        ArrayList<Float> dataAsFloat = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();

        for (Map.Entry<String, ?> element : data.entrySet()) {
            dataAsFloat.add((Float) element.getValue());
            keys.add(element.getKey());
        }

        DataEntry[] chartData = new DataEntry[dataAsFloat.size()];
        for (int i = dataAsFloat.size() - 1; i >= 0; --i) {
            chartData[i] = new ValueDataEntry(keys.get(i), dataAsFloat.get(i));
        }

        // create chart
        Cartesian chart = AnyChart.line();
        chart.data(Arrays.asList(chartData));
        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.chart);
        anyChartView.setChart(chart);

    }

}