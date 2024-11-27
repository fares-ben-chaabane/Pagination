package com.amarchaud.data.adapters


import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        // Sérialiser LocalDateTime au format ISO 8601 avec "Z"
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val value = decoder.decodeString()
        // Convertir une chaîne ISO 8601 avec "Z" en LocalDateTime
        return try {
            Instant.parse(value).toLocalDateTime(TimeZone.UTC)
        } catch (e: Exception) {
            Clock.System.now().toLocalDateTime(TimeZone.UTC)
        }
    }
}

data class ForceString(val string : String)

object OneStringSerializer : KSerializer<ForceString> {
    override val descriptor = PrimitiveSerialDescriptor("ForceString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ForceString) {
        encoder.encodeString(value.string)
    }

    override fun deserialize(decoder: Decoder): ForceString {
        return try {
            ForceString(decoder.decodeString())
        } catch (e: Exception) {
            ForceString(decoder.decodeInt().toString())
        }
    }
}