package jakopec.mvvmcrud.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import jakopec.mvvmcrud.model.Odgovor;
import jakopec.mvvmcrud.model.Osoba;
import jakopec.mvvmcrud.network.OsobeRESTSucelje;
import jakopec.mvvmcrud.network.RetrofitKlijent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OsobaViewModel extends ViewModel {

    private MutableLiveData<Odgovor> odgovor;
    private Osoba osoba;
    private OsobeRESTSucelje service;

    public OsobaViewModel(){
        super();
        odgovor = new MutableLiveData<>();
        service = RetrofitKlijent.getRetrofitInstance().create(OsobeRESTSucelje.class);
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public LiveData<Odgovor> dohvatiOsobe() {
        Call<Odgovor> call = service.dohvatiOsobe();
        odradi(call);
        return odgovor;
    }

    public LiveData<Odgovor> dodajOsobu(){
        Call<Odgovor> call = service.dodajOsobu(osoba);
        odradi(call);
        return odgovor;
    }

    public LiveData<Odgovor> promjeniOsobu(){
        Call<Odgovor> call = service.promjeniOsobu(osoba.getId(),osoba);
        odradi(call);
        return odgovor;
    }

    public LiveData<Odgovor> obrisiOsobu(){
        Call<Odgovor> call = service.obrisiOsoba(osoba.getId());
        odradi(call);
        return odgovor;
    }



    private void odradi(Call<Odgovor> call){
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
                odgovor.postValue(response.body());
            }
            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Odgovor o = new Odgovor();
                o.setGreska(true);
                o.setKljuc(t.getMessage());
                odgovor.postValue(o);
            }
        });
    }


}
