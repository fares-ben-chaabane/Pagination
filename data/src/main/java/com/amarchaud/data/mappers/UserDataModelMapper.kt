package com.amarchaud.data.mappers

import com.amarchaud.data.models.CoordinatesDataModel
import com.amarchaud.data.models.CoordinatesEntityModel
import com.amarchaud.data.models.DobDataModel
import com.amarchaud.data.models.DobEntityModel
import com.amarchaud.data.models.IdDataModel
import com.amarchaud.data.models.IdEntityModel
import com.amarchaud.data.models.LocationDataModel
import com.amarchaud.data.models.LocationEntityModel
import com.amarchaud.data.models.LoginDataModel
import com.amarchaud.data.models.NameDataModel
import com.amarchaud.data.models.NameEntityModel
import com.amarchaud.data.models.PictureDataModel
import com.amarchaud.data.models.PictureEntityModel
import com.amarchaud.data.models.RegisteredDataModel
import com.amarchaud.data.models.RegisteredEntityModel
import com.amarchaud.data.models.StreetDataModel
import com.amarchaud.data.models.StreetEntityModel
import com.amarchaud.data.models.TimezoneDataModel
import com.amarchaud.data.models.TimezoneEntityModel
import com.amarchaud.data.models.UserDataModel
import com.amarchaud.data.models.UserEntityModel
import com.amarchaud.domain.models.CoordinatesModel
import com.amarchaud.domain.models.DobModel
import com.amarchaud.domain.models.IdModel
import com.amarchaud.domain.models.LocationModel
import com.amarchaud.domain.models.LoginModel
import com.amarchaud.domain.models.NameModel
import com.amarchaud.domain.models.PictureModel
import com.amarchaud.domain.models.RegisteredModel
import com.amarchaud.domain.models.StreetModel
import com.amarchaud.domain.models.TimezoneModel
import com.amarchaud.domain.models.UserModel

// DataModel to domain

internal fun UserDataModel.toDomain() = UserModel(
    localId = -1, // only when passing from entity to domain
    gender = this.gender,
    name = this.name?.toDomain(),
    location = this.location?.toDomain(),
    email = this.email,
    login = this.login?.toDomain(),
    dob = this.dob?.toDomain(),
    registered = this.registered?.toDomain(),
    phone = this.phone,
    cell = this.cell,
    id = this.id?.toDomain(),
    picture = this.picture?.toDomain(),
    nat = this.nat,
)

internal fun NameDataModel.toDomain() = NameModel(
    title = this.title,
    first = this.first,
    last = this.last
)

internal fun LocationDataModel.toDomain() = LocationModel(
    street = this.street?.toDomain(),
    city = this.city,
    state = this.state,
    country = this.country,
    /*
    postcode = when(this.postcode) {
        is Int -> this.postcode.toString()
        is String -> this.postcode
        else -> throw Exception("impossible")
    },*/
    coordinates = this.coordinates?.toDomain(),
    timezone = this.timezone?.toDomain(),
)

internal fun StreetDataModel.toDomain() = StreetModel(
    number = this.number,
    name = this.name,
)

internal fun CoordinatesDataModel.toDomain() = CoordinatesModel(
    latitude = this.latitude,
    longitude = this.longitude,
)

internal fun TimezoneDataModel.toDomain() = TimezoneModel(
    offset = this.offset,
    description = this.description,
)

internal fun LoginDataModel.toDomain() = LoginModel(
    uuid = this.uuid,
    username = this.username,
    password = this.password,
    salt = this.salt,
    md5 = this.md5,
    sha1 = this.sha1,
    sha256 = this.sha256,
)

internal fun DobDataModel.toDomain() = DobModel(
    date = this.date?.toString(),
    age = this.age,
)

internal fun RegisteredDataModel.toDomain() = RegisteredModel(
    date = this.date?.toString(),
    age = this.age,
)

internal fun IdDataModel.toDomain() = IdModel(
    name = this.name,
    value = this.value
)

internal fun PictureDataModel.toDomain() = PictureModel(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail,
)


// DataModel to EntityModel

internal fun UserDataModel.toEntity() = UserEntityModel(
    gender = this.gender,
    name = this.name?.toEntity(),
    location = this.location?.toEntity(),
    email = this.email.orEmpty(),
    dob = this.dob?.toEntity(),
    registered = this.registered?.toEntity(),
    phone = this.phone,
    cell = this.cell,
    id = this.id?.toEntity(),
    picture = this.picture?.toEntity(),
    nat = this.nat,
)

internal fun NameDataModel.toEntity() = NameEntityModel(
    title = this.title,
    first = this.first,
    last = this.last
)

internal fun LocationDataModel.toEntity() = LocationEntityModel(
    street = this.street?.toEntity(),
    city = this.city,
    state = this.state,
    country = this.country,
    /*
    postcode = when(this.postcode) {
        is Int -> this.postcode.toString()
        is String -> this.postcode
        else -> throw Exception("impossible")
    },*/
    coordinates = this.coordinates?.toEntity(),
    timezone = this.timezone?.toEntity(),
)

internal fun StreetDataModel.toEntity() = StreetEntityModel(
    number = this.number,
    name = this.name,
)

internal fun CoordinatesDataModel.toEntity() = CoordinatesEntityModel(
    latitude = this.latitude,
    longitude = this.longitude,
)

internal fun TimezoneDataModel.toEntity() = TimezoneEntityModel(
    offset = this.offset,
    description = this.description,
)


internal fun DobDataModel.toEntity() = DobEntityModel(
    date = this.date,
    age = this.age,
)

internal fun RegisteredDataModel.toEntity() = RegisteredEntityModel(
    date = this.date,
    age = this.age,
)

internal fun IdDataModel.toEntity() = IdEntityModel(
    name = this.name,
    value = this.value
)

internal fun PictureDataModel.toEntity() = PictureEntityModel(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail,
)
