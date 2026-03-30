package com.rksrtx76.nextbuy.di

import android.content.Context
import androidx.room.Room
import com.rksrtx76.nextbuy.data.local.dao.WishlistDao
import com.rksrtx76.nextbuy.data.local.database.NextBuyDatabase
import com.rksrtx76.nextbuy.data.preference.CartDataStore
import com.rksrtx76.nextbuy.data.preference.UserPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserPreferenceDataStore(@ApplicationContext context : Context) : UserPreferencesDataStore{
        return UserPreferencesDataStore(context)
    }

    @Provides
    @Singleton
    fun provideCartDataStore(@ApplicationContext context: Context) : CartDataStore{
        return CartDataStore(context)
    }

    @Provides
    @Singleton
    fun provideNextBuyDatabase(@ApplicationContext context : Context) : NextBuyDatabase{
        return Room.databaseBuilder(
            context,
            NextBuyDatabase::class.java,
            "nextbuy_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideWishlistDao(database : NextBuyDatabase) : WishlistDao{
        return database.wishlistDao()
    }
}