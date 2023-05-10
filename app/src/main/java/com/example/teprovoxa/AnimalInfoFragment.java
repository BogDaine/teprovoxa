package com.example.teprovoxa;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnimalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimalInfoFragment newInstance(String param1, String param2) {
        AnimalInfoFragment fragment = new AnimalInfoFragment();
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
        return inflater.inflate(R.layout.fragment_animal_info, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        TextView nameTextView = (TextView) view.findViewById(R.id.name_info);
        nameTextView.setText(SelectedAnimal.INSTANCE.NAME);
        TextView ContinentTextView = (TextView) view.findViewById(R.id.continent_info);
        ContinentTextView.setText(SelectedAnimal.INSTANCE.CONTINENT);

        switch(SelectedAnimal.INSTANCE.CONTINENT){
            case "Europa":
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.good_green));
                break;
            case "Asia":
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.good_red));
                break;
            case "America":
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.good_blue));
                break;
            case "Africa":
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.good_yellow));
                break;
            case "Australia":
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.good_orange));
                break;
        }

    }
}