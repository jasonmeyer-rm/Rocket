package com.app.rocket

import com.app.rocket.feature.games.data.models.GamesResponse
import com.app.rocket.feature.games.repository.GamesRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GamesTest {

    private val scope = TestScope()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when gamesResponse is called it should return a empty list`() = scope.runTest {
        val expectedGames = GamesResponse(results = emptyList())

        val mockRepository = mockk<GamesRepository>()

        coEvery { mockRepository.getGames() } returns expectedGames

        val actualGames = mockRepository.getGames()

        coVerify(exactly = 1) { mockRepository.getGames() }

        Assert.assertEquals(expectedGames, actualGames)
    }

}
