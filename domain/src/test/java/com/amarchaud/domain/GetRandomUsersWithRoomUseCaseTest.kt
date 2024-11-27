package com.amarchaud.domain

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.amarchaud.domain.models.ErrorApiModel
import com.amarchaud.domain.models.UserModel
import com.amarchaud.domain.repository.PaginationDemoRepository
import com.amarchaud.domain.usecase.GetRandomUsersWithRoomUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRandomUsersWithRoomUseCaseTest {

    private val repositoryMock: PaginationDemoRepository = mock()
    private lateinit var useCase: GetRandomUsersWithRoomUseCase

    private val mockUser = UserModel(
        localId = 0,
        email = "example@gmail.com"
    )

    @Before
    fun setUp() {
        useCase =
            GetRandomUsersWithRoomUseCase(repository = repositoryMock)
    }

    @Test
    fun `GetRandomUsersWithRoomUseCase ok`() = runTest {
        whenever(repositoryMock.getRandomUsersRoom()).thenReturn(
            flowOf(PagingData.from(
                data = List(3) { mockUser }
            ))
        )

        val items = useCase.run()
        val snapshot = items.asSnapshot()

        Assert.assertTrue(snapshot.size == 3)
        with(snapshot.last()) {
            Assert.assertTrue(this == mockUser)
        }
    }

    @Test
    fun `GetRandomUsersWithRoomUseCase ko`() = runTest {
        whenever(repositoryMock.getRandomUsersRoom()).thenReturn(
            flowOf(PagingData.from(
                data = List(3) { mockUser },
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(ErrorApiModel.GenericError()),
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ))
        )

        val items = useCase.run()
        try {
            items.asSnapshot()
        } catch (e: Throwable) {
            Assert.assertTrue(e is ErrorApiModel.GenericError)
        }
    }
}
