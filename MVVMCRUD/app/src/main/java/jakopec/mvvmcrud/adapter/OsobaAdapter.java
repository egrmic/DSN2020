package jakopec.mvvmcrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jakopec.mvvmcrud.R;
import jakopec.mvvmcrud.model.Osoba;

public class OsobaAdapter extends RecyclerView.Adapter<OsobaAdapter.Red> {

    private List<Osoba> podaci;
    private OsobaClickListener osobaClickListener;

    public OsobaAdapter(OsobaClickListener osobaClickListener) {
        this.osobaClickListener=osobaClickListener;
    }

    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(roditelj.getContext());
        View view = layoutInflater.inflate(R.layout.red_liste, roditelj, false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(Red red, int position) {
        Osoba o = podaci.get(position);
        red.imePrezime.setText(o.getIme() + " " + o.getPrezime());
        Picasso.get().load(o.getUrlSlika()).into(red.slika);
        red.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                osobaClickListener.onItemClick(o); //build.gradle compiler options VERSION_1_8
            }
        });
    }

    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }

    public void setPodaci(List<Osoba> osobe) {
        this.podaci = osobe;
    }



    public class Red extends RecyclerView.ViewHolder{
        private ImageView slika;
        private TextView imePrezime;
        Red(View itemView) {
            super(itemView);
            slika=itemView.findViewById(R.id.slika);
            imePrezime = itemView.findViewById(R.id.imePrezime);
        }
    }



}
