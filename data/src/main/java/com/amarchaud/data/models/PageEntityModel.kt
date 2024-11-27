package com.amarchaud.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Page",
)
data class PageEntityModel(
    @PrimaryKey
    val page: Int = 0,
)
