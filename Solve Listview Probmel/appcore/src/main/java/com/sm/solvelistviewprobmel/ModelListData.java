package com.sm.solvelistviewprobmel;

public class ModelListData {
    private String title;
    private String videoId;

    public ModelListData(String argTitle) {
        this.title = argTitle;
    }
    public ModelListData(String argTitle, String argVideoId) {
        this.title = argTitle;
        this.videoId = argVideoId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public static ModelListData onGetSetRow(String argTitle) {
        return new ModelListData(argTitle);
    }
    public static ModelListData onGetSetRow(String argTitle, String argVideoId) {
        return new ModelListData(argTitle, argVideoId);
    }
    public enum JSONTag {
        NAME("name"),
        IMAGE_URL("image_url"),
        VIDEO_ID("video_id"),;

        private String value;

        JSONTag(String argValue) {
            value = argValue;
        }

        public String getValue() {
            return this.value;
        }
    }
}
