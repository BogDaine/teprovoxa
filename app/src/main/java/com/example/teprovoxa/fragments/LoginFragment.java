package com.example.teprovoxa.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teprovoxa.ApplicationController;
import com.example.teprovoxa.activities.ChallengesActivity;
import com.example.teprovoxa.R;
import com.example.teprovoxa.data.DataRepository;
import com.example.teprovoxa.data.DbOnUserQueryListener;
import com.example.teprovoxa.data.UserEntity;

import java.util.List;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = (Button)view.findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });

        CheckBox rememberMeChk = (CheckBox) view.findViewById(R.id.login_remember_chk);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        boolean rememberMe = sharedPreferences.getBoolean(R.string.app_name+".remember_me", false);
        rememberMeChk.setChecked(rememberMe);

        if(rememberMe)
            ((EditText)view.findViewById(R.id.login_username)).setText(sharedPreferences.getString(R.string.app_name+".username", ""));
        return  view;
    }

    private void loginClicked(){
        String username = ((EditText)getView().findViewById(R.id.login_username)).getText().toString(),
                password = ((EditText)getView().findViewById(R.id.login_password)).getText().toString();
        DataRepository dataRepository = new DataRepository();

        dataRepository.findUser(username, new DbOnUserQueryListener() {
            @Override
            public void onSuccess(List<UserEntity> items) {
                if(items.isEmpty()){
                    onLoginFailed((byte)1);
                    return;
                }
                if(items.get(0).password.equals(password)){
                    onLoginSuccess(username);
                    return;
                }
                onLoginFailed((byte)1);
            }
        });

    }
    private void onLoginSuccess(String usr){

        ApplicationController.getInstance().setLoggedUser(usr);

        Context context = ApplicationController.getInstance().getApplicationContext();

        ApplicationController.getInstance().setLoggedUser(usr);

        Toast toast = Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT);
        toast.show();

        startActivity(new Intent(getActivity(), ChallengesActivity.class));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        CheckBox rememberMe = (CheckBox) getView().findViewById(R.id.login_remember_chk);

        sharedPreferences.edit().putBoolean(R.string.app_name+".remember_me", rememberMe.isChecked()).apply();

        if(rememberMe.isChecked())
            sharedPreferences.edit().putString(R.string.app_name+".username", usr).apply();
        else
            sharedPreferences.edit().remove(R.string.app_name+".username").apply();
        getActivity().finish();
    }

    private void onLoginFailed(byte code){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        switch(code){
            case 1:
                builder.setMessage(R.string.bad_credentials);
                break;
            default:
                builder.setMessage(R.string.generic_error);
                break;
        }
        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}