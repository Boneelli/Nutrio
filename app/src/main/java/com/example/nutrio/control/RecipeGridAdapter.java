package com.example.nutrio.control;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.R;
import com.example.nutrio.model.Ricetta;

import java.util.List;

public class RecipeGridAdapter extends RecyclerView.Adapter<RecipeGridAdapter.RecipeViewHolder> {

    private final Context context;
    private final List<Ricetta> listaRicette;

    // Costruttore che riceve il contesto e la lista dei dati del Mock
    public RecipeGridAdapter(Context context, List<Ricetta> listaRicette) {
        this.context = context;
        this.listaRicette = listaRicette;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Collega il file XML della singola tessera (sostituisci item_recipe_grid con il tuo nome effettivo)
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe_grid, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Prende la ricetta corrente in base alla posizione della griglia
        Ricetta ricettaCorrente = listaRicette.get(position);

        // Popola la grafica con i dati del Mock
        holder.tvTitolo.setText(ricettaCorrente.getTitolo());
        holder.ivFoto.setImageResource(ricettaCorrente.getImmagineResId());
        holder.tvRating.setText(String.valueOf(ricettaCorrente.getPunteggio()));

        // --- GESTIONE DEL CLICK ---
        // Quando l'utente tocca una ricetta, l'app avvia la schermata di dettaglio passando l'oggetto intero
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DettaglioRicettaActivity.class);
            intent.putExtra("RICETTA_SELEZIONATA", ricettaCorrente); // Impacchetta la ricetta
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRicette.size(); // Dice ad Android quante tessere deve disegnare
    }

    // Classe interna che trova e aggancia i componenti grafici della singola tessera
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvTitolo;
        TextView tvRating;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivRecipeImage);
            tvTitolo = itemView.findViewById(R.id.tvRecipeTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}