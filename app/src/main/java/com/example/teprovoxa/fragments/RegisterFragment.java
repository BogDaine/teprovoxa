package com.example.teprovoxa.fragments;

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

import com.example.teprovoxa.ApplicationController;
import com.example.teprovoxa.activities.ChallengesActivity;
import com.example.teprovoxa.R;
import com.example.teprovoxa.data.DataRepository;
import com.example.teprovoxa.data.DbOnSuccessListener;
import com.example.teprovoxa.data.DbOnUserQueryListener;
import com.example.teprovoxa.data.UserEntity;
import com.example.teprovoxa.utils.CredentialsValidator;

import java.util.List;
public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button registerBtn = (Button) view.findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerClicked();
            }
        });

        return view;
    }

    private void registerClicked(){
        String username = ((EditText)getView().findViewById(R.id.reg_username)).getText().toString(),
                password = ((EditText)getView().findViewById(R.id.reg_password)).getText().toString();

        boolean ok_usr = CredentialsValidator.usernameValid(username),
                ok_pwd = CredentialsValidator.passwordValid(password);

        if(ok_usr && ok_pwd)
        {

            DataRepository dataRepository = new DataRepository();

            final boolean[] exists = new boolean[1];
            dataRepository.findUser(username, new DbOnUserQueryListener() {
                @Override
                public void onSuccess(List<UserEntity> items) {
                    if(!items.isEmpty()){
                        onRegisterFail((byte)2);
                        return;
                    }
                    dataRepository.insertUser(new UserEntity(username, password), new DbOnSuccessListener() {
                        @Override
                        public void onSuccess() {
                            onRegisterSuccess(username);
                        }
                    });
                }
            });
            return;
        }
        onRegisterFail((byte)1);
    }

    private void onRegisterSuccess(String usr){
        Context context = ApplicationController.getInstance().getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, R.string.register_success, duration);
        toast.show();
        ApplicationController.getInstance().setLoggedUser(usr);
        startActivity(new Intent(getActivity(), ChallengesActivity.class));
        getActivity().finish();
    }
    private void onRegisterFail(byte code){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setPositiveButton(
                R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        switch(code){
            case 1:
                builder.setMessage(R.string.bad_credentials_format);
                break;
            case 2:
                builder.setMessage(R.string.username_taken);
                break;
            default:
                builder.setMessage(R.string.generic_error);
                break;
        }
        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}