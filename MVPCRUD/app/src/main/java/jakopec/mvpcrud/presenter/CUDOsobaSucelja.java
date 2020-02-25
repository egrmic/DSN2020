package jakopec.mvpcrud.presenter;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;

public interface CUDOsobaSucelja {

    interface Presenter{

        Osoba getOsoba();
        void setIme(String ime);
        void setPrezime(String prezime);

        void dodajOsobu();
        void promjeniOsobu();
        void obrisiOsobu();
    }

    interface View {
        void zavrsenOdgovor(Odgovor odgovor);
    }

    interface Model {
        interface PoZavrsetku {
            void zavrsenOdgovor(Odgovor odgovor);
        }
        void dodajOsobu(PoZavrsetku poZavrsetku, Osoba osoba);
        void promjeniOsobu(PoZavrsetku poZavrsetku, Osoba osoba);
        void obrisiOsobu(PoZavrsetku poZavrsetku, Osoba osoba);
    }

}
