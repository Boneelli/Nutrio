package com.example.nutrio;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.R;
import com.example.nutrio.control.RecipeGridAdapter;
import com.example.nutrio.data.MockDataRepository;
import com.example.nutrio.model.Ricetta;

import java.util.List;

public class RicetteScopriActivity extends AppCompatActivity {

    private RecyclerView rvRecipesGrid;
    private RecipeGridAdapter adapter;
    private List<Ricetta> elencoRicette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerca_ricette);

        // 1. Collega la RecyclerView dall'XML
        rvRecipesGrid = findViewById(R.id.rvRecipesGrid);

        // 2. Recupera i dati statici dal "database simulato" del tuo Mock
        elencoRicette = MockDataRepository.getRicette();

        // 3. Configura la griglia a 2 colonne (GridLayoutManager)
        rvRecipesGrid.setLayoutManager(new GridLayoutManager(this, 2));

        // 4. Inizializza l'Adapter passandogli i dati e assegnalo alla RecyclerView
        adapter = new RecipeGridAdapter(this, elencoRicette);
        rvRecipesGrid.setAdapter(adapter);
    }
}