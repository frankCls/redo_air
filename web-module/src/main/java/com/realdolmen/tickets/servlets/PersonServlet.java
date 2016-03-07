package com.realdolmen.tickets.servlets;

import com.realdolmen.course.repository.PersonRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/people.html")
public class PersonServlet extends HttpServlet {


    @EJB
    private PersonRepository personRepository;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("People: " + personRepository.findAll());

    }
}
