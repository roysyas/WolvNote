package com.roys.wolvnote

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.roys.wolvnote.common.Constants
import com.roys.wolvnote.data.database.AppDatabase
import com.roys.wolvnote.data.database.NoteDao
import com.roys.wolvnote.data.database.NoteTable
import com.roys.wolvnote.data.repository.NoteRepositoryImpl
import com.roys.wolvnote.domain.repository.NoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class NoteTableTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var noteDao: NoteDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var noteRepository: NoteRepository

    @Before
    fun setup(){
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        noteDao = appDatabase.noteDao()
        noteRepository = NoteRepositoryImpl(noteDao)
    }

    @After
    fun teardown(){
        appDatabase.close()
    }

    @Test
    fun insert() = runTest{
        val noteTableTest = NoteTable(
            1,
            "24/05/1981",
            "testing title",
            "testing content note",
            Constants.CATEGORY_NOTE,
            "24/09/1989"
        )

        noteRepository.insertNote(noteTableTest)

        val compare: MutableList<NoteTable> = mutableListOf()
        compare.add(noteTableTest)


        val notes = noteRepository.getNotes().first()
        Log.d("resultNote", notes.toString())
        assertTrue(compare==notes)
    }
}