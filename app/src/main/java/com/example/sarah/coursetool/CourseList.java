package com.example.sarah.coursetool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import com.example.sarah.coursetool.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.app.Activity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
//

import com.example.sarah.coursetool.Course.ScheduledCourse;

public class CourseList extends BaseNavigationActivity /*implements AdapterView.OnItemSelectedListener*/ {
    Spinner spinner;

    String terms[]={"All1", "Fall", "Winter", "Summer"};
    ArrayList<String> list= new ArrayList<String>();
    String course[]={"abc","bcd","cde"};

    private ListView listView;

    ArrayAdapter<String> adapter,listadapter;
    ///abc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        listView=(ListView) findViewById(R.id.listview);

        for(int i=0;i<course.length;i++){
            list.add(course[i]);
        }

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,terms);
        listadapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);

        listView.setAdapter(listadapter);

        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = spinner.getItemAtPosition(position).toString();
                //String Fall="Fall"
                switch (s) {
                    case "Fall":
                        list.add("Fall 124");
                        list.add("Fall 234");
                        listadapter.notifyDataSetChanged();
                        //listView.setAdapter(list);
                        break;

                    case "Winter":
                        list.add("winter 123");
                        list.add("winter 234");
                        listadapter.notifyDataSetChanged();
                        break;
                }

                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
