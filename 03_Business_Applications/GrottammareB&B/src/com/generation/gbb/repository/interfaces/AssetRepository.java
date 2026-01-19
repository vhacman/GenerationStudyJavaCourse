package com.generation.gbb.repository.interfaces;

import java.util.List;

import com.generation.gbb.model.entities.Asset;

/**
 * Interface for Asset persistence management.
 * Defines CRUD operations for Asset entity.
 *
 * Asset rappresenta un bene dell'albergo con valore commerciale.
 * Essendo relativamente pochi, vengono caricati tutti in RAM all'avvio (TotalCache).
 */
public interface AssetRepository
{
	// ==================== READ ====================

	/**
	 * R - Retrieves all assets available in the system.
	 *
	 * @return List of all assets
	 */
	List<Asset> findAll();

	/**
	 * R - Retrieves single asset by its unique identifier.
	 *
	 * @param id Asset identifier to search
	 * @return Found asset, null if not exists
	 */
	Asset findById(int id);

	// ==================== CREATE ====================

	/**
	 * C - Inserts new asset into the system.
	 * Validates data before insertion.
	 *
	 * @param newAsset Asset to insert with all required data
	 * @return Inserted asset with assigned ID, null if validation fails
	 */
	Asset insert(Asset newAsset);

	// ==================== UPDATE ====================

	/**
	 * U - Updates existing asset data.
	 * Replaces asset with matching ID with new version.
	 *
	 * @param newVersion Updated asset (must have valid existing ID)
	 * @return Updated asset, null if fails or asset not found
	 */
	Asset update(Asset newVersion);

	// ==================== DELETE ====================

	/**
	 * D - Deletes asset from system by identifier.
	 *
	 * @param id Asset identifier to delete
	 * @return true if deletion successful, false otherwise
	 */
	boolean delete(int id);

	/**
	 * Crea la tabella asset nel database se non esiste già.
	 */
	void initTable();
}
