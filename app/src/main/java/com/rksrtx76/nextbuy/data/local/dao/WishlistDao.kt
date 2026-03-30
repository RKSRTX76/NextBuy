package com.rksrtx76.nextbuy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rksrtx76.nextbuy.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM products_wishlist")
    fun getAllWishlistItems() : Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_wishlist WHERE id = :productId")
    suspend fun getWishlistItemById(productId : Int) : ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(product: ProductEntity)

    @Query("DELETE FROM products_wishlist WHERE id = :productId")
    suspend fun deleteWishlistItem(productId : Int)


    @Query("DELETE FROM products_wishlist")
    suspend fun clearWishlist()

    @Query("SELECT EXISTS(SELECT 1 FROM products_wishlist WHERE id = :productId)")
    suspend fun isInWishlist(productId : Int) : Boolean

    @Query("SELECT COUNT(*) FROM products_wishlist")
    suspend fun getWishlistCount() : Int



}