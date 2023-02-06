package com.app.a2401962453_uasmobileprogramming.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.a2401962453_uasmobileprogramming.model.Ticket;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM ticket")
    List<Ticket> getAll();

    @Query("SELECT * FROM ticket WHERE id IN (:ticketIds)")
    List<Ticket> loadAllByIds(int[] ticketIds);

    @Insert
    void insertAll(Ticket... tickets);

    @Delete
    void delete(Ticket ticket);
}
