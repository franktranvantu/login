package com.example.login.student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student-servlet")
public class StudentServlet extends HttpServlet {

  private StudentDAO studentDAO;

  public void init() {
    studentDAO = new StudentDAO();
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
          showNewStudent(request, response);
          break;
        case "insert":
          insertStudent(request, response);
          break;
        case "delete":
          deleteStudent(request, response);
          break;
        case "edit":
          showEditStudent(request, response);
          break;
        case "update":
          updateStudent(request, response);
          break;
        case "show":
          showStudent(request, response);
          break;
        default:
          listStudent(request, response);
          break;
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

  private void showNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> students = studentDAO.selectAllStudents();
    request.setAttribute("students", students);
    RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
    dispatcher.forward(request, response);
  }

  private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getParameter("name");
    String status = request.getParameter("status");
    String major = request.getParameter("major");
    Student student = new Student(name, status, major);
    studentDAO.insertStudent(student);
    response.sendRedirect(request.getContextPath()+"/student-servlet?action=list");
  }

  private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    studentDAO.deleteStudent(id);
    response.sendRedirect(request.getContextPath() +"/student-servlet?action=list");
  }

  private void showEditStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Student existingStudent = studentDAO.selectStudent(id);
    request.setAttribute("student", existingStudent);
    RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
    dispatcher.forward(request, response);
  }

  private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String status = request.getParameter("status");
    String major = request.getParameter("major");
    Student student = new Student(id, name, status, major);
    studentDAO.updateStudent(student);
    response.sendRedirect(request.getContextPath() +"/student-servlet?action=list");
  }

  private void showStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> students = studentDAO.selectAllStudents();
    request.setAttribute("students", students);
    RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
    dispatcher.forward(request, response);
  }

  private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> students = studentDAO.selectAllStudents();
    request.setAttribute("students", students);
    RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
    dispatcher.forward(request, response);
  }
}
