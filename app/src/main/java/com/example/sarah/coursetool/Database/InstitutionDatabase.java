package com.example.sarah.coursetool.Database;

/**
 * A class that contains database methods related to administrative functionality, such as
 * creating new courses.
 */
public class InstitutionDatabase implements InstitutionDatabaseInterface {

    private RealDatabase database;

    /**
     * Constructs new InstitutionDatabase.
     * @param sourceDatabase
     */
    protected InstitutionDatabase(RealDatabase sourceDatabase) {
        database = sourceDatabase;
    }

    public static InstitutionDatabase InstitutionDatabase() {
        return new InstitutionDatabase(RealDatabase.getDatabase());
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

}
