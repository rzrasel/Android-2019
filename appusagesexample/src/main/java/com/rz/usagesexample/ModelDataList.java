package com.rz.usagesexample;

public class ModelDataList {
    private String title;
    private String imageUrl;
    private String videoUrl;

    public ModelDataList(String argTitle, String argImageUrl, String argVideoUrl) {
        this.title = argTitle;
        this.imageUrl = argImageUrl;
        this.videoUrl = argVideoUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public static ModelDataList onGetSetRow(String argTitle, String argImageUrl, String argVideoUrl) {
        return new ModelDataList(argTitle, argImageUrl, argVideoUrl);
    }

    public enum TagProperty {
        NAME("name"),
        IMAGE_URL("image_url"),
        VIDEO_URL("video_url"),;

        private String value;

        TagProperty(String argValue) {
            value = argValue;
        }

        public String getValue() {
            return this.value;
        }
    }
}
