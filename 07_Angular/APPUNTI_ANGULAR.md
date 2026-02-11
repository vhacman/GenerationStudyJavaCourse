# ANGULAR - Appunti della Lezione Introduttiva

---

## Che cos'e Angular

Angular e un framework open-source sviluppato e mantenuto da Google, pensato per costruire applicazioni web moderne e dinamiche. Quando si parla di "applicazione web dinamica" si intende una pagina che reagisce alle azioni dell'utente senza dover ricaricare l'intera pagina dal server ogni volta. Angular si occupa di tutta la parte che l'utente vede e con cui interagisce, cioe il cosiddetto front-end. In un'architettura completa, Angular rappresenta il lato client che comunica con un back-end (per esempio un'applicazione Spring Boot in Java) per ricevere e inviare dati.

Angular si basa interamente su TypeScript, un linguaggio di programmazione che estende JavaScript aggiungendo la tipizzazione statica. Questo significa che, a differenza di JavaScript puro dove le variabili possono contenere qualsiasi tipo di dato in qualsiasi momento, in TypeScript si puo (e spesso si deve) dichiarare il tipo di ogni variabile. Alla fine della compilazione, tutto il codice TypeScript viene tradotto automaticamente in JavaScript, perche il browser capisce solo JavaScript. Allo stesso modo, ogni file CSS dei singoli componenti viene unificato in un unico file CSS globale.

---

## Cosa installare sul PC per usare Angular

Prima di poter lavorare con Angular, bisogna preparare il proprio computer con alcuni strumenti fondamentali. Ecco cosa serve, nell'ordine corretto di installazione.

**Node.js e npm.** Node.js e un ambiente di esecuzione per JavaScript che funziona al di fuori del browser. Quando si installa Node.js, viene automaticamente installato anche npm (Node Package Manager), che e il gestore di pacchetti di Node. npm serve per scaricare e gestire tutte le librerie e le dipendenze di cui un progetto Angular ha bisogno. Si scarica Node.js dal sito ufficiale nodejs.org, scegliendo la versione LTS (Long Term Support) che e la piu stabile.

**Angular CLI.** Una volta installato Node.js, si apre il terminale e si installa la Angular CLI (Command Line Interface) con il comando:

```
npm install -g @angular/cli
```

Il flag `-g` significa "globale", cioe il comando `ng` diventa disponibile da qualsiasi cartella del sistema. La Angular CLI e lo strumento principale con cui si creano progetti, si generano componenti, si avvia il server di sviluppo e si compila l'applicazione. Dopo l'installazione, il comando `ng` diventa disponibile nel terminale.

**Visual Studio Code.** Come editor di codice, durante la lezione e stato utilizzato Visual Studio Code (VS Code), che e gratuito e offre un eccellente supporto per TypeScript e Angular, incluso il completamento automatico del codice e l'evidenziazione della sintassi.

---

## Creare un nuovo progetto Angular

Per creare un nuovo progetto Angular si utilizza il comando `ng new` seguito dal nome che si vuole dare al progetto. Durante la lezione, il professore ha creato il progetto con:

```
ng new AngularFood
```

Al momento della creazione, Angular pone alcune domande. La prima riguarda il tipo di foglio di stile da usare: il professore ha scelto CSS, che e il formato classico e piu semplice. La seconda domanda chiede se si vuole abilitare il SSR (Server-Side Rendering) e il prerendering: il professore ha risposto di no, perche per un progetto didattico di base non serve.

Una volta completata la creazione, si ottiene una cartella con il nome del progetto contenente tutta la struttura necessaria. Per aprirla in VS Code, basta fare "File > Open Folder" e selezionare la cartella del progetto.

---

## La struttura del progetto

All'interno della cartella del progetto Angular ci sono molti file e cartelle, ma le piu importanti da conoscere per iniziare sono poche.

La cartella `src` e quella principale dove si lavora. Al suo interno si trova la cartella `app`, che e il cuore dell'applicazione e contiene tutti i componenti. Il file `src/index.html` e il punto di ingresso dell'applicazione: e un normalissimo file HTML che contiene un tag speciale chiamato `<app-root></app-root>`. Questo tag non e un tag HTML standard, ma un tag personalizzato di Angular che rappresenta il componente principale dell'applicazione. Quando Angular si avvia, sostituisce quel tag con il contenuto del componente root.

Il file `src/main.ts` e il file che avvia l'applicazione Angular. Al suo interno si trova la funzione `bootstrapApplication` che prende il componente principale (`AppComponent`) e la configurazione dell'applicazione e li mette in funzione.

Il file `src/styles.css` e il foglio di stile globale, valido per tutta l'applicazione. Durante la lezione, il professore ha importato il framework CSS W3.CSS da W3Schools per avere uno stile grafico pronto all'uso, scrivendo:

```css
@import url('https://www.w3schools.com/w3css/4/w3.css');
```

La cartella `node_modules` contiene tutte le librerie scaricate automaticamente da npm. Non va mai modificata manualmente e non va inclusa nel repository Git (viene esclusa tramite il file `.gitignore`).

---

## TypeScript: le basi

TypeScript e il linguaggio in cui si scrive tutto il codice logico di un'applicazione Angular. Per chi viene da Java, TypeScript ha diverse somiglianze, ma anche differenze importanti.

**Dichiarazione delle variabili.** In TypeScript si possono dichiarare variabili con `let` (per valori che cambiano) o `const` (per valori costanti). La sintassi per dichiarare il tipo e con i due punti dopo il nome della variabile:

```typescript
let nome:string = 'Mario';
let eta:number = 25;
let attivo:boolean = true;
```

TypeScript obbliga a dare un valore iniziale alle variabili, altrimenti il compilatore segnala un errore. In realta, TypeScript puo anche inferire il tipo automaticamente dal valore assegnato, quindi scrivere `let nome = 'Mario'` funziona ugualmente perche TypeScript capisce da solo che si tratta di una stringa. Tuttavia, dichiarare esplicitamente il tipo e una buona pratica perche rende il codice piu leggibile.

**Le interfacce.** In TypeScript le interfacce funzionano in modo diverso rispetto a Java. In Java un'interfaccia contiene solo la firma dei metodi, cioe dice "quali operazioni si possono fare" senza implementarle. In TypeScript, invece, le interfacce servono principalmente a definire la forma di un oggetto, specificando quali proprieta deve avere e di che tipo sono. In pratica, un'interfaccia TypeScript assomiglia piu a una classe Java con solo attributi. Per esempio, durante la lezione il professore ha creato l'interfaccia `FoodModel`:

```typescript
export interface FoodModel {
    name:string;
    carbs:number;
    proteins:number;
    fats:number;
    vegan:boolean;
    price:number;
}
```

Questa interfaccia dice che qualsiasi oggetto di tipo `FoodModel` deve avere un nome (stringa), dei carboidrati (numero), delle proteine (numero), dei grassi (numero), un flag vegano (booleano) e un prezzo (numero). La parola chiave `export` serve per rendere l'interfaccia utilizzabile anche in altri file del progetto.

**Le classi.** TypeScript supporta anche le classi in modo simile a Java, con costruttori, metodi e proprieta. Durante la lezione il professore ha creato le classi `Room` e `HouseModel` per l'esercizio sulla casa:

```typescript
export class Room {
    type:string;
    side1:number;
    side2:number;

    constructor(type:string, side1:number, side2:number) {
        this.type = type;
        this.side1 = side1;
        this.side2 = side2;
    }

    area():number {
        return this.side1 * this.side2;
    }
}
```

Come si vede, la struttura e molto simile a Java: c'e un costruttore che riceve i parametri e li assegna alle proprieta dell'oggetto con `this`, e ci sono metodi che restituiscono un valore. La differenza sintattica principale e che in TypeScript il tipo di ritorno si scrive dopo i due punti (`:number`) anziche prima del nome del metodo come in Java.

**Gli array.** Gli array in TypeScript si dichiarano indicando il tipo degli elementi seguito da `[]`. Per esempio, `string[]` e un array di stringhe, `Room[]` e un array di oggetti Room. Per inizializzare un array vuoto si scrive `[]`.

**Lo spread operator (...).** Questo e un operatore molto importante in TypeScript (e in JavaScript moderno) che il professore ha usato piu volte durante la lezione. I tre puntini `...` servono per "espandere" un oggetto o un array, copiandone tutto il contenuto in un nuovo oggetto o array. Per esempio:

```typescript
this.food = {...this.food, [field]:newValue};
```

Questa riga crea un nuovo oggetto che e una copia identica di `this.food`, ma con una singola proprieta modificata. Il motivo per cui si fa cosi, invece di modificare direttamente la proprieta, e legato al modo in cui Angular rileva i cambiamenti: se si crea un oggetto completamente nuovo, Angular se ne accorge immediatamente e aggiorna la vista. Se si modifica solo una proprieta di un oggetto esistente, Angular potrebbe non accorgersene.

Allo stesso modo, per gli array:

```typescript
this.exercises = [this.newExercise, ...this.exercises];
```

Questa riga crea un nuovo array che contiene il nuovo esercizio all'inizio, seguito da tutti gli elementi dell'array precedente. In questo modo la lista viene aggiornata correttamente nella vista.

---

## I componenti Angular

Il concetto fondamentale di Angular e il componente. Un componente e un pezzo autonomo dell'interfaccia utente che ha la propria logica, la propria vista (HTML) e il proprio stile (CSS). Si puo pensare a un componente come a un mattoncino Lego: ogni mattoncino e indipendente, ma combinandoli insieme si costruisce l'intera applicazione.

**Generare un componente.** Per creare un nuovo componente si usa la Angular CLI con il comando:

```
ng g c NomeComponente
```

dove `ng` e il comando Angular, `g` sta per "generate" e `c` sta per "component". Durante la lezione, il professore ha generato quattro componenti: `Food`, `BMI`, `FitnessProgram` e `House`. Per ogni componente, Angular crea automaticamente una cartella dedicata contenente quattro file.

**I quattro file di un componente.** Quando si genera un componente, per esempio `Food`, vengono creati:

`food.component.ts` contiene la logica del componente. Questo file e il cervello del componente: qui si dichiarano le variabili, si scrivono i metodi e si gestisce il comportamento. E decorato con `@Component`, un decoratore che comunica ad Angular le informazioni fondamentali del componente.

`food.component.html` contiene il template HTML, cioe la vista del componente. Qui si definisce cosa l'utente vede sullo schermo. Si puo usare normale HTML mescolato con la sintassi speciale di Angular per mostrare dati dinamici.

`food.component.css` contiene gli stili CSS specifici di quel componente. Gli stili scritti qui valgono solo per quel componente e non influenzano gli altri.

`food.component.spec.ts` contiene i test automatici del componente. Per ora non e stato utilizzato durante la lezione.

**Il decoratore @Component.** Ogni componente TypeScript ha sopra la classe un decoratore `@Component` che specifica tre informazioni essenziali:

```typescript
@Component({
  selector: 'app-food',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './food.component.html',
  styleUrl: './food.component.css'
})
```

Il `selector` e il nome del tag HTML personalizzato con cui si puo inserire questo componente dentro altri template. Se il selector e `app-food`, allora nel template HTML si scrivera `<app-food></app-food>` per mostrare quel componente. La proprieta `standalone: true` indica che il componente e autonomo e non dipende da un modulo Angular. La proprieta `imports` elenca i moduli esterni necessari (per esempio `FormsModule` per i form). Il `templateUrl` punta al file HTML del componente e `styleUrl` punta al file CSS.

**Usare un componente dentro un altro.** Per inserire un componente dentro un altro bisogna fare due cose. Prima si importa il componente nel file TypeScript del componente padre, aggiungendolo all'array `imports` del decoratore `@Component`. Poi si usa il tag del selector nel template HTML del padre. Per esempio, il componente principale `AppComponent` durante la lezione importava e mostrava gli altri componenti cosi:

```typescript
// nel file app.component.ts
imports: [BMIComponent, FitnessProgramComponent, HouseComponent]
```
```html
<!-- nel file app.component.html -->
<app-house></app-house>
```

---

## Il data binding: collegare dati e vista

Il data binding e il meccanismo con cui Angular collega i dati presenti nel componente TypeScript con cio che viene mostrato nel template HTML. Esistono diversi tipi di data binding, e il professore li ha mostrati tutti durante la lezione.

**Interpolazione ({{ }}).** L'interpolazione e il modo piu semplice per mostrare il valore di una variabile nel template. Si usano le doppie parentesi graffe. Per esempio, se nel componente TypeScript c'e una variabile `food` con una proprieta `name` che vale "Lasagna", nel template si scrive:

```html
Name: {{food.name}}
```

e Angular mostra automaticamente "Name: Lasagna" nella pagina. Si possono anche richiamare metodi dentro le doppie graffe: `{{calories()}}` richiama il metodo `calories()` del componente e ne mostra il risultato. Ogni volta che i dati cambiano, Angular aggiorna automaticamente il testo visualizzato.

**Event binding ((evento)).** L'event binding serve per reagire alle azioni dell'utente, come un click su un bottone. Si usano le parentesi tonde attorno al nome dell'evento:

```html
<input type="button" value="+" (click)="changeNutrients('carbs',1)" />
```

In questo esempio, quando l'utente clicca sul bottone "+", Angular esegue il metodo `changeNutrients` passandogli la stringa `'carbs'` e il numero `1` come parametri. Il metodo nel componente TypeScript riceve questi parametri e modifica i dati di conseguenza. Il concetto di "delta" usato dal professore indica proprio questo valore differenziale: `+1` per aumentare, `-1` per diminuire.

**Two-way binding ([(ngModel)]).** Il two-way binding e il tipo piu potente di collegamento tra vista e logica. Significa che il dato scorre in entrambe le direzioni: se l'utente modifica il valore in una casella di testo, la variabile nel componente si aggiorna automaticamente, e viceversa se il codice modifica la variabile, la casella di testo si aggiorna. La sintassi e `[(ngModel)]`:

```html
<input type="text" name="name" [(ngModel)]="name" />
```

Per poter usare `[(ngModel)]` bisogna importare il modulo `FormsModule` nel componente. Questo si fa aggiungendo `FormsModule` nell'array `imports` del decoratore e importandolo in cima al file:

```typescript
import { FormsModule } from '@angular/forms';
```

Durante la lezione, il two-way binding e stato usato nel componente BMI per collegare le caselle di testo del nome, del peso e dell'altezza alle rispettive variabili, e nel componente House per collegare i campi citta, indirizzo e prezzo al metro quadro.

**Property binding ([proprieta]).** Il property binding serve per impostare una proprieta di un elemento HTML con un valore calcolato dal componente. Si usano le parentesi quadre:

```html
<input type="number" readonly [value]="bmi()" />
```

In questo caso il valore del campo viene impostato con il risultato del metodo `bmi()` del componente. La differenza con l'interpolazione e che il property binding agisce direttamente sulle proprieta dell'elemento DOM, mentre l'interpolazione agisce solo sul contenuto testuale.

---

## Il ciclo @for e il blocco @empty

Angular fornisce una sintassi per iterare su array direttamente nel template HTML. Durante la lezione il professore ha usato il costrutto `@for` con la sintassi moderna di Angular:

```html
@for (exercise of exercises; track exercise) {
    <div class="exercise">{{ exercise }}</div>
} @empty {
    <li>Nessun esercizio trovato!</li>
}
```

Il `@for` funziona come un ciclo for-each: per ogni elemento dell'array `exercises`, crea un `<div>` che mostra il testo dell'esercizio. La parola `track` serve ad Angular per tenere traccia degli elementi in modo efficiente: quando l'array cambia, Angular sa esattamente quali elementi aggiungere, rimuovere o spostare senza dover ricreare tutto da zero.

Il blocco `@empty` e opzionale e definisce cosa mostrare quando l'array e vuoto. Nel componente House, per esempio, viene mostrato "No room yet" quando non ci sono ancora stanze aggiunte. Nell'esercizio FitnessProgram viene mostrato "Nessun esercizio trovato!" quando la lista e vuota.

Per iterare su array di oggetti piu complessi, come nell'esercizio della casa, si puo accedere alle proprieta e ai metodi di ogni oggetto:

```html
@for (room of house.rooms; track $index) {
    <div class="room">{{ room.type}} - {{room.side1}}m {{room.side2}}m - {{room.area()}} sm</div>
}
```

In questo caso `track $index` usa l'indice dell'array per il tracciamento, e per ogni stanza si mostrano il tipo, le dimensioni e l'area calcolata dal metodo `area()`.

---

## Gli esercizi svolti durante la lezione

Durante la lezione il professore ha costruito quattro esercizi progressivi, ognuno dei quali introduce concetti nuovi.

**Esercizio 1: Food Component.** Il primo esercizio consiste in una scheda alimentare per un cibo chiamato "Lasagna". Il componente mostra il nome, se e vegano o no, e i valori nutrizionali (carboidrati, proteine, grassi). Per ogni nutriente ci sono due bottoni, "+" e "-", che permettono di aumentare o diminuire il valore di uno alla volta. In basso viene mostrato il totale delle calorie, calcolato automaticamente con la formula: carboidrati per 4, piu proteine per 4, piu grassi per 9. Questo esercizio dimostra l'uso dell'interpolazione per mostrare i dati, dell'event binding per gestire i click sui bottoni, e dello spread operator per aggiornare l'oggetto food in modo che Angular rilevi il cambiamento. Il professore ha anche creato l'interfaccia `FoodModel` in una cartella separata chiamata `model`, per separare la definizione dei dati dalla logica del componente.

**Esercizio 2: BMI Component.** Il secondo esercizio e un calcolatore di indice di massa corporea (BMI). L'utente inserisce il proprio nome, il peso in chilogrammi e l'altezza in centimetri, e il sistema calcola automaticamente il BMI con la formula peso diviso altezza al quadrato (convertita da centimetri a metri). Questo esercizio introduce il two-way binding con `[(ngModel)]` e il modulo `FormsModule`. I campi di input per il nome, il peso e l'altezza sono collegati bidirezionalmente alle variabili del componente, quindi appena l'utente digita un valore, il BMI si ricalcola istantaneamente. Il campo del BMI e in sola lettura (`readonly`) e usa il property binding `[value]` per mostrare il risultato del calcolo.

**Esercizio 3: FitnessProgram Component.** Il terzo esercizio e un programma di fitness dove l'utente puo aggiungere esercizi a una lista. C'e una casella di testo dove si digita il nome dell'esercizio e un bottone "+" per aggiungerlo. La lista degli esercizi viene mostrata usando il ciclo `@for`. Se la lista e vuota, appare il messaggio "Nessun esercizio trovato!". Questo esercizio introduce il concetto di array in TypeScript (`string[]`), il ciclo `@for` per iterare sugli array nel template, e il blocco `@empty` per gestire il caso di lista vuota. Il metodo `addExercise` controlla prima che il campo non sia vuoto, poi aggiunge il nuovo esercizio in cima alla lista usando lo spread operator e infine svuota il campo di testo.

**Esercizio 4: House Component.** Il quarto e ultimo esercizio e il piu complesso. Si tratta di un configuratore immobiliare dove si inseriscono le informazioni di una casa (citta, indirizzo, prezzo al metro quadro) e si possono aggiungere stanze specificandone il tipo e le dimensioni (due lati). L'applicazione calcola automaticamente l'area totale (sommando le aree di tutte le stanze) e il prezzo totale (area per prezzo al metro quadro). Questo esercizio introduce le classi TypeScript (`Room` e `HouseModel`) con costruttori e metodi, la composizione di oggetti (una casa contiene un array di stanze), e il layout a colonne usando le classi CSS di W3.CSS (`w3-row`, `w3-third`). Il professore ha definito le classi `Room` e `HouseModel` direttamente nello stesso file del componente (`house.component.ts`), mostrando che in TypeScript non e obbligatorio avere un file separato per ogni classe come in Java.

---

## Avviare e testare l'applicazione

Per avviare l'applicazione Angular in modalita di sviluppo si usa il comando:

```
ng serve
```

Questo comando compila tutto il codice TypeScript in JavaScript, unifica i CSS, e avvia un server di sviluppo locale. L'applicazione diventa accessibile nel browser all'indirizzo `http://localhost:4200`. La porta 4200 e quella predefinita di Angular.

Una caratteristica molto utile di `ng serve` e il live reload: ogni volta che si salva una modifica a qualsiasi file del progetto, Angular ricompila automaticamente il codice e aggiorna la pagina nel browser senza dover fare nulla. Questo permette di vedere immediatamente l'effetto di ogni modifica, rendendo lo sviluppo molto rapido.

Nel terminale, dopo aver lanciato `ng serve`, si vedono i messaggi di compilazione che mostrano i file generati (come `main.js`, `polyfills.js`, `styles.css`) e le loro dimensioni. Se ci sono errori nel codice, vengono mostrati direttamente nel terminale con indicazione precisa del file e della riga dove si trova il problema. Durante la lezione, per esempio, il professore ha incontrato un errore di sintassi nella riga dello spread operator (`this.food = {...this.food, [field]:newValue}`) che il compilatore ha segnalato immediatamente.

---

## Riepilogo dei comandi fondamentali

`npm install -g @angular/cli` serve per installare Angular CLI globalmente sul sistema, va eseguito una sola volta.

`ng new NomeProgetto` crea un nuovo progetto Angular completo con tutta la struttura di cartelle e file necessari.

`ng g c NomeComponente` genera un nuovo componente con i suoi quattro file (TypeScript, HTML, CSS e test) all'interno della cartella `src/app`.

`ng serve` avvia il server di sviluppo locale sulla porta 4200 con il live reload attivo.

---

## Concetti chiave da ricordare

La cartella `src/app` e il cuore dell'applicazione dove risiedono tutti i componenti. Ogni componente e composto da quattro file che separano la logica (`.ts`), la vista (`.html`), lo stile (`.css`) e i test (`.spec.ts`). Il selector di un componente e il nome del tag HTML personalizzato con cui lo si inserisce nel template di un altro componente. L'interpolazione con le doppie graffe `{{ }}` serve per mostrare dati nella vista. L'event binding con le parentesi tonde `(click)` serve per reagire alle azioni dell'utente. Il two-way binding con `[(ngModel)]` crea un collegamento bidirezionale tra un campo di input e una variabile del componente, e richiede l'import di `FormsModule`. Lo spread operator `...` crea copie di oggetti e array, ed e fondamentale per far si che Angular rilevi correttamente i cambiamenti nei dati. Il ciclo `@for` permette di iterare sugli array direttamente nel template HTML, e il blocco `@empty` gestisce il caso di array vuoto. Angular gira su un server di sviluppo locale alla porta 4200 e si aggiorna automaticamente ad ogni modifica del codice.
