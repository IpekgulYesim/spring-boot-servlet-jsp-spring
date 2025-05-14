package jrout.tutorial.springbootservletjsp.service;

import java.util.List;

import jrout.tutorial.springbootservletjsp.model.Kullanici;

public interface IKullaniciService {
    Kullanici getKullanici(int KullaniciId);
    List<Kullanici> getKullaniciList(String ad);
    String getPassword(String kullaniciAdi);
    String getAdSoyad(String kullaniciAdi);
    int getIdKUllanici(String KullaniciAdi);
}
