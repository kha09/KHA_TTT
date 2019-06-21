package com.example.timetable2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setupUIview();
        initToolbar();
        setupListview();
    }
    private void setupUIview(){
        toolbar = findViewById(R.id.ToolbarSubjectDetails);
        listview = findViewById(R.id.listSubjectDetails);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListview(){
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF, null);
        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titles);

        if(subject_selected.equalsIgnoreCase("Communication")){
            syllabus = getResources().getStringArray(R.array.Communiction);
        }else if(subject_selected.equalsIgnoreCase("MSD")){
            syllabus = getResources().getStringArray(R.array.AWP);
        }else{
            syllabus = getResources().getStringArray(R.array.AWP);
        }

        SubjectDetialsAdapter subjectDetialsAdapter = new SubjectDetialsAdapter(this, titles, syllabus);
        listview.setAdapter(subjectDetialsAdapter);
    }
    public class SubjectDetialsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,syllabus;
        private String[] titlearray;
        private String[] syllabullsArray;
        private ImageView imageView;

        public SubjectDetialsAdapter(Context context, String[] title, String[] syllabus){
            mContext = context;
            titlearray = title;
            syllabullsArray = syllabus;
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
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item,null);
            }
            title = convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = convertView.findViewById(R.id.tvSyllabus);
//            imageView = convertView.findViewById(R.id.ivMain);

            title.setText(titlearray[position]);
            syllabus.setText(syllabullsArray[position]);

//            if (titlearray[position].equalsIgnoreCase("Timetable")){
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
