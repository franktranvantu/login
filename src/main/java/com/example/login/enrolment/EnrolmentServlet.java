package com.example.login.enrolment;

import com.example.login.student.Student;
import com.example.login.student.StudentDAO;
import com.example.login.subject.Subject;
import com.example.login.subject.SubjectDAO;

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
  private StudentDAO studentDAO;
  private SubjectDAO subjectDAO;

  public void init() {
    enrolmentDAO = new EnrolmentDAO();
    studentDAO = new StudentDAO();
    subjectDAO = new SubjectDAO();
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
        showNewEnrolment(request, response);
        break;
      case "insert":
        insertEnrolment(request, response);
        break;
      case "delete":
        deleteEnrolment(request, response);
        break;
      case "edit":
        showEditEnrolment(request, response);
        break;
      case "update":
        updateEnrolment(request, response);
        break;
      case "show":
        showEnrolment(request, response);
        break;
      default:
        listEnrolment(request, response);
        break;
    }
  }

  private void showNewEnrolment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    List<Student> students = studentDAO.selectAllStudents();
    List<Subject> subjects = subjectDAO.selectAllSubjects();
    request.setAttribute("enrolments", enrolments);
    request.setAttribute("students", students);
    request.setAttribute("subjects", subjects);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    dispatcher.forward(request, response);
  }

  private void insertEnrolment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int studentId = Integer.parseInt(request.getParameter("student_id"));
    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
    boolean isEnrolValid = studentDAO.isEnrolValid(studentId, subjectId);
    if (isEnrolValid) {
      Enrolment enrolment = new Enrolment(studentId, subjectId);
      enrolmentDAO.insertEnrolment(enrolment);
    } else {
      request.setAttribute("error", "Student does not complete prerequisite subject");
    }
    response.sendRedirect(request.getContextPath()+"/enrolment-servlet?action=list");
  }

  private void deleteEnrolment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    enrolmentDAO.deleteEnrolment(id);
    response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
  }

  private void showEditEnrolment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Enrolment enrolment = enrolmentDAO.selectEnrolment(id);
    request.setAttribute("enrolment", enrolment);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    dispatcher.forward(request, response);
  }

  private void updateEnrolment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    int studentId = Integer.parseInt(request.getParameter("student_id"));
    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
    Enrolment enrolment = new Enrolment(id, studentId, subjectId);
    enrolmentDAO.updateEnrolment(enrolment);
    response.sendRedirect(request.getContextPath() +"/enrolment-servlet?action=list");
  }

  private void showEnrolment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    request.setAttribute("enrolments", enrolments);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-form.jsp");
    dispatcher.forward(request, response);
  }

  private void listEnrolment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Enrolment> enrolments = enrolmentDAO.selectAllEnrolments();
    request.setAttribute("enrolments", enrolments);
    RequestDispatcher dispatcher = request.getRequestDispatcher("enrolment-list.jsp");
    dispatcher.forward(request, response);
  }
}
