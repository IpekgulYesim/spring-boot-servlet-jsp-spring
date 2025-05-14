package jrout.tutorial.springbootservletjsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@WebServlet(name = "DBServlet" , urlPatterns = {"/kullaniciDBController"})
public class KullaniciDBServlet extends HttpServlet {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Boolean emailVar(String email) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE EMAIL ='" + email + "')", Boolean.class);
    }

    private Boolean kullaniciAdiVar(String kulllaniciAdi) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE KULLANICI_ADI ='" + kulllaniciAdi + "')", Boolean.class);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String islem = req.getParameter("islem");
        String KullaniciId = req.getParameter("KullaniciId");
        String adi = req.getParameter("ADI");
        String soyadi = req.getParameter("SOYADI");
        String kullaniciAdi = req.getParameter("kullaniciAdi");
        String email = req.getParameter("EMAIL");
        String password = req.getParameter("password");
        String sql = "";

        switch(islem) {
            case "Ekle":
                if( emailVar(email) )
                { 
                    
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/user_info.html?Hata='emaili '" + email +"' olan kayıt zaten var.'");
                    dispatcher.forward(req, resp);
                }
                else if( kullaniciAdiVar(kullaniciAdi))
                {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/user_info.html?Hata='kullanıcı adi '" + kullaniciAdi +"' olan kayıt zaten var.'");
                    dispatcher.forward(req, resp);                    
                }
                else 
                {
                    sql = "INSERT INTO kullanici (ADI, SOYADI, KULLANICI_ADI, EMAIL, PASSWORD) ";
                    sql += "VALUES ('" + adi + "', '" + soyadi + "', '" + kullaniciAdi + "', '" + email + "', '" + password + "')";
                    jdbcTemplate.execute(sql);

                }
                break;

            case "Guncelle":
                sql = "UPDATE kullanici SET ADI = '" + adi + "', SOYADI = '" + soyadi + "' ";
                sql += ", KULLANICI_ADI = '" + kullaniciAdi + "', EMAIL = '" + email + "' ";
                sql += ", password = '" + password + "' ";
                sql += "WHERE idKULLANICI = " + KullaniciId;
                jdbcTemplate.execute(sql);
                break;

            case "Sil":
                sql = "DELETE FROM kullanici WHERE idKULLANICI = " + KullaniciId; 
                jdbcTemplate.execute(sql);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
