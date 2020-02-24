package jakopec.alati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetaljiActivity extends AppCompatActivity {

    @BindView(R.id.id) TextView id;
    @BindView(R.id.ime) TextView ime;
    @BindView(R.id.prezime) TextView prezime;
    @BindView(R.id.slika) ImageView slika;
    @BindView(R.id.nazad) Button nazad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Osoba osoba = (Osoba)intent.getSerializableExtra(
                "osoba");
        id.setText(String.valueOf(osoba.getId()));
        ime.setText(osoba.getIme());
        prezime.setText(osoba.getPrezime());

        Picasso.get().load(osoba.getUrlSlika()).into(slika);

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });

    }

    private void nazad(){
        finish();
    }

}
