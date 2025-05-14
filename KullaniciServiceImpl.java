package jrout.tutorial.springbootservletjsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jrout.tutorial.springbootservletjsp.dao.IKullaniciDAO;
import jrout.tutorial.springbootservletjsp.model.Kullanici;
import jrout.tutorial.springbootservletjsp.service.IKullaniciService;

@Service
public class KullaniciServiceImpl implements IKullaniciService {

    @Autowired
    private IKullaniciDAO KullaniciDAO;

    @Override
    public Kullanici getKullanici(int KullaniciId) {
        Kullanici Kullanici = KullaniciDAO.getKullanici(KullaniciId);
        return Kullanici;
    }

    @Override
    public List<Kullanici> getKullaniciList(String ad) {
        List<Kullanici> KullaniciList = KullaniciDAO.getKullaniciList(ad);
        return KullaniciList;
    }

    @Override
    public String getPassword(String kullaniciAdi) {
        return KullaniciDAO.getPassword(kullaniciAdi);
    }

    @Override
    public String getAdSoyad(String kullaniciAdi) {
        return KullaniciDAO.getAdSoyad(kullaniciAdi);
    }

    @Override
    public int getIdKUllanici(String KullaniciAdi) {
        return KullaniciDAO.getIdKUllanici(KullaniciAdi);
    }
}
