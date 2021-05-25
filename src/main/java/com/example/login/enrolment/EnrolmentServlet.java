package com.example.login.enrolment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/enrolment-servlet")
public class EnrolmentServlet extends HttpServlet {

  private EnrolmentDAO enrolmentDAO;

  public void init() {
    enrolmentDAO = new EnrolmentDAO();
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
  }

  private void showNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    request.setAttribute("enrolments", enrolments);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    dispatcher.forward(request, response);
  }

  private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int studentId = Integer.parseInt(request.getParameter("student_id"));
    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
    Enrolment enrolment = new Enrolment(studentId, subjectId);
    enrolmentDAO.insertEnrolment(enrolment);
    response.sendRedirect(request.getContextPath()+"/enrolment-servlet?action=list");
  }

  private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    enrolmentDAO.deleteEnrolment(id);
    response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
  }

  private void showEditStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Enrolment enrolment = enrolmentDAO.selectEnrolment(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    request.setAttribute("enrolment", enrolment);
    dispatcher.forward(request, response);
  }

  private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    int studentId = Integer.parseInt(request.getParameter("student_id"));
    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
    Enrolment enrolment = new Enrolment(id, studentId, subjectId);
    enrolmentDAO.updateEnrolment(enrolment);
    response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
  }

  private void showStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    request.setAttribute("enrolments", enrolments);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    dispatcher.forward(request, response);
  }

  private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    request.setAttribute("enrolments", enrolments);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-list.jsp");
    dispatcher.forward(request, response);
  }
}
