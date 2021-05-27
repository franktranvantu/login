package com.example.login.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

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

  public User selectUser(String username, String password, int level) {
    String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND level = ?";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      preparedStatement.setInt(3, level);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String userName = rs.getString("username");
        String pass = rs.getString("password");
        int lv = Integer.parseInt(rs.getString("level"));
        return new User(id, userName, pass, lv);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insertUser(User user) {
    String sql = "INSERT INTO user(username, password, level) VALUES (?, ?, ?)";
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setInt(3, user.getLevel());

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
