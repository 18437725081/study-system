package com.bs.util;

import java.math.BigDecimal;

/**
 * @author 张靖烽
 * @name BigDecimalUtil
 * @description
 * @create 2017-11-16 18:13
 **/
public class BigDecimalUtil {

    private BigDecimalUtil() {
    }

    /**
     * @author 张靖烽
     * @description +
     * @createtime 2017-11-16 19:42
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return String.valueOf(b1.add(b2));
    }

    /**
     * @author 张靖烽
     * @description -
     * @createtime 2017-11-16 19:42
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * @author 张靖烽
     * @description *
     * @createtime 2017-11-16 19:42
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * @author 张靖烽
     * @description /
     * @createtime 2017-11-16 19:42
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        //四舍五入，保留两位小数
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
}
