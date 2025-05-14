package jrout.tutorial.springbootservletjsp.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@WebServlet(name="USearchServlet", urlPatterns="/UsearchController")
public class USearchControllerServlet extends HttpServlet{
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
