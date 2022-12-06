package com.example.learnenglish.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learnenglish.Entities.EventListEntities;
import com.example.learnenglish.Entities.MyNoteEntities;
import com.example.learnenglish.Entities.MyRemainderEntities;
import com.example.learnenglish.dao.MyNotesDao;

@Database(entities = {MyNoteEntities.class, MyRemainderEntities.class, EventListEntities.class},version = 1,exportSchema = false)
public abstract class MyNoteDatabase extends RoomDatabase {

    public static MyNoteDatabase myNoteDatabase;

    public static synchronized MyNoteDatabase getMyNoteDatabase(Context context){

        if (myNoteDatabase == null)
        {
            myNoteDatabase= Room.databaseBuilder(
                    context,
                    MyNoteDatabase.class,
                    "note_db"
            ).build();
        }
        return myNoteDatabase;
    }
    public abstract MyNotesDao notesDao();
}
