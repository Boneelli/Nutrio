package com.example.nutrio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<String> communityList;

    public CommunityAdapter(List<String> communityList) {
        this.communityList = communityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Peschiamo la grafica della singola riga che hai creato
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nome = communityList.get(position);
        holder.tvNome.setText(nome);

        holder.itemView.setOnClickListener(v -> {
            // Prepariamo l'Intent per aprire l'Activity della Chat
            android.content.Intent intent = new android.content.Intent(v.getContext(), ChatCommunityActivity.class);
            // Passiamo il nome della community (es. "Amici") alla schermata successiva
            intent.putExtra("NOME_COMMUNITY", nome);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return communityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Colleghiamo l'ID del testo del tuo item_row_community.xml
            tvNome = itemView.findViewById(R.id.tvCommunityName);
        }
    }
}