package com.sm.ansar;

public class ModelDashboard {
    private int resourceId;
    private String title;
    private int count;

    /*public ModelDashboard() {
    }*/

    public ModelDashboard(int argResourceId, String argTitle, int argCount) {
        this.resourceId = argResourceId;
        this.title = argTitle;
        this.count = argCount;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCount() {
        return this.count;
    }

    public static ModelDashboard onGetSetModelRow(int argResourceId, String argTitle, int argCount) {
        return new ModelDashboard(argResourceId, argTitle, argCount);
    }

    /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_drawable_image);
    //Setting up converted bitmap image inside imageview.
    image.setImageBitmap(bitmap);*/
}
