/**
 * @author: Noah S and Lauchlan
 * Listing
 * 
 */
package com.example.sarah.coursetool.CourseListing;

public class Listing {
    public String title;
    public String description;
    public int imageId;

    public Listing(String title, String description, int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle (String t) {
        this.title = t;
    }

    public String getDescription () {
        return this.description;
    }

    public void setDescription (String d) {
        this.description = d;
    }

    public int getImageId () {
        return this.imageId;
    }

    public void setImageId (int i) {
        this.imageId = i;
    }

    @Override
    public String toString() {
        String s = this.getTitle() + " , " + this.getImageId() + " , " + this.getDescription();
        return s;
    }
}
