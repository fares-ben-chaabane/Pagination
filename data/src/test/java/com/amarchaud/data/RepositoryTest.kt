package com.amarchaud.data

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.asSnapshot
import com.amarchaud.data.api.PaginationDemoApi
import com.amarchaud.data.db.PaginationDemoDao
import com.amarchaud.data.mappers.toDomain
import com.amarchaud.data.models.ResultsDataModel
import com.amarchaud.data.models.UserDataModel
import com.amarchaud.data.models.UserEntityModel
import com.amarchaud.domain.models.ErrorApiModel
import com.amarchaud.domain.models.UserModel
import com.amarchaud.domain.repository.PaginationDemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.amarchaud.data.repository.PaginationDemoRepositoryImpl
import retrofit2.Response

class RepositoryTest {

    private val apiMock: PaginationDemoApi = mock()
    private val daoMock: PaginationDemoDao = mock()

    private lateinit var repository: PaginationDemoRepository

    private val mockEntityUser = UserEntityModel(
        _id = 0,
        email = "example@gmail.com"
    )

    private val mockApiUser = UserDataModel(
        email = "example@gmail.com"
    )

    private val mockPagingSource = object : PagingSource<Int, UserEntityModel>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserEntityModel> {
            // Simulate loading data from a data source
            return LoadResult.Page(
                data = List(3) { mockEntityUser },
                prevKey = null,
                nextKey = null
            )
        }

        override fun getRefreshKey(state: PagingState<Int, UserEntityModel>): Int? {
            // Return null because the refresh key is not needed for this mock implementation
            return null
        }
    }

    @Before
    fun setUp() {
        repository = PaginationDemoRepositoryImpl(
            paginationDemoApi = apiMock,
            paginationDemoDao = daoMock
        )
    }

    @Test
    fun `check getRandomUsersRoom and map it to domain`() = runTest {
        whenever(daoMock.getUsersPagingSource()).thenReturn(mockPagingSource)
        whenever(apiMock.getRandomUsers(any(), any(), any())).thenReturn(
            Response.success(
                ResultsDataModel(
                    users = List(3) { mockApiUser }
                )
            )
        )

        val items: Flow<PagingData<UserModel>> = repository.getRandomUsersRoom()
        val snapshot = items.asSnapshot()

        Assert.assertTrue(snapshot.size == 3)
        with(snapshot.last()) {
            Assert.assertTrue(this == mockEntityUser.toDomain())
        }
    }

    @Test
    fun `check getRandomUsersRoom KO`() = runTest {
        whenever(daoMock.getUsersPagingSource()).thenReturn(mockPagingSource)
        whenever(apiMock.getRandomUsers(any(), any(), any())).thenReturn(
            Response.error(
                400,
                "{ \"error\": \"error\" }".toResponseBody()
            )
        )

        val items: Flow<PagingData<UserModel>> = repository.getRandomUsersRoom()
        try {
            items.asSnapshot()
        } catch (e: Throwable) {
            Assert.assertTrue(e is ErrorApiModel.ApiServerErrorWithCode)
        }
    }

    @Test
    fun `check getUserFromCache and map it to domain`() = runTest {
        whenever(daoMock.getUserFromCache(localId = 0)).thenReturn(mockEntityUser)

        val res = repository.getUserFromCache(localId = 0)

        Assert.assertTrue(res == mockEntityUser.toDomain())
    }
}