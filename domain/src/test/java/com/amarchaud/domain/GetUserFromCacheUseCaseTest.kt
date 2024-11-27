package com.amarchaud.domain

import com.amarchaud.domain.repository.PaginationDemoRepository
import com.amarchaud.domain.usecase.GetUserFromCacheUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetUserFromCacheUseCaseTest  {
    private val repositoryMock: PaginationDemoRepository = mock()
    private lateinit var useCase: GetUserFromCacheUseCase

    private val mockUser = com.amarchaud.domain.models.UserModel(
        localId = 0,
        email = "example@gmail.com"
    )

    @Before
    fun setUp() {
        useCase = GetUserFromCacheUseCase(repository = repositoryMock)
    }

    @Test
    fun `GetUserFromCacheUseCase ok`() = runTest {
        whenever(repositoryMock.getUserFromCache(any())).thenReturn(mockUser)

        val res = useCase.run(0)

        Assert.assertTrue(res == mockUser)
    }
}