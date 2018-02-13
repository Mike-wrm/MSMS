package msms.comp3350.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import msms.comp3350.main.R;
import msms.comp3350.objects.User;

public class UserDisplayActivity extends Activity {
    private User inputUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);

        // Get the passed User
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            inputUser = (User) extras.getSerializable("SelectedUser");
        }
        else{
            //Error getting extra value
            finish();
        }

        // Set the "back" button to go back to the list of users
        Button backButton = (Button) findViewById(R.id.buttonBackToUserList);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        displayData();
    }

    private void displayData(){
        String outputText;

        if (inputUser != null) {
            outputText = "User ID: " + inputUser.getuID() + "\n";
            outputText = outputText + "Name: " + inputUser.getName() + "\n";
            outputText = outputText + "Password: " + inputUser.getPass() + "\n";
            outputText = outputText + "Age: " + inputUser.getAge() + "\n";
            outputText = outputText + "Gender: " + inputUser.getGender() + "\n";
            outputText = outputText + "Subscription End Date: " + inputUser.getEndDate().getTime();

            TextView output = (TextView) findViewById(R.id.user_info_text);
            output.setText((CharSequence) outputText);
        }
        else {
            Messages.fatalError(this, "User was not found.");
        }
    }

    public void buttonUserDeleteOnClick(View v) {
        if (inputUser != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserDisplayActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Are you sure you want to delete this User ?\n" + inputUser.getName());
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    //Starting the previous Intent
                    Intent previousScreen = new Intent(getApplicationContext(), UserListActivity.class);
                    previousScreen.putExtra("DeleteUserKey", inputUser);
                    setResult(1002, previousScreen);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            Messages.fatalError(this, "User was not found.");
        }
    }

    public void buttonUserUpdateOnClick(View v){
    }
}
