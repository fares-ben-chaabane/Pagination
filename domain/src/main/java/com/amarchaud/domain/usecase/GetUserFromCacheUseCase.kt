package com.amarchaud.domain.usecase

import com.amarchaud.domain.repository.PaginationDemoRepository
import javax.inject.Inject

class GetUserFromCacheUseCase @Inject constructor(
    private val repository: PaginationDemoRepository
) {
    suspend fun run(localId: Long) = repository.getUserFromCache(localId)
}