package jrout.tutorial.springbootservletjsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import jrout.tutorial.springbootservletjsp.model.Kullanici;
import jrout.tutorial.springbootservletjsp.service.IKullaniciService;

@WebServlet(name = "Servlet" , urlPatterns = {"/kullaniciController"})
public class KullaniciControllerServlet extends HttpServlet {

    @Autowired
    private IKullaniciService kullaniciService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ad = req.getParameter("ADI");
        String KullaniciId = req.getParameter("KullaniciId");
        String kullaniciAdi = req.getParameter("KullaniciAdi");
        String password = req.getParameter("password");
        List<Kullanici> kullaniciList = null;

        Kullanici kullaniciInfo = null;
        if(kullaniciAdi != null && kullaniciAdi.trim().length() > 0 && 
           password != null && password.trim().length() > 0 ) {
            String sifre = kullaniciService.getPassword(kullaniciAdi);

            if( password.equals(sifre) ) {
                String message = "Şifre doğru";
                req.setAttribute("message", message); // This will be available as ${message}
                req.getRequestDispatcher("/sonuc.jsp").forward(req, resp);
            }
            else
            {
                String message = "Şifre yanlış";
                req.setAttribute("message", message); // This will be available as ${message}
                req.getRequestDispatcher("/sonuc.jsp").forward(req, resp);
            }
        
        }else if(KullaniciId != null && KullaniciId.trim().length() > 0) {
            kullaniciInfo = kullaniciService.getKullanici(Integer.parseInt(KullaniciId));
            req.setAttribute("kullanici",kullaniciInfo);
            RequestDispatcher kullaniciView = req.getRequestDispatcher("/kullaniciView.jsp");
            kullaniciView.include(req,resp);
        }else if(ad != null && ad.trim().length() > 0) {
            kullaniciList = kullaniciService.getKullaniciList(ad);
            req.setAttribute("kullaniciList",kullaniciList);

            RequestDispatcher kullaniciListView = req.getRequestDispatcher("kullaniciListView");
            kullaniciListView.forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
