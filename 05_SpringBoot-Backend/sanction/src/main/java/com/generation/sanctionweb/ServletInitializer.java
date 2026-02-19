package com.generation.sanctionweb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Inizializzatore per il deployment dell'applicazione su servlet container (WAR).
 * Permette l'esecuzione dell'applicazione su server applicativi esterni.
 * 
 * @author Generation Team
 * @version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {
    /*
     * ARCHITETTURA OOP - DEPLOYMENT:
     * 
     *     SpringBootServletInitializer  →  Entry point per WAR
     *     Builder Pattern               →  Configurazione fluent
     *     Inheritance                  →  Estende classe base Spring
     */

    /**
     * Configura l'applicazione Spring Boot per il deployment su servlet container.
     * 
     * @param application builder da configurare
     * @return application builder configurato
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SanctionApplication.class);
	}
}
