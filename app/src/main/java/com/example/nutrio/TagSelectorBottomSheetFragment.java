package com.example.nutrio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagSelectorBottomSheetFragment extends BottomSheetDialogFragment {

    public interface OnTagsApplyListener {
        void onApplyTags(List<String> selectedTags);
    }

    private OnTagsApplyListener listener;
    private Map<String, Boolean> selectedTags = new HashMap<>();

    // Tag per categoria
    private static final String[] TEMPO = {"10 min", "15 min", "30 min"};
    private static final String[] DIFFICOLTA = {"Facile", "Medio", "Difficile"};
    private static final String[] COSTO = {"Basso", "Medio", "Alto"};
    private static final String[] PORTATA = {"Colazione", "Primo", "Secondo", "Dessert"};
    private static final String[] PREFERENZE = {"Vegano", "Vegetariano", "Senza glutine", "Senza lattosio"};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Prova a ottenere il listener da Activity o Fragment genitore
        if (context instanceof OnTagsApplyListener) {
            listener = (OnTagsApplyListener) context;
        } else if (getParentFragment() instanceof OnTagsApplyListener) {
            listener = (OnTagsApplyListener) getParentFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tag_selector, container, false);

        // Inizializza i tag come non selezionati
        initializeTagsState();

        // Popola le griglie
        populateGrid(view, R.id.gridTempo, TEMPO);
        populateGrid(view, R.id.gridDifficolta, DIFFICOLTA);
        populateGrid(view, R.id.gridCosto, COSTO);
        populateGrid(view, R.id.gridPortata, PORTATA);
        populateGrid(view, R.id.gridPreferenze, PREFERENZE);

        // Reset button
        view.findViewById(R.id.btnResetTags).setOnClickListener(v -> resetAllTags());

        // Apply button
        view.findViewById(R.id.btnApplyTags).setOnClickListener(v -> applyTags());

        return view;
    }

    private void initializeTagsState() {
        for (String tag : TEMPO) selectedTags.put(tag, false);
        for (String tag : DIFFICOLTA) selectedTags.put(tag, false);
        for (String tag : COSTO) selectedTags.put(tag, false);
        for (String tag : PORTATA) selectedTags.put(tag, false);
        for (String tag : PREFERENZE) selectedTags.put(tag, false);
    }

    private void populateGrid(View parentView, int gridId, String[] tags) {
        GridLayout grid = parentView.findViewById(gridId);
        grid.removeAllViews();

        for (String tagName : tags) {
            Button btnTag = (Button) LayoutInflater.from(getContext()).inflate(R.layout.item_tag_circle, grid, false);
            btnTag.setText(tagName);
            btnTag.setSelected(false);
            // Inizialmente grigio e testo nero
            btnTag.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));

            btnTag.setOnClickListener(v -> {
                boolean isNowSelected = !btnTag.isSelected();
                btnTag.setSelected(isNowSelected);
                selectedTags.put(tagName, isNowSelected);

                // Aggiorna il colore del testo in base allo stato
                if (isNowSelected) {
                    // Verde: testo bianco
                    btnTag.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
                } else {
                    // Grigio: testo nero
                    btnTag.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
                }
            });

            grid.addView(btnTag);
        }
    }

    private void resetAllTags() {
        selectedTags.replaceAll((k, v) -> false);

        // Deseleziona tutti i bottoni
        View view = getView();
        if (view != null) {
            deselectAllButtons(view.findViewById(R.id.gridTempo));
            deselectAllButtons(view.findViewById(R.id.gridDifficolta));
            deselectAllButtons(view.findViewById(R.id.gridCosto));
            deselectAllButtons(view.findViewById(R.id.gridPortata));
            deselectAllButtons(view.findViewById(R.id.gridPreferenze));
        }
    }

    private void deselectAllButtons(ViewGroup grid) {
        if (grid == null) return;
        for (int i = 0; i < grid.getChildCount(); i++) {
            View child = grid.getChildAt(i);
            if (child instanceof Button) {
                Button btn = (Button) child;
                btn.setSelected(false);
                btn.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
            }
        }
    }

    private void applyTags() {
        List<String> selectedTagList = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : selectedTags.entrySet()) {
            if (entry.getValue()) {
                selectedTagList.add(entry.getKey());
            }
        }

        if (listener != null) {
            listener.onApplyTags(selectedTagList);
        }

        dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}

