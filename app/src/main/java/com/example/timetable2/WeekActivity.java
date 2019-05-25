package com.example.timetable2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class WeekActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setupUIViews();
        initToolbar();
    }
    private void setupUIViews(){
        toolbar = findViewById(R.id.ToolbarWeek);
        listView = findViewById(R.id.listWeek);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
    }
    private void setupListView(){
        String[] week = getResources().getStringArray(R.array.Week);
    }
}
