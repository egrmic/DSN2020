package jakopec.mvpcrud.presenter;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;
import jakopec.mvpcrud.network.RESTOsoba;

public class CUDOsobaPresenter implements CUDOsobaSucelja.Presenter, CUDOsobaSucelja.Model.PoZavrsetku {

    private CUDOsobaSucelja.View view;
    private CUDOsobaSucelja.Model model;
    private Osoba osoba;

    public CUDOsobaPresenter(CUDOsobaSucelja.View view, Osoba osoba) {
        this.view = view;
        this.osoba = osoba;
        this.model = new RESTOsoba();
    }

    @Override
    public Osoba getOsoba() {
        return osoba;
    }

    @Override
    public void setIme(String ime) {
        osoba.setIme(ime);
    }

    @Override
    public void setPrezime(String prezime) {
        osoba.setPrezime(prezime);
    }


    @Override
    public void dodajOsobu() {
        model.dodajOsobu(this, osoba);
    }

    @Override
    public void promjeniOsobu() {
        model.promjeniOsobu(this, osoba);
    }

    @Override
    public void obrisiOsobu() {
        model.obrisiOsobu(this, osoba);
    }

    @Override
    public void zavrsenOdgovor(Odgovor odgovor) {
        view.zavrsenOdgovor(odgovor);
    }

}
