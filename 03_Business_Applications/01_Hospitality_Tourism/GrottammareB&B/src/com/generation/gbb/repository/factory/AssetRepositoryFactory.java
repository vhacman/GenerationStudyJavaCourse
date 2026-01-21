package com.generation.gbb.repository.factory;

import com.generation.gbb.repository.SQLAssetRepository;
import com.generation.gbb.repository.interfaces.AssetRepository;

/**
 * Factory for creating AssetRepository instances.
 * Implements Factory Method pattern to centralize repository creation.
 */
public class AssetRepositoryFactory
{
	/**
	 * Singleton instance of SQL repository.
	 * Inizializzato una sola volta per riutilizzo della TotalCache.
	 */
	static AssetRepository sqlAssetRepo = new SQLAssetRepository();

	/**
	 * Creates and returns an AssetRepository instance.
	 * Returns the SQL repository with TotalCache for optimal performance.
	 *
	 * @return Configured AssetRepository instance
	 */
	public static AssetRepository make()
	{
		return sqlAssetRepo;
	}
}
