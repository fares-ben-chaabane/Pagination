package com.example.ui

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.amarchaud.domain.usecase.GetRandomUsersWithRoomUseCase
import com.amarchaud.ui.screen.mainList.MainListViewModel
import com.amarchaud.ui.screen.mainList.models.UserGenericUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MainListViewModelTest {
    private val getRandomUsersWithRoomUseCaseMock: GetRandomUsersWithRoomUseCase = mock()
    private lateinit var viewModel: MainListViewModel

    private val mockUser = com.amarchaud.domain.models.UserModel(
        localId = 0,
        email = "example@gmail.com"
    )
    private val mockUiUser = UserGenericUiModel(
        localId = 0,
        email = "example@gmail.com"
    )

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel =
            MainListViewModel(getRandomUsersWithRoomUseCase = getRandomUsersWithRoomUseCaseMock)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUsers ok`() = runTest {
        whenever(getRandomUsersWithRoomUseCaseMock.run()).thenReturn(
            flowOf(
                PagingData.from(
                    data = List(3) { mockUser },
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            )
        )

        val items = viewModel.users
        val itemsSnapshot: List<UserGenericUiModel> = items.asSnapshot()

        // this does not work, why ? UncompletedCoroutinesError
        Assert.assertTrue(itemsSnapshot.size == 3)
    }
}