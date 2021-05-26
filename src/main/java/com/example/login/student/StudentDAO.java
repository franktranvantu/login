package com.example.login.student;

import com.example.login.subject.Subject;
import com.example.login.subject.SubjectDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

  private SubjectDAO subjectDAO;

  private String url = "jdbc:mysql://localhost:3306/helen";
  private String username = "root";
  private String password = "frank";

  public StudentDAO() {
    subjectDAO = new SubjectDAO();
  }

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

  public List<Student> selectAllStudents() {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM student";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String status = rs.getString("status");
        String major = rs.getString("major");
        students.add(new Student(id, name, status, major));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return students;
  }

  public Student selectStudent(int studentId) {
    String sql = "SELECT * FROM student WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentId);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String status = rs.getString("status");
        String major = rs.getString("major");
        return new Student(id, name, status, major);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insertStudent(Student student) {
    String sql = "INSERT INTO student(name, status, major) VALUES (?, ?, ?)";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, student.getName());
      preparedStatement.setString(2, student.getStatus());
      preparedStatement.setString(3, student.getMajor());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateStudent(Student student) {
    String sql = "UPDATE student SET name = ?, status = ?, major = ? WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, student.getName());
      preparedStatement.setString(2, student.getStatus());
      preparedStatement.setString(3, student.getMajor());
      preparedStatement.setInt(4, student.getId());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteStudent(int id) {
    String sql = "DELETE FROM student WHERE id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean isEnrolValid(int studentId, int subjectId) {
    Subject subject = subjectDAO.selectSubject(subjectId);
    String sql = "SELECT subject_id FROM student_subject_complete WHERE student_id = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentId);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int completedSubjectId = rs.getInt("subject_id");
        if (subject.getPrerequisite() == completedSubjectId) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
