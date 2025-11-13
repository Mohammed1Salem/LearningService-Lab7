package com.examplet.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Course ID cannot be empty")
    private String courseId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 40, message = "Title must be between 3–40 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, max = 200, message = "Description must be between 5–200 characters")
    private String description;

    @Min(value = 1, message = "Max score must be more than 0")
    @Max(value = 1000, message = "Max score cannot exceed 1000")
    private int maxScore;

    private boolean active;
}
