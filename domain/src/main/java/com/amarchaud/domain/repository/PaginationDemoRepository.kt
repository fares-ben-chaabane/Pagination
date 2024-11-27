package com.amarchaud.domain.repository

import androidx.paging.PagingData
import com.amarchaud.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface PaginationDemoRepository {
    fun getRandomUsersRoom(): Flow<PagingData<UserModel>>
    suspend fun getUserFromCache(localId: Long): UserModel?
}