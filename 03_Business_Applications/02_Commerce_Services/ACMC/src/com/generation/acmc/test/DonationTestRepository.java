package com.generation.acmc.test;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Donation;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.repository.SQLDonationRepository;

// Assicurati che Mockito sia nel classpath: mockito-core
public class DonationTestRepository
{

    private SQLDonationRepository repo = Context.getDependency(SQLDonationRepository.class);

    @Test
    void insertCmdTest() throws Exception {
        // Dati di test
        Member member = new Member();

        Donation donation = new Donation();
        donation.setMember(member);
        donation.setAmount(new BigDecimal("50.00"));
        donation.setDate(LocalDate.of(2025, 1, 1));
        donation.setNotes("Test");

        PreparedStatement ps = repo.getInsertCmd(donation);

        // Verifica che il PreparedStatement non sia null
        assert ps != null : "getInsertCmd() should not return null";
    }

    @Test
    void updateCmdTest() throws Exception {
        // Dati di test
        Member member = new Member();

        Donation donation = new Donation();
        donation.setMember(member);
        donation.setAmount(new BigDecimal("50.00"));
        donation.setDate(LocalDate.of(2025, 1, 1));
        donation.setNotes("Test");

        PreparedStatement ps = repo.getUpdateCmd(donation);

        // Verifica che il PreparedStatement non sia null
        assert ps != null : "getUpdateCmd() should not return null";
    }

}
