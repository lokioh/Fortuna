package fr.iut.appmob.fortuna.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.text.DecimalFormat;

import fr.iut.appmob.fortuna.data.DataManagement;
import fr.iut.appmob.fortuna.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static TextView[] txtViews;
    private static ProgressBar[] progressBars;
    private static int[] progessBarValues;
    private static final String[] categories = {
            "home",
            "phone",
            "car",
            "health",
            "food",
            "other"
    };

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        Activity activity = getActivity();

        init(view, activity);
        setContent();

        return view;

    }

    // init our needed arrays
    private void init(View view, Activity activity) {
        txtViews = new TextView[] {
                (TextView) view.findViewById(R.id.textView_homeValue),
                (TextView) view.findViewById(R.id.textView_phoneValue),
                (TextView) view.findViewById(R.id.textView_carValue),
                (TextView) view.findViewById(R.id.textView_healthValue),
                (TextView) view.findViewById(R.id.textView_foodValue),
                (TextView) view.findViewById(R.id.textView_otherValue)
        };

        progressBars = new ProgressBar[] {
                (ProgressBar) view.findViewById(R.id.progressBar_home),
                (ProgressBar) view.findViewById(R.id.progressBar_phone),
                (ProgressBar) view.findViewById(R.id.progressBar_car),
                (ProgressBar) view.findViewById(R.id.progressBar_health),
                (ProgressBar) view.findViewById(R.id.progressBar_food),
                (ProgressBar) view.findViewById(R.id.progressBar_other)
        };

        progessBarValues = new int[] {
                DataManagement.getProgress(activity, categories[0]),
                DataManagement.getProgress(activity, categories[1]),
                DataManagement.getProgress(activity, categories[2]),
                DataManagement.getProgress(activity, categories[3]),
                DataManagement.getProgress(activity, categories[4]),
                DataManagement.getProgress(activity, categories[5])
        };

    }

    // set fragment content
    private void setContent() {
        SharedPreferences MONEY = getActivity().getSharedPreferences("MONEY", Context.MODE_PRIVATE);

        DecimalFormat df = new DecimalFormat("#.###");
        for (int i = 0; i < txtViews.length; ++i)
            txtViews[i].setText("- $" + df.format(MONEY.getFloat("expense" + categories[i], 0)));

        for (int i = 0; i < progressBars.length; ++i)
            progressBars[i].setProgress(progessBarValues[i]);

    }

}