package com.generation.gbb.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.gbb.model.entities.Asset;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.factory.AssetRepositoryFactory;
import com.generation.gbb.repository.interfaces.AssetRepository;

class AssetRepositoryTest
{

    @Test
    void test() {
        /**
         * Un asset è un bene dell'albergo
         * ha un valore commerciale
         * abbiamo relativamente pochi asset
         * e possono essere caricati tutti in ram all'avvio
         */
        
        ProfilingMonitor.queryNumber = 0;
        ProfilingMonitor.rowsLoaded = 0;
        
        // mi aspetto di avere un componente reale con sotto un Db
        AssetRepository repo = AssetRepositoryFactory.make();
        // usiamo una Totalcache nel repository
        
        // nel database di prova consideriamo tre assets
        // una vecchia panda che usiamo per gli spostamenti aziendali
        // una cucina elettrica che usiamo per preparare la colazione
        // un pc aziendale su cui abbiamo questo programma
        
        // io non vi dico quali sono le proprietà di asset
        
        List<Asset> all = repo.findAll();
        
        Asset panda = repo.findById(1);
        assert(panda.getName().equals("Panda"));
        assert(panda.getDescription().equals("Vecchia panda"));
        assert(panda.getCost() == 2000);
        assert(panda.getValue() == 500);
        
        assert(ProfilingMonitor.queryNumber == 1);
        assert(ProfilingMonitor.rowsLoaded == 3);
        
        // metodi del repository solo i basilari:
        // insert, update, delete, findAll(), findById(), findWhere()
    }
}
