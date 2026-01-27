package com.generation.acmc2.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc2.model.entities.Member;

public interface MemberRepository 
{
	List<Member> findAll() throws SQLException;	
	List<Member> findWhere(String cond) throws SQLException;
	Member findById(int id) throws SQLException;	
	// se complete = true, caricherò anche le sue donazioni
	Member findById(int id, boolean complete) throws SQLException;
	
	Member update(Member m) throws SQLException;	
	Member insert(Member m) throws SQLException;
	boolean delete(int id) throws SQLException;
	

}
