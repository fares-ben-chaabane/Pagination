package com.amarchaud.data.adapters

import androidx.room.TypeConverter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object RoomConverter {

    data object LocalDateRoomConverter {
        @TypeConverter
        fun fromLocalDateTime(value: LocalDateTime?): String? {
            // Convertir LocalDateTime en chaîne ISO 8601
            val utcDateString = value?.toString()
            return "${utcDateString}Z"
        }

        @TypeConverter
        fun toLocalDateTime(value: String?): LocalDateTime? {
            // Convertir une chaîne ISO 8601 en LocalDateTime
            return value?.let {
                try {
                    Instant.parse(it)
                        .toLocalDateTime(TimeZone.UTC) // Utiliser UTC si la chaîne contient 'Z'
                } catch (e: Exception) {
                    Clock.System.now().toLocalDateTime(TimeZone.UTC)
                }
            }
        }
    }
}
