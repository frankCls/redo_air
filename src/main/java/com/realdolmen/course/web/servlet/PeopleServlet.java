package com.realdolmen.course.web.servlet;

import com.realdolmen.course.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/people.html")
public class PeopleServlet extends HttpServlet {
    @EJB
    private PersonServiceBean personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("people", personService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/people.jsp").forward(request, response);
    }
}
