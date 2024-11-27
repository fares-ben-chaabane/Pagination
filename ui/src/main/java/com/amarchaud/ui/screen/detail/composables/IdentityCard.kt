package com.amarchaud.ui.screen.detail.composables

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amarchaud.ui.R
import com.amarchaud.ui.screen.detail.models.UserDetailUiModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun IdentityCard(
    modifier: Modifier = Modifier,
    user: UserDetailUiModel
) {
    val callPermission = rememberPermissionState(Manifest.permission.CALL_PHONE)
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_profil_24),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = user.mainInfo.completeName)
        }
        Divider()
        Text(text = user.mainInfo.email)
        Divider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = user.address)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    //val map = "http://maps.google.com/maps?q=loc:${ user.coordinates.latitude},${user.coordinates.longitude}"
                    val map = "https://maps.google.co.in/maps?q=${user.address}"
                    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                    context.startActivity(mapIntent)
                },
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = Color.Black
            )
        }
        Divider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = user.phoneNumber)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    if (callPermission.status.isGranted) {
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.setData(Uri.parse("tel:${user.phoneNumber}"))
                        context.startActivity(callIntent)
                    } else {
                        callPermission.launchPermissionRequest()
                    }
                },
                painter = painterResource(id = R.drawable.ic_phone_call),
                contentDescription = "",
                tint = Color.Black
            )
        }
        Divider()
        Text(text = user.birthday)
    }
}