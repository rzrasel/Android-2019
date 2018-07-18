package com.sm.solvelistviewprobmel;

public class ModelListData {
    private String title;

    public ModelListData(String argTitle) {
        this.title = argTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public static ModelListData onGetSetRow(String argTitle) {
        return new ModelListData(argTitle);
    }
}
