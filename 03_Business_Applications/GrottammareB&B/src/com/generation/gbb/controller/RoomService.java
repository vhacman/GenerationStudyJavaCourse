package com.generation.gbb.controller;

import java.util.List;

import com.generation.gbb.model.entities.Room;
import com.generation.gbb.repository.RoomRepository;
import com.generation.gbb.view.RoomView;
import com.generation.gbb.view.RoomViewFactory;
import com.generation.library.Console;

public class RoomService
{
    private RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo)
    {
        this.roomRepo = roomRepo;
    }

    public void insertRoomFromConsole()
    {
        Room r = new Room();
        try
        {
            Console.print("Inserire nome stanza:");
            r.setName(Console.readString());
            Console.print("Inserire descrizione:");
            r.setDescription(Console.readString());
            Console.print("Inserire dimensione (mq):");
            r.setSize(Console.readDouble());
            Console.print("Inserire piano (0-6):");
            r.setFloor(Console.readInt());
            Console.print("Inserire prezzo per notte (€):");
            r.setPrice(Console.readDouble());

            roomRepo.insert(r);
            Console.print("Stanza inserita con successo!");
        }
        catch(RuntimeException e)
        {
            Console.print("Salvataggio non riuscito");
            Console.print(e.getMessage());
            for (String error : r.getErrors())
                Console.print(error);
        }
    }

    public void roomList()
    {
        List<Room> all = roomRepo.findAll();
        RoomView view = RoomViewFactory.make("summary");
        Console.print("\n=== Lista Stanze Disponibili (" + all.size() + ") ===");
        Console.print(view.render(all));
    }

    public void findRoomById()
    {
        Console.print("Inserire id stanza:");
        int  id = Console.readInt();
        Room r  = roomRepo.findById(id);
        if (r == null)
            Console.print("Stanza non trovata");
        else
        {
            RoomView view = RoomViewFactory.make("full");
            Console.print(view.render(r));
        }
    }

    public void findRoomByNameOrDescriptionContaining()
    {
        Console.print("Inserire parte del nome o della descrizione:");
        String      part  = Console.readString().trim();
        List<Room>  found = roomRepo.findByNameOrDescriptionContaining(part);
        if (found.isEmpty())
            Console.print("Nessuna stanza trovata con '" + part + "'");
        else
        {
            Console.print("\n=== " + found.size() + " Stanze Trovate ===");
            RoomView view = RoomViewFactory.make("summary");
            Console.print(view.render(found));
        }
    }

    public void findRoomsByPriceLesserThan()
    {
        Console.print("Inserire budget massimo:");
        int          budget = Console.readInt();
        List<Room>   rooms  = roomRepo.findByPriceLesserThan(budget);
        if (rooms.isEmpty())
            Console.print("Nessuna stanza sotto €" + budget);
        else
        {
            Console.print("\n=== " + rooms.size() + " Stanze sotto €" + budget + " ===");
            RoomView view = RoomViewFactory.make("summary");
            Console.print(view.render(rooms));
        }
    }

    /**
     * Updates an existing room from console input.
     * Prompts for room ID, displays current data, and allows field modification.
     */
    public void updateRoomFromConsole()
    {
        Console.print("Inserire ID stanza da modificare:");
        int  id = Console.readInt();
        Room r  = roomRepo.findById(id);

        if (r == null)
        {
            Console.print("Stanza non trovata");
            return;
        }

        RoomView view = RoomViewFactory.make("full");
        Console.print("Stanza attuale:");
        Console.print(view.render(r));

        try
        {
            Console.print("Nuovo nome (invio per mantenere):");
            String name = Console.readString();
            if (!name.trim().isEmpty()) r.setName(name);

            Console.print("Nuova descrizione (invio per mantenere):");
            String description = Console.readString();
            if (!description.trim().isEmpty()) r.setDescription(description);

            Console.print("Nuova dimensione mq (0 per mantenere):");
            double size = Console.readDouble();
            if (size > 0) r.setSize(size);

            Console.print("Nuovo piano (-1 per mantenere):");
            int floor = Console.readInt();
            if (floor >= 0) r.setFloor(floor);

            Console.print("Nuovo prezzo € (0 per mantenere):");
            double price = Console.readDouble();
            if (price > 0) r.setPrice(price);

            roomRepo.update(r);
            Console.print("Stanza aggiornata con successo!");
        }
        catch(RuntimeException e)
        {
            Console.print("Aggiornamento non riuscito");
            Console.print(e.getMessage());
            for (String error : r.getErrors())
                Console.print(error);
        }
    }

    /**
     * Deletes a room from repository by ID.
     * Prompts for room ID, displays details, and confirms deletion.
     */
    public void deleteRoom()
    {
        Console.print("Inserire ID stanza da eliminare:");
        int  id = Console.readInt();
        Room r  = roomRepo.findById(id);

        if (r == null)
        {
            Console.print("Stanza non trovata");
            return;
        }

        RoomView view = RoomViewFactory.make("full");
        Console.print(view.render(r));
        Console.print("Si vuole cancellare questa stanza?");
        if (Console.readString().equals("S"))
        {
            boolean result = roomRepo.delete(id);
            Console.print(result ? "Cancellato" : "Stanza non trovata");
        }
    }

    /**
     * Modifies the price of an existing room.
     * Prompts for room ID and new price value.
     */
    public void modifyRoomPrice()
    {
        Console.print("Inserire ID stanza:");
        int  id = Console.readInt();
        Room r  = roomRepo.findById(id);

        if (r == null)
        {
            Console.print("Stanza non trovata");
            return;
        }

        RoomView view = RoomViewFactory.make("full");
        Console.print("Stanza attuale:");
        Console.print(view.render(r));
        Console.print("Inserire nuovo prezzo per notte (€):");
        int newPrice = Console.readInt();

        try
        {
            r.setPrice(newPrice);
            roomRepo.update(r);
            Console.print("Prezzo stanza aggiornato con successo!");
        }
        catch(RuntimeException e)
        {
            Console.print("Aggiornamento non riuscito");
            Console.print(e.getMessage());
            for (String error : r.getErrors())
                Console.print(error);
        }
    }
}
