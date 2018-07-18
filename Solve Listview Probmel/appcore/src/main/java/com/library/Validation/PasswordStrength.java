package com.library.Validation;

/**
 * Created by Rz Rasel on 13-Aug-16.
 */
public class PasswordStrength {
    public float getPasswordRating(String argPassword) throws IllegalArgumentException {
        if (argPassword == null) {
            throw new IllegalArgumentException();
        }
        int passwordStrength = 0;
        if (argPassword.length() > 7) {
            passwordStrength++;
        } // minimal pw length of 6
        if (argPassword.toLowerCase() != argPassword) {
            passwordStrength++;
        } // lower and upper case
        int numDigits = getNumberDigits(argPassword);
        if (numDigits > 0 && numDigits != argPassword.length()) {
            passwordStrength++;
        } // contains digits and non-digits
        if (argPassword.length() > 10) {
            passwordStrength++;
        } // good pw length of 9+
        return (float) passwordStrength;
    }

    private int getNumberDigits(String argInString) {
        if (isEmpty(argInString)) {
            return 0;
        }
        int numDigits = 0;
        int length = argInString.length();
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(argInString.charAt(i))) {
                numDigits++;
            }
        }
        return numDigits;
    }

    private boolean isEmpty(String argInString) {
        return argInString == null || argInString.length() == 0;
    }

    public boolean checkConfirmPassword(String argPassword, String argConfirmPassword) {
        boolean passStatus = false;
        if (argConfirmPassword != null && argPassword != null) {
            if (argPassword.equals(argConfirmPassword)) {
                passStatus = true;
            }
        }
        return passStatus;
    }
}
//https://github.com/VenomVendor/Password-Strength-Checker/blob/master/src/vee/psw/strength/PasswordStrengthChecker.java
//http://stackoverflow.com/questions/5177517/password-strength-checker-in-android