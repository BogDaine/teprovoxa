package com.example.teprovoxa;

import android.content.Context;
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
            UserEntity newUser = new UserEntity(username, password);

            DataRepository dataRepository = new DataRepository();
            dataRepository.insertUser(newUser, new DbOnSuccessListener() {
                @Override
                public void onSuccess() {
                    onRegisterSuccess(newUser.username);
                }
            });
            //boolean exists = (!userDao.findByName(username).isEmpty());
            //if(exists)
            //    {
            //        return;
            //    }
            //userDao.insertAll(newUser);
            return;
        }
        if(!ok_usr){}
        if(!ok_pwd){}
    }
    private void onRegisterSuccess(String usr){
        Context context = ApplicationController.getInstance().getApplicationContext();

        ApplicationController.getInstance().setLoggedUser(usr);

        CharSequence text = "User registered";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}