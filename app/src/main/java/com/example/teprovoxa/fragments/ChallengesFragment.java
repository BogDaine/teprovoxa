package com.example.teprovoxa.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teprovoxa.ApplicationController;
import com.example.teprovoxa.activities.AuthActivity;
import com.example.teprovoxa.adapters.ChallengesAdapter;
import com.example.teprovoxa.R;
import com.example.teprovoxa.data.Challenge;
import com.example.teprovoxa.data.DataRepository;
import com.example.teprovoxa.data.DbOnChallengeQueryListener;
import com.example.teprovoxa.data.DbOnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class ChallengesFragment extends Fragment {

    private DataRepository dataRepository = new DataRepository();
    private ArrayList<Challenge> challenges;
    private RecyclerView challengesRv;
    private ChallengesAdapter challengesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        Button addButton = (Button) view.findViewById(R.id.add_challenge_btn);
        Button logoutButton = (Button) view.findViewById(R.id.app_logout_btn);
        challengesRv = (RecyclerView)view.findViewById(R.id.challenges_rv);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChallengeClicked();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutClicked();
            }
        });
        setupRecyclerView();
        return view;
    }
    private void setupRecyclerView(){

        Context context = this.getContext();
        dataRepository.findAllChallenges(new DbOnChallengeQueryListener() {
            @Override
            public void onSuccess(List<Challenge> items) {
                challenges = new ArrayList<Challenge>(items);
                challengesAdapter = new ChallengesAdapter(challenges, new ChallengesAdapter.OnChallengeClickedListener() {
                    @Override
                    public void onChallengeClicked(int position) {
                        onChallengeItemClicked(position);
                    }
                });

                challengesRv.setAdapter(challengesAdapter);
                challengesRv.setLayoutManager(new LinearLayoutManager(context));
            }
        });
    }
    private void onChallengeItemClicked(int position){
        ApplicationController.getInstance().setLastAccessedChallenge(challenges.get(position));
        Navigation.findNavController(getView()).navigate(R.id.action_challengesFragment_to_challengeCommentsFragment);
    }

    private void addChallengeClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_challenge_dialog, null);


        builder.setMessage(R.string.add_challenge)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String title = ((EditText)view.findViewById(R.id.newchlg_title)).getText().toString();
                        String text = ((EditText)view.findViewById(R.id.newchlg_text)).getText().toString();

                        addChallenge(title, text);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.setView(view);

        builder.create().show();
    }
    private void getAllChallenges(){
        dataRepository.findAllChallenges(new DbOnChallengeQueryListener() {
            @Override
            public void onSuccess(List<Challenge> items) {
                setChallenges(items);
            }
        });
    }
    private void setChallenges(List<Challenge> items){
        challenges = new ArrayList<Challenge>(items);
        challengesAdapter.setData(challenges);
        challengesAdapter.notifyDataSetChanged();
    }

    private void addChallenge(String title, String text){
        if(title.length() < 3){
            return;
        }
        String username = ApplicationController.getInstance().getLoggedUser();

        Challenge challenge = new Challenge(title, text, username);
        dataRepository.insertChallenge(challenge, new DbOnSuccessListener(){
            @Override
            public void onSuccess() {
                onAddChallenge();
            }
        });
    }
    private void onAddChallenge(){
        Context context = ApplicationController.getInstance().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, R.string.challenge_add_success, duration);
        toast.show();

        getAllChallenges();
    }
    private void logoutClicked(){
        //are you sure?
        startActivity(new Intent(getActivity(), AuthActivity.class));
        ApplicationController.getInstance().setLoggedUser(null);
        getActivity().finish();
    }
}