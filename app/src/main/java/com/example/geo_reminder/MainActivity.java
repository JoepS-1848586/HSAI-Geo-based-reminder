package com.example.geo_reminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Reminder> m_reminders;
    private ReminderListAdapter m_listadapter;

    private static int REQUEST_CODE_ADD_ITEM = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findReminders();
        m_listadapter = new ReminderListAdapter(this, m_reminders);

        ListView v = findViewById(R.id.reminder_list);
        v.setAdapter(m_listadapter);
    }

    private void findReminders(){
        m_reminders = new ArrayList<>();

        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "reminders")
                .allowMainThreadQueries().build();

        m_reminders.addAll(db.reminderDao().getAll());

        // test-data
//        addReminder(new Reminder("test1",78,63, 10));
//        addReminder(new Reminder("test2",123,143, 13));
    }

    public void deleteReminder(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deleting : " + m_reminders.get(position).getInfo());
        builder.setTitle("Deleting item");

        builder.setPositiveButton(R.string.activity_main_deleteConfirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Reminder r = m_reminders.get(position);
                m_listadapter.notifyDataSetChanged();
                try {
                    Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "reminders")
                            .allowMainThreadQueries().build().reminderDao().delete(r);
                    m_reminders.remove(position);
                    Toast.makeText(getApplicationContext(), R.string.activity_main_deleteToast, Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), R.string.activity_main_deleteFailToast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.activity_main_deleteCancel, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onAdd(View view){
        Intent i = new Intent(this, AddReminderActivity.class);
        startActivityForResult(i, REQUEST_CODE_ADD_ITEM);
    }

    private void addReminder(Reminder i){
        try {
            AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "reminders")
                    .allowMainThreadQueries().build();
            db.reminderDao().InsertReminders(i);
            m_reminders.add(i);
        }catch (Exception e){
            Toast.makeText(this, R.string.activity_add_reminder_failToast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_ITEM){
            if(resultCode == Activity.RESULT_OK){
                addReminder((Reminder)data.getSerializableExtra(AddReminderActivity.EXTRA_NEW_ITEM));
                Toast.makeText(this, R.string.activity_add_reminder_succesToast, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
