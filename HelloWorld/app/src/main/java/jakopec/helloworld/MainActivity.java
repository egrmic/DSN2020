package jakopec.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tekst = findViewById(R.id.tvHello);

        tekst.setText(getString(R.string.pozdrav));

        Button gumb = findViewById(R.id.btnNovaAktinost);
        gumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otvoriNovuAktivnost();

            }
        });

    }

    private void otvoriNovuAktivnost() {

        Intent intent = new Intent(this,DrugaAktivnost.class);
        startActivity(intent);

    }


}
