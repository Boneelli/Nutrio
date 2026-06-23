package com.example.nutrio;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_community); // La grafica che abbiamo fatto prima!

        // 1. Tasto per tornare indietro
        ImageView btnBack = findViewById(R.id.btnBackFromChat);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. Recuperiamo il nome che abbiamo cliccato ("Amici", "Amici 2", ecc.)
        String nomeCommunity = getIntent().getStringExtra("NOME_COMMUNITY");

        // 3. Mettiamo il nome in cima alla barra verde della chat
        TextView tvTitle = findViewById(R.id.tvChatHeaderTitle);
        if (tvTitle != null && nomeCommunity != null) {
            tvTitle.setText(nomeCommunity);
        }
    }
}