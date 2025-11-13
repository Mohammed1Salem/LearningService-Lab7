package com.examplet.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 40, message = "Title must be between 3–40 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, max = 200, message = "Description must be between 5–200 characters")
    private String description;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Max(value = 200, message = "Duration must not exceed 200 hours")
    private int durationHours;

    private boolean published;
}
