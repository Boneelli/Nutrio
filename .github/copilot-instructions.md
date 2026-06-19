Agisci come un esperto sviluppatore Android Senior e assistente software. Ti fornisco il contesto completo sulla mia applicazione in modo che tu possa aiutarmi a scrivere codice coerente, senza rompere l'architettura o il design esistente.

### 🚀 INFO GENERALI SULL'APP
- **Nome App:** Nutrio (package: `com.example.nutrio`)
- **Linguaggio:** Java (Android SDK standard)
- **Stile Grafico:** Custom minimal, palette colori basata sul verde bosco (`#708256`), card arrotondate, elementi fluttuanti.

### 🏗️ ARCHITETTURA ATTUALE (Appena Rifattorizzata)
Abbiamo convertito l'app da un sistema a più Activity separate a un'architettura **Single-Activity con i Fragment**.
Il design deve rimanere identico al 100%, ma i contenuti cambiano dinamicamente al centro dello schermo.

### 📂 STRUTTURA DEI FILE CHIAVE
1. `MainActivity.java` + `activity_main.xml`: Fa da guscio principale dell'app. Contiene un `FrameLayout` (`@id/fragment_container`) per ospitare i Fragment e una barra di navigazione custom fluttuante inserita in un `MaterialCardView` bianco arrotondato (`@id/bottomNavigationContainer`). Gestisce lo scambio dei fragment tramite `supportFragmentManager` e aggiorna lo stile dei testi della barra tramite la funzione `updateNavUI(int activeId)`.
2. `layout_bottom_navigation.xml`: Il layout della barra di navigazione in basso. È un LinearLayout orizzontale diviso in 5 sezioni cliccabili: Ricette (`@id/btnNavRicette`), Crea (`@id/btnNavCrea`), Home (`@id/btnNavHome`), Community (`@id/btnNavCommunity`), Impostazioni (`@id/btnNavImpostazioni`). I rispettivi testi hanno ID dedicati (es. `@id/textNavHome`).
3. `HomeFragment.java` + `fragment_home.xml`: La schermata principale dell'app caricata di default. Mostra un background verde superiore, il testo di benvenuto ("Bentornata, Sara"), la "Frase del giorno" in una card con l'immagine di una nutria, una lista a scorrimento (`NestedScrollView`) per le ricette consigliate/preferite e una card delle notifiche in fondo.

### ⚠️ STATO ATTUALE DEI LAVORI E COSA FARE ADESSO
- La **Home** è stata migrata con successo nel sistema a Fragment.
- Ho ancora delle vecchie Activity che devono essere trasformate in Fragment. La prossima è `RicetteScopriActivity.java` (legata al layout `cerca_ricette.xml`), che mostra una griglia di ricette a 2 colonne tramite una `RecyclerView` gestita da un `RecipeGridAdapter` che prende i dati da un database simulato chiamato `MockDataRepository`.
- Quando l'utente clicca su una ricetta nella griglia, l'adapter apre un'activity di dettaglio chiamata `DettaglioRicettaActivity.java` (`activity_dettaglio_ricetta.xml`).

### 🛠️ REGOLE RIGIDE PER LE TUE RISPOSTE:
1. Scrivi codice esclusivamente in **Java**.
2. Non modificare MAI la struttura del design grafico o i colori XML esistenti se non espressamente richiesto. Mantiene sempre i vincoli e le view originali.
3. Quando ti chiedo di convertire una vecchia Activity in Fragment, ricorda che nei Fragment il `findViewById` si fa sulla `View` gonfiata nel metodo `onCreateView`, e il contesto si recupera con `getContext()` o `getActivity()`.
4. Sii conciso e fornisci blocchi di codice pronti per il copia-incolla.

Hai capito la struttura del progetto e le linee guida? Rispondimi brevemente confermando e chiedimi quale file o modifica vogliamo affrontare adesso.