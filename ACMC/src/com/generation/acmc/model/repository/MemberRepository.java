package com.generation.acmc.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;

public interface MemberRepository 
{
	/**
	 * Restituisce tutti i membri presenti nel sistema.
	 * 
	 * @return lista di tutti i membri registrati, mai null (può essere vuota).
	 */
	List<Member> findAll();

	/**
	 * Restituisce il membro corrispondente all'ID specificato.
	 * 
	 * @param id identificativo univoco del membro da cercare
	 * @return membro trovato, null se non esiste
	 */
	Member findById(int id);

	/**
	 * Restituisce il membro corrispondente al cognome specificato.
	 * 
	 * @param lastName cognome del membro da cercare
	 * @return membro trovato, null se non esiste
	 */
	Member findByLastName(String lastName);

	/**
	 * Restituisce tutti i membri il cui cognome contiene la sottostringa specificata.
	 * 
	 * @param lastName sottostringa da cercare nel cognome
	 * @return lista di membri che soddisfano il criterio, mai null (può essere vuota)
	 */
	List<Member> findByLastNameContaining(String lastName);

	/**
	 * Restituisce tutti i membri che hanno il livello di membership specificato.
	 * 
	 * @param level livello di membership da filtrare
	 * @return lista di membri con il livello specificato, mai null (può essere vuota)
	 */
	List<Member> findByLevel(MembershipLevel level);

	/**
	 * Inserisce un nuovo membro nel sistema.
	 * 
	 * @param member membro da inserire
	 * @return membro inserito, con eventuale ID generato
	 * @throws SQLException se si verifica un errore durante l'inserimento
	 */
	Member insert(Member member) throws SQLException;

	/**
	 * Aggiorna i dati di un membro esistente.
	 * 
	 * @param member membro da aggiornare (deve avere un ID valido)
	 * @return membro aggiornato
	 * @throws SQLException se si verifica un errore durante l'aggiornamento
	 */
	Member update(Member member) throws SQLException;

	/**
	 * Elimina un membro dal sistema in base al suo ID.
	 * 
	 * @param id identificativo del membro da eliminare
	 * @return true se l'eliminazione è avvenuta, false se il membro non esiste
	 * @throws SQLException se si verifica un errore durante l'eliminazione
	 */
	boolean delete(int id) throws SQLException;

    
}
