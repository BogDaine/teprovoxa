package com.example.teprovoxa.data;

import android.os.AsyncTask;

import java.util.List;


public class FindUserTask extends AsyncTask<String, List<UserEntity>, List<UserEntity>> {
    private DbOnUserQueryListener listener;
    private AppDatabase appDatabase;
    public FindUserTask(AppDatabase db, DbOnUserQueryListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected List<UserEntity> doInBackground(String... strings) {
        return appDatabase.userDao().findByName(strings[0]);
    }

    @Override
    protected void onPostExecute(List<UserEntity> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}
