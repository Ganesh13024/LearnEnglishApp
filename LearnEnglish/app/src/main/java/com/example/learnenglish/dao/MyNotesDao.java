package com.example.learnenglish.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.learnenglish.Entities.EventListEntities;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.Entities.MyRemainderEntities;

import java.util.List;

@Dao
public interface MyNotesDao {
    @Query("SELECT * FROM note ORDER BY id DESC")
    List<MyNoteEntities> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(MyNoteEntities noteEntities);

    @Delete
    void deleteNotes(MyNoteEntities noteEntities);



    @Query("SELECT * FROM reminder ORDER BY id DESC")
    List<MyRemainderEntities> getAllRemainder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRemainder(MyRemainderEntities remainderEntities);

    @Delete
    void deleteRemainder(MyRemainderEntities remainderEntities);

    @Query("SELECT * FROM eventList ORDER BY id DESC")
    List<EventListEntities> getAllEventsList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(EventListEntities eventList);
}
