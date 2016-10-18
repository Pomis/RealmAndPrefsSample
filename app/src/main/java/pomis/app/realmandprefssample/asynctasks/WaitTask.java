package pomis.app.realmandprefssample.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import pomis.app.realmandprefssample.R;

/**
 * Created by romanismagilov on 18.10.16.
 */

public class WaitTask extends AsyncTask {
    Activity activity;
    Button button;
    public WaitTask(Activity activity) {
        this.activity = activity;
        button = (Button) activity.findViewById(R.id.b_wait);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            for (int i = 0; i < 10; ++i) {
                Thread.sleep(230);
                publishProgress(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Toast.makeText(activity, "Успешно подождал 2300мс", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        button.setText("Прошло: "+(int)values[0]*230+" мс.");
    }
}
