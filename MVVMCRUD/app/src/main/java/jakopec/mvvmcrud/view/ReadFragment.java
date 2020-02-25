package jakopec.mvvmcrud.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jakopec.mvvmcrud.R;
import jakopec.mvvmcrud.adapter.OsobaAdapter;
import jakopec.mvvmcrud.adapter.OsobaClickListener;
import jakopec.mvvmcrud.model.Odgovor;
import jakopec.mvvmcrud.model.Osoba;
import jakopec.mvvmcrud.viewmodel.OsobaViewModel;

public class ReadFragment extends Fragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    RecyclerView recyclerView;

    OsobaViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,
                container, false);
        ButterKnife.bind(this,view);

        model = ((MainActivity)getActivity()).getModel();

        definirajListu();
        definirajSwipe();

        osvjeziPodatke();

        return view;
    }

    private void osvjeziPodatke(){
        model.dohvatiOsobe().observe(this, new Observer<Odgovor>() {
            @Override
            public void onChanged(@Nullable Odgovor odgovor) {
                ((OsobaAdapter)recyclerView.getAdapter()).setPodaci(odgovor.getOsobe());
                recyclerView.getAdapter().notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                osvjeziPodatke();
            }
        });
    }

    private void definirajListu() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OsobaAdapter(new OsobaClickListener() {
            @Override
            public void onItemClick(Osoba osoba) {
                model.setOsoba(osoba);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void novaOsoba(){
        model.setOsoba(new Osoba());
        ((MainActivity)getActivity()).cud();
    }



}