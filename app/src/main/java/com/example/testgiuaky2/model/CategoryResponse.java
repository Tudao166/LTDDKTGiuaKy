package com.example.testgiuaky2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<CategoryResult> result;

    // Constructor for the old API usage - keep for backward compatibility
    public CategoryResponse(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Default constructor for Gson deserialization
    public CategoryResponse() {
    }

    // Fields for backward compatibility with the old CategoryResponse
    private String name;
    private String description;
    private String imageUrl;

    // Getters and setters for backward compatibility
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getters for the new API2 response structure
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<CategoryResult> getResult() {
        return result;
    }

    // Inner class for the CategoryResult from API2
    public static class CategoryResult {
        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("imageUrl")
        private String imageUrl;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}