package com.example.nutrio.control;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrio.R;
import com.example.nutrio.model.Ricetta;

public class DettaglioRicettaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_ricetta);

        // Gestione del bottone per tornare indietro
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Recupera i dati passati dall'adapter
        Ricetta ricetta = (Ricetta) getIntent().getSerializableExtra("RICETTA_SELEZIONATA");

        if (ricetta != null) {
            TextView tvTitolo = findViewById(R.id.tvDettaglioTitolo);
            ImageView ivFoto = findViewById(R.id.ivDettaglioFoto);
            RatingBar ratingBar = findViewById(R.id.ratingBar);

            TextView tvTempo = findViewById(R.id.tvDettaglioTempo);
            TextView tvCosto = findViewById(R.id.tvDettaglioCosto);
            TextView tvPortata = findViewById(R.id.tvDettaglioPortata);
            TextView tvRegimi = findViewById(R.id.tvDettaglioRegimi);
            TextView tvIngredienti = findViewById(R.id.tvDettaglioIngredienti);
            LinearLayout containerProcedimento = findViewById(R.id.containerProcedimento);

            // Popolamento dati base
            tvTitolo.setText(ricetta.getTitolo());
            ivFoto.setImageResource(ricetta.getImmagineResId());
            ratingBar.setRating((float) ricetta.getPunteggio());

            // Impostazione testi dentro i cerchietti verdi
            tvTempo.setText(ricetta.getTempoPrep().replace(" ", "\n"));
            tvCosto.setText(ricetta.getCosto() + "\ncost");
            tvPortata.setText(ricetta.getPortata());

            if (!ricetta.getRegimeAlimentare().isEmpty()) {
                tvRegimi.setText(ricetta.getRegimeAlimentare().get(0)); // Mostra il primo regime
            } else {
                tvRegimi.setVisibility(android.view.View.GONE);
            }

            // Elenco Ingredienti strutturato (Quantità + Unità + Nome)
            StringBuilder sbIngredients = new StringBuilder();
            for (Ricetta.Ingrediente ing : ricetta.getIngredienti()) {
                sbIngredients.append(ing.getQuantita() > 0 ? (int)ing.getQuantita() + " " : "")
                        .append(ing.getUnitaMisura()).append("   ")
                        .append(ing.getNome()).append("\n");
            }
            tvIngredienti.setText(sbIngredients.toString());

            // CREAZIONE DINAMICA DEI PASSAGGI CON NUMERO CIRCOLARE VERDE
            int stepCounter = 1;
            for (String passaggio : ricetta.getPassaggi()) {
                // Layout orizzontale per il singolo step (Numero + Testo)
                LinearLayout rowLayout = new LinearLayout(this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                rowLayout.setPadding(0, 0, 0, 24);

                // Il cerchietto con il numero
                TextView tvNumber = new TextView(this);
                tvNumber.setText(String.valueOf(stepCounter));
                tvNumber.setTextColor(getResources().getColor(android.R.color.white));
                tvNumber.setBackgroundResource(R.drawable.bg_circle_tag); // Riutilizziamo la forma tonda
                tvNumber.setGravity(Gravity.CENTER);

                // Dimensione del cerchietto numerico piccolo (28dp x 28dp)
                int pixelDim = (int) (28 * getResources().getDisplayMetrics().density);
                LinearLayout.LayoutParams numParams = new LinearLayout.LayoutParams(pixelDim, pixelDim);
                numParams.setMargins(0, 4, 16, 0);
                tvNumber.setLayoutParams(numParams);

                // Il testo del passaggio
                TextView tvStepText = new TextView(this);
                tvStepText.setText(passaggio);
                tvStepText.setTextColor(getResources().getColor(android.R.color.black));
                tvStepText.setTextSize(14);
                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tvStepText.setLayoutParams(textParams);

                // Unisce i componenti
                rowLayout.addView(tvNumber);
                rowLayout.addView(tvStepText);
                containerProcedimento.addView(rowLayout);

                stepCounter++;
            }
        }
    }
}