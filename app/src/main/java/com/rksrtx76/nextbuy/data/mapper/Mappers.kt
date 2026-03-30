package com.rksrtx76.nextbuy.data.mapper

import com.rksrtx76.nextbuy.data.local.entity.ProductEntity
import com.rksrtx76.nextbuy.data.remote.dto.CategoryDto
import com.rksrtx76.nextbuy.data.remote.dto.ProductResponse
import com.rksrtx76.nextbuy.data.remote.dto.ReviewResponse
import com.rksrtx76.nextbuy.data.remote.dto.UserProfileDto
import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.model.Review
import com.rksrtx76.nextbuy.domain.model.UserProfile
import kotlin.math.roundToInt

private const val USD_TO_INR = 83.0

fun ProductResponse.toDomain() : Product{
    val priceInr = usdToInr(price)
    return Product(
        id = id,
        title = title,
        description = description,
        brand = brand ?: "",
        category = category,
        price = priceInr,
        discountPercentage = discountPercentage,
        discountedPrice = computeDiscountedPrice(priceInr, discountPercentage),
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        images = images,
        tags = tags,
        availabilityStatus = availabilityStatus,
        minimumOrderQuantity = minimumOrderQuantity,
        returnPolicy = returnPolicy,
        warrantyInformation = warrantyInformation,
        reviews = reviewResponses?.map { it.toDomain() } ?: emptyList()
    )
}

fun CategoryDto.toDomain() : Category{
    return Category(
        slug = slug,
        name = name,
        url = url
    )
}

private fun usdToInr(price : Double) : Double{
    return (price * USD_TO_INR * 100).roundToInt() / 100.0
}

private fun computeDiscountedPrice(price : Double, discountPercentage : Double) : Double{
    if(price <= 0.0) return 0.0
    val roundedDiscount = discountPercentage.roundToInt().toDouble()  // 10.5 → 11, 10.4 → 10
    return (price * (1 - roundedDiscount / 100) * 100).roundToInt() / 100.0
}

private fun ReviewResponse.toDomain() : Review{
    return Review(
        rating = rating,
        comment = comment,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail,
        date = date
    )
}


fun ProductEntity.toDomain() : Product{
    val priceInr = usdToInr(price)
    return Product(
        id = id,
        title = title,
        description = description,
        brand = brand,
        category = category,
        price = priceInr,
        discountPercentage = discountPercentage,
        discountedPrice = computeDiscountedPrice(priceInr, discountPercentage),
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        images = images,
        tags = tags,
        availabilityStatus = availabilityStatus,
        minimumOrderQuantity = minimumOrderQuantity,
        returnPolicy = returnPolicy,
        warrantyInformation = warrantyInformation,
        reviews = emptyList()
    )
}

fun Product.toEntity() : ProductEntity{
    val priceInr = usdToInr(price)
    return ProductEntity(
        id = id,
        availabilityStatus = availabilityStatus,
        brand = brand,
        category = category,
        description = description,
        discountPercentage = discountPercentage,
        images = images,
        minimumOrderQuantity = minimumOrderQuantity,
        price = priceInr,
        rating = rating,
        returnPolicy = returnPolicy,
        stock = stock,
        tags = tags,
        thumbnail = thumbnail,
        title = title,
        warrantyInformation = warrantyInformation
    )
}



fun UserProfileDto.toDomain() : UserProfile{
    return UserProfile(
        userId = userId,
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName.orEmpty(),
        profilePhotoUrl = profilePhotoUrl.orEmpty(),
        phoneNumber = phoneNumber.orEmpty(),
        address = address.orEmpty(),
        city = city.orEmpty(),
        state = state.orEmpty(),
        pinCode = pinCode.orEmpty(),
        country = country.orEmpty()
    )
}

fun UserProfile.toDto() : UserProfileDto {
    return UserProfileDto(
        userId = userId,
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        profilePhotoUrl = profilePhotoUrl,
        phoneNumber = phoneNumber,
        address = address,
        city = city,
        state = state,
        pinCode = pinCode,
        country = country,
    )
}