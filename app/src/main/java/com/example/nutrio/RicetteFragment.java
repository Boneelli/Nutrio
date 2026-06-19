package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.control.RecipeGridAdapter;
import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.model.Ricetta;

import java.util.List;

public class RicetteFragment extends Fragment {

    private RecyclerView rvRecipesGrid;
    private RecipeGridAdapter adapter;
    private List<Ricetta> elencoRicette;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gonfia il layout e configura la RecyclerView (conversione dall'Activity originale)
        View view = inflater.inflate(R.layout.fragment_ricette, container, false);

        rvRecipesGrid = view.findViewById(R.id.rvRecipesGrid);

        // Recupera dati dal mock
        elencoRicette = MockDataRepository.getRicette();

        rvRecipesGrid.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new RecipeGridAdapter(getContext(), elencoRicette);
        rvRecipesGrid.setAdapter(adapter);

        return view;
    }
}