package com.example.login.subject;

import com.example.login.student.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

  private String url = "jdbc:mysql://172.21.3.49:3306/helen";
  private String username = "bean";
  private String password = "beandev@123";

  public Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return connection;
  }

  public List<Subject> selectAllSubjects() {
    List<Subject> subjects = new ArrayList<>();
    String sql = "SELECT pre.id, pre.name AS name, s.name AS prerequisite_name FROM subject s RIGHT JOIN subject pre ON s.id = pre.prerequisite;";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String prerequisiteName = rs.getString("prerequisite_name");
        subjects.add(new Subject(id, name, prerequisiteName));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return subjects;
  }

  public Subject selectSubject(int subjectId) {
    String sql = "SELECT * FROM subject WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, subjectId);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int prerequisite = Integer.parseInt(rs.getString("prerequisite"));
        return new Subject(id, name, prerequisite);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insertSubject(Subject subject) {
    String sql = "INSERT INTO subject(name, prerequisite) VALUES (?, ?)";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, subject.getName());
      preparedStatement.setInt(2, subject.getPrerequisite());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateSubject(Subject subject) {
    String sql = "UPDATE subject SET name = ?, prerequisite = ? WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, subject.getName());
      preparedStatement.setInt(2, subject.getPrerequisite());
      preparedStatement.setInt(3, subject.getId());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteSubject(int id) {
    String sql = "DELETE FROM subject WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
