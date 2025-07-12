package com.roys.wolvnote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteTable::class, PasswordTable::class],
    version = 1,
    exportSchema = false,
    autoMigrations = []
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun passwordDao(): PasswordDao
}