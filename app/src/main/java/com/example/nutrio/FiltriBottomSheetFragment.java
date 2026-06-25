package com.example.nutrio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

/**
 * BottomSheet per i filtri delle ricette.
 */
public class FiltriBottomSheetFragment extends BottomSheetDialogFragment {

    public interface OnFiltriApplyListener {
        void onApplyFilters(int costo, int difficolta, int tempoMax, String categoria, List<String> preferenze);
    }

    private OnFiltriApplyListener listener;
    
    // Stato del tempo selezionato (0=nessuno, 5=<5min, 10=<10min, 15=<15min, 30=<30min)
    private int selectedTempo = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Prova a ottenere il listener da Context (Activity)
        if (context instanceof OnFiltriApplyListener) {
            listener = (OnFiltriApplyListener) context;
        }
        // Se non è un'Activity con listener, prova a cercare il Fragment padre
        if (listener == null && getParentFragment() instanceof OnFiltriApplyListener) {
            listener = (OnFiltriApplyListener) getParentFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filtri_ricette, container, false);

        // Slider Costo
        Slider sliderCosto = view.findViewById(R.id.sliderCosto);
        sliderCosto.setValueFrom(0f);
        sliderCosto.setValueTo(2f);
        sliderCosto.setStepSize(1f);
        sliderCosto.setValue(0f);

        // Slider Difficoltà
        Slider sliderDifficolta = view.findViewById(R.id.sliderDifficolta);
        sliderDifficolta.setValueFrom(0f);
        sliderDifficolta.setValueTo(2f);
        sliderDifficolta.setStepSize(1f);
        sliderDifficolta.setValue(0f);

        // Bottoni Tempo
        Button btnTempo5 = view.findViewById(R.id.btnTempo5);
        Button btnTempo10 = view.findViewById(R.id.btnTempo10);
        Button btnTempo15 = view.findViewById(R.id.btnTempo15);
        Button btnTempo30 = view.findViewById(R.id.btnTempo30);
        
        // ChipGroup Categoria
        ChipGroup chipGroup = view.findViewById(R.id.chipGroupCategorie);
        
        // ChipGroup Preferenze
        ChipGroup chipGroupPreferenze = view.findViewById(R.id.chipGroupPreferenze);
        
        // Bottoni
        TextView btnReset = view.findViewById(R.id.btnResetFilters);
        MaterialButton btnApply = view.findViewById(R.id.btnApplyFilters);


        // ===== TEMPO BUTTONS =====
        btnTempo5.setOnClickListener(v -> toggleTempoButton(btnTempo5, btnTempo10, btnTempo15, btnTempo30, 5));
        btnTempo10.setOnClickListener(v -> toggleTempoButton(btnTempo10, btnTempo5, btnTempo15, btnTempo30, 10));
        btnTempo15.setOnClickListener(v -> toggleTempoButton(btnTempo15, btnTempo5, btnTempo10, btnTempo30, 15));
        btnTempo30.setOnClickListener(v -> toggleTempoButton(btnTempo30, btnTempo5, btnTempo10, btnTempo15, 30));

        // ===== RESET =====
        btnReset.setOnClickListener(v -> {
            // Reset sliders
            sliderCosto.setValue(0f);
            sliderDifficolta.setValue(0f);
            
            // Reset tempo buttons
            resetTempoButtons(btnTempo5, btnTempo10, btnTempo15, btnTempo30);
            selectedTempo = 0;
            
            // Deseleziona chip categoria
            for (int id : chipGroup.getCheckedChipIds()) {
                Chip c = chipGroup.findViewById(id);
                if (c != null) c.setChecked(false);
            }

            // Deseleziona chip preferenze
            for (int id : chipGroupPreferenze.getCheckedChipIds()) {
                Chip c = chipGroupPreferenze.findViewById(id);
                if (c != null) c.setChecked(false);
            }
        });

        // ===== APPLICA =====
        btnApply.setOnClickListener(v -> {
            int costo = Math.round(sliderCosto.getValue());
            int difficolta = Math.round(sliderDifficolta.getValue());
            
            // Categoria selezionata
            String categoria = "";
            int checkedCategoryId = chipGroup.getCheckedChipId();
            if (checkedCategoryId != -1) {
                Chip checkedChip = chipGroup.findViewById(checkedCategoryId);
                if (checkedChip != null) {
                    categoria = checkedChip.getText().toString();
                }
            }
            
            // Preferenze selezionate
            List<String> preferenze = new ArrayList<>();
            for (int id : chipGroupPreferenze.getCheckedChipIds()) {
                Chip c = chipGroupPreferenze.findViewById(id);
                if (c != null) preferenze.add(c.getText().toString());
            }
            
            if (listener != null) {
                listener.onApplyFilters(costo, difficolta, selectedTempo, categoria, preferenze);
            }
            dismiss();
        });

        return view;
    }

    /**
     * Gestisce il toggle dei bottoni del tempo.
     * Se il bottone è già selezionato, lo deseleziona; altrimenti seleziona solo quello e deseleziona gli altri.
     */
    private void toggleTempoButton(Button selected, Button btn2, Button btn3, Button btn4, int tempoValue) {
        if (selectedTempo == tempoValue) {
            // Deseleziona
            selectedTempo = 0;
            resetTempoButtons(selected, btn2, btn3, btn4);
        } else {
            // Seleziona questo e deseleziona gli altri
            selectedTempo = tempoValue;
            setTempoButtonSelected(selected, true);
            setTempoButtonSelected(btn2, false);
            setTempoButtonSelected(btn3, false);
            setTempoButtonSelected(btn4, false);
        }
    }

    private void setTempoButtonSelected(Button btn, boolean selected) {
        if (selected) {
            // Selezionato: sfondo verde #708256, testo bianco
            btn.setBackgroundColor(0xFF708256);
            btn.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
        } else {
            // Deselezionato: sfondo grigio chiaro, testo grigio
            btn.setBackgroundColor(0xFFF0F0F0);
            btn.setTextColor(0xFF666666);
        }
    }

    private void resetTempoButtons(Button btn1, Button btn2, Button btn3, Button btn4) {
        setTempoButtonSelected(btn1, false);
        setTempoButtonSelected(btn2, false);
        setTempoButtonSelected(btn3, false);
        setTempoButtonSelected(btn4, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}


