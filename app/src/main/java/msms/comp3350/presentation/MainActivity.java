package msms.comp3350.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import msms.comp3350.application.Main;
import msms.comp3350.application.Services;
import msms.comp3350.business.ChartData;
import msms.comp3350.main.R;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        Main.startUp();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Services.closeDataAccess();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        }
        catch (IOException ioe)
        {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public void buttonMoviesOnClick(View v)
    {
        Intent movieListActivity = new Intent(MainActivity.this, MovieListActivity.class);
        MainActivity.this.startActivity(movieListActivity);
    }

    public void buttonUsersOnClick(View v)
    {
        Intent userListActivity = new Intent(MainActivity.this, UserListActivity.class);
        MainActivity.this.startActivity(userListActivity);
    }

    public void buttonReportsOnClick(View v)
    {
        Intent reportList = new Intent(MainActivity.this, ReportListActivity.class);
        Bundle args = new Bundle();
        String[][] listInfo = ChartData.getGlobalLists();
        args.putStringArray("listinfo", listInfo[0]);
        args.putStringArray("types", listInfo[1]);
        args.putStringArray("subjects", listInfo[2]);
        reportList.putExtras(args);
        MainActivity.this.startActivity(reportList);
    }
}