package com.example.testgiuaky2.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String name;
    private String images;
    private String description;

    // Constructor updated to use String for id
    public Category(String id, String name, String images, String description) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.description = description;
    }

    // Constructor for backward compatibility that takes int id
    public Category(int id, String name, String images, String description) {
        this.id = String.valueOf(id);
        this.name = name;
        this.images = images;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // For backward compatibility
    public int getIdAsInt() {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // For backward compatibility
    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}