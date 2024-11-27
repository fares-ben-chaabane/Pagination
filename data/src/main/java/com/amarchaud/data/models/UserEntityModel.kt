package com.amarchaud.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "Users",
)
data class UserEntityModel(
    @PrimaryKey(autoGenerate = true)
    val _id: Long = 0,
    @ColumnInfo(name = "gender")
    val gender: String? = null,
    @Embedded
    val name: NameEntityModel? = null,
    @Embedded
    val location: LocationEntityModel? = null,
    @ColumnInfo(name = "email")
    val email: String,
    @Embedded
    val dob: DobEntityModel? = null,
    @Embedded
    val registered: RegisteredEntityModel? = null,
    @ColumnInfo(name = "phone")
    val phone: String? = null,
    @ColumnInfo(name = "cell")
    val cell: String? = null,
    @Embedded
    val id: IdEntityModel? = null,
    @Embedded
    val picture: PictureEntityModel? = null,
    @ColumnInfo(name = "nat")
    val nat: String? = null,
)

data class NameEntityModel(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
)

data class LocationEntityModel(
    @Embedded
    val street: StreetEntityModel? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postcode: String? = null,
    @Embedded
    val coordinates: CoordinatesEntityModel? = null,
    @Embedded
    val timezone: TimezoneEntityModel? = null,
)

data class StreetEntityModel(
    @ColumnInfo(name = "street_number")
    val number: Long? = null,
    @ColumnInfo(name = "street_name")
    val name: String? = null,
)

data class CoordinatesEntityModel(
    val latitude: String? = null,
    val longitude: String? = null,
)

data class TimezoneEntityModel(
    val offset: String? = null,
    val description: String? = null,
)

data class DobEntityModel(
    @ColumnInfo(name = "dob_date")
    val date: LocalDateTime? = null,
    @ColumnInfo(name = "dob_age")
    val age: Long? = null,
)

data class RegisteredEntityModel(
    val date: LocalDateTime? = null,
    val age: Long? = null,
)

data class IdEntityModel(
    val name: String? = null,
    val value: String? = null,
)

data class PictureEntityModel(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null,
)
