package com.roys.wolvnote.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roys.wolvnote.domain.model.PasswordTable

@Dao
interface PasswordDao {
    @Insert
    suspend fun insertPassword(passwordTable: PasswordTable)

    @Query("SELECT * FROM password_table ORDER BY password_id DESC LIMIT 1")
    suspend fun getPassword(): PasswordTable?
}