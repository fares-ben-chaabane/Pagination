package com.amarchaud.domain.models

import java.time.LocalDateTime

data class UserModel(
    val localId: Long,
    val gender: String? = null,
    val name: NameModel? = null,
    val location: LocationModel? = null,
    val email: String? = null,
    val login: LoginModel? = null,
    val dob: DobModel? = null,
    val registered: RegisteredModel? = null,
    val phone: String? = null,
    val cell: String? = null,
    val id: IdModel? = null,
    val picture: PictureModel? = null,
    val nat: String? = null,
)

data class NameModel(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
)

data class LocationModel(
    val street: StreetModel? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    //val postcode: String? = null,
    val coordinates: CoordinatesModel? = null,
    val timezone: TimezoneModel? = null,
)

data class StreetModel(
    val number: Long? = null,
    val name: String? = null,
)

data class CoordinatesModel(
    val latitude: String? = null,
    val longitude: String? = null,
)

data class TimezoneModel(
    val offset: String? = null,
    val description: String? = null,
)

data class LoginModel(
    val uuid: String? = null,
    val username: String? = null,
    val password: String? = null,
    val salt: String? = null,
    val md5: String? = null,
    val sha1: String? = null,
    val sha256: String? = null,
)

data class DobModel(
    val date: String? = null,
    val age: Long? = null,
)

data class RegisteredModel(
    val date: String? = null,
    val age: Long? = null,
)

data class IdModel(
    val name: String? = null,
    val value: String? = null,
)

data class PictureModel(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null,
)

// current page
data class InfoModel(
    val seed: String? = null,
    val results: Long? = null,
    val page: Long? = null,
    val version: String? = null,
)
