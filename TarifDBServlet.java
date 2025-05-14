package jrout.tutorial.springbootservletjsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@WebServlet(name = "TarifDBServlet", urlPatterns = "/tarifDBController")
public class TarifDBServlet extends HttpServlet {

    private final String INSERT_SQL = "INSERT INTO tarifler(TARIF_ADI,YEMEK_TURU,idKULLANICI) values(?,?,?)";
    String sql;
    String message;

    @Autowired 
    JdbcTemplate jdbcTemplate;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String KullaniciId = req.getSession().getId() ;
        String YemekAdi = req.getParameter("YemekAdi");
        String YemekTuru = req.getParameter("YemekTuru");
        String Yapilisi = req.getParameter("Yapilisi");
        String TarifId = req.getParameter("TarifId");
        String islem = req.getParameter("islem");

        switch(islem) {
            case "Kaydet":
                KeyHolder holder = new GeneratedKeyHolder();
		        jdbcTemplate.update(new PreparedStatementCreator() {
			
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                    
                    ps.setString(1, YemekAdi);
                    ps.setString(2, YemekTuru);
                    ps.setString(3, KullaniciId);

                    return ps;
                    }
                }, holder);

                int yeniTarifId = holder.getKey().intValue();
                req.setAttribute("TarifId", yeniTarifId);
        
                message = "Başarıyla Oluşturuldu.";
                req.setAttribute("message", message); 
                req.getRequestDispatcher("/tarif.html").forward(req, resp);

                break;
                
            case "Guncelle":
                sql = "UPDATE tarifler ";
                sql += "YEMEK_ADI='" + YemekAdi + "',";
                sql += "YEMEK_TURU='" + YemekTuru + "',";
                sql += "YAPILISI='" + Yapilisi + "' ";
                sql += "WHERE idTARIFLER=" + TarifId;

                jdbcTemplate.execute(sql);

                message = "Başarıyla güncellendi.";
                req.setAttribute("message", message); 
                req.getRequestDispatcher("/tarif.html").forward(req, resp);

                break;

            case "Sil":
                sql = "DELETE FROM tarifler WHERE idTARIFLER=" + TarifId;
                jdbcTemplate.execute(sql);
                message = "Başarıyla silindi.";
                req.setAttribute("message", message); 
                req.getRequestDispatcher("/tarif.html").forward(req, resp);

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
