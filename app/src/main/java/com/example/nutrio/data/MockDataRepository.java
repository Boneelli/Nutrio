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
                    new Ricetta.Ingrediente("Farina di riso", 20, "g")
            );
            List<String> passaggiPollo = Arrays.asList(
                    "Infarina i petti di pollo uniformemente.",
                    "Scalda l'olio in padella e rovescia il pollo fino a doratura.",
                    "Spremi il limone, versalo in padella e lascia sfumare per 3 minuti."
            );
            listaRicette.add(new Ricetta(
                    "Petto di pollo al limone",
                    R.drawable.petto_di_pollo,
                    4.8,
                    ingPollo,
                    passaggiPollo,
                    "Basso",
                    "<15min",
                    "Secondo",
                    Arrays.asList("Senza lattosio", "Senza glutine")
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
                    R.drawable.pancake_vegani,
                    4.9,
                    ingPancake,
                    passaggiPancake,
                    "Basso",
                    "<10min",
                    "Colazione",
                    Arrays.asList("Vegana", "Vegetariana", "Senza lattosio")
            ));


            // ==========================================
            // 👀 NUOVE AGGIUNTE: 3 COLAZIONI
            // ==========================================

            // --- RICETTA 3: Chia Pudding ai Frutti di Bosco (Vegana, Vegetariana, Senza glutine, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingChia = Arrays.asList(
                    new Ricetta.Ingrediente("Semi di Chia", 30, "g"),
                    new Ricetta.Ingrediente("Latte di Cocco", 150, "ml"),
                    new Ricetta.Ingrediente("Frutti di bosco freschi", 50, "g"),
                    new Ricetta.Ingrediente("Sciroppo d'acero", 10, "ml")
            );
            List<String> passaggiChia = Arrays.asList(
                    "In un bicchiere unisci i semi di chia col latte di cocco e lo sciroppo d'acero.",
                    "Mescola bene per evitare grumi e lascia riposare in frigo per almeno 4 ore (o tutta la notte).",
                    "Estrai dal frigo, mescola un'ultima volta e guarnisci superficialmente con i frutti di bosco."
            );
            listaRicette.add(new Ricetta(
                    "Chia Pudding ai Frutti di Bosco",
                    R.drawable.chia,
                    4.7,
                    ingChia,
                    passaggiChia,
                    "Basso",
                    "<5min",
                    "Colazione",
                    Arrays.asList("Vegana", "Vegetariana", "Senza glutine", "Senza lattosio")
            ));

            // --- RICETTA 4: Porridge Caldo Mele e Cannella (Vegana, Vegetariana, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingPorridge = Arrays.asList(
                    new Ricetta.Ingrediente("Fiocchi d'avena", 50, "g"),
                    new Ricetta.Ingrediente("Latte d'avena", 150, "ml"),
                    new Ricetta.Ingrediente("Mela a cubetti", 0.5, "pezzo"),
                    new Ricetta.Ingrediente("Cannella in polvere", 1, "pizzico")
            );
            List<String> passaggiPorridge = Arrays.asList(
                    "Versa i fiocchi d'avena e il latte d'avena in un pentolino.",
                    "Cuoci a fuoco basso per circa 5 minuti mescolando fino a ottenere una consistenza cremosa.",
                    "Versa in una ciotola, aggiungi la mela tagliata fine e una spolverata di cannella."
            );
            listaRicette.add(new Ricetta(
                    "Porridge Mele e Cannella",
                    R.drawable.porridge,
                    4.6,
                    ingPorridge,
                    passaggiPorridge,
                    "Basso",
                    "<10min",
                    "Colazione",
                    Arrays.asList("Vegana", "Vegetariana", "Senza lattosio")
            ));

            // --- RICETTA 5: Frittelline Soffici alla Ricotta (Vegetariana) ---
            List<Ricetta.Ingrediente> ingFrittelle = Arrays.asList(
                    new Ricetta.Ingrediente("Ricotta vaccina fresca", 100, "g"),
                    new Ricetta.Ingrediente("Uovo intero", 1, "pezzo"),
                    new Ricetta.Ingrediente("Farina 00", 30, "g"),
                    new Ricetta.Ingrediente("Miele d'api", 15, "g")
            );
            List<String> passaggiFrittelle = Arrays.asList(
                    "In una ciotola lavora la ricotta insieme al tuorlo d'uovo e al miele.",
                    "Incorpa la farina poco alla volta mescolando dal basso verso l'alto.",
                    "Monta l'albume a neve ferma, uniscilo al composto e cuoci a cucchiaiate su una padella calda."
            );
            listaRicette.add(new Ricetta(
                    "Frittelline alla Ricotta",
                    R.drawable.frittelle,
                    4.8,
                    ingFrittelle,
                    passaggiFrittelle,
                    "Basso",
                    "<15min",
                    "Colazione",
                    Arrays.asList("Vegetariana")
            ));


            // ==========================================
            // 👀 NUOVE AGGIUNTE: 4 PRIMI
            // ==========================================

            // --- RICETTA 6: Risotto Cremoso alla Zucca (Vegetariana, Senza glutine) ---
            List<Ricetta.Ingrediente> ingRisotto = Arrays.asList(
                    new Ricetta.Ingrediente("Riso Carnaroli", 80, "g"),
                    new Ricetta.Ingrediente("Polpa di zucca", 150, "g"),
                    new Ricetta.Ingrediente("Brodo vegetale", 400, "ml"),
                    new Ricetta.Ingrediente("Parmigiano Reggiano", 15, "g")
            );
            List<String> passaggiRisotto = Arrays.asList(
                    "Taglia la zucca a piccoli pezzi e cuocila nel brodo finché non diventa tenera.",
                    "Tosta il riso a secco in una casseruola per 2 minuti.",
                    "Aggiungi gradualmente il brodo con la zucca schiacciata e manteca alla fine col parmigiano."
            );
            listaRicette.add(new Ricetta(
                    "Risotto Cremoso alla Zucca",
                    R.drawable.risotto_zucca,
                    4.9,
                    ingRisotto,
                    passaggiRisotto,
                    "Medio",
                    "<30min",
                    "Primo",
                    Arrays.asList("Vegetariana", "Senza glutine")
            ));

            // --- RICETTA 7: Pasta Integrale al Pesto di Avocado (Vegana, Vegetariana, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingPasta = Arrays.asList(
                    new Ricetta.Ingrediente("Pasta integrale", 80, "g"),
                    new Ricetta.Ingrediente("Avocado maturo", 0.5, "pezzo"),
                    new Ricetta.Ingrediente("Foglie di Basilico", 6, "pezzi"),
                    new Ricetta.Ingrediente("Mandorle pelate", 10, "g")
            );
            List<String> passaggiPasta = Arrays.asList(
                    "Lessa la pasta in abbondante acqua salata seguendo i minuti sulla confezione.",
                    "Frulla la polpa dell'avocado con il basilico, le mandorle e un filo di acqua calda della pasta.",
                    "Scola la pasta e amalgamala a fuoco spento con la crema di avocado ottenuta."
            );
            listaRicette.add(new Ricetta(
                    "Pasta al Pesto di Avocado",
                    R.drawable.pasta_avocado,
                    4.5,
                    ingPasta,
                    passaggiPasta,
                    "Basso",
                    "<15min",
                    "Primo",
                    Arrays.asList("Vegana", "Vegetariana", "Senza lattosio")
            ));

            // --- RICETTA 8: Vellutata di Ceci e Rosmarino (Vegana, Vegetariana, Senza glutine, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingVellutata = Arrays.asList(
                    new Ricetta.Ingrediente("Ceci cotti in barattolo", 200, "g"),
                    new Ricetta.Ingrediente("Patata piccola", 1, "pezzo"),
                    new Ricetta.Ingrediente("Olio EVO", 10, "ml"),
                    new Ricetta.Ingrediente("Rosmarino fresco", 1, "rametto")
            );
            List<String> passaggiVellutata = Arrays.asList(
                    "Taglia la patata a cubetti molto piccoli e cuocila in acqua bollente per 10 minuti.",
                    "Aggiungi i ceci scolati e il rametto di rosmarino, lasciando insaporire sul fuoco.",
                    "Rimuovi il rosmarino e riduci tutto in crema liscia usando un frullatore a immersione."
            );
            listaRicette.add(new Ricetta(
                    "Vellutata Ceci e Rosmarino",
                    R.drawable.vellutata_ceci,
                    4.7,
                    ingVellutata,
                    passaggiVellutata,
                    "Basso",
                    "<20min",
                    "Primo",
                    Arrays.asList("Vegana", "Vegetariana", "Senza glutine", "Senza lattosio")
            ));

            // --- RICETTA 9: Gnocchi alla Sorrentina Rapidi (Vegetariana) ---
            List<Ricetta.Ingrediente> ingGnocchi = Arrays.asList(
                    new Ricetta.Ingrediente("Gnocchi di patate", 150, "g"),
                    new Ricetta.Ingrediente("Passata di pomodoro", 120, "ml"),
                    new Ricetta.Ingrediente("Mozzarella vaccina", 60, "g"),
                    new Ricetta.Ingrediente("Olio EVO", 5, "ml")
            );
            List<String> passaggiGnocchi = Arrays.asList(
                    "Lessa gli gnocchi in acqua salata e scolali non appena salgono a galla.",
                    "Mescolali bene in una ciotola con la passata di pomodoro riscaldata e l'olio.",
                    "Trasferisci in una pirofila, copri con mozzarella a cubetti e passa al grill per 5 minuti."
            );
            listaRicette.add(new Ricetta(
                    "Gnocchi alla Sorrentina",
                    R.drawable.gnocchi_sorrentina,
                    4.9,
                    ingGnocchi,
                    passaggiGnocchi,
                    "Basso",
                    "<15min",
                    "Primo",
                    Arrays.asList("Vegetariana")
            ));


            // ==========================================
            // 👀 NUOVE AGGIUNTE: 3 SECONDI
            // ==========================================

            // --- RICETTA 10: Burger di Lenticchie e Spezie (Vegana, Vegetariana, Senza glutine, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingBurger = Arrays.asList(
                    new Ricetta.Ingrediente("Lenticchie lesse scolate", 150, "g"),
                    new Ricetta.Ingrediente("Farina di ceci", 25, "g"),
                    new Ricetta.Ingrediente("Carota piccola grattugiata", 1, "pezzo"),
                    new Ricetta.Ingrediente("Curcuma o spezie a piacere", 1, "pizzico")
            );
            List<String> passaggiBurger = Arrays.asList(
                    "Schiaccia le lenticchie con una forchetta all'interno di una ciotola capiente.",
                    "Unisci la farina di ceci, la carota grattugiata finemente e le spezie scelte.",
                    "Compatta l'impasto formando un burger stabile e cuocilo in padella 4 minuti per lato."
            );
            listaRicette.add(new Ricetta(
                    "Burger Home-made di Lenticchie",
                    R.drawable.burger_lenticchie,
                    4.6,
                    ingBurger,
                    passaggiBurger,
                    "Basso",
                    "<15min",
                    "Secondo",
                    Arrays.asList("Vegana", "Vegetariana", "Senza glutine", "Senza lattosio")
            ));

            // --- RICETTA 11: Filetto di Salmone al Cartoccio (Senza glutine, Senza lattosio) ---
            List<Ricetta.Ingrediente> ingSalmone = Arrays.asList(
                    new Ricetta.Ingrediente("Filetto di salmone fresco", 150, "g"),
                    new Ricetta.Ingrediente("Zucchina media", 0.5, "pezzo"),
                    new Ricetta.Ingrediente("Pomodorini ciliegino", 4, "pezzi"),
                    new Ricetta.Ingrediente("Olio EVO", 5, "ml")
            );
            List<String> passaggiSalmone = Arrays.asList(
                    "Disponi il filetto di salmone pulito al centro di un foglio di carta forno.",
                    "Circonda il pesce con le zucchine tagliate a rondelle e i pomodorini tagliati in due.",
                    "Condisci con l'olio, sigilla bene i bordi del foglio a sacchetto e inforna a 180°C per 18 minuti."
            );
            listaRicette.add(new Ricetta(
                    "Salmone al Cartoccio con Verdure",
                    R.drawable.salmone,
                    4.8,
                    ingSalmone,
                    passaggiSalmone,
                    "Basso",
                    "<20min",
                    "Secondo",
                    Arrays.asList("Senza glutine", "Senza lattosio")
            ));

            // --- RICETTA 12: Frittata Soffice Zucchine e Menta (Vegetariana, Senza glutine) ---
            List<Ricetta.Ingrediente> ingFrittata = Arrays.asList(
                    new Ricetta.Ingrediente("Uova intere medie", 2, "pezzi"),
                    new Ricetta.Ingrediente("Zucchina grattugiata", 1, "pezzo"),
                    new Ricetta.Ingrediente("Parmigiano stagionato", 10, "g"),
                    new Ricetta.Ingrediente("Foglioline di menta fresca", 3, "pezzi")
            );
            List<String> passaggiFrittata = Arrays.asList(
                    "Sbatti energicamente le uova con sale, pepe e il parmigiano in una ciotolina.",
                    "Strizza con cura la zucchina grattugiata per togliere l'acqua in eccesso e inseriscila nelle uova.",
                    "Aggiungi la menta spezzettata e cuoci in forno statico a 180°C per 15 minuti dentro una teglia piccola."
            );
            listaRicette.add(new Ricetta(
                    "Frittata al Forno Zucchine e Menta",
                    R.drawable.frittata_zucchine,
                    4.7,
                    ingFrittata,
                    passaggiFrittata,
                    "Basso",
                    "<20min",
                    "Secondo",
                    Arrays.asList("Vegetariana", "Senza glutine")
            ));
        }
        return listaRicette;
    }
}