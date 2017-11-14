package com.turvo.utils;

import java.util.ArrayList;

import org.junit.Test;

import com.turvo.exception.VerifyException;

/**
 * The Class VerifierTest.
 */
public class VerifierTest {

    /**
     * Test verify null.
     */
    @Test(expected = VerifyException.class)
    public void testVerifyNull() {
	Verifier.verifyNull(null, "message");
    }

    /**
     * Test verify null or empty.
     */
    @Test(expected = VerifyException.class)
    public void testVerifyNullOrEmpty() {
	Verifier.verifyNullOrEmpty("", "message");
    }

    /**
     * Test verify empty collection.
     */
    @Test(expected = VerifyException.class)
    public void testVerifyEmptyCollection() {
	Verifier.verifyEmptyCollection(new ArrayList<>(), "message");
    }
}
