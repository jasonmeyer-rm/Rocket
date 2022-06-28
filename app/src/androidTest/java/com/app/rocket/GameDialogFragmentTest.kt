package com.app.rocket

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.rocket.feature.games.ui.dialog.GameDialogFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameDialogFragmentTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testViewBottomSheetInflation_With_OnClick() {
        val scenario = launchFragmentInContainer<GameDialogFragment>(
            themeResId = R.style.Theme_Rocket,
            initialState = Lifecycle.State.INITIALIZED)

        scenario.moveToState(Lifecycle.State.RESUMED)

        TODO("View assertions")

    }
}