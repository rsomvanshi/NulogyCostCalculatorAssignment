package com.nulogy.util;

import java.math.BigDecimal;

public class Utils {
    public static float roundOffFloat(float value) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(value));
        BigDecimal roundOff = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return roundOff.floatValue();
    }
}
