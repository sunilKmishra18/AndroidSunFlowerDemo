package com.sunilmishra.android.androidsunflowerdemo.utilities

import org.junit.Assert.assertEquals
import org.junit.Test

class GrowZoneUtilKtTest {
    @Test
    fun getZoneForLatitude() {
        assertEquals(13, getZoneForLatitude(0.0))
        assertEquals(13, getZoneForLatitude(7.0))
        assertEquals(12, getZoneForLatitude(7.1))
        assertEquals(1, getZoneForLatitude(84.1))
        assertEquals(1, getZoneForLatitude(90.0))
    }

    @Test
    fun getZoneForLatitude_negativeLatitudes() {
        assertEquals(13, getZoneForLatitude(-7.0))
        assertEquals(12, getZoneForLatitude(-7.1))
        assertEquals(1, getZoneForLatitude(-84.1))
        assertEquals(1, getZoneForLatitude(-90.0))
    }
}