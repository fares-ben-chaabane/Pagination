package com.amarchaud.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amarchaud.data.adapters.RoomConverter
import com.amarchaud.data.models.PageEntityModel
import com.amarchaud.data.models.UserEntityModel

@Database(
    entities = [
        UserEntityModel::class, PageEntityModel::class
    ], version = 1, exportSchema = false
)
@TypeConverters(
    RoomConverter.LocalDateRoomConverter::class,
)
abstract class PaginationDemoDb : RoomDatabase() {
    abstract fun getPaginationDemoDao(): PaginationDemoDao
}

