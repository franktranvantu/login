package com.example.login.subject;

public class Subject {

  private int id;
  private String name;
  private String prerequisite;

  public Subject(String name, String prerequisite) {
    this.name = name;
    this.prerequisite = prerequisite;
  }

  public Subject(int id, String name, String prerequisite) {
    this.id = id;
    this.name = name;
    this.prerequisite = prerequisite;
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

  public String getPrerequisite() {
    return prerequisite;
  }

  public void setPrerequisite(String prerequisite) {
    this.prerequisite = prerequisite;
  }
}
