package com.hopeco.phonelist.di

import android.content.Context
import androidx.room.Room
import com.hopeco.phonelist.data.database.PersonDao
import com.hopeco.phonelist.data.database.PersonDatabase
import com.hopeco.phonelist.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : PersonDatabase
    {
        return Room.databaseBuilder(
            context,
            PersonDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(personDatabase: PersonDatabase) : PersonDao
    {
        return personDatabase.personDao()
    }
}