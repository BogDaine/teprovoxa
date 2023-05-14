package com.example.teprovoxa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        ApplicationController.getInstance().setLoggedUser(usr);

        CharSequence text = "User registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void onRegisterFail(byte code){
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
                builder.setMessage("Username and password must be longer than 3 characters and not contain spaces.");
                break;
            case 2:
                builder.setMessage("Username is taken.");
                break;
            default:
                builder.setMessage("Something didn't work, but we don't know what. This shouldn't have happened.");
                break;
        }
        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}