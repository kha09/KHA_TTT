package com.example.timetable2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetable2.utils.LetterImageView;

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
        String[] faculty_names = getResources().getStringArray(R.array.faculty_name);

        // String[] week = getResources().getStringArray(R.array.Week);
        FacultyAdapter simpleAdapter = new FacultyAdapter(this, R.layout.faculty_single_item, faculty_names);
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public class FacultyAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] faculty = new String[]{};

        public FacultyAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.faculty = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterFaculty);
                holder.tvFaculty = (TextView)convertView.findViewById(R.id.tvFacultyName);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(faculty[position].charAt(0));
            holder.tvFaculty.setText(faculty[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvFaculty;
        }
    }

}
