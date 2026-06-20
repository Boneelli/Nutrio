package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nutrio.model.Ricetta;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class RemixRicettaActivity extends AppCompatActivity {

    private Ricetta ricettaOriginale;
    private ChipGroup chipGroupTags;
    private LinearLayout containerIngredienti;
    private LinearLayout containerProcedimento;
    private List<String> currentTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remix_ricetta);

        ricettaOriginale = (Ricetta) getIntent().getSerializableExtra("RICETTA_SELEZIONATA");

        setupToolbar();

        // Riferimenti UI
        EditText etTitolo = findViewById(R.id.etRemixTitolo);
        EditText etTempo = findViewById(R.id.etRemixTempo);
        EditText etCosto = findViewById(R.id.etRemixCosto);
        chipGroupTags = findViewById(R.id.chipGroupTags);
        containerIngredienti = findViewById(R.id.containerIngredienti);
        containerProcedimento = findViewById(R.id.containerProcedimento);

        if (ricettaOriginale != null) {
            etTitolo.setText(ricettaOriginale.getTitolo() + " (Remix)");
            etTempo.setText(ricettaOriginale.getTempoPrep());
            etCosto.setText(ricettaOriginale.getCosto());

            for (String tag : ricettaOriginale.getRegimeAlimentare()) {
                addTagToGroup(tag);
            }

            for (Ricetta.Ingrediente ing : ricettaOriginale.getIngredienti()) {
                String desc = (ing.getQuantita() > 0 ? (int)ing.getQuantita() + " " : "") + ing.getUnitaMisura() + " " + ing.getNome();
                addItemToList(containerIngredienti, desc);
            }

            for (String p : ricettaOriginale.getPassaggi()) {
                addItemToList(containerProcedimento, p);
            }
        }

        findViewById(R.id.btnAddTag).setOnClickListener(v -> showAddTagDialog());
        findViewById(R.id.btnAddIngrediente).setOnClickListener(v -> addItemToList(containerIngredienti, ""));
        findViewById(R.id.btnAddPassaggio).setOnClickListener(v -> addItemToList(containerProcedimento, ""));

        findViewById(R.id.btnSalvaRemix).setOnClickListener(v -> {
            Toast.makeText(this, "RICETTA PUBBLICATA CON SUCCESSO!", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarRemix);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void addItemToList(LinearLayout container, String text) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_remix_list, container, false);
        EditText et = itemView.findViewById(R.id.etItemText);
        et.setText(text);
        
        itemView.findViewById(R.id.btnRemoveItem).setOnClickListener(v -> container.removeView(itemView));
        
        container.addView(itemView);
    }

    private void addTagToGroup(String tagText) {
        if (tagText.isEmpty() || currentTags.contains(tagText)) return;
        Chip chip = new Chip(this);
        chip.setText(tagText);
        chip.setCloseIconVisible(true);
        chip.setTextColor(getResources().getColor(android.R.color.black));
        chip.setChipStrokeColorResource(android.R.color.black);
        chip.setChipStrokeWidth(2f);
        chip.setChipBackgroundColorResource(android.R.color.white);
        chip.setOnCloseIconClickListener(v -> {
            chipGroupTags.removeView(chip);
            currentTags.remove(tagText);
        });
        chipGroupTags.addView(chip);
        currentTags.add(tagText);
    }

    private void showAddTagDialog() {
        EditText etNewTag = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("AGGIUNGI TAG")
                .setView(etNewTag)
                .setPositiveButton("OK", (dialog, which) -> addTagToGroup(etNewTag.getText().toString().trim()))
                .setNegativeButton("ANNULLA", null)
                .show();
    }
}