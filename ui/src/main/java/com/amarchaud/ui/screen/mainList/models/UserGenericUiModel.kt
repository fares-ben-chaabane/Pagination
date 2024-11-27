package com.amarchaud.ui.screen.mainList.models

data class UserGenericUiModel(
    val localId: Long = -1,
    val imageUrl: String = String(),
    val email: String = String(),
    val completeName: String = String(),
)