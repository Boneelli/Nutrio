package com.example.nutrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        // 1. Rendiamo cliccabile il tasto "Crea community"
        MaterialCardView btnCrea = view.findViewById(R.id.btnApriCreaCommunity);
        btnCrea.setOnClickListener(v -> {
            // Apriamo la schermata di creazione!
            Intent intent = new Intent(getActivity(), CreaCommunityActivity.class);
            startActivity(intent);
        });

        // 2. Prepariamo la lista (RecyclerView)
        RecyclerView rvLista = view.findViewById(R.id.rvListaCommunity);
        rvLista.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Creiamo un po' di dati finti per vedere se funziona
        List<String> mieCommunity = new ArrayList<>();
        mieCommunity.add("Amici");
        mieCommunity.add("Amici 2");
        mieCommunity.add("Amici 3");
        mieCommunity.add("Amici 4");

        // 4. Colleghiamo i dati alla grafica tramite l'Adapter
        CommunityAdapter adapter = new CommunityAdapter(mieCommunity);
        rvLista.setAdapter(adapter);

        return view;
    }
}