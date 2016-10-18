package pomis.app.realmandprefssample;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

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
    }

    private void initPrefs() {
        viewName = (TextView) findViewById(R.id.tv_name);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name", "Я тебя не знаю.");
        viewName.setText(name);
    }

    void initRealm(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
    }
}
