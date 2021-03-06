package com.model;
// Generated Aug 23, 2012 11:13:37 AM by Hibernate Tools 3.2.1.GA



/**
 * Coursefile generated by hbm2java
 */
public class Coursefile  implements java.io.Serializable {


     private String courseId;
     private String courseName;
     private String department;
     private String courseTeacher;
     private String semester;

    public Coursefile() {
    }

	
    public Coursefile(String courseId) {
        this.courseId = courseId;
    }
    public Coursefile(String courseId, String courseName, String department, String courseTeacher, String semester) {
       this.courseId = courseId;
       this.courseName = courseName;
       this.department = department;
       this.courseTeacher = courseTeacher;
       this.semester = semester;
    }
   
    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getCourseTeacher() {
        return this.courseTeacher;
    }
    
    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
    public String getSemester() {
        return this.semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }




}


