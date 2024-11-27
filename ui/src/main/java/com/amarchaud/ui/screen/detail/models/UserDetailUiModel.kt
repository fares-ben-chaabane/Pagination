package com.amarchaud.ui.screen.detail.models

import com.amarchaud.ui.screen.mainList.models.UserGenericUiModel
import org.osmdroid.util.GeoPoint

data class UserDetailUiModel(
    // same as UserUiModel
    val mainInfo: UserGenericUiModel = UserGenericUiModel(),
    // plus a lot of info
    val mainImageUrl: String = String(),
    val coordinates: GeoPoint = GeoPoint(0.0, 0.0),
    val address: String = String(),
    val phoneNumber: String = String(),
    val birthday: String = String()
)