package jrout.tutorial.springbootservletjsp.dao;

import java.util.List;

import jrout.tutorial.springbootservletjsp.model.Kullanici;

public interface IKullaniciDAO {
    Kullanici getKullanici(int KullaniciId);
    List<Kullanici> getKullaniciList(String ad);
    String getPassword(String KullaniciAdi);
    String getAdSoyad(String kullaniciAdi);
    int getIdKUllanici(String KullaniciAdi);
}

