package com.starglass.api.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberHelper {

    private NumberHelper() {
    }

    public static String decimalFormat(Float value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("0.00", symbols);
        return format.format(value);
    }

}
