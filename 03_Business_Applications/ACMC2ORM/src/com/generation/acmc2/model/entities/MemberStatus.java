package com.generation.acmc2.model.entities;

/**
 * Enum che definisce lo stato in cui si trova un membro
 */
public enum MemberStatus 
{
	BANNED(0),
	NONE(0),
	BRONZE(1000),
	SILVER(5000),
	GOLD(10000),
	GRAY(50000);
	
	
	public final int thres;
	/**
	 * thres indica Di quanti soldi ho bisogno per essere un membro
	 * di questo livello. Mi riferisco alle donazioni
	 * dell'ultimo anno
	 */
	private MemberStatus(int thres)
	{
		this.thres = thres;
	}
	
	/**
	 * Dimmi a quale stato corrisponde questa soglia
	 * quanto devo avere versato nell'ultimo anno?
	 * @param thres
	 */
	public static MemberStatus getByThreshold(int thres)
	{
		if(thres>=GRAY.thres)
			return GRAY;
		
		if(thres>=GOLD.thres)
			return GOLD;
		
		if(thres>=SILVER.thres)
			return SILVER;
		
		if(thres>BRONZE.thres)
			return BRONZE;
		
		return NONE;	
	}
	
}
