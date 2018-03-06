package msms.comp3350.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import msms.comp3350.main.R;

public class ReportList extends Activity {
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        /* TODO: 1. Uncomment lines 19-51
        // Setup the ListView:
        // TODO: 2. Tell the array adapter the source array, and array type
        ArrayAdapter<SOME_TYPE> adapter = new ArrayAdapter<SOME_TYPE>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, SOME_ARRAY);
        final ListView listView = (ListView) findViewById(R.id.listMovies);
        listView.setAdapter(adapter);

        // Called when an item is selected from the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                if (position == selectedPosition)// Same item selected: deselect this item
                {
                    listView.setItemChecked(position, false);
                    selectedPosition = -1;
                }
                else// New item selected
                {
                    listView.setItemChecked(position, true);
                    selectedPosition = position;

                    // Launch a new intent:
                    // TODO: 3. Start a new intent, depending on the value of: position
                    Intent SOME_REPORT_INTENT = new Intent(getApplicationContext(), SOME_REPORT_CLASS.class);
//                    SOME_REPORT_INTENT.putExtra(STRING_NAME, SOME_VALUE); Optional: can add an extra to the intent
                    ReportList.this.startActivity(SOME_REPORT_CLASS);
                }
            }
        });
        */
    }
}
