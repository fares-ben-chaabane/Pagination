package com.amarchaud.ui.screen.mainList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.amarchaud.domain.usecase.GetRandomUsersWithRoomUseCase
import com.amarchaud.ui.screen.mainList.mappers.toGenericUiModel
import com.amarchaud.ui.screen.mainList.models.UserGenericUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    getRandomUsersWithRoomUseCase: GetRandomUsersWithRoomUseCase
) : ViewModel() {
    val users: Flow<PagingData<UserGenericUiModel>> = getRandomUsersWithRoomUseCase
        .run()
        .map { pagingData ->
            pagingData.map { user ->
                user.toGenericUiModel()
            }
        }
        .cachedIn(viewModelScope)
}