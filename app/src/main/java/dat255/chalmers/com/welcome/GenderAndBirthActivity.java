package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;

public class GenderAndBirthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_and_birth);
    }

    public void enableButtonNext(View view) {
        Button b = (Button)findViewById(R.id.buttonNext);
        b.setEnabled(true);
    }

    private void saveInfo() {
        //Save the user's selected gender
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupGender);
        RadioButton pressedButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GENDER, pressedButton.getTag().toString());
        editor.apply();

        //Save the user's selected birth date
        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDayOfMonth();

        editor.putInt(DOB_YEAR, year);
        editor.putInt(DOB_MONTH, month);
        editor.putInt(DOB_DAY, day);
        editor.commit();

    }

    public void showJobActivity(View view) {
        //Save all data
        saveInfo();

        //Switch to the next activity
        Intent intent = new Intent(GenderAndBirthActivity.this, JobActivity.class);
        startActivity(intent);
    }
}
