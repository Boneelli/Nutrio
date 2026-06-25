package com.example.nutrio.model;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nutrio.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class FiltriBottomSheetFragment extends BottomSheetDialogFragment {

    // 1. Definiamo l'interfaccia ESATTAMENTE come la aspetta la tua Activity
    public interface OnFiltriApplyListener {
        void onApplyFilters(int costo, int difficolta, int tempoMax, String categoria, List<String> preferenze);
    }

    private OnFiltriApplyListener listener;

    // 2. Colleghiamo l'Activity o il Fragment genitore al Fragment quando viene mostrato
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Preferiamo il parentFragment (quando si usa getChildFragmentManager dal Fragment host)
        if (getParentFragment() instanceof OnFiltriApplyListener) {
            listener = (OnFiltriApplyListener) getParentFragment();
        } else if (context instanceof OnFiltriApplyListener) {
            // Fallback all'Activity (compatibilità con la vecchia Activity)
            listener = (OnFiltriApplyListener) context;
        } else {
            // Non forziamo il crash: il BottomSheet può aprirsi anche senza listener (visual only)
            listener = null;
        }
    }

    // 3. Qui andrà collegato il layout XML del tuo pannello dei filtri
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtri_bottom_sheet, container, false);

        // Categoria buttons
        MaterialButton btnCatPrimi = view.findViewById(R.id.btnCatPrimi);
        MaterialButton btnCatSecondi = view.findViewById(R.id.btnCatSecondi);
        MaterialButton btnCatDolci = view.findViewById(R.id.btnCatDolci);

        // Preferenze buttons
        MaterialButton btnPrefVegano = view.findViewById(R.id.btnPrefVegano);
        MaterialButton btnPrefSenzaGlutine = view.findViewById(R.id.btnPrefSenzaGlutine);

        // Applica / Chiudi
        MaterialButton btnApply = view.findViewById(R.id.btnApply);

        // Impostiamo lo stile iniziale non selezionato per tutti i bottoni (sfondo nero, testo bianco)
        MaterialButton[] allButtons = new MaterialButton[]{btnCatPrimi, btnCatSecondi, btnCatDolci, btnPrefVegano, btnPrefSenzaGlutine};
        for (MaterialButton bb : allButtons) {
            if (bb != null) {
                bb.setSelected(false);
                bb.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                bb.setTextColor(Color.WHITE);
                bb.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#708256")));
                bb.setStrokeWidth(2);
            }
        }

        View.OnClickListener toggle = v -> {
            MaterialButton b = (MaterialButton) v;
            boolean now = !b.isSelected();
            b.setSelected(now);
            if (now) {
                b.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#708256")));
                b.setTextColor(Color.WHITE);
                b.setStrokeWidth(0);
            } else {
                b.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                b.setTextColor(Color.WHITE);
                b.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#708256")));
                b.setStrokeWidth(2);
            }
        };

        btnCatPrimi.setOnClickListener(toggle);
        btnCatSecondi.setOnClickListener(toggle);
        btnCatDolci.setOnClickListener(toggle);
        btnPrefVegano.setOnClickListener(toggle);
        btnPrefSenzaGlutine.setOnClickListener(toggle);

        btnApply.setOnClickListener(v -> {
            // Visual-only: chiude il pannello. Se vuoi chiamare l'applicaFiltri esistente, lo può fare, ma non applicherà filtri effettivi.
            applicaFiltri();
        });

        return view;
    }

    // Metodo d'esempio da chiamare quando l'utente clicca su "Applica" nel pannello
    private void applicaFiltri() {
        // Leggiamo lo stato corrente dei bottoni dal View del Fragment (visual only)
        String categoria = "";
        List<String> categorieSelezionate = new ArrayList<>();
        List<String> preferenzeSelezionate = new ArrayList<>();

        View v = getView();
        if (v != null) {
            MaterialButton b1 = v.findViewById(R.id.btnCatPrimi);
            MaterialButton b2 = v.findViewById(R.id.btnCatSecondi);
            MaterialButton b3 = v.findViewById(R.id.btnCatDolci);

            if (b1 != null && b1.isSelected()) categorieSelezionate.add(b1.getText().toString());
            if (b2 != null && b2.isSelected()) categorieSelezionate.add(b2.getText().toString());
            if (b3 != null && b3.isSelected()) categorieSelezionate.add(b3.getText().toString());

            MaterialButton p1 = v.findViewById(R.id.btnPrefVegano);
            MaterialButton p2 = v.findViewById(R.id.btnPrefSenzaGlutine);
            if (p1 != null && p1.isSelected()) preferenzeSelezionate.add(p1.getText().toString());
            if (p2 != null && p2.isSelected()) preferenzeSelezionate.add(p2.getText().toString());
        }

        if (!categorieSelezionate.isEmpty()) {
            categoria = android.text.TextUtils.join(", ", categorieSelezionate);
        }

        if (listener != null) {
            // Invia i valori selezionati (visual-only): costo/difficoltà/tempo impostati a 0
            listener.onApplyFilters(0, 0, 0, categoria, preferenzeSelezionate);
        }

        // Chiude il BottomSheet
        dismiss();
    }
}