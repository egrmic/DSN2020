package jakopec.mvpcrud.presenter;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.network.RESTOsoba;

public class ReadOsobaPresenter implements ReadOsobaSucelja.Presenter, ReadOsobaSucelja.Model.PoZavrsetku {

    private ReadOsobaSucelja.View view;
    private ReadOsobaSucelja.Model model;


    public ReadOsobaPresenter(ReadOsobaSucelja.View view) {
        this.view = view;
        this.model = new RESTOsoba();
    }

    @Override
    public void dohvatiOsobe() {
        model.dohvatiOsobe(this);
    }

    @Override
    public void zavrsenOdgovor(Odgovor odgovor) {
        view.zavrsenOdgovor(odgovor);
    }

}
