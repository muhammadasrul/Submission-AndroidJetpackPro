package com.asrul.jffplus.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.asrul.jffplus.R
import com.asrul.jffplus.utils.DummyData
import org.junit.Before

import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DummyData.dataMovie()
    private val dummyTvShow = DummyData.dataTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.text_about)).check(matches(isDisplayed()))
        onView(withId(R.id.text_about)).check(matches(withText(dummyMovie[0].about)))
        onView(withId(R.id.text_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.text_cast)).check(matches(withText(dummyMovie[0].cast)))
        onView(withId(R.id.text_director)).check(matches(isDisplayed()))
        onView(withId(R.id.text_director)).check(matches(withText(dummyMovie[0].director)))
        onView(withId(R.id.text_length)).check(matches(isDisplayed()))
        onView(withId(R.id.text_length)).check(matches(withText(dummyMovie[0].length)))
        onView(withId(R.id.text_year)).check(matches(isDisplayed()))
        onView(withId(R.id.text_year)).check(matches(withText(dummyMovie[0].year)))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.text_about)).check(matches(isDisplayed()))
        onView(withId(R.id.text_about)).check(matches(withText(dummyTvShow[0].about)))
        onView(withId(R.id.text_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.text_cast)).check(matches(withText(dummyTvShow[0].cast)))
        onView(withId(R.id.text_director)).check(matches(isDisplayed()))
        onView(withId(R.id.text_director)).check(matches(withText(dummyTvShow[0].director)))
        onView(withId(R.id.text_length)).check(matches(isDisplayed()))
        onView(withId(R.id.text_length)).check(matches(withText(dummyTvShow[0].length)))
        onView(withId(R.id.text_year)).check(matches(isDisplayed()))
        onView(withId(R.id.text_year)).check(matches(withText(dummyTvShow[0].year)))
    }
}