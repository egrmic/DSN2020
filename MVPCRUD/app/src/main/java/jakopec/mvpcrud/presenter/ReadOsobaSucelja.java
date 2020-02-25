package jakopec.mvpcrud.presenter;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;

public interface ReadOsobaSucelja {

    interface Presenter{
        void dohvatiOsobe();
    }

    interface View {
        void zavrsenOdgovor(Odgovor odgovor);
    }

    interface Model {

        void dohvatiOsobe(PoZavrsetku poZavrsetku);

        interface PoZavrsetku {
            void zavrsenOdgovor(Odgovor odgovor);
        }

    }

}
