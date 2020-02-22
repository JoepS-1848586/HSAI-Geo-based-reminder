package com.example.geo_reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AddReminderActivity extends AppCompatActivity {

    public static String EXTRA_NEW_ITEM = "com.example.geo_reminder.NEW_ITEM";

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
}
