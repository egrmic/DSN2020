package jakopec.mvpcrud.network;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OsobeRESTSucelje {

    @GET("osobe")
    Call<Odgovor> dohvatiOsobe();

    @POST("osobe")
    Call<Odgovor> dodajOsobu(@Body Osoba o);

    @PUT("osobe/{id}")
    Call<Odgovor> promjeniOsobu(@Path("id") int id, @Body Osoba o);

    @DELETE("osobe/{id}")
    Call<Odgovor> obrisiOsoba(@Path("id") int id);
}


