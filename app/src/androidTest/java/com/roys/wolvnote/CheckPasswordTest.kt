package com.roys.wolvnote

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.roys.wolvnote.data.database.AppDatabase
import com.roys.wolvnote.data.database.PasswordDao
import com.roys.wolvnote.domain.model.PasswordTable
import com.roys.wolvnote.data.repository.PasswordRepositoryImpl
import com.roys.wolvnote.domain.repository.PasswordRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class CheckPasswordTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var passwordDao: PasswordDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var passwordRepository: PasswordRepository

    @Before
    fun setup(){
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        passwordDao = appDatabase.passwordDao()
        passwordRepository = PasswordRepositoryImpl(passwordDao)
    }

    @After
    fun tearDown(){
        appDatabase.close()
    }

    @Test
    fun checkPasswordEmpty()= runTest{
        val result = passwordRepository.getPassword()
        Log.d("resultPassword", result.toString())
    }

    @Test
    fun checkPassword()= runTest {
        val passwordTableTest = PasswordTable(
            1,
            "passwordTest123",
            "hintTest123"
        )

        passwordRepository.insertPassword(passwordTableTest)

        val result = passwordRepository.getPassword()
        Log.d("resultPassword", result.toString())
        assertTrue(passwordTableTest==result)
    }
}