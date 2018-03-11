package msms.comp3350.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.main.R;
import msms.comp3350.objects.User;

public class UserDisplayActivity extends Activity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener
{
    private User inputUser;

    private int expYear = 0;
    private int expMonth = 0;
    private int expDay = 0;
    private char selectedGender = 'm';
    private String name = "";
    private String userID = "";
    private String password = "";
    private String age = "";

    private Spinner genderSpinner = null;

    private TextView useridText = null;
    private EditText nameText = null;
    private EditText passwordText = null;
    private EditText ageText = null;
    private TextView expDateText = null;

    private Button pickDateButton = null;

    private DatePickerDialog datePickerDialog;
    public void onDateSet(DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
        expYear = year;
        expMonth = monthOfYear;
        expDay = dayOfMonth;
        displayData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);

        // Get the passed User
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            //The key argument here must match that used in the other activity
            inputUser = (User) extras.getSerializable("SelectedUser");
        }
        else
        {
            //Error getting extra value
            finish();
        }

        // Set the "back" button to go back to the list of users
        Button backButton = (Button) findViewById(R.id.cancel_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        // Setup gender spinner:
        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> genderAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, SpinnerArrays.getGenders());
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(this);

        // Setup textEdit boxes:
        useridText = (TextView) findViewById(R.id.userid_text);
        nameText = (EditText) findViewById(R.id.name_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        ageText = (EditText) findViewById(R.id.age_text);
        expDateText = (TextView) findViewById(R.id.subend_year_text);

        Calendar expDate = inputUser.getEndDate();
        expYear = expDate.get(Calendar.YEAR);
        expMonth = expDate.get(Calendar.MONTH);
        expDay = expDate.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(UserDisplayActivity.this, UserDisplayActivity.this, expYear, expMonth, expDay);
        // add a click listener to the select a date button
        pickDateButton = (Button) findViewById(R.id.pickDate);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        displayData();
    }

    // Handles a selection from the genre spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        System.out.println("Run onItemSelected.");
        switch(parent.getId())// Which spinner was changed?
        {
            case R.id.gender_spinner:
                String selectedGenderString = (String) genderSpinner.getItemAtPosition(pos);
                if (selectedGenderString.equals("Male"))
                {
                    selectedGender = 'm';
                }
                else if (selectedGenderString.equals("Female"))
                {
                    selectedGender = 'f';
                }
                else{
                    selectedGender = 'm'; // Default to male gender
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    private void displayData()
    {
        String outputText;

        if (inputUser != null)
        {
            useridText.setText(String.valueOf(inputUser.getuID()));
            nameText.setText(inputUser.getName());
            passwordText.setText(inputUser.getPass());
            ageText.setText(String.valueOf(inputUser.getAge()));

            char gender = inputUser.getGender();
            switch(gender)
            {
                case 'm':
                    genderSpinner.setSelection(0);
                    break;
                case 'f':
                    genderSpinner.setSelection(1);
                    break;
                default:
                    genderSpinner.setSelection(0);
                    break;
            }

            datePickerDialog = new DatePickerDialog(UserDisplayActivity.this, UserDisplayActivity.this, expYear, expMonth, expDay);
            // set expDate in EditText
            expDateText.setText(
                    new StringBuilder()
                            // Month is 0 based so add 1
                            .append(expMonth + 1).append("/")
                            .append(expDay).append("/")
                            .append(expYear).append(" "));
        }
        else
        {
            Messages.fatalError(this, "User was not found.");
        }
    }

    public void buttonUserDeleteOnClick(View v)
    {
        if (inputUser != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserDisplayActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Are you sure you want to delete this User ?\n" + inputUser.getName());
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                    //Starting the previous Intent
                    Intent previousScreen = new Intent(getApplicationContext(), UserListActivity.class);
                    previousScreen.putExtra("DeleteUserKey", inputUser);
                    setResult(1002, previousScreen);
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else
        {
            Messages.fatalError(this, "User was not found.");
        }
    }

    public void buttonUserUpdateOnClick(View v)
    {
        // Grab text input:
        name = nameText.getText().toString();
        userID = useridText.getText().toString();
        password = passwordText.getText().toString();
        age = ageText.getText().toString();

        // Note: This should not occur, since User ID cannot be changed;
        // Keep here just in case
        try
        {
            Integer.parseInt(userID);
        }
        catch (NumberFormatException e) {
            Messages.warning(this, "User ID must be a number.");
            return;
        }

        try
        {
            Integer.parseInt(age);
        }
        catch (NumberFormatException e) {
            Messages.warning(this, "Age must be a number.");
            return;
        }

        Calendar expDate = Calendar.getInstance();
        expDate.set(expYear, expMonth, expDay);

        String errorString = checkInputUser(Integer.parseInt(userID), name, password, Integer.parseInt(age), selectedGender, expYear, expMonth, expDay);
        if (null == errorString) {
            inputUser.setuID(Integer.parseInt(userID));
            inputUser.setName(name);
            inputUser.setPass(password);
            inputUser.setAge(Integer.parseInt(age));
            inputUser.setGender(selectedGender);
            inputUser.setEndDate(expDate);

            //Starting the previous Intent
            Intent previousScreen = new Intent(getApplicationContext(), UserListActivity.class);
            previousScreen.putExtra("UpdateUserKey", inputUser);
            setResult(1002, previousScreen);
            finish();
        }
        else
        {
            Messages.warning(this, errorString);
        }
    }

    public static String checkInputUser (int uID, String userName, String password, int age, char gender, int expYear, int expMonth, int expDay)
    {
        String errorString = null;
        // error checking
        if(null == userName || userName.equals(""))
        {
            errorString = "You need to name your user.";
        }
        if(null == password || password.equals(""))
        {
            errorString = "You need to enter a password.";
        }

        if(age < 1)
        {
            errorString = "Invalid age entered.";
        }

        if(uID < 1)
        {
            errorString = "Invalid user ID entered.";
        }

        if(!(gender == 'f' || gender == 'm'))
        {
            errorString = "Invalid gender entered.";
        }

        if (expYear <= Calendar.getInstance().get(Calendar.YEAR))
        {
            if (expYear < Calendar.getInstance().get(Calendar.YEAR))
            {
                errorString = "Invalid date entry. Can't enter user with expired account";
            }
            else if (expYear == Calendar.getInstance().get(Calendar.YEAR) && expMonth <= Calendar.getInstance().get(Calendar.MONTH))
            {
                if (expMonth < Calendar.getInstance().get(Calendar.MONTH))
                {
                    errorString = "Invalid date entry. Can't enter user with expired account";
                }
                else if (expMonth == Calendar.getInstance().get(Calendar.MONTH) && expDay <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                {
                    errorString = "Invalid date entry. Can't enter user with expired account";
                }
            }

        }


        return errorString;
    }
}
