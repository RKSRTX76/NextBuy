package com.rksrtx76.nextbuy.di

import com.rksrtx76.nextbuy.data.repository.AuthRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.CartRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.ProductRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.UserPreferenceRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.UserProfileRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.WishlistRepositoryImpl
import com.rksrtx76.nextbuy.domain.repository.AuthRepository
import com.rksrtx76.nextbuy.domain.repository.CartRepository
import com.rksrtx76.nextbuy.domain.repository.ProductRepository
import com.rksrtx76.nextbuy.domain.repository.UserPreferenceRepository
import com.rksrtx76.nextbuy.domain.repository.UserProfileRepository
import com.rksrtx76.nextbuy.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        userProfileRepositoryImpl: UserProfileRepositoryImpl
    ) : UserProfileRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferenceRepository(
        userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl
    ) : UserPreferenceRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ) : ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ) : CartRepository

    @Binds
    @Singleton
    abstract fun bindWishlistRepository(
        wishlistRepositoryImpl: WishlistRepositoryImpl
    ) : WishlistRepository

}