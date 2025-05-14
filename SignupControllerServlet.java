package jrout.tutorial.springbootservletjsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@WebServlet(name="SignupServlet", urlPatterns="/signupController")
public class SignupControllerServlet extends HttpServlet {
   
    public Boolean emailVar(String email) {
        String query = "SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE EMAIL='" + email + "')";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    public Boolean kullaniciAdiVar(String kullaniciAdi) {
        String query = "SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE KULLANICI_ADI = '" + kullaniciAdi + "')";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        

        String adi = req.getParameter("Adi");
        String soyadi = req.getParameter("Soyadi");
        String kullaniciAdi = req.getParameter("KullaniciAdi");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        String sql = "INSERT INTO kullanici (ADI, SOYADI, KULLANICI_ADI, EMAIL, PASSWORD)";
        sql += " VALUES ('"+ adi + "', '" + soyadi + "', '" + kullaniciAdi + "', '" + email + "', '" + password + "')";
        if(!emailVar(email) && !kullaniciAdiVar(kullaniciAdi)) {
            jdbcTemplate.execute(sql);
            String message = "Başarıyla Oluşturuldu.";
            req.setAttribute("message", message); 
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
        else {
            String message = "Email zaten var.";
            req.setAttribute("message", message); 
            req.getRequestDispatcher("/signup.html").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
