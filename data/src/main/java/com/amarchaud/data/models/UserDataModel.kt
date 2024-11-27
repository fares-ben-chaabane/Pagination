package com.amarchaud.data.models

import com.amarchaud.data.adapters.ForceString
import com.amarchaud.data.adapters.LocalDateTimeSerializer
import com.amarchaud.data.adapters.OneStringSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsDataModel(
    @SerialName("results")
    val users: List<UserDataModel> = emptyList(),
    @SerialName("info")
    val info: InfoDataModel? = null,
)

@Serializable
data class UserDataModel(
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("name")
    val name: NameDataModel? = null,
    @SerialName("location")
    val location: LocationDataModel? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("login")
    val login: LoginDataModel? = null,
    @SerialName("dob")
    val dob: DobDataModel? = null,
    @SerialName("registered")
    val registered: RegisteredDataModel? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("cell")
    val cell: String? = null,
    @SerialName("id")
    val id: IdDataModel? = null,
    @SerialName("picture")
    val picture: PictureDataModel? = null,
    @SerialName("nat")
    val nat: String? = null,
)

@Serializable
data class NameDataModel(
    @SerialName("title")
    val title: String? = null,
    @SerialName("first")
    val first: String? = null,
    @SerialName("last")
    val last: String? = null,
)

@Serializable
data class LocationDataModel(
    @SerialName("street")
    val street: StreetDataModel? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("state")
    val state: String? = null,
    @SerialName("country")
    val country: String? = null,
    @Serializable(with = OneStringSerializer::class)
    val postcode: ForceString? = null, // problem with api, sometimes Int, sometimes String
    @SerialName("coordinates")
    val coordinates: CoordinatesDataModel? = null,
    @SerialName("timezone")
    val timezone: TimezoneDataModel? = null,
)

@Serializable
data class StreetDataModel(
    @SerialName("number")
    val number: Long? = null,
    @SerialName("name")
    val name: String? = null,
)

@Serializable
data class CoordinatesDataModel(
    @SerialName("latitude")
    val latitude: String? = null,
    @SerialName("longitude")
    val longitude: String? = null,
)

@Serializable
data class TimezoneDataModel(
    @SerialName("offset")
    val offset: String? = null,
    @SerialName("description")
    val description: String? = null,
)

@Serializable
data class LoginDataModel(
    @SerialName("uuid")
    val uuid: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("password")
    val password: String? = null,
    @SerialName("salt")
    val salt: String? = null,
    @SerialName("md5")
    val md5: String? = null,
    @SerialName("sha1")
    val sha1: String? = null,
    @SerialName("sha256")
    val sha256: String? = null,
)

@Serializable
data class DobDataModel(
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("date")
    val date: LocalDateTime? = null,
    @SerialName("age")
    val age: Long? = null,
)

@Serializable
data class RegisteredDataModel(
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("date")
    val date: LocalDateTime? = null,
    @SerialName("age")
    val age: Long? = null,
)

@Serializable
data class IdDataModel(
    @SerialName("name")
    val name: String? = null,
    @SerialName("value")
    val value: String? = null,
)

@Serializable
data class PictureDataModel(
    @SerialName("large")
    val large: String? = null,
    @SerialName("medium")
    val medium: String? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
)

// current page
@Serializable
data class InfoDataModel(
    @SerialName("seed")
    val seed: String? = null,
    @SerialName("results")
    val results: Long? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("version")
    val version: String? = null,
)
