package com.example.geo_reminder;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDao {
    @Query("SELECT * FROM reminders")
    List<Reminder> getAll();

    @Insert
    void InsertReminders(Reminder... reminders);

    @Delete
    void delete(Reminder reminder);
}
