package com.generation.acmc.test;

import java.sql.PreparedStatement;
import org.junit.jupiter.api.Test;
import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Expense;
import com.generation.acmc.model.repository.SQLExpenseRepository;

public class ExpenseTestRepository
{
    private SQLExpenseRepository repo = Context.getDependency(SQLExpenseRepository.class);

    @Test
    void insertCmdTest() throws Exception
    {
       Expense expense = new Expense();
       expense.setReason("Prova1");
       expense.setDate("2026-01-30");
       expense.setCost("45000");
       
       PreparedStatement ps = repo.getInsertCmd(expense);
       //assert <espressione booleana> : "<messaggio di errore>";

       assert ps!= null : "Ps should not be null";
    }
    
    @Test
    void getUpdateCmdTest() throws Exception {
        Expense expense = new Expense();
        expense.setReason("Prova1");
        expense.setDate("2026-01-30");
        expense.setCost("45000");

        PreparedStatement ps = repo.getUpdateCmd(expense);
        assert ps != null : "getUpdateCmd() should not return null";
    }
    
}
