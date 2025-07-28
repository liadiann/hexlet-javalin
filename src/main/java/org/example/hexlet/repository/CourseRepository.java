package org.example.hexlet.repository;

import lombok.Getter;
import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    @Getter
    private static List<Course> entities = new ArrayList<>();

    public static void save(Course user) {
        user.setId(entities.size() + 1);
        entities.add(user);
    }

    public static Optional<Course> find(Integer id) {
        return entities.stream()
                .filter(e -> e.getId().equals(id))
                .findAny();
    }

    public static void delete(Integer id) {
        entities.remove((int)id);
    }

    public static List<Course> search(String term) {
        return entities.stream()
                .filter(e -> e.getName().startsWith(term))
                .toList();
    }
}
