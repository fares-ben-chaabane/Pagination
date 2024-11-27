package com.amarchaud.ui.screen.mainList.mappers

import com.amarchaud.domain.models.UserModel
import com.amarchaud.ui.screen.mainList.models.UserGenericUiModel

internal fun UserModel.toGenericUiModel() = UserGenericUiModel(
    localId = this.localId,
    completeName = "${this.name?.title} ${this.name?.first} ${this.name?.last}",
    email = this.email.orEmpty(),
    imageUrl = this.picture?.medium.orEmpty()
)