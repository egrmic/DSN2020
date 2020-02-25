package jakopec.mvvmcrud.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jakopec.mvvmcrud.R;
import jakopec.mvvmcrud.adapter.OsobaAdapter;
import jakopec.mvvmcrud.model.Odgovor;
import jakopec.mvvmcrud.model.Osoba;
import jakopec.mvvmcrud.viewmodel.OsobaViewModel;

public class CUDFragment extends Fragment {

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

    OsobaViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this,view);
        model = ((MainActivity)getActivity()).getModel();

        if (model.getOsoba().getId() == 0) {
            definirajNovaOsoba();
            return view;
        }
        definirajPromjenaBrisanjeOsoba();

        return view;
    }

    private void definirajPromjenaBrisanjeOsoba() {
        novaOsoba.setVisibility(View.GONE);
        ime.setText(model.getOsoba().getIme());
        prezime.setText(model.getOsoba().getPrezime());
        Picasso.get().load(model.getOsoba().getUrlSlika()).into(slika);

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

    private void promjenaOsoba(){
        model.getOsoba().setIme(ime.getText().toString());
        model.getOsoba().setPrezime(prezime.getText().toString());
        model.promjeniOsobu().observe(this, new Observer<Odgovor>() {
            @Override
            public void onChanged(@Nullable Odgovor odgovor) {
                if(!odgovor.isGreska()){
                    nazad();
                }else{
                    Toast.makeText(getContext(), odgovor.getKljuc(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void definirajNovaOsoba() {
        promjenaOsoba.setVisibility(View.GONE);
        obrisiOsoba.setVisibility(View.GONE);
        novaOsoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaOsoba();
            }
        });
    }

    private void novaOsoba(){
        model.getOsoba().setIme(ime.getText().toString());
        model.getOsoba().setPrezime(prezime.getText().toString());
        model.dodajOsobu().observe(this, new Observer<Odgovor>() {
            @Override
            public void onChanged(@Nullable Odgovor odgovor) {
                if(!odgovor.isGreska()){
                    nazad();
                }else{
                    Toast.makeText(getContext(), odgovor.getKljuc(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void obrisiOsoba(){
        model.getOsoba().setIme(ime.getText().toString());
        model.getOsoba().setPrezime(prezime.getText().toString());
        model.obrisiOsobu().observe(this, new Observer<Odgovor>() {
            @Override
            public void onChanged(@Nullable Odgovor odgovor) {
                if(!odgovor.isGreska()){
                    nazad();
                }else{
                    Toast.makeText(getContext(), odgovor.getKljuc(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick(R.id.nazad)
    public void nazad(){
        ((MainActivity)getActivity()).read();
    }



}