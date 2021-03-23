package fr.iut.appmob.fortuna.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

import fr.iut.appmob.fortuna.DataManagement;
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

    TextView textView_homeView, textView_phoneView, textView_carView,
            textView_healthView, textView_foodView, textView_otherView;

    ProgressBar progressBar_home, progressBar_phone, progressBar_car,
            progressBar_health, progressBar_food, progressBar_other;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        textView_homeView = (TextView) view.findViewById(R.id.textView_homeValue);
        textView_phoneView = (TextView) view.findViewById(R.id.textView_phoneValue);
        textView_carView = (TextView) view.findViewById(R.id.textView_carValue);
        textView_healthView = (TextView) view.findViewById(R.id.textView_healthValue);
        textView_foodView = (TextView) view.findViewById(R.id.textView_foodValue);
        textView_otherView = (TextView) view.findViewById(R.id.textView_otherValue);

        int homeProgress = (int) DataManagement.getProgressHome(getActivity());
        int phoneProgress = (int) DataManagement.getProgressPhone(getActivity());
        int carProgress = (int) DataManagement.getProgressCar(getActivity());
        int healthProgress = (int) DataManagement.getProgressHealth(getActivity());
        int foodProgress = (int) DataManagement.getProgressFood(getActivity());
        int otherProgress = (int) DataManagement.getProgressOther(getActivity());

        progressBar_home = (ProgressBar) view.findViewById(R.id.progressBar_home);
        progressBar_phone = (ProgressBar) view.findViewById(R.id.progressBar_phone);
        progressBar_car = (ProgressBar) view.findViewById(R.id.progressBar_car);
        progressBar_health = (ProgressBar) view.findViewById(R.id.progressBar_health);
        progressBar_food = (ProgressBar) view.findViewById(R.id.progressBar_food);
        progressBar_other = (ProgressBar) view.findViewById(R.id.progressBar_other);
        progressBar_home.setProgress(homeProgress);
        progressBar_phone.setProgress(phoneProgress);
        progressBar_car.setProgress(carProgress);
        progressBar_health.setProgress(healthProgress);
        progressBar_food.setProgress(foodProgress);
        progressBar_other.setProgress(otherProgress);

        setContent();

        return view;
    }

    public void setContent() {

        SharedPreferences MONEY = this.getActivity().getSharedPreferences("MONEY", Context.MODE_PRIVATE);

        Float homeValue = MONEY.getFloat("expenseHome", 0);
        Float phoneValue = MONEY.getFloat("expensePhone",0);
        Float carValue = MONEY.getFloat("expenseCar", 0);
        Float healthValue = MONEY.getFloat("expenseHealth", 0);
        Float foodValue = MONEY.getFloat("expenseFood", 0);
        Float otherValue = MONEY.getFloat("expenseOther", 0);
        DecimalFormat df = new DecimalFormat("#.###");
        textView_homeView.setText("- $" + df.format(homeValue));
        textView_phoneView.setText("- $" + df.format(phoneValue));
        textView_carView.setText("- $" + df.format(carValue));
        textView_healthView.setText("- $" + df.format(healthValue));
        textView_foodView.setText("- $" + df.format(foodValue));
        textView_otherView.setText("- $" + df.format(otherValue));
    }

}