package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.control.HorizontalRecipeAdapter;
import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.model.Ricetta;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Collega la grafica del file fragment_home.xml
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        try {
            RecyclerView rvConsigliate = view.findViewById(R.id.rvConsigliate);
            RecyclerView rvPreferite = view.findViewById(R.id.rvPreferite);

            List<Ricetta> all = MockDataRepository.getRicette();
            if (all == null) all = new ArrayList<>();

            // Per semplicità: consigliate = primi 6, preferite = ultimi 6 (se disponibili)
            List<Ricetta> consigliate = new ArrayList<>();
            List<Ricetta> preferite = new ArrayList<>();

            for (int i = 0; i < all.size(); i++) {
                if (i < 6) consigliate.add(all.get(i));
                if (i >= Math.max(0, all.size() - 6)) preferite.add(all.get(i));
            }

            if (rvConsigliate != null) {
                rvConsigliate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvConsigliate.setAdapter(new HorizontalRecipeAdapter(getContext(), consigliate));
            } else {
                Log.w("HomeFragment", "rvConsigliate non trovato");
            }

            if (rvPreferite != null) {
                rvPreferite.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvPreferite.setAdapter(new HorizontalRecipeAdapter(getContext(), preferite));
            } else {
                Log.w("HomeFragment", "rvPreferite non trovato");
            }

        } catch (Exception e) {
            Log.e("HomeFragment", "Errore inizializzazione RecyclerView Home", e);
        }

        return view;
    }
}