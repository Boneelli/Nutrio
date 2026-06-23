package com.example.nutrio;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_community);

        // 1. Tasto per tornare indietro e Titolo
        ImageView btnBack = findViewById(R.id.btnBackFromChat);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        String nomeCommunity = getIntent().getStringExtra("NOME_COMMUNITY");
        TextView tvTitle = findViewById(R.id.tvChatHeaderTitle);
        if (tvTitle != null && nomeCommunity != null) tvTitle.setText(nomeCommunity);

        // 2. Configura la lista (RecyclerView)
        RecyclerView rvChat = findViewById(R.id.rvChatCommunityMessages);
        rvChat.setLayoutManager(new LinearLayoutManager(this));

        // 3. Creiamo la finta conversazione (uguale al tuo mockup!)
        List<ChatMessage> fintaChat = new ArrayList<>();
        fintaChat.add(new ChatMessage("Ciao ragazzi, oggi ho preparato una nuova ricetta... andatela a vedere sul mio profilo!", "Chiara", false));
        fintaChat.add(new ChatMessage("Ciao Sara! sembra squisita!", "Tu", true));
        fintaChat.add(new ChatMessage("L'ho trovata sul ricettario di mia nonna", "Tu", true));
        fintaChat.add(new ChatMessage("Io ci aggiungerei un po' di pepe!", "Luigi", false));
        fintaChat.add(new ChatMessage("Ahahah, potrebbe essere una buona idea!", "Tu", true));

        // 4. Colleghiamo i messaggi all'Adapter
        ChatAdapter adapter = new ChatAdapter(fintaChat);
        rvChat.setAdapter(adapter);
    }
}