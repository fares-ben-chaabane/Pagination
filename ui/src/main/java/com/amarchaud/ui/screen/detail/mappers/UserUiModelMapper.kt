package com.amarchaud.ui.screen.detail.mappers

import com.amarchaud.domain.models.UserModel
import com.amarchaud.ui.screen.detail.models.UserDetailUiModel
import com.amarchaud.ui.screen.mainList.mappers.toGenericUiModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.osmdroid.util.GeoPoint
import kotlinx.datetime.toLocalDateTime

internal fun UserModel.toDetailUiModel() = UserDetailUiModel(
    mainInfo = this.toGenericUiModel(),
    mainImageUrl = this.picture?.large.orEmpty(),
    coordinates = GeoPoint(
        this.location?.coordinates?.latitude?.toDouble() ?: 0.0,
        this.location?.coordinates?.longitude?.toDouble() ?: 0.0
    ),
    address = "${this.location?.street?.number} ${this.location?.street?.name} ${this.location?.city}",
    phoneNumber = "${this.phone}",
    birthday = "${this.dob?.date?.toUiDate()}"
)

fun formatDateTime(localDateTime: LocalDateTime): String {
    val monthName = localDateTime.month.name  // Get the month name
    val day = localDateTime.dayOfMonth
    val year = localDateTime.year
    val hour = if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
    val amPm = if (localDateTime.hour >= 12) "PM" else "AM"
    val minute = localDateTime.minute

    return "$monthName $day, $year at $hour:$minute $amPm"
}

private fun String.toUiDate() : String {
    // Add 'Z' to indicate UTC, as Instant expects UTC format
    val utcDateString = "${this}Z"

    // Parse the string into an Instant and then convert to LocalDateTime
    val instant = Instant.parse(utcDateString)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // Format the LocalDateTime into a human-readable string
    val formattedDate = formatDateTime(localDateTime)

    // date without Z
    return formattedDate
}