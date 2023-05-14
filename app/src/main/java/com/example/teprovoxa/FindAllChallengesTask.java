package com.example.teprovoxa;

import android.os.AsyncTask;

import com.example.teprovoxa.AppDatabase;
import com.example.teprovoxa.DbOnSuccessListener;
import com.example.teprovoxa.UserEntity;

import java.util.ArrayList;
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
