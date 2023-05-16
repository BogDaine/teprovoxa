package com.example.teprovoxa.data;

import android.os.AsyncTask;


public class InsertCommentTask extends AsyncTask<Comment, Void, Void> {
    private DbOnSuccessListener listener;
    private AppDatabase appDatabase;
    public InsertCommentTask(AppDatabase db, DbOnSuccessListener l)
    {
        appDatabase = db;
        listener = l;
    }

    @Override
    protected Void doInBackground(Comment... comments) {
        appDatabase.commentDao().insertAll(comments[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.onSuccess();
    }
}
