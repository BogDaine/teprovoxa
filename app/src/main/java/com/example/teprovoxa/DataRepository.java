package com.example.teprovoxa;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    public static interface OnSuccesListener {
        void onSuccess();
    }
    public static interface OnUserQueryListener {
        void onSuccess(List<UserEntity> items);
    }
    private final AppDatabase appDatabase;
    public DataRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertUser(UserEntity newUser, DbOnSuccessListener listener){
        new InsertUserTask(appDatabase, listener).execute(newUser);
    }
    public void findUser(String usr, DbOnUserQueryListener listener){
        new FindUserTask(appDatabase, listener).execute(usr);
    }
}
