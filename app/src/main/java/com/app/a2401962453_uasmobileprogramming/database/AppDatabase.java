package com.app.a2401962453_uasmobileprogramming.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.a2401962453_uasmobileprogramming.model.Ticket;

@Database(entities = {Ticket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TicketDao ticketDao();
}
