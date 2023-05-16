package com.example.teprovoxa.data;

import android.os.AsyncTask;

import java.util.List;


public class FindAllChallengesTask extends AsyncTask<Void, Void, List<Challenge>> {
    private DbOnChallengeQueryListener listener;
    private AppDatabase appDatabase;
    public FindAllChallengesTask(AppDatabase db, DbOnChallengeQueryListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected List<Challenge> doInBackground(Void... voids) {
        return appDatabase.challengeDao().findAll();
    }

    @Override
    protected void onPostExecute(List<Challenge> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}
