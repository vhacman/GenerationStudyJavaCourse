package com.generation.pl.controller;

import java.sql.SQLException;
import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.interfaces.AdminRepository;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            AdminRepository adminRepo = Context.getDependency(AdminRepositorySQL.class);
            if (!adminRepo.existsAnyAdmin())
            {
                AdminMain.insertAdmin(true);
            }
        }
        catch (SQLException e)
        {
            Console.print("Errore nel controllo admin: " + e.getMessage());
            return;
        }        
        boolean exit = false;
        while (!exit)
        {            
            String menuContent = Template.load("template/txt/menu/main_menu.txt");
            Console.print(menuContent);            
            String choice = Console.readString().trim().toLowerCase();            
            switch(choice)
            {
                case "1":
                case "student":
                    new StudentMain().run();
                    break;
                case "2":
                case "teacher":
                    new TeacherMain().run();
                    break;
                case "3":
                case "admin":
                    new AdminMain().run();
                    break;
                case "0":
                case "exit":
                case "quit":
                    Console.print("Arrivederci!");
                    exit = true;
                    break;
                default:
                    Console.print("Opzione non valida. Riprova.");
            }
        }
    }
}
