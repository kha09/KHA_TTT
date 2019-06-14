package com.example.timetable2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class DayDetail extends AppCompatActivity {
    private ListView listView;
    private Toolbar toolbar;
    public static String[] Sunday;
    public static String[] Monday;
    public static String[] Wednesday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    private String[] PreferredDay;
    private String[] PreferredTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        setupUIViews();
        initTool();
        setupListView();
    }

    private void setupUIViews() {
        listView = findViewById(R.id.listDayDetail);
        toolbar = findViewById(R.id.ToolbarDayDetail);
    }

    private void initTool() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null));
        //Maybe delete this
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView() {
        Monday = getResources().getStringArray(R.array.Monday);
        Sunday = getResources().getStringArray(R.array.sunday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);

        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);
        if (selected_day.equalsIgnoreCase("Monday")) {
            PreferredDay = Monday;
            PreferredTime = Time1;
        } else if (selected_day.equalsIgnoreCase("Sunday")) {
            PreferredDay = Sunday;
            PreferredTime = Time2;
        } else if (selected_day.equalsIgnoreCase("Wednesday")) {
            PreferredDay = Wednesday;
            PreferredTime = Time3;
        }
    }
}
