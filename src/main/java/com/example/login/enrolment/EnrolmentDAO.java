package com.example.login.enrolment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentDAO {

  private String url = "jdbc:mysql://localhost:3306/helen";
  private String username = "root";
  private String password = "frank";

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

  public List<Enrolment> selectAllEnrolments() {
    List<Enrolment> enrolments = new ArrayList<>();
    String sql = "SELECT e.id, s.name AS student_name, su.name AS subject_name FROM enrolment e JOIN student s ON e.student_id = s.id JOIN subject su ON e.subject_id = su.id";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String studentName = rs.getString("student_name");
        String subjectName = rs.getString("subject_name");
        enrolments.add(new Enrolment(id, studentName, subjectName));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return enrolments;
  }

  public Enrolment selectEnrolment(int EnrolmentId) {
    String sql = "SELECT e.id, s.name AS student_name, su.name AS subject_name FROM enrolment e JOIN student s ON e.student_id = s.id JOIN subject su ON e.subject_id = su.id WHERE e.id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, EnrolmentId);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String studentName = rs.getString("student_name");
        String subjectName = rs.getString("subject_name");
        return new Enrolment(id, studentName, subjectName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insertEnrolment(Enrolment enrolment) {
    String sql = "INSERT INTO enrolment(student_id, subject_id) VALUES (?, ?)";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, enrolment.getStudentId());
      preparedStatement.setInt(2, enrolment.getSubjectId());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateEnrolment(Enrolment enrolment) {
    String sql = "UPDATE enrolment SET student_id = ?, subject_id = ? WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, enrolment.getStudentId());
      preparedStatement.setInt(2, enrolment.getSubjectId());
      preparedStatement.setInt(3, enrolment.getId());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteEnrolment(int id) {
    String sql = "DELETE FROM enrolment WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
