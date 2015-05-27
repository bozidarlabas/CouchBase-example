package example.couchbase.bozidar.couchbase_example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    final String TAG = "CouchBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CouchBase manager
        Manager couchBaseManager = null;
        try {
            couchBaseManager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createDatabase(couchBaseManager);


    }

    private void createDatabase(Manager manager) {
        // create a name for the database and make sure the name is legal
        String dbName = "test";
        if(!Manager.isValidDatabaseName(dbName)){
            Log.d(TAG, "Incorect database name");
            return;
        }

        //create a new database
        Database database;
        try{
            database = manager.getDatabase(dbName);
            Log.d(TAG, "Database is created");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            Log.d(TAG, "Cannot get database");
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
