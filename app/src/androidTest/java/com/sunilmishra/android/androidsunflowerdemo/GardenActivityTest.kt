package com.sunilmishra.android.androidsunflowerdemo

import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class GardenActivityTest{
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(GardenActivity::class.java)

    @Test
    fun clickOnAndroidHomeIcon_OpensAndClosesNavigation() {

    }

}