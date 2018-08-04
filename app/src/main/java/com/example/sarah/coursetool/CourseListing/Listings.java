package com.example.sarah.coursetool.CourseListing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.CourseList;
import com.example.sarah.coursetool.Database.RealDatabase;
import com.example.sarah.coursetool.R;
import com.example.sarah.coursetool.UserProfile.Profile;
import com.example.sarah.coursetool.UserProfile.StudentProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Listings extends BaseNavigationActivity {

    Spinner spinner;
    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();
    RecyclerView.Adapter viewAdapter;


    ArrayList<String> prereqs = new ArrayList<String>();
    ArrayList<CourseListing> completedByUser = new ArrayList<CourseListing>();
    ArrayList<CourseListing> allCourseList = new ArrayList<CourseListing>();
    DataGenerator data = DataGenerator.getGenerator();
    ArrayList<String> has = new ArrayList<String>();
    ArrayList<String> missing = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        Log.d("Hello","Loaded");
        dataGenerator.getAllCourses(inputData);
        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        viewAdapter = new viewAdapter(inputData, getApplicationContext());
        courseListContainer.setAdapter(viewAdapter);

        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = spinner.getItemAtPosition(position).toString();
                switch (s) {
                    case "All":
                        dataGenerator.getAllCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Fall":
                        dataGenerator.getFallCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Winter":
                        dataGenerator.getWinterCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Summer":
                        dataGenerator.getSummerCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    default:
                        dataGenerator.getFacultySpecificCourses(inputData, s);
                        viewAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void closePopup(View v) {
        ViewGroup g = (ViewGroup) v.getParent().getParent();
        View x = g.getChildAt(2);
        TextView message = findViewById(R.id.enrollMessage);
        message.setText("");
        TextView enrolled = findViewById(R.id.courseSpotsLeft);
        enrolled.setText("35 of 60 seats remaining");
        x.setVisibility(View.GONE);
    }

    public void enrollStudent(View v){
        View vh = findViewById(R.id.courseInfoContainer);
        CourseListing listing = (CourseListing) vh.getTag();
        Button button = findViewById(R.id.enrollmentButton);
        TextView preReqs = findViewById(R.id.courseSpotsLeft);
        TextView courseIDMain = findViewById(R.id.courseIdMain);
        TextView courseDeptMain = findViewById(R.id.courseDeptMain);
        String course = courseDeptMain.toString().concat(" "+ courseIDMain.toString());
        int schedID = courseIDMain.getId();
        TextView enrolled = findViewById(R.id.courseSpotsLeft);
        TextView message = findViewById(R.id.enrollMessage);
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
            Log.d("Timeouterror",e.toString());
            return;
        }
        if(button.getText().equals("Unenroll")) {
            try {
                Log.d("monkey13", listing.courseUniqueID);
                conn.unenrollFromCourse(listing.courseUniqueID);
            } catch (TimeoutException e) {
                message.setText("Failed to unenroll due to database timeout");
                return;
            }
            listing.enrolled--;
            enrolled.setText(""+(listing.capacity - listing.enrolled)+" of "+listing.capacity+" seats remaining");
            message.setText("Unenrollment Successful");
            button.setText("Enroll");
            viewAdapter.notifyDataSetChanged();
            return;
        }
        HashMap<String, ScheduledCourse> courses = profile.getEnrolledCourses();
        if (listing.capacity - listing.enrolled < 1) {
            message.setText("Enrollment failed - no empty seats");
            return;
        }

        /*String preReqString;
        preReqString = "";
        preReqString = checkPreReqs(profile, listing);
           if(!preReqString.equals("pass")) {
                message.setText(preReqString);
                return;
            }*/


        for(Map.Entry<String, ScheduledCourse> scheduledCourse:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(scheduledCourse.getValue());
            if (listing.courseStartTime.before(inputCourse.courseEndTime) && listing.courseEndTime.after(inputCourse.courseStartTime)) {
                for (int dayCount = 0; dayCount < 7; dayCount++) {
                    if (listing.courseDays[dayCount] == 1 && inputCourse.courseDays[dayCount] == 1) {
                        if (listing.courseStartTime.getHours() < inputCourse.courseStartTime.getHours()) {
                            if (listing.courseEndTime.getHours() > inputCourse.courseStartTime.getHours()) {
                                message.setText("Enrollment failed - time conflict");
                                return;
                            } else if (listing.courseEndTime.getHours() == inputCourse.courseStartTime.getHours()) {
                                if (listing.courseEndTime.getMinutes() > inputCourse.courseStartTime.getMinutes()) {
                                    message.setText("Enrollment failed - time conflict");
                                    return;
                                }
                            }
                        }
                        if (listing.courseStartTime.getHours() == inputCourse.courseStartTime.getHours()) {
                            if (listing.courseEndTime.getHours() == inputCourse.courseEndTime.getHours() && listing.courseStartTime.getHours() == listing.courseEndTime.getHours()) {
                                if (listing.courseStartTime.getMinutes() >= inputCourse.courseStartTime.getMinutes()) {
                                    if (listing.courseStartTime.getMinutes() < inputCourse.courseEndTime.getMinutes()) {
                                        message.setText("Enrollment failed - time conflict");
                                        return;
                                    }
                                } else if (listing.courseStartTime.getMinutes() < inputCourse.courseStartTime.getMinutes()) {
                                    if (listing.courseEndTime.getMinutes() > inputCourse.courseStartTime.getMinutes()) {
                                        message.setText("Enrollment failed - time conflict");
                                        return;
                                    }
                                }
                            } else if (listing.courseEndTime.getHours() < inputCourse.courseEndTime.getHours()) {
                                if (listing.courseEndTime.getHours() == inputCourse.courseStartTime.getHours()) {
                                    if (listing.courseEndTime.getMinutes() > inputCourse.courseStartTime.getMinutes()) {
                                        message.setText("Enrollment failed - time conflict");
                                        return;
                                    }
                                } else {
                                    message.setText("Enrollment failed - time conflict");
                                    return;
                                }
                            } else if (listing.courseEndTime.getHours() > inputCourse.courseEndTime.getHours()) {
                                if (listing.courseStartTime.getHours() < inputCourse.courseEndTime.getHours()) {
                                    message.setText("Enrollment failed - time conflict");
                                    return;
                                } else if (listing.courseStartTime.getHours() == inputCourse.courseEndTime.getHours()) {
                                    if (listing.courseStartTime.getMinutes() < inputCourse.courseEndTime.getMinutes()) {
                                        message.setText("Enrollment failed - time conflict");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        completedByUser.clear();
        data.getCompletedCourses(completedByUser);
        if (listing.getCoursePreqs().size() != 0) {
            prereqs.clear();
            prereqs = listing.getCoursePreqs();
            has.clear();
            for (int h = 0; h < completedByUser.size(); h++) {
                has.add(completedByUser.get(h).getCourseTitle());
            }
            missing.clear();
            for (int z = 0; z < prereqs.size(); z++) {
                if (!has.contains(prereqs.get(z))) {
                    missing.add(prereqs.get(z));
                }
            }
            int flag = 0;
            int commaCount = 0;
            String errMsg = "";
            String hold = "";
            hold = "Enrollment failed - pre reqs not met: ";
            if (!missing.isEmpty()) {
                for (int i = 0; i < missing.size(); i++) {
                    if (flag == 0) {
                        flag = 1;
                    }
                    if (commaCount == 0) {
                        hold += missing.get(i);
                        commaCount++;
                    } else {
                        hold += ", " + missing.get(i);
                    }
                }
                hold += ".";
                errMsg = hold;
                hold = "";
                message.setText(errMsg);
                return;
            }
        }
        try {
            conn.enroll(listing.courseUniqueID);
        } catch (TimeoutException e) {
            message.setText("Failed to enroll due to database timeout");
            return;
        }
        listing.enrolled++;
        enrolled.setText(""+(listing.capacity - listing.enrolled)+" of "+listing.capacity+" seats remaining");
        //message.setText("Enrollment Successful");
        message.setText(listing.getCoursePreqs().size());
        button.setText("Unenroll");
        viewAdapter.notifyDataSetChanged();
    }

    /**
     * Method will check if user has completed preReqs for course they are attempting to enroll in
     * The method will return a string pass if check passes, but upon failure method will return a list of preReqs the user must complete
     * @param attemptToEnroll
     * @return
     * Created by HA&NS on 07/22/18
     */
    /*ArrayList<String> prereqs;
    ArrayList<CourseListing> completedByUser;
    ArrayList<String> has;
    ArrayList<String> missing;
    DataGenerator data = DataGenerator.getGenerator();
    ArrayList<CourseListing> allCourseList = new ArrayList<CourseListing>();*/

    /*public String checkPreReqs(Profile profile, CourseListing attemptToEnroll) {

        /*data.getAllCourses(allCourseList);
        for (int t = 0; t < allCourseList.size(); t++) {
            if (profile.getCourseGrade(allCourseList.get(t).courseUniqueID) >= 0) {
                completedByUser.add(allCourseList.get(t));
            }
        }*/
       /* completedByUser.clear();
        data.getCompletedCourses(completedByUser);
        prereqs.clear();
        prereqs = attemptToEnroll.getCoursePreqs();

        //if the user has a pre-req, add it to the needed list, other wise add it to missing list
        /*for (int h = 0; h < completedByUser.size(); h++) {
            for (int j = 0; j < prereqs.size(); j++) {
                if (completedByUser.get(h).getCourseTitle().equals(prereqs.get(j))) {
                    has.add(prereqs.get(j));
                }
            }
        }*/

       /* for (int h = 0; h < completedByUser.size(); h++) {
            has.add(completedByUser.get(h).getCourseTitle());
        }

        missing.clear();
        //if a course from prereqs is not in has, we need to add said course from prereqs to missing
        for (int z = 0; z < prereqs.size(); z++) {
            if (!has.contains(prereqs.get(z))) {
                missing.add(prereqs.get(z));
            }
        }
        //if there are any pre reqs missing from profile, the title of said pre req will be added to the error msg
        //a lot of random stuff to just set up error msg neatly
        int flag = 0;
        int commaCount = 0;
        String errMsg = "Enrollment failed - pre reqs not met: ";
        String pass = "pass";
        if (missing.isEmpty()) {
            return pass;
        } else {
            for (int i = 0; i < missing.size(); i++) {
                if (flag == 0) {
                    flag = 1;
                }
                if (commaCount == 0) {
                    errMsg += missing.get(i);
                    commaCount++;
                } else {
                    errMsg += ", " + missing.get(i);
                }
            }
            errMsg += ".";
            return errMsg;
        }
    }*/

    /**
     * OnClick method that switches to a new activity in which professors/administrators can set
     * students' grade for the class.
     *
     * @param View
     */
    /*
    public void onPressAssignGradesToStudentButton(View View){
        // get the course listing that is currently being viewed on the screen
        View vh = findViewById(R.id.courseInfoContainer);
        CourseListing listing = (CourseListing) vh.getTag();

        // Create an intent to switch activities, passing along the DB key for this course
        Intent intent = new Intent(this, AssignGradesActivity.class);
        intent.putExtra("CourseKey", listing.courseUniqueID);
        startActivity(intent);
    }
*/
    /**
     * OnClick method that switches to a new activity in which professors/administrators can set
     * students' grade for the class.
     *
     * @param View
     */
    public void onPressAssignGradesToStudentButton(View View){
        // get the course listing that is currently being viewed on the screen
        View vh = findViewById(R.id.courseInfoContainer);
        CourseListing listing = (CourseListing) vh.getTag();

        // Create an intent to switch activities, passing along the DB key for this course
        Intent intent = new Intent(this, AssignGradesActivity.class);
        intent.putExtra("CourseKey", listing.courseUniqueID);
        startActivity(intent);
    }
}
