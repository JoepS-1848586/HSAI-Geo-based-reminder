package com.example.geo_reminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class AddReminderActivity extends AppCompatActivity {

    public static String EXTRA_NEW_ITEM = "com.example.geo_reminder.NEW_ITEM";

    private static final int REQUEST_CODE_RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
    }

    private void returnReminder(Reminder r){
        Intent i = new Intent();
        i.putExtra(EXTRA_NEW_ITEM, r);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void onSubmit(View view){
        String info = ((EditText)findViewById(R.id.info_field)).getText().toString();
        Reminder r = new Reminder(info, 10,10,12);
        returnReminder(r);
    }

    public void onRecord(View view){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say address");

        try {
            startActivityForResult(i, REQUEST_CODE_RECORD);
        } catch (Exception a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry your device not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_RECORD){
            if(resultCode == Activity.RESULT_OK){
                List<String> strings = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                TextView view = findViewById(R.id.address_input);
                view.setText(strings.get(0));
            }else{
                Toast.makeText(getApplicationContext(),
                        "Error recording message",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onSearch(View view){

    }
}
