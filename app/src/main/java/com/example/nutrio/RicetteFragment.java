package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.model.FiltriBottomSheetFragment;
import com.example.nutrio.model.Ricetta;

import java.util.List;

public class RicetteFragment extends Fragment implements FiltriBottomSheetFragment.OnFiltriApplyListener {

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

        // Configura il click listener per l'icona filtri
        ImageView ivFilterIcon = view.findViewById(R.id.ivFilterIcon);
        if (ivFilterIcon != null) {
            ivFilterIcon.setOnClickListener(v -> {
                FiltriBottomSheetFragment filtriFragment = new FiltriBottomSheetFragment();
                filtriFragment.show(getChildFragmentManager(), "filtri_ricette");
            });
        }

        return view;
    }

    @Override
    public void onApplyFilters(int costo, int difficolta, int tempoMax, String categoria, List<String> preferenze) {
        // TODO: Filtrare elencoRicette in base ai parametri e aggiornare adapter
        Log.d("Filtri", "Costo: " + costo + ", Difficoltà: " + difficolta +
              ", Tempo: " + tempoMax + ", Categoria: " + categoria + ", Preferenze: " + preferenze);
        
        // Esempio di filtraggio (da implementare secondo la tua logica):
        // List<Ricetta> filtered = filterRicette(elencoRicette, costo, difficolta, tempoMax, categoria, preferenze);
        // adapter.updateData(filtered);
    }
}