package com.examplet.learningmanagementsystem.Service;

import com.examplet.learningmanagementsystem.Model.Assignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {

    private final ArrayList<Assignment> assignments = new ArrayList<>();

    public ArrayList<Assignment> getAll() {
        return assignments;
    }

    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    public boolean update(String id, Assignment assignment) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(id)) {
                assignments.set(i, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        return assignments.removeIf(a -> a.getId().equals(id));
    }

    public boolean activate(String id) {
        for (Assignment a : assignments) {
            if (a.getId().equals(id)) {
                a.setActive(true);
                return true;
            }
        }
        return false;
    }

    public boolean deactivate(String id) {
        for (Assignment a : assignments) {
            if (a.getId().equals(id)) {
                a.setActive(false);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Assignment> getByCourse(String courseId) {
        ArrayList<Assignment> result = new ArrayList<>();
        for (Assignment a : assignments) {
            if (a.getCourseId().equals(courseId)) result.add(a);
        }
        return result;
    }

    public int countActiveAssignments() {
        int count = 0;
        for (Assignment a : assignments) {
            if (a.isActive()) count++;
        }
        return count;
    }
}
