package com.example.login.enrolment;

public class Enrolment {

  private int id;
  private int studentId;
  private String studentName;
  private int subjectId;
  private String subjectName;
  private boolean isCompleted;

  public Enrolment(int studentId, int subjectId, boolean isCompleted) {
    this.studentId = studentId;
    this.subjectId = subjectId;
    this.isCompleted = isCompleted;
  }

  public Enrolment(int id, int studentId, int subjectId) {
    this.id = id;
    this.studentId = studentId;
    this.subjectId = subjectId;
  }

  public Enrolment(int id, int studentId, int subjectId, boolean isCompleted) {
    this.id = id;
    this.studentId = studentId;
    this.subjectId = subjectId;
    this.isCompleted = isCompleted;
  }

  public Enrolment(int id, String studentName, String subjectName) {
    this.id = id;
    this.studentName = studentName;
    this.subjectName = subjectName;
  }

  public Enrolment(int id, String studentName, String subjectName, boolean isCompleted) {
    this.id = id;
    this.studentName = studentName;
    this.subjectName = subjectName;
    this.isCompleted = isCompleted;
  }

  public Enrolment(int studentId, String studentName, int subjectId, String subjectName) {
    this.studentId = studentId;
    this.studentName = studentName;
    this.subjectId = subjectId;
    this.subjectName = subjectName;
  }

  public Enrolment(int id, int studentId, String studentName, int subjectId, String subjectName) {
    this.id = id;
    this.studentId = studentId;
    this.studentName = studentName;
    this.subjectId = subjectId;
    this.subjectName = subjectName;
  }

  public Enrolment(int id, int studentId, String studentName, int subjectId, String subjectName, boolean isCompleted) {
    this.id = id;
    this.studentId = studentId;
    this.studentName = studentName;
    this.subjectId = subjectId;
    this.subjectName = subjectName;
    this.isCompleted = isCompleted;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }
}
