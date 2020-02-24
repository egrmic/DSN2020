package jakopec.alati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickListener {

    @BindView(R.id.lista)
    RecyclerView recyclerView;

    @BindString(R.string.REST_URL)
    String REST_URL;

    AdapterListe adapter;


    public static final int CUD=1;
    public static final int OK=2;
    public static final int GRESKA=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        definirajKomponente();

        ucitaj();

    }

    private void definirajKomponente(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterListe(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.dodajNovi)
    public void kreirajNovi() {
        Intent intent = new Intent(
                this,CUDActivity.class);
        intent.putExtra("novi",true);
        startActivityForResult(intent,1);
    }

    private void ucitaj(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OsobeSucelje osobeSucelje = retrofit.create(OsobeSucelje.class);
        Call<Odgovor> call = osobeSucelje.dohvatiOsobe();
        call.enqueue(new Callback<Odgovor>() {
            @Override
            public void onResponse(Call<Odgovor> call, Response<Odgovor> response) {
                if(response.body()==null){
                    return;
                }

                adapter.setPodaci(response.body().getOsobe());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Odgovor> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nešto nije u redu",
                        Toast.LENGTH_LONG).show();
                Log.wtf("Greška", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Osoba osoba = adapter.getItem(position);

        Intent intent = new Intent(
                this,CUDActivity.class);
        intent.putExtra("osoba",osoba);
        startActivityForResult(intent,CUD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case OK:
                ucitaj();
                break;
            case GRESKA:
                Toast.makeText(this, "Dogodila se pogreška, akcija nije izvedena", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


