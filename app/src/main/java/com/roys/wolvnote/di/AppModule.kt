package com.roys.wolvnote.di

import android.app.Application
import androidx.room.Room
import com.roys.wolvnote.data.database.AppDatabase
import com.roys.wolvnote.data.repository.NoteRepositoryImpl
import com.roys.wolvnote.data.repository.PasswordRepositoryImpl
import com.roys.wolvnote.domain.repository.NoteRepository
import com.roys.wolvnote.domain.repository.PasswordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(context: Application): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(db: AppDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao())
    }

    @Singleton
    @Provides
    fun providePasswordRepository(db: AppDatabase): PasswordRepository {
        return PasswordRepositoryImpl(db.passwordDao())
    }
}