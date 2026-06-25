package com.example.nutrio;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DettaglioCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_community);

        ImageView btnBack = findViewById(R.id.btnBackInfoComm);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                String nome = getIntent().getStringExtra("NOME_COMMUNITY");
                android.content.Intent intent = new android.content.Intent(DettaglioCommunityActivity.this, ChatCommunityActivity.class);
                intent.putExtra("NOME_COMMUNITY", nome);
                startActivity(intent);
                finish();
            });
        }

        String nome = getIntent().getStringExtra("NOME_COMMUNITY");
        String descr = getIntent().getStringExtra("DESCRIZIONE_COMMUNITY");

        TextView tvNome = findViewById(R.id.tvInfoCommNome);
        TextView tvDescr = findViewById(R.id.tvInfoDescText);
        if (tvNome != null && nome != null) tvNome.setText(nome);
        if (tvDescr != null && descr != null && !descr.isEmpty()) tvDescr.setText(descr);

        // Se veniamo dalla creazione, mostriamo un messaggio centrato temporaneo
        boolean justCreated = getIntent().getBooleanExtra("CREATED_SUCCESS", false);
        if (justCreated) {
            TextView tvOverlay = findViewById(R.id.tvCreatedSuccessOverlay);
            if (tvOverlay != null) {
                // Porta il TextView in primo piano e dagli elevazione per sovrapporsi a eventuali view
                tvOverlay.bringToFront();
                tvOverlay.setElevation(40f);

                tvOverlay.setAlpha(0f);
                tvOverlay.setVisibility(View.VISIBLE);
                tvOverlay.animate().alpha(1f).setDuration(200).start();
                tvOverlay.postDelayed(() -> tvOverlay.animate().alpha(0f).setDuration(300).withEndAction(() -> tvOverlay.setVisibility(View.GONE)).start(), 1500);
            }
        }

        RecyclerView rv = findViewById(R.id.rvPartecipanti);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Mock membri
        List<String> membri = new ArrayList<>();
        membri.add("Chiara");
        membri.add("Luigi");
        membri.add("Sara");
        membri.add("Marco");

        ParticipantAdapter adapter = new ParticipantAdapter(membri);
        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        String nome = getIntent().getStringExtra("NOME_COMMUNITY");
        android.content.Intent intent = new android.content.Intent(DettaglioCommunityActivity.this, ChatCommunityActivity.class);
        intent.putExtra("NOME_COMMUNITY", nome);
        startActivity(intent);
        finish();
    }
}
