package com.example.teprovoxa.data;

import com.example.teprovoxa.ApplicationController;

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

    public void insertChallenge(Challenge challenge, DbOnSuccessListener listener){
        new InsertChallengeTask(appDatabase, listener).execute(challenge);
    }
    public void findAllChallenges(DbOnChallengeQueryListener listener){
        new FindAllChallengesTask(appDatabase, listener).execute();
    }

    public void findChallengeComments(int challengeId, DbOnCommentQueryListener listener){
        new FindPostCommentsTask(appDatabase, listener).execute(challengeId);
    }
    public void insertComment(Comment comment, DbOnSuccessListener listener){
        new InsertCommentTask(appDatabase, listener).execute(comment);
    }
}
