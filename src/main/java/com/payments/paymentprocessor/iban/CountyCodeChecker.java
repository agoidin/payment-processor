package com.payments.paymentprocessor.iban;

public class CountyCodeChecker {

    public static boolean validCode(String iban) {
        for (CountryCode code: CountryCode.values()) {
            if(iban.contains(code.name())) return true;
        }

        return false;
    }
}
