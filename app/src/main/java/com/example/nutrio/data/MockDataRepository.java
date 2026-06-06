package com.example.nutrio.data;

import com.example.nutrio.R;
import com.example.nutrio.model.Ricetta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockDataRepository {

    private static List<Ricetta> listaRicette;

    public static List<Ricetta> getRicette() {
        if (listaRicette == null) {
            listaRicette = new ArrayList<>();

            // --- RICETTA 1: Petto di pollo al limone (Senza lattosio e Senza glutine) ---
            List<Ricetta.Ingrediente> ingPollo = Arrays.asList(
                    new Ricetta.Ingrediente("Petto di Pollo", 400, "g"),
                    new Ricetta.Ingrediente("Limone (Succo)", 1, "pezzo"),
                    new Ricetta.Ingrediente("Olio EVO", 15, "ml"),
                    new Ricetta.Ingrediente("Farina di riso", 20, "g") // Usiamo farina di riso per renderla gluten-free
            );
            List<String> passaggiPollo = Arrays.asList(
                    "Infarina i petti di pollo uniformemente.",
                    "Scalda l'olio in padella e rovescia il pollo fino a doratura.",
                    "Spremi il limone, versalo in padella e lascia sfumare per 3 minuti."
            );
            listaRicette.add(new Ricetta(
                    "Petto di pollo al limone",
                    R.drawable.ricette,
                    4.8,
                    ingPollo,
                    passaggiPollo,
                    "Basso",
                    "<15min",
                    "Secondo",
                    Arrays.asList("Senza lattosio", "Senza glutine") // TAG MULTIPLI
            ));

            // --- RICETTA 2: Pancake Vegani (Vegana, Vegetariana, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingPancake = Arrays.asList(
                    new Ricetta.Ingrediente("Farina d'avena", 150, "g"),
                    new Ricetta.Ingrediente("Latte di Mandorla", 200, "ml"),
                    new Ricetta.Ingrediente("Banana matura", 1, "pezzo")
            );
            List<String> passaggiPancake = Arrays.asList(
                    "Schiaccia la banana in una ciotola capiente.",
                    "Aggiungi farina e latte di mandorla, poi mescola con una frusta.",
                    "Cuoci un mestolo di impasto per volta su una padella antiaderente calda."
            );
            listaRicette.add(new Ricetta(
                    "Pancake alla banana",
                    R.drawable.ricette,
                    4.9,
                    ingPancake,
                    passaggiPancake,
                    "Basso",
                    "<10min",
                    "Colazione",
                    Arrays.asList("Vegana", "Vegetariana", "Senza lattosio") // TAG MULTIPLI
            ));

            // Puoi continuare ad aggiungere qui sotto altre ricette seguendo questo schema...
        }
        return listaRicette;
    }
}