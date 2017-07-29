package com.nulogy.assignment.util;

import java.math.BigDecimal;

/**
 * @author rsomvanshi
 *
 */
public class Utils {

    /** Round off Float to 2 decimal places
     * @return float
     */
    public static float roundOffFloat(float value) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(value));
        BigDecimal roundOff = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return roundOff.floatValue();
    }
}
