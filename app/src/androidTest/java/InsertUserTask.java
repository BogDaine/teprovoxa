import android.os.AsyncTask;

import com.example.teprovoxa.ApplicationController;
import com.example.teprovoxa.DataRepository;
import com.example.teprovoxa.UserEntity;

import java.util.List;


public class InsertUserTask extends AsyncTask<UserEntity, Void, Void> {
    private DataRepository.OnSuccesListener listener;

    public InsertUserTask(DataRepository.OnSuccesListener l){
        listener = l;
    }

    @Override
    protected Void doInBackground(UserEntity... userEntities) {
        ApplicationController.getAppDatabase().userDao().insertAll(userEntities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.onSuccess();
    }
}
