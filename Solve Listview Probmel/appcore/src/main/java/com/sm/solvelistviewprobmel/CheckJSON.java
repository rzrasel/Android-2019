package com.sm.solvelistviewprobmel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckJSON {
    public static boolean isValidJSON(String argJsonString) {
        try {
            new JSONObject(argJsonString);
        } catch (JSONException e) {
            try {
                new JSONArray(argJsonString);
            } catch (JSONException ex) {
                return false;
            }
        }
        return true;
    }
}
