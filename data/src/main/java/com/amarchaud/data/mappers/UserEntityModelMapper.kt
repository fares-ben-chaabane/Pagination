package com.amarchaud.data.mappers

import com.amarchaud.data.models.CoordinatesEntityModel
import com.amarchaud.data.models.DobEntityModel
import com.amarchaud.data.models.IdEntityModel
import com.amarchaud.data.models.LocationEntityModel
import com.amarchaud.data.models.NameEntityModel
import com.amarchaud.data.models.PictureEntityModel
import com.amarchaud.data.models.RegisteredEntityModel
import com.amarchaud.data.models.StreetEntityModel
import com.amarchaud.data.models.TimezoneEntityModel
import com.amarchaud.data.models.UserEntityModel
import com.amarchaud.domain.models.CoordinatesModel
import com.amarchaud.domain.models.DobModel
import com.amarchaud.domain.models.IdModel
import com.amarchaud.domain.models.LocationModel
import com.amarchaud.domain.models.NameModel
import com.amarchaud.domain.models.PictureModel
import com.amarchaud.domain.models.RegisteredModel
import com.amarchaud.domain.models.StreetModel
import com.amarchaud.domain.models.TimezoneModel
import com.amarchaud.domain.models.UserModel

internal fun UserEntityModel.toDomain() = UserModel(
    localId = this._id,
    gender = this.gender,
    name = this.name?.toDomain(),
    location = this.location?.toDomain(),
    email = this.email,
    dob = this.dob?.toDomain(),
    registered = this.registered?.toDomain(),
    phone = this.phone,
    cell = this.cell,
    id = this.id?.toDomain(),
    picture = this.picture?.toDomain(),
    nat = this.nat,
)

internal fun NameEntityModel.toDomain() = NameModel(
    title = this.title,
    first = this.first,
    last = this.last
)

internal fun LocationEntityModel.toDomain() = LocationModel(
    street = this.street?.toDomain(),
    city = this.city,
    state = this.state,
    country = this.country,
    //postcode = this.postcode,
    coordinates = this.coordinates?.toDomain(),
    timezone = this.timezone?.toDomain(),
)

internal fun StreetEntityModel.toDomain() = StreetModel(
    number = this.number,
    name = this.name,
)

internal fun CoordinatesEntityModel.toDomain() = CoordinatesModel(
    latitude = this.latitude,
    longitude = this.longitude,
)

internal fun TimezoneEntityModel.toDomain() = TimezoneModel(
    offset = this.offset,
    description = this.description,
)

internal fun DobEntityModel.toDomain() = DobModel(
    date = this.date?.toString(),
    age = this.age,
)

internal fun RegisteredEntityModel.toDomain() = RegisteredModel(
    date = this.date?.toString(),
    age = this.age,
)

internal fun IdEntityModel.toDomain() = IdModel(
    name = this.name,
    value = this.value
)

internal fun PictureEntityModel.toDomain() = PictureModel(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail,
)