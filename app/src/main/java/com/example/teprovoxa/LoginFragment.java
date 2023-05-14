package com.example.teprovoxa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = (Button)view.findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });

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

        CharSequence text = "Logged in";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(new Intent(getActivity(), ChallengesActivity.class));
        getActivity().finish();
    }

    private void onLoginFailed(byte code){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Write your message here.");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        switch(code){
            case 1:
                builder.setMessage("Username and/or password incorrect.");
                break;
            default:
                builder.setMessage("Something didn't work, but we don't know what. This shouldn't have happened.");
                break;
        }
        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}