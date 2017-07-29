package com.nulogy.assignment;

import static org.junit.Assert.*;
import org.junit.Test;

import com.nulogy.assignment.util.Utils;

public class NupackCostEstimatorTest {

    @Test
    public void testCostAfterFlatMarkup() {
    	NupackCostEstimator costEstimatorObj = new NupackCostEstimator(1299.99f, 3, "food");
        float costAfterFlatMarkup = costEstimatorObj.getCostAfterFlatMarkup();
        assertEquals(1364.99f, costAfterFlatMarkup, 0.01);
    }

    @Test
    public void testCostAfterTotalMarkup() {
    	NupackCostEstimator costEstimatorObj = new NupackCostEstimator(1299.99f, 3, "food");
        float costAfterTotalMarkup = costEstimatorObj.getCostAfterTotalMarkup();
        assertEquals(1591.58f, costAfterTotalMarkup, 0.01);
    }

    @Test
    public void testFloatRoundOff() {
        assertEquals(1591.59f, Utils.roundOffFloat(1591.588f), 0.0);
    }
}
