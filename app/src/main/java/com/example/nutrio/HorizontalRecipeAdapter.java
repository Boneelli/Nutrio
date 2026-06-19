package com.example.nutrio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.model.Ricetta;

import java.util.List;

public class HorizontalRecipeAdapter extends RecyclerView.Adapter<HorizontalRecipeAdapter.ViewHolder> {

    private final Context context;
    private final List<Ricetta> lista;

    public HorizontalRecipeAdapter(Context context, List<Ricetta> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_recipe_horizontal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ricetta r = lista.get(position);
        holder.tvTitle.setText(r.getTitolo());
        holder.ivPhoto.setImageResource(r.getImmagineResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DettaglioRicettaActivity.class);
            intent.putExtra("RICETTA_SELEZIONATA", r);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return lista.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivRecipeImage);
            tvTitle = itemView.findViewById(R.id.tvRecipeTitle);
        }
    }
}


