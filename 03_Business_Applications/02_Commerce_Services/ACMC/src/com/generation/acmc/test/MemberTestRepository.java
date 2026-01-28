package com.generation.acmc.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.acmc.model.repository.SQLMemberRepository;

public class MemberTestRepository
{
    private SQLMemberRepository repo = Context.getDependency(SQLMemberRepository.class);

    @Test
    void testFindByLastName() throws Exception
    {
        Member member = new Member();
        member.setFirstName("Mario");
        member.setLastName("Rossi");
        member.setGender("M");
        member.setDob(LocalDate.of(1990, 5, 15));
        member.setIncomeEst(new BigDecimal("2500000.00"));
        member.setLevel(MembershipLevel.GOLD);

        repo.insert(member); // Usa insert() per creare un nuovo record

        Member found = repo.findByLastName("Rossi");
        assertNotNull(found, "Member should not be null");
    }


    @Test
    void testFindByLastNameContaining() throws Exception
    {
        Member member1 = new Member();
        member1.setFirstName("Mario");
        member1.setLastName("Rossi");
        member1.setGender("M");
        member1.setDob(LocalDate.of(1990, 5, 15));
        member1.setIncomeEst(new BigDecimal("2500000.00"));
        member1.setLevel(MembershipLevel.GOLD);

        Member member2 = new Member();
        member2.setFirstName("Luca");
        member2.setLastName("Rossini");
        member2.setGender("M");
        member2.setDob(LocalDate.of(1985, 3, 20));
        member2.setIncomeEst(new BigDecimal("3000000.00"));
        member2.setLevel(MembershipLevel.SILVER);

        repo.insert(member1); // Usa insert() per creare nuovi record
        repo.insert(member2);

        List<Member> found = repo.findByLastNameContaining("Ross");
        assertNotNull(found, "List should not be null");
    }

    @Test
    void testFindByLevel() throws Exception
    {
        Member member1 = new Member();
        member1.setFirstName("Mario");
        member1.setLastName("Rossi");
        member1.setGender("M");
        member1.setDob(LocalDate.of(1990, 5, 15));
        member1.setIncomeEst(new BigDecimal("2500000.00"));
        member1.setLevel(MembershipLevel.GOLD);

        Member member2 = new Member();
        member2.setFirstName("Luca");
        member2.setLastName("Verdi");
        member2.setGender("M");
        member2.setDob(LocalDate.of(1985, 3, 20));
        member2.setIncomeEst(new BigDecimal("3000000.00"));
        member2.setLevel(MembershipLevel.GOLD);

        repo.insert(member1); // Usa insert() per creare nuovi record
        repo.insert(member2);

        List<Member> found = repo.findByLevel(MembershipLevel.GOLD);
        assertNotNull(found, "List should not be null");
    }

    @Test
    void testGetInsertCmd() throws Exception
    {
        Member member = new Member();
        member.setFirstName("PIETRO");
        member.setLastName("Rossi");
        member.setGender("M");
        member.setDob(LocalDate.of(1990, 5, 15));
        member.setIncomeEst(new BigDecimal("2500000.00"));
        member.setLevel(MembershipLevel.GOLD);

        PreparedStatement ps = repo.getInsertCmd(member);
        //assert <espressione booleana> : "<messaggio di errore>";
        assert ps != null : "PreparedStatement should not be null";
    }

    @Test
    void testGetUpdateCmd() throws Exception
    {
        Member member = new Member();
        member.setId(1);
        member.setFirstName("Mario");
        member.setLastName("Rossi");
        member.setGender("M");
        member.setDob(LocalDate.of(1990, 5, 15));
        member.setIncomeEst(new BigDecimal("2500000.00"));
        member.setLevel(MembershipLevel.GOLD);

        PreparedStatement ps = repo.getUpdateCmd(member);
        assertNotNull(ps, "PreparedStatement should not be null");
    }


}
