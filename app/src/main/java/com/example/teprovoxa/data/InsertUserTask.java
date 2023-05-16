package com.example.teprovoxa.data;

import android.os.AsyncTask;


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
