package com.example.teprovoxa.data;

import android.os.AsyncTask;

import java.util.List;


public class FindPostCommentsTask extends AsyncTask<Integer, Void, List<Comment>> {
    private DbOnCommentQueryListener listener;
    private AppDatabase appDatabase;
    public FindPostCommentsTask(AppDatabase db, DbOnCommentQueryListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected List<Comment> doInBackground(Integer... ints) {
        return appDatabase.commentDao().findByChallenge(ints[0]);
    }

    @Override
    protected void onPostExecute(List<Comment> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}
