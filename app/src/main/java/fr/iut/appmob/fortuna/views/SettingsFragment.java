package fr.iut.appmob.fortuna.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.iut.appmob.fortuna.MainActivity;
import fr.iut.appmob.fortuna.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static TextView name;
    private static ImageView icon;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        init(view);
        setContent();

        return view;

    }

    // init our needed variables
    private void init(View view) {
        name = (TextView) view.findViewById(R.id.userText);
        icon = (ImageView) view.findViewById(R.id.userIcon);

    }

    // set fragment content
    private void setContent() {
        SharedPreferences CONFIG = getActivity().getSharedPreferences("CONFIG", Context.MODE_PRIVATE);

        name.setText(concatName(CONFIG.getString("first_name", "NONE"),
                                CONFIG.getString("last_name", "NONE")));
        icon.setImageResource(CONFIG.getInt("icon", R.drawable.ic_man));

    }

    // concat str1 with str2 and put the first letter of each in UpperCase
    private String concatName(String str1, String str2) {
        return str1.substring(0, 1).toUpperCase() + str1.substring(1) +
                " " + str2.substring(0, 1).toUpperCase() + str2.substring(1);

    }

}