package com.example.nutrio;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CreaCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Colleghiamo l'XML che hai già creato!
        setContentView(R.layout.activity_crea_community);

        // Tasto indietro
        ImageView btnBack = findViewById(R.id.btnBackCreaComm);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish()); // Chiude questa schermata e torna indietro
        }
    }
}