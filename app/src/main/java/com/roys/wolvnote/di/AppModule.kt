package com.roys.wolvnote.di

import android.app.Application
import androidx.room.Room
import com.roys.wolvnote.data.database.AppDatabase
import com.roys.wolvnote.data.remote.ApiService
import com.roys.wolvnote.data.remote.KtorApiService
import com.roys.wolvnote.data.repository.LocationRepositoryImpl
import com.roys.wolvnote.data.repository.NoteRepositoryImpl
import com.roys.wolvnote.data.repository.PasswordRepositoryImpl
import com.roys.wolvnote.data.repository.WeatherRepositoryImpl
import com.roys.wolvnote.domain.repository.LocationRepository
import com.roys.wolvnote.domain.repository.NoteRepository
import com.roys.wolvnote.domain.repository.PasswordRepository
import com.roys.wolvnote.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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

    @Singleton
    @Provides
    fun provideLocationRepository(context: Application): LocationRepository {
        return LocationRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(apiService: ApiService): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideApiService(client: HttpClient): ApiService {
        return KtorApiService(client)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient{
        return HttpClient(CIO){
            install(ContentNegotiation){json()}
        }
    }
}