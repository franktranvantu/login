package com.example.login.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

  private LoginDAO loginDAO;

  public void init() {
    loginDAO = new LoginDAO();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null) {
      action = "No action";
    }
    RequestDispatcher dispatcher;
    try {
      switch (action) {
        case "new":
          showRegisterForm(request, response);
        case "login":
          login(request, response);
          break;
        case "register":
          register(request, response);
          break;
        default:
          showLoginForm(request, response);
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

  private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("register-form.jsp");
    dispatcher.forward(request, response);
  }

  private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
    dispatcher.forward(request, response);
  }

  private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    int level = Integer.parseInt(request.getParameter("level"));
    User user = new User(username, password, level);
    loginDAO.insertUser(user);
    HttpSession session = request.getSession();
    session.setAttribute("user", user);
    response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    int level = Integer.parseInt(request.getParameter("level"));
    User user = loginDAO.selectUser(username, password, level);
    if (user != null) {
      HttpSession session = request.getSession();
      session.setAttribute("user", user);
      response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
    } else {
      request.setAttribute("error", "User invalid");
      RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
      dispatcher.forward(request, response);
    }
  }
}
