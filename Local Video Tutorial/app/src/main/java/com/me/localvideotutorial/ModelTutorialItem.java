package com.me.localvideotutorial;

public class ModelTutorialItem {
    private String title;
    private int tutorialPath;

    public ModelTutorialItem(String argTitle, int argTutorialPath) {
        this.title = argTitle;
        this.tutorialPath = argTutorialPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTutorialPath() {
        return tutorialPath;
    }

    public void setTutorialPath(int argTutorialPath) {
        this.tutorialPath = argTutorialPath;
    }

    public static ModelTutorialItem onGetSetModelRow(String argTitle, int argTutorialPath) {
        return new ModelTutorialItem(argTitle, argTutorialPath);
    }
}
