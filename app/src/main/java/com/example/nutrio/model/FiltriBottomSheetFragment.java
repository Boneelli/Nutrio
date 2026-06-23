package com.example.nutrio.model;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class FiltriBottomSheetFragment extends BottomSheetDialogFragment {

    // 1. Definiamo l'interfaccia ESATTAMENTE come la aspetta la tua Activity
    public interface OnFiltriApplyListener {
        void onApplyFilters(int costo, int difficolta, int tempoMax, String categoria, List<String> preferenze);
    }

    private OnFiltriApplyListener listener;

    // 2. Colleghiamo l'Activity al Fragment quando viene mostrato
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFiltriApplyListener) {
            listener = (OnFiltriApplyListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " deve implementare OnFiltriApplyListener");
        }
    }

    // 3. Qui andrà collegato il layout XML del tuo pannello dei filtri
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Se hai già un XML per i filtri, inseriscilo qui. Esempio:
        // View view = inflater.inflate(R.layout.fragment_filtri_bottom_sheet, container, false);
        // return view;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // Metodo d'esempio da chiamare quando l'utente clicca su "Applica" nel pannello
    private void applicaFiltri() {
        if (listener != null) {
            // Passiamo i dati letti dalla UI alla tua RicetteScopriActivity
            List<String> preferenzeSelezionate = new ArrayList<>();
            preferenzeSelezionate.add("Vegano"); // Esempio

            listener.onApplyFilters(1, 2, 30, "Primi", preferenzeSelezionate);
            dismiss(); // Chiude il Bottom Sheet
        }
    }
}