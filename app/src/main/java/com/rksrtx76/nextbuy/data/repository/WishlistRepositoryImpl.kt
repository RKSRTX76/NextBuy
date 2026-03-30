package com.rksrtx76.nextbuy.data.repository

import com.rksrtx76.nextbuy.data.local.dao.WishlistDao
import com.rksrtx76.nextbuy.data.mapper.toDomain
import com.rksrtx76.nextbuy.data.mapper.toEntity
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val wishlistDao: WishlistDao
) : WishlistRepository{
    override fun getWishlistProducts(): Flow<List<Product>> {
        return wishlistDao.getAllWishlistItems().map { productEntities ->
            productEntities.map {
                it.toDomain()
            }
        }
    }

    override suspend fun addToWishlist(product: Product) {
        return  wishlistDao.insertWishlistItem(product.toEntity())
    }

    override suspend fun removeFromWishlist(productId: Int) {
        return  wishlistDao.deleteWishlistItem(productId)
    }

    override suspend fun isInWishlist(productId: Int): Boolean {
        return  wishlistDao.isInWishlist(productId)
    }

    override suspend fun clearWishlist() {
        return  wishlistDao.clearWishlist()
    }

    override suspend fun getWishlistCount(): Int {
        return  wishlistDao.getWishlistCount()
    }

}