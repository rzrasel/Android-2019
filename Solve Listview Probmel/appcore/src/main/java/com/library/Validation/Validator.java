package com.library.Validation;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rz Rasel on 03-Aug-16.
 */
public class Validator {
    private List<ValidationResult> validationResults = new ArrayList<ValidationResult>();

    public boolean isEmpty(String argString) {
        argString = argString.trim();
        return argString == null || argString.isEmpty() || argString.equals("");
    }

    public Validator isEmpty(String argString, String argMsg) {
        argString = argString.trim();
        if (argString == null || argString.isEmpty() || argString.equals("")) {
            String msg = String.format("%s is empty", argMsg).toString();
            validationResults.add(new ValidationResult(false, msg));
        }
        return this;
    }

    private boolean isImageViewEmpty(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable) drawable).getBitmap() != null;
        }
        return hasImage;
    }

    public boolean isValidMobileNo(String argMobileNo) {
        argMobileNo = argMobileNo.trim();
        //argMobileNo.matches("01[0-9]{9}")
        //if (argMobileNo.startsWith("01") && argMobileNo.matches("01[0-9]{9}") && argMobileNo.length() == 11) {
        if (argMobileNo.startsWith("01") && argMobileNo.matches("01[0-9]{9}")) {
            //LogWriter.Log("MOBILE_LENGTH_1: " + argMobileNo.length());
            return true;
        } else if (argMobileNo.startsWith("88") && argMobileNo.matches("[0-9]{13}")) {
            //LogWriter.Log("MOBILE_LENGTH_1: " + argMobileNo.length());
            return true;
        }
        //LogWriter.Log("MOBILE_LENGTH_2: " + argMobileNo + " --- " + argMobileNo.length());
        return false;
    }

    public boolean isValidPassword(String argPassword, int argMinLen, int argMaxLen) {
        argPassword = argPassword.trim();
        /*if (argPassword.length() >= argMaxLen)
            return true;
        else if (argPassword.length() <= argMinLen)
            return true;
        return false;*/
        return argPassword.length() >= argMinLen && argPassword.length() <= argMaxLen;
    }

    public boolean isValidCurrency(String argCurrency, int argLen) {
        argCurrency = argCurrency.trim();
        //String pattern = "([0-9]{4})(\.)([0-2]{2})"; // 4 digits followe by . followed by 2 digits
        //Try this pattern ^([0-9]{1,4})(.[0-9]{1,2})?$
        String strPattern = "(0|[1-9]+[0-9]*)?(\\.[0-9]{0,2})?";
        strPattern = "(0|[1-9]+[0-9]*)?(\\.[0-9]{0,2})?";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(argCurrency);
        /*if (matcher.matches() && !argCurrency.equals("0")) {
            if (argLen > 0) {
                String[] currencyParts = argCurrency.split(".");
                if (currencyParts.length > 0 && currencyParts[0].length() < argLen) {
                    return true;
                }
            } else {
                return true;
            }
        }*/
        return matcher.matches() && !argCurrency.equals("0");
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        return pass != null && pass.length() > 6;
    }

    public Map<Boolean, String> isValid() {
        Map<Boolean, String> retVal = new HashMap<Boolean, String>();
        boolean haveError = true;
        String errorMsg = "";
        for (ValidationResult result : validationResults) {
            //System.out.println(result.getMessage());
            if (!result.getIsisValidated()) {
                haveError = false;
            }
            errorMsg += result.getMessage();
        }
        retVal.put(haveError, errorMsg);
        return retVal;
    }

    private class ValidationResult {
        private boolean isValid = true;
        private String message = "";

        public ValidationResult() {
            //
        }

        public ValidationResult(boolean argIsVaild, String argMessage) {
            this.isValid = argIsVaild;
            this.message = argMessage;
        }

        public boolean getIsisValidated() {
            return this.isValid;
        }

        public String getMessage() {
            return this.message;
        }
    }
}