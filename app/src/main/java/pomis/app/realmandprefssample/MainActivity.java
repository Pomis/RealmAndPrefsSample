package pomis.app.realmandprefssample;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import pomis.app.realmandprefssample.asynctasks.WaitTask;
import pomis.app.realmandprefssample.models.NewsPost;

public class MainActivity extends AppCompatActivity {

    private static final String MY_TAG = "kek";
    EditText editName;
    TextView viewName;
    Button buttonSetName;
    SharedPreferences prefs;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRealm();
        initPrefs();
        initButton();
    }

    private void initButton() {
        buttonSetName = (Button) findViewById(R.id.b_name);
        editName = (EditText) findViewById(R.id.et_name);
        buttonSetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit()
                        .putString("name", editName.getText().toString())
                        .apply();
            }
        });

        Button addRealmButton = (Button) findViewById(R.id.b_add_realm);
        addRealmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        NewsPost newsPost = realm.createObject(NewsPost.class);
                        newsPost.title = editName.getText().toString();
                        newsPost.text = "34234324234234gdgdfg";
                        newsPost.author = "fhkjsdhfksjdhfkjdsfhjksd";
                        newsPost.number = (int) (Math.random() * 1000);
                    }
                });
                showDB();
            }
        });

        Button waitButton = (Button) findViewById(R.id.b_wait);
        waitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
            }
        });
    }

    private void startAsyncTask() {
        new WaitTask(this).execute();
    }

    private void showDB() {
        Log.d(MY_TAG, "Realms count: " + realm.where(NewsPost.class).count());

        RealmResults<NewsPost> posts = realm.where(NewsPost.class).findAll();
        for (NewsPost post : posts) {
            Log.d(MY_TAG,
                    "========================================" +
                            "\nauthor: " + post.author +
                            "\ntext: " + post.text +
                            "\ntitle: " + post.title +
                            "\nnumber: " + post.number

            );
        }
    }

    private void initPrefs() {
        viewName = (TextView) findViewById(R.id.tv_name);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name", "Я тебя не знаю.");
        viewName.setText(name);
    }

    void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
    }
}
