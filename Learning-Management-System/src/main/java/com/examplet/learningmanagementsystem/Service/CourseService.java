package com.examplet.learningmanagementsystem.Service;

import com.examplet.learningmanagementsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    private final ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getAll() {
        return courses;
    }

    public void add(Course course) {
        courses.add(course);
    }

    public boolean update(String id, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        return courses.removeIf(c -> c.getId().equals(id));
    }

    public boolean publishCourse(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c.setPublished(true);
                return true;
            }
        }
        return false;
    }

    public boolean unpublishCourse(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c.setPublished(false);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getPublishedCourses() {
        ArrayList<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.isPublished()) result.add(c);
        }
        return result;
    }

    public String classifyCourseLength(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                if (c.getDurationHours() <= 10) return "short";
                if (c.getDurationHours() <= 30) return "medium";
                return "long";
            }
        }
        return null;
    }
}
