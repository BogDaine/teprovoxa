package com.example.teprovoxa.data;

import android.os.AsyncTask;


public class InsertChallengeTask extends AsyncTask<Challenge, Void, Void> {
    private DbOnSuccessListener listener;
    private AppDatabase appDatabase;
    public InsertChallengeTask(AppDatabase db, DbOnSuccessListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected Void doInBackground(Challenge... challenges) {
        appDatabase.challengeDao().insertAll(challenges[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.onSuccess();
    }
}
