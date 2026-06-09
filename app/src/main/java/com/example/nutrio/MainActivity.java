package com.example.nutrio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private TextView textRicette, textCrea, textHome, textCommunity, textImpostazioni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Collegamento dei layout cliccabili dei pulsanti nella navbar custom
        LinearLayout btnRicette = findViewById(R.id.btnNavRicette);
        LinearLayout btnCrea = findViewById(R.id.btnNavCrea);
        LinearLayout btnHome = findViewById(R.id.btnNavHome);
        LinearLayout btnCommunity = findViewById(R.id.btnNavCommunity);
        LinearLayout btnImpostazioni = findViewById(R.id.btnNavImpostazioni);

        // Collegamento dei TextView per poter modificare lo stato attivo/inattivo
        textRicette = findViewById(R.id.textNavRicette);
        textCrea = findViewById(R.id.textNavCrea);
        textHome = findViewById(R.id.textNavHome);
        textCommunity = findViewById(R.id.textNavCommunity);
        textImpostazioni = findViewById(R.id.textNavImpostazioni);

        // Carica la schermata Home di default all'avvio dell'app
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), R.id.btnNavHome);
        }

        // Gestione dei click per cambiare frammento
        btnHome.setOnClickListener(v -> loadFragment(new HomeFragment(), R.id.btnNavHome));

        // NOTA: Sostituirai "new Fragment()" con i tuoi reali fragment appena li crei
        btnRicette.setOnClickListener(v -> loadFragment(new Fragment(), R.id.btnNavRicette));
        btnCrea.setOnClickListener(v -> loadFragment(new Fragment(), R.id.btnNavCrea));
        btnCommunity.setOnClickListener(v -> loadFragment(new Fragment(), R.id.btnNavCommunity));
        btnImpostazioni.setOnClickListener(v -> loadFragment(new Fragment(), R.id.btnNavImpostazioni));
    }

    // Funzione centralizzata per scambiare i frammenti all'interno del FrameLayout
    private void loadFragment(Fragment fragment, int clickedId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        updateNavUI(clickedId);
    }

    // Funzione grafica per evidenziare in verde il testo della pagina in cui ci si trova
    private void updateNavUI(int activeId) {
        int colorInattivo = Color.parseColor("#757575");
        int colorAttivo = Color.parseColor("#708256");

        TextView[] testi = {textRicette, textCrea, textHome, textCommunity, textImpostazioni};
        for (TextView t : testi) {
            if (t != null) {
                t.setTextColor(colorInattivo);
                t.setTypeface(null, Typeface.NORMAL);
            }
        }

        if (activeId == R.id.btnNavRicette && textRicette != null) {
            textRicette.setTextColor(colorAttivo);
            textRicette.setTypeface(null, Typeface.BOLD);
        } else if (activeId == R.id.btnNavCrea && textCrea != null) {
            textCrea.setTextColor(colorAttivo);
            textCrea.setTypeface(null, Typeface.BOLD);
        } else if (activeId == R.id.btnNavHome && textHome != null) {
            textHome.setTextColor(colorAttivo);
            textHome.setTypeface(null, Typeface.BOLD);
        } else if (activeId == R.id.btnNavCommunity && textCommunity != null) {
            textCommunity.setTextColor(colorAttivo);
            textCommunity.setTypeface(null, Typeface.BOLD);
        } else if (activeId == R.id.btnNavImpostazioni && textImpostazioni != null) {
            textImpostazioni.setTextColor(colorAttivo);
            textImpostazioni.setTypeface(null, Typeface.BOLD);
        }
    }
}