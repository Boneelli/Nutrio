package com.example.nutrio;

public class ChatMessage {
    private String testo;
    private String mittente;
    private boolean isMioMessaggio; // Se è true sarà verde a destra, se false grigio a sinistra

    public ChatMessage(String testo, String mittente, boolean isMioMessaggio) {
        this.testo = testo;
        this.mittente = mittente;
        this.isMioMessaggio = isMioMessaggio;
    }

    public String getTesto() { return testo; }
    public String getMittente() { return mittente; }
    public boolean isMioMessaggio() { return isMioMessaggio; }
}