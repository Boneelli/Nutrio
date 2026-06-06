package com.example.nutrio.model;

import java.io.Serializable;
import java.util.List;

public class Ricetta implements Serializable {
    private String titolo;
    private int immagineResId; // ID del drawable (es. R.drawable.piatto1)
    private double punteggio; // Es. 4.9
    private List<Ingrediente> ingredienti;
    private List<String> passaggi;

    // Tag richiesti
    private String costo;          // Basso, Medio, Alto
    private String tempoPrep;      // <5 min, <10min, ecc.
    private String portata;        // Colazione, Primo, Secondo
    private List <String> regimeAlimentare; // Senza lattosio, Senza glutine, ecc.

    // Costruttore
    public Ricetta(String titolo, int immagineResId, double punteggio,
                   List<Ingrediente> ingredienti, List<String> passaggi,
                   String costo, String tempoPrep, String portata, List<String> regimeAlimentare) {
        this.titolo = titolo;
        this.immagineResId = immagineResId;
        this.punteggio = punteggio;
        this.ingredienti = ingredienti;
        this.passaggi = passaggi;
        this.costo = costo;
        this.tempoPrep = tempoPrep;
        this.portata = portata;
        this.regimeAlimentare = regimeAlimentare;
    }

    // Sottoclasse per l'ingrediente strutturato
    public static class Ingrediente implements Serializable {
        private String nome;
        private double quantita;
        private String unitaMisura; // g, ml, cucchiai, q.b.

        public Ingrediente(String nome, double quantita, String unitaMisura) {
            this.nome = nome;
            this.quantita = quantita;
            this.unitaMisura = unitaMisura;
        }

        // Getter per i dettagli dell'ingrediente
        public String getNome() { return nome; }
        public double getQuantita() { return quantita; }
        public String getUnitaMisura() { return unitaMisura; }
    }

    // Getter per la ricetta
    public String getTitolo() { return titolo; }
    public int getImmagineResId() { return immagineResId; }
    public double getPunteggio() { return punteggio; }
    public List<Ingrediente> getIngredienti() { return ingredienti; }
    public List<String> getPassaggi() { return passaggi; }
    public String getCosto() { return costo; }
    public String getTempoPrep() { return tempoPrep; }
    public String getPortata() { return portata; }
    public List<String> getRegimeAlimentare() { return regimeAlimentare; }
}