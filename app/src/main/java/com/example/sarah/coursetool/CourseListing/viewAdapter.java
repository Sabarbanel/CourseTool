package com.example.sarah.coursetool.CourseListing;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.Database.RealDatabase;
import com.example.sarah.coursetool.R;
import com.example.sarah.coursetool.UserProfile.StudentProfile;

public class viewAdapter extends RecyclerView.Adapter<CourseHolder> {

    //Benefitted from https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/
    // and https://stackoverflow.com/questions/39218567/how-to-add-setonclicklistener-for-each-item-in-cardview
    // and https://stackoverflow.com/questions/39218567/how-to-add-setonclicklistener-for-each-item-in-cardview
    // and https://stackoverflow.com/questions/2271570/android-findviewbyid-finding-view-by-id-when-view-is-not-on-the-same-layout-in
    // and https://stackoverflow.com/questions/31301470/cannot-resolve-method-getlayoutinflater

    ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();
    Context context;
    DateFormat formatter = new SimpleDateFormat("MMMM-dd HH:mm");

    public viewAdapter(ArrayList<CourseListing> someData, Context newContext){
        inputData = someData;
        context = newContext;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        CourseHolder holder = new CourseHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CourseHolder someHolder, int i){
        someHolder.courseInfo = inputData.get(i);
        someHolder.courseTitle.setText(inputData.get(i).courseTitle);
        someHolder.courseProf.setText(inputData.get(i).courseProf);
        someHolder.courseDept.setText(inputData.get(i).courseDepartment);
        someHolder.courseDesc.setText(inputData.get(i).courseDescription);
        someHolder.courseStart.setText(formatter.format(inputData.get(i).courseStartTime));
        someHolder.courseEnd.setText(formatter.format(inputData.get(i).courseEndTime));
        someHolder.courseId.setText(Integer.toString(inputData.get(i).courseID));
        someHolder.courseRoom.setText(Integer.toString(inputData.get(i).courseRoom));
        String prereqs = "";
        for(String x:inputData.get(i).coursePreqs){
            prereqs += x + ", ";
        }
        if(prereqs.length() >= 1){
            prereqs = prereqs.substring(0, prereqs.length()-2);
        }
        if(prereqs.equals("")){
            prereqs = "None";
        }
        someHolder.coursePrereqs.setText("Prerequisites: "+prereqs);
        someHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup g = (ViewGroup) v.getParent().getParent().getParent().getParent().getParent();
                View x = g.getChildAt(2);
                ViewGroup y = (ViewGroup) x;
                TextView title = (TextView) y.getChildAt(1);
                TextView tt = (TextView) v.findViewById(R.id.courseTitle);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(2);
                tt = (TextView) v.findViewById(R.id.courseProf);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(3);
                tt = (TextView) v.findViewById(R.id.courseDept);
                title.setText(tt.getText());
                String daysOfWeek = "";
                if (someHolder.courseInfo.courseDays[1] == 1)
                    daysOfWeek += "M";
                if (someHolder.courseInfo.courseDays[2] == 1)
                    daysOfWeek += "T";
                if (someHolder.courseInfo.courseDays[3] == 1)
                    daysOfWeek += "W";
                if (someHolder.courseInfo.courseDays[4] == 1)
                    daysOfWeek += "R";
                if (someHolder.courseInfo.courseDays[5] == 1)
                    daysOfWeek += "F";
                title = (TextView) y.getChildAt(4);
                tt = (TextView) v.findViewById(R.id.courseDaysMain);
                title.setText(daysOfWeek);
                title = (TextView) y.getChildAt(5);
                tt = (TextView) v.findViewById(R.id.courseDesc);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(6);
                tt = (TextView) v.findViewById(R.id.courseStart);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(8);
                tt = (TextView) v.findViewById(R.id.courseEnd);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(9);
                tt = (TextView) v.findViewById(R.id.courseId);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(10);
                tt = (TextView) v.findViewById(R.id.courseRoom);
                title.setText(tt.getText());
                title = (TextView) y.getChildAt(11);
                tt = (TextView) v.findViewById(R.id.coursePrereqs);
                title.setText(tt.getText());
                Button enrollButton = (Button) y.getChildAt(12);
                RealDatabase conn = RealDatabase.getDatabase();
                int counter = 0;
                while(conn.snapshotIsNull() && counter < 2) {
                    try {
                        counter++;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                StudentProfile profile;
                try {
                    profile = (StudentProfile) conn.getUserProfile();
                } catch (TimeoutException e) {
                    return;
                }
                CourseListing listing = (CourseListing) someHolder.courseInfo;
                HashMap<String, ScheduledCourse> courses;
                try {
                    courses = conn.getEnrolledCourses();
                } catch (TimeoutException e) {
                    return;
                }
                for(Map.Entry<String, ScheduledCourse> scheduledCourse:courses.entrySet()) {
                    CourseListing inputCourse = new CourseListing(scheduledCourse.getValue());
                    if (inputCourse.courseUniqueID.equals(listing.courseUniqueID)) {
                        enrollButton.setText("Unenroll");
                        break;
                    } else {
                        enrollButton.setText("Enroll");
                    }
                }
                title = (TextView) y.getChildAt(13);
                title.setText(""+(someHolder.courseInfo.capacity - someHolder.courseInfo.enrolled)+" of "+someHolder.courseInfo.capacity+" seats remaining");
                x.setTag(someHolder.courseInfo);
                x.setVisibility(View.VISIBLE);


            }
        });

    }

    @Override
    public int getItemCount() {
        return inputData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recycler){
        super.onAttachedToRecyclerView(recycler);
    }

    public void insert(int position, CourseListing entry){
        inputData.add(position, entry);
        notifyItemInserted(position);
    }

    public void remove(CourseListing entry) {
        int position = inputData.indexOf(entry);
        inputData.remove(entry);
        notifyItemRemoved(position);
    }

}
