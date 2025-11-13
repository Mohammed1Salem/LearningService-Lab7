package com.examplet.learningmanagementsystem.Controller;

import com.examplet.learningmanagementsystem.Model.Assignment;
import com.examplet.learningmanagementsystem.Service.AssignmentService;
import com.examplet.learningmanagementsystem.ApiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(assignmentService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Assignment assignment, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        assignmentService.add(assignment);
        return ResponseEntity.ok(new ApiResponse("Assignment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Assignment assignment, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        if (assignmentService.update(id, assignment)) return ResponseEntity.ok(new ApiResponse("Assignment updated"));
        return ResponseEntity.badRequest().body(new ApiResponse("Assignment not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (assignmentService.delete(id)) return ResponseEntity.ok(new ApiResponse("Assignment deleted"));
        return ResponseEntity.badRequest().body(new ApiResponse("Assignment not found"));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activate(@PathVariable String id) {
        if (assignmentService.activate(id)) return ResponseEntity.ok(new ApiResponse("Assignment activated"));
        return ResponseEntity.badRequest().body(new ApiResponse("Assignment not found"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable String id) {
        if (assignmentService.deactivate(id)) return ResponseEntity.ok(new ApiResponse("Assignment deactivated"));
        return ResponseEntity.badRequest().body(new ApiResponse("Assignment not found"));
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable String courseId) {
        return ResponseEntity.ok(assignmentService.getByCourse(courseId));
    }

    @GetMapping("/active-count")
    public ResponseEntity<?> countActive() {
        return ResponseEntity.ok(assignmentService.countActiveAssignments());
    }
}
