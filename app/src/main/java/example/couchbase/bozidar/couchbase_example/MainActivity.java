package example.couchbase.bozidar.couchbase_example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private final String TAG = "CouchBase";
    private Database database;
    private Manager couchBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        createManager();

        createDatabase();

        createDocument();


    }

    private void createManager(){
        //CouchBase manager
        try {
            couchBaseManager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDocument() {


        // create an object that contains data for a document
        Map<String, Object> docContent = new HashMap<>();
        docContent.put("message", "Hello Couchbase Lite");
        docContent.put("message2", "Hello CouchBase Lite2");

        //Create an emtpy document
        Document document = database.createDocument();

        try {
            document.putProperties(docContent);
            Log.d (TAG, "Document written to database " + " with ID = " + document.getId());
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        String docID = document.getId();

    }

    private void createDatabase() {
        // create a name for the database and make sure the name is legal
        String dbName = "test";
        if(!Manager.isValidDatabaseName(dbName)){
            Log.d(TAG, "Incorect database name");
            return;
        }

        //create a new database
        try{
            database = couchBaseManager.getDatabase(dbName);
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
