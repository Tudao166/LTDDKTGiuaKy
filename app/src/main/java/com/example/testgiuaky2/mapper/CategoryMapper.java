package com.example.testgiuaky2.mapper;

import com.example.testgiuaky2.model.Category;
import com.example.testgiuaky2.model.CategoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    // Convert from API2's CategoryResponse.CategoryResult to Category
    public static Category toCategory(CategoryResponse.CategoryResult result) {
        if (result == null) {
            return null;
        }
        return new Category(
                result.getId(),
                result.getName(),
                result.getImageUrl(), // imageUrl in API2 corresponds to images in Category
                result.getDescription()
        );
    }

    // Convert a list of CategoryResponse.CategoryResult to a list of Category
    public static List<Category> toCategoryList(List<CategoryResponse.CategoryResult> results) {
        if (results == null || results.isEmpty()) {
            return new ArrayList<>(); // Return empty list if input is null or empty
        }

        return results.stream()
                .map(CategoryMapper::toCategory)
                .collect(Collectors.toList());
    }

    // This method remains for backward compatibility but won't be used with API2
    public static CategoryResponse toCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponse(
                category.getName(),
                category.getDescription(),
                category.getImages()
        );
    }

    // This method remains for backward compatibility but won't be used with API2
    public static Category toCategory(CategoryResponse categoryResponse, int id) {
        if (categoryResponse == null) {
            return null;
        }
        return new Category(
                String.valueOf(id),  // Convert int id to String to match new Category model
                categoryResponse.getName(),
                categoryResponse.getImageUrl(),
                categoryResponse.getDescription()
        );
    }
}