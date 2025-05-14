package jrout.tutorial.springbootservletjsp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import jrout.tutorial.springbootservletjsp.dao.IKullaniciDAO;
import jrout.tutorial.springbootservletjsp.model.Kullanici;


@Component
public class KullaniciDAOImpl implements IKullaniciDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class KullaniciRowMapper implements RowMapper < Kullanici > {
        @Override
        public Kullanici mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kullanici kullanici = new Kullanici();
            kullanici.setidKULLANICI(rs.getInt("idKullanici"));
            kullanici.setADI(rs.getString("ADI"));
            kullanici.setSOYADI(rs.getString("SOYADI"));
            kullanici.setEMAIL(rs.getString("email"));
            kullanici.setKULLANICI_ADI(rs.getString("KULLANICI_ADI"));
            kullanici.setPassword(rs.getString("password"));
            return kullanici;
        }
    }


    public Kullanici getKullanici(int idKullanici) {
        return jdbcTemplate.queryForObject("select * from Kullanici where idKULLANICI=?", new Object[] {idKullanici},
        new BeanPropertyRowMapper<Kullanici> (Kullanici.class));
    }

    public List < Kullanici > getKullaniciList(String ADI) {
        return jdbcTemplate.query("select * from Kullanici where ADI = '" + ADI + "'",  new KullaniciRowMapper());
    }

    public String getPassword(String KullaniciAdi) {
        return jdbcTemplate.queryForObject("SELECT password FROM kullanici where KULLANICI_ADI = '" + KullaniciAdi + "'", String.class);
    }

    public Boolean emailVar(String email) {
        String query = "SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE EMAIL='" + email + "')";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    public Boolean kullaniciAdiVar(String kullaniciAdi) {
        String query = "SELECT EXISTS(SELECT idKULLANICI FROM kullanici WHERE KULLANICI_ADI = '" + kullaniciAdi + "')";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    public String getAdSoyad(String KullaniciAdi) {
        String query = "SELECT CONCAT(ADI, '_', SOYADI) FROM kullanici WHERE KULLANICI_ADI = " + "'" + KullaniciAdi + "'";
        return jdbcTemplate.queryForObject(query, String.class);
    }

    @SuppressWarnings("null")
    public int getIdKUllanici(String KullaniciAdi) {
        String query = "SELECT idKULLANICI FROM kullanici WHERE KULLANICI_ADI = '" + KullaniciAdi + "'";
        return jdbcTemplate.queryForObject(query, int.class);
    }
}
