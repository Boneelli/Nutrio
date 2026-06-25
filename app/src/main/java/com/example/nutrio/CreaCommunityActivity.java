package com.example.nutrio;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
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

        // Conferma creazione community -> apri chat della community appena creata
        Button btnConferma = findViewById(R.id.btnConfermaCreaCommunity);
        EditText etNome = findViewById(R.id.etNomeCommunity);
        EditText etDescrizioneRegole = findViewById(R.id.etDescrizioneRegole);

        if (btnConferma != null) {
            btnConferma.setOnClickListener(v -> {
                String nome = (etNome != null) ? etNome.getText().toString().trim() : "Nuova community";
                if (nome.isEmpty()) nome = "Nuova community";

                // Qui si potrebbe salvare la community nel mock repository. Per ora navighiamo direttamente alla pagina della community.
                Intent intent = new Intent(CreaCommunityActivity.this, DettaglioCommunityActivity.class);
                intent.putExtra("NOME_COMMUNITY", nome);
                String descr = (etDescrizioneRegole != null) ? etDescrizioneRegole.getText().toString().trim() : "";
                intent.putExtra("DESCRIZIONE_COMMUNITY", descr);
                // segnala che la community è stata appena creata
                intent.putExtra("CREATED_SUCCESS", true);
                startActivity(intent);

                // Chiudiamo la schermata di creazione
                finish();
            });
        }
    }
}