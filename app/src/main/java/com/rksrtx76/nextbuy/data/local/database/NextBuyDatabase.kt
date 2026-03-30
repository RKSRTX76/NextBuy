package com.rksrtx76.nextbuy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rksrtx76.nextbuy.data.local.converter.StringListConverter
import com.rksrtx76.nextbuy.data.local.dao.WishlistDao
import com.rksrtx76.nextbuy.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class NextBuyDatabase : RoomDatabase(){
    abstract fun wishlistDao() : WishlistDao
}