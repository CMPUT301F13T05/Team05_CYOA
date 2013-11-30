package com.uofa.adventure_app.activity;
/**
 * Organizes the objects in the custom list view 
 * 
 * @author Ulvi Ibrahimov
 *
 */
public class RowItem {
    private int imageId;
    private String title;
    private String desc;
 
    public RowItem(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
    }
    /**
     * Gets the Image Id.
     * @return int
     */
    public int getImageId() {
        return imageId;
    }
    /**
     * Sets the image id.
     * @param int imageId
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    /**
     * gets the description.
     * @return String
     */
    public String getDesc() {
        return desc;
    }
    /**
     * sets the description.
     * @param String desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * gets the title
     * @return String
     */
    public String getTitle() {
        return title;
    }
    /**
     * sets the title
     * @param String title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}