package com.generation.sanctionweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale dell'applicazione Spring Boot.
 * Avvia il contesto Spring e configura tutti i componenti dell'applicazione.
 * 
 * @author Generation Team
 * @version 1.0
 */
@SpringBootApplication
public class SanctionApplication {
    /*
     * ARCHITETTURA OOP - SPRING BOOT:
     * 
     *    @SpringBootApplication  →  Configurazione automatica
     *     SpringApplication      →  Entry point dell'applicazione
     *     Inversion of Control   →  Spring gestisce il ciclo di vita
     *     Convention over Config →  Configurazione implicita
     */

    /**
     * Metodo main per l'avvio dell'applicazione.
     * 
     * @param args argomenti da linea di comando
     */
	public static void main(String[] args) {
		SpringApplication.run(SanctionApplication.class, args);
	}
}
