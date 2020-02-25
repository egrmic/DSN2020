package jakopec.mvpcrud.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jakopec.mvpcrud.R;
import jakopec.mvpcrud.adapter.OsobaAdapter;
import jakopec.mvpcrud.adapter.OsobaClickListener;
import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;
import jakopec.mvpcrud.presenter.ReadOsobaPresenter;
import jakopec.mvpcrud.presenter.ReadOsobaSucelja;

public class MainActivity extends AppCompatActivity implements ReadOsobaSucelja.View {

    public static final int CUD=1;
    public static final int OK=2;
    public static final int GRESKA=3;

    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista) RecyclerView recyclerView;


    private ReadOsobaSucelja.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        definirajSwipe();
        definirajListu();


        presenter = new ReadOsobaPresenter(this);

        swipeRefreshLayout.setRefreshing(true);
        presenter.dohvatiOsobe();
    }

    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.dohvatiOsobe();
            }
        });

    }

    private void definirajListu() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OsobaAdapter(new OsobaClickListener() {
            @Override
            public void onItemClick(Osoba osoba) {
                detalji(osoba);
            }
        }));
    }

    @OnClick(R.id.fab)
    public void novaOsoba(){
        detalji(new Osoba());
    }


    private void detalji(Osoba osoba){
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
                presenter.dohvatiOsobe();
                break;
            case GRESKA:
                Toast.makeText(this, "Dogodila se pogreška, akcija nije izvedena", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    @Override
    public void zavrsenOdgovor(Odgovor odgovor) {
        swipeRefreshLayout.setRefreshing(false);
        if(odgovor.isGreska()){
            Toast.makeText(MainActivity.this,
                    odgovor.getKljuc(),
                    Toast.LENGTH_LONG).show();
            return;
        }
        Log.wtf("Odgovor: ", String.valueOf(odgovor.getOsobe().size()));
        ((OsobaAdapter)recyclerView.getAdapter()).setPodaci(odgovor.getOsobe());
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
