package com.amarchaud.domain.usecase

import com.amarchaud.domain.repository.PaginationDemoRepository
import javax.inject.Inject

class GetRandomUsersWithRoomUseCase @Inject constructor(
    private val repository: PaginationDemoRepository
) {
    fun run() = repository.getRandomUsersRoom()
}