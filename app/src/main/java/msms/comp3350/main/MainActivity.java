package msms.comp3350.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import msms.comp3350.main.presentation.AddActivity;
import msms.comp3350.main.presentation.MovieListActivity;
import msms.comp3350.main.presentation.UserListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        Intent studentsIntent = new Intent(MainActivity.this, MovieListActivity.class);
        MainActivity.this.startActivity(studentsIntent);
    }

    public void buttonUsersOnClick(View v) {
        Intent coursesIntent = new Intent(MainActivity.this, UserListActivity.class);
        MainActivity.this.startActivity(coursesIntent);
    }
    public void buttonAddOnClick(View v) {
        Intent coursesIntent = new Intent(MainActivity.this, AddActivity.class);
        MainActivity.this.startActivity(coursesIntent);
    }

}
