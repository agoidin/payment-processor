package com.payments.paymentprocessor.iban;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

    private static final String regex = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$";

    public static boolean regexValid(String iban) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(iban);
        return matcher.find();
    }

}
