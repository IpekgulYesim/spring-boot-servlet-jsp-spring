package jrout.tutorial.springbootservletjsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import jrout.tutorial.springbootservletjsp.model.Kullanici;
import jrout.tutorial.springbootservletjsp.service.IKullaniciService;

@WebServlet(name="LoginServlet", urlPatterns="/loginController")
public class LoginControllerServlet extends HttpServlet{
    @Autowired
    private IKullaniciService kullaniciService;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kullaniciAdi = req.getParameter("KullaniciAdi");
        String password = req.getParameter("password");
        List<Kullanici> kullaniciList = null;

        Kullanici kullaniciInfo = null;
        if(kullaniciAdi != null && kullaniciAdi.trim().length() > 0 && 
           password != null && password.trim().length() > 0 ) {
            String sifre = kullaniciService.getPassword(kullaniciAdi);

            resp.setContentType("text/html");

            if(password.equals(sifre) ) {
                HttpSession session = req.getSession();
                session.setAttribute("ADI + SOYADI", kullaniciService.getAdSoyad(kullaniciAdi));
                session.setAttribute("KULLANICI-ADI", kullaniciAdi); 
                session.setAttribute("idKULLANICI", kullaniciService.getIdKUllanici(kullaniciAdi));
                session.setMaxInactiveInterval(0);
                resp.sendRedirect("/update.jsp?id=" + kullaniciService.getIdKUllanici(kullaniciAdi) + "&kullanici=" + kullaniciAdi + "&adsoyad=" + kullaniciService.getAdSoyad(kullaniciAdi));
            }
            else
            {
                String message = "Şifre yanlış";
                req.setAttribute("message", message); 
                req.getRequestDispatcher("/login.html").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
