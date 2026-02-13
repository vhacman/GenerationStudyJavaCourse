package com.generation.javaeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * La classe JavaeatApplication rappresenta il punto di ingresso dell'applicazione Spring Boot.
 * Spring Boot è un framework che semplifica la configurazione e l'avvio di applicazioni Spring,
 * offrendo funzionalità di auto-configurazione e convenzioni predefinite che riducono la quantità
 * di codice boilerplate necessario per creare applicazioni enterprise.
 */
@SpringBootApplication
public class JavaeatApplication
{

    /**
     * Il metodo main è il punto di ingresso standard di ogni applicazione Java.
     * Quando la JVM viene avviata, cerca questo metodo come punto di partenza per l'esecuzione.
     * In questo contesto, il metodo delega l'inizializzazione a SpringApplication,
     * che si occupa di configurare il contesto dell'applicazione, impostare i bean,
     * e avviare il server embedded (tipicamente Tomcat per applicazioni web).
     *
     * @param args Gli argomenti passati da riga di comando, che possono influenzare
     *             il comportamento dell'applicazione durante l'avvio.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(JavaeatApplication.class, args);
    }
}
