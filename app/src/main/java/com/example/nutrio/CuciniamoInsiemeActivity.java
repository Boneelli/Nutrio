package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrio.model.Ricetta;

import java.util.ArrayList;
import java.util.List;

public class CuciniamoInsiemeActivity extends AppCompatActivity {

    private static final int VIEW_TYPE_BOT = 0;
    private static final int VIEW_TYPE_USER = 1;

    private static class ChatMessage {
        String text;
        boolean isBot;
        ChatMessage(String text, boolean isBot) {
            this.text = text;
            this.isBot = isBot;
        }
    }

    private RecyclerView rvChat;
    private ChatAdapter adapter;
    private List<ChatMessage> messaggi = new ArrayList<>();
    private Ricetta ricetta;
    private int stepAttuale = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuciniamo_insieme);

        ricetta = (Ricetta) getIntent().getSerializableExtra("RICETTA_SELEZIONATA");

        rvChat = findViewById(R.id.rvChat);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(messaggi);
        rvChat.setAdapter(adapter);

        // Pulsazione Nutria
        startPulsingEffect();

        // Bottone indietro con conferma
        findViewById(R.id.btnBackChat).setOnClickListener(v -> mostraDialogConfermaUscita());

        // Logica click OVUNQUE per avanzare
        View.OnClickListener clickListener = v -> avanzaRicetta();
        findViewById(R.id.rootLayoutChat).setOnClickListener(clickListener);
        findViewById(R.id.flNutriaContainer).setOnClickListener(clickListener);
        // Anche la RecyclerView deve passare i click al parent se non intercettati
        rvChat.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                avanzaRicetta();
            }
            return false;
        });

        // Messaggio iniziale
        aggiungiMessaggio("Ciao! Sono la tua nutria assistente. Sei pronta a cucinare " + (ricetta != null ? ricetta.getTitolo() : "questa ricetta") + "?", true);
    }

    private void avanzaRicetta() {
        if (ricetta == null) return;
        
        // Se non siamo all'inizio, aggiungiamo il feedback dell'utente
        if (stepAttuale >= -1 && stepAttuale < ricetta.getPassaggi().size()) {
            if (stepAttuale >= 0 || messaggi.size() > 2) {
                 aggiungiMessaggio("Ok fatto", false);
            }
        }

        stepAttuale++;
        if (stepAttuale < ricetta.getPassaggi().size()) {
            aggiungiMessaggio("Passaggio " + (stepAttuale + 1) + ": " + ricetta.getPassaggi().get(stepAttuale), true);
        } else if (stepAttuale == ricetta.getPassaggi().size()) {
            aggiungiMessaggio("Ottimo lavoro! Abbiamo finito la ricetta. Buon appetito!", true);
            findViewById(R.id.rootLayoutChat).setClickable(false);
            findViewById(R.id.flNutriaContainer).setClickable(false);
        }
    }

    private void aggiungiMessaggio(String testo, boolean isBot) {
        messaggi.add(new ChatMessage(testo, isBot));
        adapter.notifyItemInserted(messaggi.size() - 1);
        rvChat.scrollToPosition(messaggi.size() - 1);
    }

    private void startPulsingEffect() {
        View pulseView = findViewById(R.id.vPulse);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f, 0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        pulseView.startAnimation(scaleAnimation);
        pulseView.startAnimation(alphaAnimation);
    }

    private void mostraDialogConfermaUscita() {
        new AlertDialog.Builder(this)
                .setTitle("Abbandonare la cucina?")
                .setMessage("Se torni indietro ora, i tuoi progressi in questa ricetta verranno persi.")
                .setPositiveButton("Esci", (dialog, which) -> finish())
                .setNegativeButton("Resta", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        mostraDialogConfermaUscita();
    }

    // Adapter migliorato per gestire due tipi di bolle
    private static class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<ChatMessage> items;

        ChatAdapter(List<ChatMessage> items) { this.items = items; }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).isBot ? VIEW_TYPE_BOT : VIEW_TYPE_USER;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_BOT) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble, parent, false);
                return new BotViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bubble_user, parent, false);
                return new UserViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            String text = items.get(position).text;
            if (holder instanceof BotViewHolder) {
                ((BotViewHolder) holder).tvMessage.setText(text);
            } else {
                ((UserViewHolder) holder).tvMessage.setText(text);
            }
        }

        @Override
        public int getItemCount() { return items.size(); }

        static class BotViewHolder extends RecyclerView.ViewHolder {
            TextView tvMessage;
            BotViewHolder(View itemView) {
                super(itemView);
                tvMessage = itemView.findViewById(R.id.tvChatMessage);
            }
        }

        static class UserViewHolder extends RecyclerView.ViewHolder {
            TextView tvMessage;
            UserViewHolder(View itemView) {
                super(itemView);
                tvMessage = itemView.findViewById(R.id.tvChatMessageUser);
            }
        }
    }
}