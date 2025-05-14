package jrout.tutorial.springbootservletjsp.service;

import jrout.tutorial.springbootservletjsp.model.Tarif;

import java.util.List;

public interface ITarifService {
    Tarif getTarif(int TarifId);
    List<Tarif> getKullaniciTarifleri(int KullaniciId); // Kullanıcının yemekleri
    List<Tarif> getYemekTurundenTarifler(String YemekTuru);
}
