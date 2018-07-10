package com.example.sarah.coursetool.Database;

public interface InstitutionDatabaseInterface {

    /**
     * Create course in the database
     *
     * @param courseName
     * @param capacity
     * @param professor
     * @param departmentCode
     * @param description
     * @param prerequisites
     * @param daysOfWeek - ex: MWF
     * @param startTime
     * @param endTime
     * @param startDate
     * @param endDate
     */
    String createCourse(String courseName, int capacity, String professor, String departmentCode,
                      String description, String prerequisites, String daysOfWeek, String startTime,
                      String endTime, String startDate, String endDate);

}
