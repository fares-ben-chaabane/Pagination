package com.amarchaud.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarchaud.domain.usecase.GetUserFromCacheUseCase
import com.amarchaud.ui.screen.detail.mappers.toDetailUiModel
import com.amarchaud.ui.screen.detail.models.UserDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getUserFromCacheUseCase: GetUserFromCacheUseCase
) : ViewModel() {

    // new way in navigation 2.8
    private val localId =
        stateHandle.get<Long>("id") ?: throw NullPointerException("localId can't be null")

    // former way in navigation 2.7 and before
    // private val localId =
       // stateHandle.get<Long>(MainActivity.Extra.USER_LOCAL_ID) ?: throw NullPointerException("localId can't be null")

    private val _userDetailUiModel = MutableStateFlow(UserDetailUiModel())
    val userDetailUiModel = _userDetailUiModel.asStateFlow()

    init {
        viewModelScope.launch {
            getUserFromCacheUseCase.run(localId)?.let { userModel ->
                _userDetailUiModel.update {
                    userModel.toDetailUiModel()
                }
            }
        }
    }
}