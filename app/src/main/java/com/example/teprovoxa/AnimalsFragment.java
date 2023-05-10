package com.example.teprovoxa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalsFragment extends Fragment {

    ArrayList<Animal> animals;
    OnAnimalClickedListener mListener;


    public AnimalsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animals, container, false);

        mListener = new OnAnimalClickedListener() {
            @Override
            public void onAnimalClicked(int position) {
                SelectedAnimal.INSTANCE.NAME = animals.get(position).getName();
                SelectedAnimal.INSTANCE.CONTINENT = animals.get(position).getmContinent();
                Navigation.findNavController(getView()).navigate(R.id.action_animalsFragment_to_animalInfoFragment);
            }
        };


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView rvAnimals =  (RecyclerView) getView().findViewById(R.id.rvAnimals);
        animals = Animal.createAnimalsList();
        AnimalsAdapter adapter = new AnimalsAdapter(animals, mListener);
        rvAnimals.setAdapter(adapter);
        rvAnimals.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        //Navigation.findNavController(getView()).navigate(R.id.action_animalsFragment_to_animalInfoFragment);
    }
}
