package com.turvo.utils;

import org.junit.Assert;
import org.junit.Test;

import com.turvo.exception.VerifyException;

/**
 * The Class AssetUtilsTest.
 */
public class AssetUtilsTest {

    /**
     * Test parse date to long.
     */
    @Test
    public void testParseDateToLong() {
	Assert.assertNotNull(AssetUtils.parseDateToLong("2017-11-12T16:04:24"));
    }

    /**
     * Test parse date to long with invalid date.
     */
    @Test(expected = VerifyException.class)
    public void testParseDateToLongWithInvalidDate() {
	AssetUtils.parseDateToLong("2017-11-1216:04:24");
    }
}
