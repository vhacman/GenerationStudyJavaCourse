package com.generation.gbb.test;

import java.util.List;

import com.generation.gbb.model.entities.Asset;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.factory.AssetRepositoryFactory;
import com.generation.gbb.repository.interfaces.AssetRepository;

/**
 * Test manuale per verificare il funzionamento del repository Asset.
 * Simula esattamente il comportamento del test JUnit.
 */
public class TestAssetManual
{
	public static void main(String[] args)
	{
		System.out.println("=== Test Asset Repository ===\n");

		// Reset del ProfilingMonitor (come nel test JUnit)
		ProfilingMonitor.queryNumber = 0;
		ProfilingMonitor.rowsLoaded = 0;

		// Ottieni il repository dalla factory
		AssetRepository repo = AssetRepositoryFactory.make();
		System.out.println("✓ Repository creato");

		// Chiamata a findAll() - dovrebbe fare 1 query
		List<Asset> all = repo.findAll();
		System.out.println("✓ findAll() eseguito - trovati " + all.size() + " asset");

		// Verifica il primo asset (Panda)
		Asset panda = repo.findById(1);
		System.out.println("\n=== Verifica Asset Panda ===");
		System.out.println("Nome: " + panda.getName() + " (atteso: Panda)");
		System.out.println("Descrizione: " + panda.getDescription() + " (atteso: Vecchia panda)");
		System.out.println("Cost: " + panda.getCost() + " (atteso: 2000)");
		System.out.println("Value: " + panda.getValue() + " (atteso: 500)");

		// Verifica asserzioni
		assert(panda.getName().equals("Panda")) : "Nome non corretto";
		assert(panda.getDescription().equals("Vecchia panda")) : "Descrizione non corretta";
		assert(panda.getCost() == 2000) : "Cost non corretto";
		assert(panda.getValue() == 500) : "Value non corretto";
		System.out.println("✓ Tutte le asserzioni su Panda sono corrette");

		// Verifica ProfilingMonitor
		System.out.println("\n=== Verifica ProfilingMonitor ===");
		System.out.println("Query eseguite: " + ProfilingMonitor.queryNumber + " (atteso: 1)");
		System.out.println("Righe caricate: " + ProfilingMonitor.rowsLoaded + " (atteso: 3)");

		assert(ProfilingMonitor.queryNumber == 1) : "Numero di query non corretto: " + ProfilingMonitor.queryNumber;
		assert(ProfilingMonitor.rowsLoaded == 3) : "Numero di righe non corretto: " + ProfilingMonitor.rowsLoaded;
		System.out.println("✓ ProfilingMonitor corretto");

		System.out.println("\n=== TEST PASSATO ✓ ===");
	}
}
