package com.example.login.subject;

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

@WebServlet("/subject-servlet")
public class SubjectServlet extends HttpServlet {

  private SubjectDAO subjectDAO;

  public void init() {
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
    RequestDispatcher dispatcher;
    try {
      switch (action) {
        case "new":
          showNewSubject(request, response);
          break;
        case "insert":
          insertSubject(request, response);
          break;
        case "delete":
          deleteSubject(request, response);
          break;
        case "edit":
          showEditSubject(request, response);
          break;
        case "update":
          updateSubject(request, response);
          break;
        case "show":
          showSubject(request, response);
          break;
        default:
          listSubject(request, response);
          break;
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }

  private void showNewSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Subject> subjects = subjectDAO.selectAllSubjects();
    request.setAttribute("subjects", subjects);
    RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
    dispatcher.forward(request, response);
  }

  private void insertSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getParameter("name");
    String prerequisite = request.getParameter("prerequisite");
    Subject Subject = new Subject(name, prerequisite);
    subjectDAO.insertSubject(Subject);
    response.sendRedirect(request.getContextPath()+"/subject-servlet?action=list");
  }

  private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    subjectDAO.deleteSubject(id);
    response.sendRedirect(request.getContextPath() +"/subject-servlet?action=list");
  }

  private void showEditSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Subject existingSubject = subjectDAO.selectSubject(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
    request.setAttribute("subject", existingSubject);
    dispatcher.forward(request, response);
  }

  private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String prerequisite = request.getParameter("prerequisite");
    Subject subject = new Subject(id, name, prerequisite);
    subjectDAO.updateSubject(subject);
    response.sendRedirect(request.getContextPath() +"/subject-servlet?action=list");
  }

  private void showSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Subject> Subjects = subjectDAO.selectAllSubjects();
    request.setAttribute("subjects", Subjects);
    RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
    dispatcher.forward(request, response);
  }

  private void listSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Subject> subjects = subjectDAO.selectAllSubjects();
    request.setAttribute("subjects", subjects);
    RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
    dispatcher.forward(request, response);
  }
}
