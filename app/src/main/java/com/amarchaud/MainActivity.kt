package com.amarchaud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amarchaud.ui.screen.detail.UserDetailComposable
import com.amarchaud.ui.screen.mainList.MainListComposable
import com.amarchaud.ui.theme.PaginationDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Serializable
    object Home

    @Serializable
    data class Detail(val id: Long)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            PaginationDemoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home,
                ) {
                    composable<Home> {
                        MainListComposable(
                            onUserClick = {
                                navController.navigate(Detail(it))
                            }
                        )
                    }

                    composable<Detail> {
                        UserDetailComposable(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}