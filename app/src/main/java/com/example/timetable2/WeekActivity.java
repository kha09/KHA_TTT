package com.example.timetable2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class WeekActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setupUIViews();
        initToolbar();
        setupListView();
    }
    private void setupUIViews(){
        toolbar = findViewById(R.id.ToolbarWeek);
        listView = findViewById(R.id.listWeek);
        sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
        //Maybe delete this
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
    public class SimpleAdapter2 extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,description;
        private String[] titlearray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter2(Context context, String[] title, String[] description){
            mContext = context;
            titlearray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titlearray.length;
        }

        @Override
        public Object getItem(int position) {
            return titlearray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.activity_week_single_item,null);
            }
            title = convertView.findViewById(R.id.tvMain2);
            description = convertView.findViewById(R.id.tvDescription2);
            imageView = convertView.findViewById(R.id.ivMain2);

            title.setText(titlearray[position]);
            description.setText(descriptionArray[position]);

            if (titlearray[position].equalsIgnoreCase("Sunday")){
                imageView.setImageResource(R.drawable.s);
            }else if (titlearray[position].equalsIgnoreCase("Monday")){
                imageView.setImageResource(R.drawable.m);

            }else {
                imageView.setImageResource(R.drawable.w);
            }
            return convertView;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
