package com.example.geo_reminder;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ReminderDao reminderDao();
}
