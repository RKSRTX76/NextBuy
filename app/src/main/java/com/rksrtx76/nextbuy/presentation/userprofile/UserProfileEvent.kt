package com.rksrtx76.nextbuy.presentation.userprofile

sealed class UserProfileEvent {
    data class FirstNameChanged(val value : String) : UserProfileEvent()
    data class LastNameChanged(val value : String) : UserProfileEvent()
    data class PhoneNumberChanged(val value : String) : UserProfileEvent()

    data class PasswordChanged(val value : String) : UserProfileEvent()
    data class AddressChanged(val value : String) : UserProfileEvent()
    data class CityChanged(val value : String) : UserProfileEvent()
    data class StateChanged(val value : String) : UserProfileEvent()
    data class PinCodeChanged(val value : String) : UserProfileEvent()
    data class CountryChanged(val value : String) : UserProfileEvent()
    data class ProfilePhotoChanged(val value : String) : UserProfileEvent()
    data object SaveProfile : UserProfileEvent()
    data object UpdateProfile : UserProfileEvent()
    data object ResetSaveSuccess : UserProfileEvent()
    data object ClearError : UserProfileEvent()
}
