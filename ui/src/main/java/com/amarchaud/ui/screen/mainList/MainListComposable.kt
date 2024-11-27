package com.amarchaud.ui.screen.mainList

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.amarchaud.ui.R
import com.amarchaud.ui.screen.mainList.composables.ErrorComposable
import com.amarchaud.ui.screen.mainList.composables.OneUser
import com.amarchaud.ui.screen.mainList.composables.OneUserShimmer
import com.amarchaud.ui.screen.mainList.models.UserGenericUiModel
import com.amarchaud.ui.screen.mainList.models.toMessage
import com.amarchaud.ui.theme.PaginationDemoTheme
import kotlinx.coroutines.flow.MutableStateFlow

internal val heightOneCell = 96.dp
private const val MAX_ELEMENTS_TO_ANIMATE = 16
private const val ALPHA_DURATION = 1300
private const val TRANSLATION_DURATION = 800
private const val INIT_OFFSET_VALUE = 200
private const val OFFSET_BY_INDEX = 60
private val animationCubic = CubicBezierEasing(0f, 0.56f, 0.46f, 1f)

@Composable
fun MainListComposable(
    viewModel: MainListViewModel = hiltViewModel(),
    onUserClick: (Long) -> Unit
) {
    val users = viewModel.users.collectAsLazyPagingItems()

    MainListScreen(
        users = users,
        onUserClick = onUserClick,
        onRefresh = {
            users.refresh()
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainListScreen(
    users: LazyPagingItems<UserGenericUiModel>,
    onUserClick: (Long) -> Unit,
    onRefresh: () -> Unit,
) {
    val context = LocalContext.current
    var canDisplayEnterAnimation by rememberSaveable { mutableStateOf(false) }
    var canDisplayPullToRefresh by rememberSaveable { mutableStateOf(false) }

    var translationY: List<State<Dp>> = remember { mutableStateListOf() }
    if (translationY.isEmpty() && LocalInspectionMode.current.not()) {
        translationY = buildList {
            for (i in 0 until MAX_ELEMENTS_TO_ANIMATE) {
                add(
                    i,
                    animateDpAsState(
                        targetValue = if (canDisplayEnterAnimation) {
                            0.dp
                        } else {
                            (INIT_OFFSET_VALUE + i * OFFSET_BY_INDEX).dp // start from bottom
                        },
                        animationSpec = tween(
                            durationMillis = TRANSLATION_DURATION,
                            easing = animationCubic,
                        ),
                        label = "animate item $i",
                    ),
                )
            }
        }
    }

    val alpha by animateFloatAsState(
        targetValue = when {
            LocalInspectionMode.current -> 1f
            canDisplayEnterAnimation -> 1f
            else -> 0f
        },
        animationSpec = tween(
            durationMillis = ALPHA_DURATION,
            easing = animationCubic,
        ),
        label = "animate alpha",
    )

    val state = rememberPullRefreshState(
        refreshing = canDisplayPullToRefresh && users.loadState.refresh == LoadState.Loading,
        onRefresh = {
            canDisplayEnterAnimation = false
            onRefresh()
        }
    )

    LaunchedEffect(key1 = users.loadState.refresh, key2 = users.itemCount) {
        if (users.loadState.refresh is LoadState.NotLoading && users.itemCount > 0) {
            canDisplayPullToRefresh = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state = state),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.matchParentSize(),
            columns = GridCells.Fixed(2),
        ) {

            item(span = {
                GridItemSpan(2)
            }) {
                Spacer(modifier = Modifier.statusBarsPadding())
            }

            when (users.loadState.refresh) {
                is LoadState.Loading -> {
                    canDisplayEnterAnimation = false

                    items(count = 12) {
                        OneUserShimmer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )
                    }
                }

                else -> {
                    canDisplayEnterAnimation = true

                    // always display Room DB
                    items(
                        count = users.itemCount,
                    ) { index ->
                        users[index]?.let {
                            OneUser(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .offset(
                                        y = if (index < MAX_ELEMENTS_TO_ANIMATE && translationY.isNotEmpty()) {
                                            translationY[index].value
                                        } else {
                                            0.dp
                                        },
                                    )
                                    .alpha(
                                        alpha = alpha,
                                    ),
                                user = it,
                                onClick = {
                                    onUserClick(it.localId)
                                }
                            )
                        }
                    }

                    // display error
                    if (users.loadState.refresh is LoadState.Error) {
                        item(span = {
                            GridItemSpan(2)
                        }) {
                            ErrorComposable(
                                modifier = Modifier.matchParentSize(),
                                errorMessage = stringResource(
                                    id = R.string.error_refresh,
                                    ((users.loadState.refresh as LoadState.Error).error as com.amarchaud.domain.models.ErrorApiModel)
                                        .toMessage(context)
                                ),
                                onRefresh = onRefresh
                            )
                        }
                    }
                }
            }

            when (users.loadState.append) { // Pagination
                is LoadState.Error -> {
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        ErrorComposable(
                            modifier = Modifier.fillMaxSize(),
                            errorMessage = stringResource(
                                id = R.string.error_append,
                                ((users.loadState.append as LoadState.Error).error as com.amarchaud.domain.models.ErrorApiModel)
                                    .toMessage(context)
                            ),
                            onRefresh = onRefresh
                        )
                    }
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = stringResource(id = R.string.loading))

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }
        }

        PullRefreshIndicator(
            refreshing = canDisplayPullToRefresh && users.loadState.refresh == LoadState.Loading,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
private fun MainListScreenPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = List(10) { mockUser },
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(endOfPaginationReached = false),
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            )
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

@Preview
@Composable
private fun MainListScreenLoadingPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = emptyList<UserGenericUiModel>(),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Loading,
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ),
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

@Preview(name = "error after first loading")
@Composable
private fun MainListScreenErrorLoadingEmptyPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = emptyList<UserGenericUiModel>(),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(com.amarchaud.domain.models.ErrorApiModel.GenericError()),
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ),
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

@Preview(name = "error after N loading")
@Composable
private fun MainListScreenErrorLoadingPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = List(5) { mockUser },
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(com.amarchaud.domain.models.ErrorApiModel.GenericError()),
                    append = LoadState.NotLoading(endOfPaginationReached = false),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ),
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

@Preview
@Composable
private fun MainListScreenLoadingPagerPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = List(5) { mockUser },
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(endOfPaginationReached = false),
                    append = LoadState.Loading,
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ),
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

@Preview
@Composable
private fun MainListScreenErrorLoadingPagerPreview() {
    PaginationDemoTheme {
        val items = MutableStateFlow(
            PagingData.from(
                data = List(5) { mockUser },
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(endOfPaginationReached = false),
                    append = LoadState.Error(com.amarchaud.domain.models.ErrorApiModel.GenericError()),
                    prepend = LoadState.NotLoading(endOfPaginationReached = false)
                )
            ),
        ).collectAsLazyPagingItems()

        MainListScreen(
            users = items,
            onUserClick = {},
            onRefresh = {}
        )
    }
}

internal val mockUser = UserGenericUiModel(
    email = "example@toto.fr",
    completeName = "Mr Antoine Marchaud"
)