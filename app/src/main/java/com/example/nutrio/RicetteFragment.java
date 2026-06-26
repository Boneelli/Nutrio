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
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.FiltriBottomSheetFragment;
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
                // Rimuove i chip visivi quando si aprono i filtri
                ChipGroup cg = view.findViewById(R.id.cgSelectedFilters);
                if (cg != null) cg.removeAllViews();

                FiltriBottomSheetFragment filtriFragment = new FiltriBottomSheetFragment();
                filtriFragment.show(getChildFragmentManager(), "filtri_ricette");
            });
        }

        return view;
    }

    @Override
    public void onApplyFilters(int costo, int difficolta, int tempoMax, String categoria, List<String> preferenze) {
        // Mostriamo visivamente i filtri selezionati sopra la griglia (solo UI)
        View root = getView();
        if (root == null) return;

        ChipGroup cg = root.findViewById(R.id.cgSelectedFilters);
        if (cg == null) return;

        cg.removeAllViews();

        if (categoria != null && !categoria.isEmpty()) {
            Chip chip = new Chip(getContext());
            chip.setText(categoria);
            chip.setClickable(false);
            chip.setCloseIconVisible(false);
            chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#708256")));
            chip.setTextColor(Color.WHITE);
            cg.addView(chip);
        }

        if (preferenze != null) {
            for (String p : preferenze) {
                Chip chip = new Chip(getContext());
                chip.setText(p);
                chip.setClickable(false);
                chip.setCloseIconVisible(false);
                // preferenze chips: sfondo verde e testo bianco per coerenza
                chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#708256")));
                chip.setTextColor(Color.WHITE);
                cg.addView(chip);
            }
        }

        // Log per debug
        Log.d("Filtri", "Applicati (UI): Categoria=" + categoria + ", Preferenze=" + preferenze);
    }
}