package com.ucodeacademy.day_01_simple_request_and_validation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class HamcrestAssertion {

    @Test
    public void simpleHamcrestAssertion(){
        MatcherAssert.assertThat(20+10, Matchers.is(30));
        MatcherAssert.assertThat(20 -5, Matchers.is(15));

        MatcherAssert.assertThat(10, Matchers.is(10));

        // JUnit assertion
        Assert.assertEquals(20+10, 30);
        Assert.assertTrue(8 < 90);

    }
}
