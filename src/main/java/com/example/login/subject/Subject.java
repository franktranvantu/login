package com.example.login.subject;

public class Subject {

  private int id;
  private String name;
  private int prerequisite;
  private String prerequisiteName;

  public Subject(String name, int prerequisite) {
    this.name = name;
    this.prerequisite = prerequisite;
  }

  public Subject(int id, String name, int prerequisite) {
    this.id = id;
    this.name = name;
    this.prerequisite = prerequisite;
  }

  public Subject(int id, String name, String prerequisiteName) {
    this.id = id;
    this.name = name;
    this.prerequisiteName = prerequisiteName;
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

  public int getPrerequisite() {
    return prerequisite;
  }

  public void setPrerequisite(int prerequisite) {
    this.prerequisite = prerequisite;
  }

  public String getPrerequisiteName() {
    return prerequisiteName;
  }

  public void setPrerequisiteName(String prerequisiteName) {
    this.prerequisiteName = prerequisiteName;
  }
}
