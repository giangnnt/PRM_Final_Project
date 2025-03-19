package com.example.prm392_final_project.model;

import com.google.gson.annotations.SerializedName;

public class PaginationResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("size")
    private int size;

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("totalItems")
    private int totalItems;

    public int getPage() { return page; }
    public int getSize() { return size; }
    public int getTotalPages() { return totalPages; }
    public int getTotalItems() { return totalItems; }
}
