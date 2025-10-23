package com.EduFluxAI.backend.dto;

public class CourseFilterRequest {
    private String searchTerm;
    private String tag;
    private String price; // "all", "paid", "free"

    public CourseFilterRequest() {}

    public CourseFilterRequest(String searchTerm, String tag, String price) {
        this.searchTerm = searchTerm;
        this.tag = tag;
        this.price = price;
    }

    // Getters and Setters
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
