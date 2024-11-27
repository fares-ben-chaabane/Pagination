package com.amarchaud.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.amarchaud.data.api.PaginationDemoApi
import com.amarchaud.data.db.PaginationDemoDao
import com.amarchaud.data.mappers.toDomain
import com.amarchaud.domain.models.UserModel
import com.amarchaud.domain.repository.PaginationDemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import repository.remotemediator.RandomUsersRemoteMediator
import javax.inject.Inject

class PaginationDemoRepositoryImpl @Inject constructor(
    private val paginationDemoApi: PaginationDemoApi,
    private val paginationDemoDao: PaginationDemoDao
) : PaginationDemoRepository {

    // methode 2 : with room
    @OptIn(ExperimentalPagingApi::class)
    override fun getRandomUsersRoom(): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        remoteMediator = RandomUsersRemoteMediator(
            PaginationDemoApi = paginationDemoApi,
            PaginationDemoDao = paginationDemoDao
        ),
        pagingSourceFactory = {
            paginationDemoDao.getUsersPagingSource()
        }
    ).flow.map {
        it.map { oneUserEntity -> oneUserEntity.toDomain() }
    }

    override suspend fun getUserFromCache(localId: Long) =
        paginationDemoDao.getUserFromCache(localId)?.toDomain()
}