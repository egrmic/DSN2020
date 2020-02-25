package jakopec.mvvmcrud;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.junit.Test;

import jakopec.mvvmcrud.adapter.OsobaAdapter;
import jakopec.mvvmcrud.model.Odgovor;
import jakopec.mvvmcrud.model.Osoba;
import jakopec.mvvmcrud.network.OsobeRESTSucelje;
import jakopec.mvvmcrud.network.RetrofitKlijent;
import jakopec.mvvmcrud.viewmodel.OsobaViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OsobaUnitTest {

    @Test
    public void citanje_isCorrect() {
        System.out.println("Krenuo");
        OsobeRESTSucelje service= RetrofitKlijent.getRetrofitInstance().create(OsobeRESTSucelje.class);
        Call<Odgovor> call = service.dohvatiOsobe();
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
                System.out.println("Vratio se: " + response.body().isGreska());
                assertTrue(response.body().getOsobe().size()>0);
                gotov();
            }
            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                assertTrue(false);
            }
        });


    }

    private void gotov(){
        System.out.println("Gotov");
    }

    @Test
    public void dodavanje_isCorrect() {

        OsobeRESTSucelje service= RetrofitKlijent.getRetrofitInstance().create(OsobeRESTSucelje.class);
        Osoba o = new Osoba();
        o.setIme("Test");
        o.setPrezime("Test");
        Call<Odgovor> call = service.dodajOsobu(o);
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
               if (response.body().isGreska()){
                   fail();
               }else{
                   assertTrue(true);
               }
            }
            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                fail();
            }
        });


    }


}