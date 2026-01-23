package com.generation.acmc.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.library.Console;

public class MainTestConnection
{
	public static void main(String[] args)
	{
	    try
	    {
	        // Test 1: Get connection from Context
	    	Console.print("=== TEST CONNESSIONE DATABASE ===");
	        Connection connection = Context.getDependency(Connection.class);

	        if (connection != null && !connection.isClosed())
	        {
	        	Console.print("✓ Connessione al database riuscita!");
	        	Console.print("  Database: acmc.db");
	        }
	        else
	        {
	            Console.print("✗ Connessione fallita!");
	            return;
	        }

	        // Test 2: Insert a test member
	        Console.print("\n=== TEST INSERIMENTO MEMBER ===");
	        Member x = new Member();
	        x.setFirstName("John");
	        x.setLastName("Doe");
	        x.setGender("M");
	        x.setDob(LocalDate.of(1990, 1, 1));
	        x.setIncomeEst(new BigDecimal("1500000")); // Almeno 1 milione
	        x.setLevel(MembershipLevel.BRONZE);

	        String sql = "INSERT INTO member(firstName, lastname, gender, dob, incomeEst, level) VALUES(?,?,?,?,?,?)";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, x.getFirstName());
	        ps.setString(2, x.getLastName());
	        ps.setString(3, x.getGender());
	        ps.setString(4, x.getDob().toString()); // Salva la data come stringa YYYY-MM-DD
	        ps.setBigDecimal(5, x.getIncomeEst());
	        ps.setString(6, x.getLevel().name());

	        int rows = ps.executeUpdate();

	        if (rows > 0)
	        {
	        	Console.print("✓ Member inserito con successo!");
	        	Console.print("  Nome: " + x.getFirstName() + " " + x.getLastName());
	        	Console.print("  Livello: " + x.getLevel());
	        	Console.print("  Reddito: " + x.getIncomeEst());
	        }

	    }
	    catch (Exception e)
	    {
	    	Console.print("\n✗ ERRORE durante il test:");
	        e.printStackTrace();
	    }
	}

	
}
