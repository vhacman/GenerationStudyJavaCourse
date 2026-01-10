package com.generation.lmdb.model.entities;

/**
 * Base per tutte le altre entità
 */
public class Entity
{

	/*
	 * VISIBILITÀ - PROTECTED
	 *
	 * protected è un livello di visibilità intermedio tra private e public:
	 * - PRIVATE: solo questa classe può vedere l'attributo
	 * - PROTECTED: questa classe E le classi figlie possono vedere l'attributo
	 * - PUBLIC: tutti possono vedere l'attributo
	 *
	 * Uso protected per l'id perché:
	 * 1. Le classi figlie (Product, Producer, Batch) devono poterlo usare
	 * 2. Non voglio che sia public perché voglio controllare l'accesso tramite getter/setter
	 * 3. Se fosse private, le classi figlie non potrebbero accedervi direttamente
	 */
	protected int id;

	// Getter e Setter
	public int 		getId() 		{ return id; }
	public void 	setId(int id) 	{ this.id = id; }

	// Validazione
	public boolean isValid() { return id > 0; }
	
	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}

}
