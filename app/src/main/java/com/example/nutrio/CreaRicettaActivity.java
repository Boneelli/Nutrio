package com.example.nutrio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class CreaRicettaActivity extends AppCompatActivity implements TagSelectorBottomSheetFragment.OnTagsApplyListener {

    private ChipGroup chipGroupTags;
    private LinearLayout containerIngredienti;
    private LinearLayout containerProcedimento;
    private List<String> currentTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_ricetta);

        setupToolbar();

        // Riferimenti UI
        chipGroupTags = findViewById(R.id.chipGroupCreaTags);
        containerIngredienti = findViewById(R.id.containerCreaIngredienti);
        containerProcedimento = findViewById(R.id.containerCreaProcedimento);

        // Aggiungi un primo campo vuoto per ingredienti e passaggi
        addItemToList(containerIngredienti, "");
        addItemToList(containerProcedimento, "");

        findViewById(R.id.btnAddCreaTag).setOnClickListener(v -> showAddTagDialog());
        findViewById(R.id.btnAddCreaIngrediente).setOnClickListener(v -> addItemToList(containerIngredienti, ""));
        findViewById(R.id.btnAddCreaPassaggio).setOnClickListener(v -> addItemToList(containerProcedimento, ""));

        findViewById(R.id.btnPubblicaRicetta).setOnClickListener(v -> {
            if (validaCampi()) {
                Toast.makeText(this, "RICETTA PUBBLICATA CON SUCCESSO!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarCrea);
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
        et.setHint(container == containerIngredienti ? "es. 200g Farina" : "Descrivi il passaggio...");
        
        itemView.findViewById(R.id.btnRemoveItem).setOnClickListener(v -> {
            if (container.getChildCount() > 1) {
                container.removeView(itemView);
            } else {
                Toast.makeText(this, "Devi inserire almeno un elemento", Toast.LENGTH_SHORT).show();
            }
        });
        
        container.addView(itemView);
    }

    private void addTagToGroup(String tagText) {
        if (tagText.isEmpty() || currentTags.contains(tagText)) return;
        Chip chip = new Chip(this);
        chip.setText(tagText);
        chip.setCloseIconVisible(true);
        chip.setTextColor(ContextCompat.getColor(this, android.R.color.black));
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
        TagSelectorBottomSheetFragment fragment = new TagSelectorBottomSheetFragment();
        fragment.show(getSupportFragmentManager(), "tag_selector");
    }

    @Override
    public void onApplyTags(List<String> selectedTags) {
        for (String tag : selectedTags) {
            addTagToGroup(tag);
        }
    }

    private boolean validaCampi() {
        EditText etTitolo = findViewById(R.id.etCreaTitolo);
        if (etTitolo.getText().toString().trim().isEmpty()) {
            etTitolo.setError("Il titolo è obbligatorio!");
            etTitolo.requestFocus();
            return false;
        }
        return true;
    }
}