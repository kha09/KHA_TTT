package com.example.timetable2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class FacultyActivity extends AppCompatActivity {
    private ListView listview;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        setupUiViews();
        initToolbar();
    }
    private void setupUiViews(){
        listview = findViewById(R.id.listFaculty);
        toolbar = findViewById(R.id.ToolbarFaculty);
        sharedPreferences = getSharedPreferences("myFaculty", MODE_PRIVATE);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListView(){
        String[] title = getResources().getStringArray(R.array.Week);
        String[] description = getResources().getStringArray(R.array.Day);

        // String[] week = getResources().getStringArray(R.array.Week);
        SimpleAdapter2 simpleAdapter = new SimpleAdapter2(this, title, description);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch(i){
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Sunday").apply();
                        //me//display message when pressed Sunday;
                        Toast.makeText(WeekActivity.this, "yes pls",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();

                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();

                        break;
                    }
                    default:break;
                }
            }
        });
    }

}
