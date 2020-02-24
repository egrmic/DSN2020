package jakopec.alati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CUDActivity extends AppCompatActivity {

    @BindView(R.id.ime) EditText ime;
    @BindView(R.id.prezime) EditText prezime;
    @BindView(R.id.slika) ImageView slika;
    Osoba osoba;

    @BindView(R.id.novaOsoba) Button novaOsoba;
    @BindView(R.id.promjenaOsoba) Button promjenaOsoba;
    @BindView(R.id.obrisiOsoba) Button obrisiOsoba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cud);

        ButterKnife.bind(this);

        Intent intent = getIntent();

         boolean novi = intent.getBooleanExtra("novi",false);
         if(novi){
             promjenaOsoba.setVisibility(View.GONE);
             obrisiOsoba.setVisibility(View.GONE);
             novaOsoba.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     novaosoba();
                 }
             });
             return;
         }


             novaOsoba.setVisibility(View.GONE);
             osoba = (Osoba)intent.getSerializableExtra(
                     "osoba");
             ime.setText(osoba.getIme());
             prezime.setText(osoba.getPrezime());
             Picasso.get().load(osoba.getUrlSlika()).into(slika);

         //2 sat
        promjenaOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promjenaOsoba();
            }
        });

        obrisiOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisiOsoba();
            }
        });


    }


    private void novaosoba() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.REST_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OsobeSucelje osobeSucelje = retrofit.create(OsobeSucelje.class);
        osoba = new Osoba();
        osoba.setIme(ime.getText().toString());
        osoba.setPrezime(prezime.getText().toString());
        Call<Odgovor> call = osobeSucelje.dodajOsobu(osoba);
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {

                Odgovor o = response.body();
                nazad(!o.isGreska());
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Log.wtf("greska",t);
                nazad(false);
            }
        });
    }


    private void promjenaOsoba() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.REST_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OsobeSucelje osobeSucelje = retrofit.create(OsobeSucelje.class);

        osoba.setIme(ime.getText().toString());
        osoba.setPrezime(prezime.getText().toString());
        Call<Odgovor> call = osobeSucelje.promjeniOsobu(osoba.getId(),osoba);
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {

                Odgovor o = response.body();
                nazad(!o.isGreska());
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Log.wtf("greska",t);
                nazad(false);
            }
        });
    }

    private void obrisiOsoba() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.REST_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OsobeSucelje osobeSucelje = retrofit.create(OsobeSucelje.class);
        Call<Odgovor> call = osobeSucelje.obrisiOsoba(osoba.getId());
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {

                Odgovor o = response.body();
                nazad(!o.isGreska());
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Log.wtf("greska",t);
                nazad(false);
            }
        });
    }


    private void nazad(boolean ok){
        setResult(ok ? MainActivity.OK : MainActivity.GRESKA, null);
        finish();
    }
}
