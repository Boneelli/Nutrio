package com.example.nutrio;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.chip.ChipGroup;

import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.FiltriBottomSheetFragment;
import com.example.nutrio.model.Ricetta;

import java.util.List;
import android.util.Log;

public class RicetteScopriActivity extends AppCompatActivity
        implements FiltriBottomSheetFragment.OnFiltriApplyListener {

    private RecyclerView rvRecipesGrid;
    private RecipeGridAdapter adapter;
    private List<Ricetta> elencoRicette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ricette);

        // 1. Collega la RecyclerView dall'XML
        rvRecipesGrid = findViewById(R.id.rvRecipesGrid);

        // 2. Recupera i dati statici dal "database simulato" del tuo Mock
        elencoRicette = MockDataRepository.getRicette();

        // 3. Configura la griglia a 2 colonne (GridLayoutManager)
        rvRecipesGrid.setLayoutManager(new GridLayoutManager(this, 2));

        // 4. Inizializza l'Adapter passandogli i dati e assegnalo alla RecyclerView
        adapter = new RecipeGridAdapter(this, elencoRicette);
        rvRecipesGrid.setAdapter(adapter);

        // Apri i filtri al click sull'icona nella search bar
        ImageView ivFilter = findViewById(R.id.ivFilterIcon);
        if (ivFilter != null) {
            ivFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Rimuove i chip visivi quando si aprono i filtri
                    ChipGroup cg = findViewById(R.id.cgSelectedFilters);
                    if (cg != null) cg.removeAllViews();

                    FiltriBottomSheetFragment fragment = new FiltriBottomSheetFragment();
                    fragment.show(getSupportFragmentManager(), "filtri_ricette");
                }
            });
        }
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