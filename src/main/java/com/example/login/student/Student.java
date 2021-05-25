package com.example.login.student;

public class Student {

  private int id;
  private String name;
  private String status;
  private String major;

  public Student(String name, String status, String major) {
    this.name = name;
    this.status = status;
    this.major = major;
  }

  public Student(int id, String name, String status, String major) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.major = major;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }
}
