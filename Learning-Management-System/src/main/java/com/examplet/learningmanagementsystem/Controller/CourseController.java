package com.examplet.learningmanagementsystem.Controller;

import com.examplet.learningmanagementsystem.Model.Course;
import com.examplet.learningmanagementsystem.Service.CourseService;
import com.examplet.learningmanagementsystem.ApiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        courseService.add(course);
        return ResponseEntity.ok(new ApiResponse("Course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Course course, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        if (courseService.update(id, course)) return ResponseEntity.ok(new ApiResponse("Course updated"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (courseService.delete(id)) return ResponseEntity.ok(new ApiResponse("Course deleted"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publish(@PathVariable String id) {
        if (courseService.publishCourse(id)) return ResponseEntity.ok(new ApiResponse("Course published"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @PutMapping("/unpublish/{id}")
    public ResponseEntity<?> unpublish(@PathVariable String id) {
        if (courseService.unpublishCourse(id)) return ResponseEntity.ok(new ApiResponse("Course unpublished"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @GetMapping("/published")
    public ResponseEntity<?> getPublished() {
        ArrayList<Course> list = courseService.getPublishedCourses();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/length/{id}")
    public ResponseEntity<?> classify(@PathVariable String id) {
        String level = courseService.classifyCourseLength(id);
        if (level == null) return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
        return ResponseEntity.ok(level);
    }
}
