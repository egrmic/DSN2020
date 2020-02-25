package jakopec.mvpcrud.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jakopec.mvpcrud.R;
import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.model.Osoba;
import jakopec.mvpcrud.presenter.CUDOsobaPresenter;
import jakopec.mvpcrud.presenter.CUDOsobaSucelja;

public class CUDActivity extends AppCompatActivity implements CUDOsobaSucelja.View {

    @BindView(R.id.ime)
    EditText ime;
    @BindView(R.id.prezime)
    EditText prezime;
    @BindView(R.id.slika)
    ImageView slika;

    @BindView(R.id.novaOsoba)
    Button novaOsoba;
    @BindView(R.id.promjenaOsoba)
    Button promjenaOsoba;
    @BindView(R.id.obrisiOsoba)
    Button obrisiOsoba;

    private CUDOsobaSucelja.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cud);
        ButterKnife.bind(this);
        definirajDogadajeNaUnosnimKomponentama();
        Intent intent = getIntent();
        presenter = new CUDOsobaPresenter(this, (Osoba) intent.getSerializableExtra(
                "osoba"));
        if (presenter.getOsoba().getId() == 0) {
            definirajNovaOsoba();
            return;
        }
        definirajPromjenaBrisanjeOsoba();
    }

    private void definirajDogadajeNaUnosnimKomponentama() {
        ime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.setIme(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        prezime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.setPrezime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void definirajPromjenaBrisanjeOsoba() {
        novaOsoba.setVisibility(View.GONE);
        ime.setText(presenter.getOsoba().getIme());
        prezime.setText(presenter.getOsoba().getPrezime());
        Picasso.get().load(presenter.getOsoba().getUrlSlika()).into(slika);

        promjenaOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.promjeniOsobu();
            }
        });

        obrisiOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.obrisiOsobu();
            }
        });

    }

    private void definirajNovaOsoba() {
        promjenaOsoba.setVisibility(View.GONE);
        obrisiOsoba.setVisibility(View.GONE);
        novaOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.dodajOsobu();
            }
        });
    }


    //2 sat
    private void nazad(boolean ok) {
        setResult(ok ? MainActivity.OK : MainActivity.GRESKA, null);
        finish();
    }


    @Override
    public void zavrsenOdgovor(Odgovor odgovor) {
        nazad(!odgovor.isGreska());
    }
}
