package com.example.nutrio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> messaggi;
    // Codici per distinguere i due tipi di bolla
    private static final int VIEW_TYPE_ALTRI = 0;
    private static final int VIEW_TYPE_MIO = 1;

    public ChatAdapter(List<ChatMessage> messaggi) {
        this.messaggi = messaggi;
    }

    // Decide quale layout usare a seconda di chi scrive
    @Override
    public int getItemViewType(int position) {
        if (messaggi.get(position).isMioMessaggio()) {
            return VIEW_TYPE_MIO;
        } else {
            return VIEW_TYPE_ALTRI;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MIO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble_user, parent, false);
            return new MioMessaggioViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble, parent, false);
            return new AltriMessaggioViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage msg = messaggi.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_MIO) {
            MioMessaggioViewHolder mioHolder = (MioMessaggioViewHolder) holder;
            mioHolder.tvTesto.setText(msg.getTesto());
        } else {
            AltriMessaggioViewHolder altriHolder = (AltriMessaggioViewHolder) holder;
            altriHolder.tvTesto.setText(msg.getTesto());
            altriHolder.tvMittente.setText(msg.getMittente());
        }
    }

    @Override
    public int getItemCount() {
        return messaggi.size();
    }

    // Cestino per i TUOI messaggi
    public static class MioMessaggioViewHolder extends RecyclerView.ViewHolder {
        TextView tvTesto;
        public MioMessaggioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTesto = itemView.findViewById(R.id.tvChatMessageUser);
        }
    }

    // Cestino per gli ALTRI messaggi
    public static class AltriMessaggioViewHolder extends RecyclerView.ViewHolder {
        TextView tvTesto;
        TextView tvMittente;
        public AltriMessaggioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTesto = itemView.findViewById(R.id.tvChatMessage);
            tvMittente = itemView.findViewById(R.id.tvMsgSenderName);
        }
    }
}