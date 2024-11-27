package com.amarchaud.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amarchaud.data.models.PageEntityModel
import com.amarchaud.data.models.UserEntityModel

@Dao
interface PaginationDemoDao {

    @Query("SELECT * FROM Users ORDER BY _id")
    fun getUsersPagingSource(): PagingSource<Int, UserEntityModel>

    @Query("SELECT * FROM Users WHERE _id=:localId")
    suspend fun getUserFromCache(localId: Long): UserEntityModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntityModel>)

    @Query("DELETE FROM Users")
    suspend fun clearAll()


    @Query("SELECT MAX(page) FROM Page LIMIT 1")
    suspend fun getLastPage(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPage(page: PageEntityModel)

    @Query("DELETE FROM Page")
    suspend fun clearPage()
}