package com.example.timetable2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        SimpleAdapter simpleAdapter = new SimpleAdapter(DayDetail.this, PreferredDay, PreferredTime);
        listView.setAdapter(simpleAdapter);

    }
    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject,time;
        private String[] subbjectArrray;
        private String[] timeArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] subbjectArrray, String[] timeArray){
            mContext = context;
            this.subbjectArrray = subbjectArrray;
            this.timeArray = timeArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subbjectArrray.length;
        }

        @Override
        public Object getItem(int position) {
            return subbjectArrray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item,null);
            }
            subject = convertView.findViewById(R.id.tvDayDetails);
            time = convertView.findViewById(R.id.tvTimeDayDetail);
            imageView = convertView.findViewById(R.id.ivDayDetail);

            subject.setText(subbjectArrray[position]);
            time.setText(timeArray[position]);
//
//            if (subbjectArrray[position].equalsIgnoreCase("Signals")){
//                imageView.setImageResource(R.drawable.timetable);
//            }else if (titlearray[position].equalsIgnoreCase("Subjects")){
//                imageView.setImageResource(R.drawable.book);
//            }else if (titlearray[position].equalsIgnoreCase("Faculty")){
//                imageView.setImageResource(R.drawable.contact);
//            }else {
//                imageView.setImageResource(R.drawable.settings);
//            }
            return convertView;
        }
    }
}
