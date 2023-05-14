package com.example.teprovoxa;

import android.os.AsyncTask;

import com.example.teprovoxa.AppDatabase;
import com.example.teprovoxa.DbOnSuccessListener;
import com.example.teprovoxa.UserEntity;

import java.util.List;


public class InsertUserTask extends AsyncTask<UserEntity, Void, Void> {
    private DbOnSuccessListener listener;
    private AppDatabase appDatabase;
    public InsertUserTask(AppDatabase db, DbOnSuccessListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected Void doInBackground(UserEntity... userEntities) {
       appDatabase.userDao().insertAll(userEntities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.onSuccess();
    }
}
