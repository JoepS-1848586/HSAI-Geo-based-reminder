package com.example.geo_reminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ReminderListAdapter extends BaseAdapter {

    private List<Reminder> m_reminders;
    private LayoutInflater inflater;
    private MainActivity m_context;

    public ReminderListAdapter(MainActivity context, List<Reminder> reminders ){
        m_context = context;
        m_reminders = reminders;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return m_reminders.size();
    }

    @Override
    public Object getItem(int position) {
        return m_reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        if(view == null){
            view = inflater.inflate(R.layout.ls_reminder_item, parent, false);
        }
        TextView name = (TextView)view.findViewById(R.id.ls_reminder_item_nameView);
        name.setText(m_reminders.get(position).getInfo());

        Button but = view.findViewById(R.id.ls_reminder_item_deleteView);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete(position);
            }
        });

        return view;
    }

    private void onDelete(int position){
        m_context.deleteReminder(position);
    }
}
