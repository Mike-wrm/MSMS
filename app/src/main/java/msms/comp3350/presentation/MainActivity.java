package msms.comp3350.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import msms.comp3350.application.Services;
import msms.comp3350.main.R;

public class MainActivity extends AppCompatActivity {

    public static final String dbName = "temp";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Services.createDataAccess(dbName);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Services.closeDataAccess();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void buttonMoviesOnClick(View v) {
        Intent movieListActivity = new Intent(MainActivity.this, MovieListActivity.class);
        MainActivity.this.startActivity(movieListActivity);
    }

    public void buttonUsersOnClick(View v) {
        Intent userListActivity = new Intent(MainActivity.this, UserListActivity.class);
        MainActivity.this.startActivity(userListActivity);
    }
}
