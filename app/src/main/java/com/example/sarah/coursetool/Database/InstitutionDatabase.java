package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.UserProfile.StudentProfile;

/**
 * A class that contains database methods related to administrative functionality, such as
 * creating new courses.
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
public class InstitutionDatabase implements InstitutionDatabaseInterface {
    private RealDatabase database;

    /**
     * Constructs new InstitutionDatabase.
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public InstitutionDatabase() {
        database = RealDatabase.getDatabase();
    }

    @Override
    public String createCourse(String courseName, int capacity, String professor,
                               String departmentCode, String description, String prerequisites,
                               String daysOfWeek, String startTime, String endTime,
                               String startDate, String endDate) {
        return database.createCourse(courseName, capacity, professor, departmentCode, description,
                prerequisites, daysOfWeek, startTime, endTime, startDate, endDate);
    }

    @Override
    public void removeCourse(String key) {
        database.removeCourse(key);
    }

    @Override
    public void addProfile(StudentProfile newProfile) {
        database.addProfile(newProfile);
    }

    @Override
    public void assignGradeToStudent(String studentUsername, String courseKey, int grade) {
        database.assignGradeToStudent(studentUsername, courseKey, grade);
    }
}
