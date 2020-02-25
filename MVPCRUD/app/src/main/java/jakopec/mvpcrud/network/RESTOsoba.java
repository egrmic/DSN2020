package jakopec.mvpcrud.network;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;
import jakopec.mvpcrud.presenter.CUDOsobaSucelja;
import jakopec.mvpcrud.presenter.ReadOsobaSucelja;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RESTOsoba implements ReadOsobaSucelja.Model, CUDOsobaSucelja.Model {

    OsobeRESTSucelje service;

    public RESTOsoba(){
       service = RetrofitKlijent.getRetrofitInstance().create(OsobeRESTSucelje.class);
    }

    @Override
    public void dohvatiOsobe(ReadOsobaSucelja.Model.PoZavrsetku poZavrsetku) {
        Call<Odgovor> call = service.dohvatiOsobe();
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
                poZavrsetku.zavrsenOdgovor(response.body());
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Odgovor odgovor = new Odgovor();
                odgovor.setGreska(true);
                odgovor.setKljuc(t.getMessage());
                poZavrsetku.zavrsenOdgovor(odgovor);
            }
        });
    }

    @Override
    public void dodajOsobu(CUDOsobaSucelja.Model.PoZavrsetku poZavrsetku, Osoba osoba) {
        Call<Odgovor> call = service.dodajOsobu(osoba);
        odradiPoziv(call, poZavrsetku);
    }

    @Override
    public void promjeniOsobu(CUDOsobaSucelja.Model.PoZavrsetku poZavrsetku, Osoba osoba) {
        Call<Odgovor> call = service.promjeniOsobu(osoba.getId(),osoba);
        odradiPoziv(call, poZavrsetku);
    }

    @Override
    public void obrisiOsobu(CUDOsobaSucelja.Model.PoZavrsetku poZavrsetku, Osoba osoba) {
        Call<Odgovor> call = service.obrisiOsoba(osoba.getId());
        odradiPoziv(call, poZavrsetku);
    }

    private void odradiPoziv(Call<Odgovor> call, CUDOsobaSucelja.Model.PoZavrsetku poZavrsetku){
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
                poZavrsetku.zavrsenOdgovor(response.body());
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Odgovor odgovor = new Odgovor();
                odgovor.setGreska(true);
                odgovor.setKljuc(t.getMessage());
                poZavrsetku.zavrsenOdgovor(odgovor);
            }
        });
    }


}
