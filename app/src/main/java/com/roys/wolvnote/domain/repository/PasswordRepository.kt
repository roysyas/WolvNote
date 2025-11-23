package com.roys.wolvnote.domain.repository

import com.roys.wolvnote.domain.model.PasswordTable

interface PasswordRepository {
    suspend fun insertPassword(passwordTable: PasswordTable)
    suspend fun getPassword(): PasswordTable?
}