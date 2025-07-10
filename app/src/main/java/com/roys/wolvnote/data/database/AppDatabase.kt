package com.roys.wolvnote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteTable::class, PasswordTable::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun passwordDao(): PasswordDao
}